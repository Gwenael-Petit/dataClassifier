package fr.groupeh6.sae.views;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import fr.groupeh6.sae.controllers.FileChooserController;
import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.controllers.NewPointController;
import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.DistanceModel;
import fr.groupeh6.sae.model.FileChooserModel;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.MainModel;
import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.utils.AbstractSubject;
import fr.groupeh6.sae.model.utils.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView extends Stage implements Observer {
	
	MainModel model;
	MainController controller;
	
	@FXML
	NumberAxis xAxis, yAxis;
	@FXML
	ScatterChart<Number,Number> sc;
	@FXML
	ComboBox<AbstractColumn> xColumn, yColumn, columnClass;
	@FXML
	Button bLoadTrain, bLoadCSV, bSetDistance, bModify, bCategorisation, bNewPoint;
	@FXML
	TextField tfK;
	@FXML
	CheckBox defaultDistance;
	@FXML
	Label modelType, robustesseLabel;
	
	public MainView() {}
	
	public MainView(MainModel model, MainController controller) throws IOException {
		this.model = model;
		this.controller = controller;
		model.attach(this);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("mainview.fxml"));
		loader.setController(this);
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		this.setTitle("Sae");
		this.setScene(scene);
		this.show();
		
		init();
	}
	
	public void init() {
		xColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setXColumn(newV));
		yColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setYColumn(newV));
		columnClass.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setClassClassifier(newV));
		
		bNewPoint.setOnAction(e -> {
			NewPointModel npm = new NewPointModel(model);
			NewPointController npc = new NewPointController(npm);
			new NewPointView(this, npm, npc);
		});
		
		bLoadTrain.setOnAction(e -> {
				FileChooserModel fileChooserModel = new FileChooserModel(controller, true);
				FileChooserController fileChooserController = new FileChooserController(fileChooserModel);
				new FileChooserView(this, fileChooserModel, fileChooserController);
		});
		
		bLoadCSV.setOnAction(e -> {
			FileChooserModel fileChooserModel = new FileChooserModel(controller, false);
			FileChooserController fileChooserController = new FileChooserController(fileChooserModel);
			new FileChooserView(this, fileChooserModel, fileChooserController);
		});
		
		bCategorisation.setOnAction(e -> {
			setEnable(List.of(bModify), true);
			setEnable(List.of(columnClass, tfK, defaultDistance, bCategorisation, bSetDistance), false);
			
			if(!tfK.getText().isBlank()) {
				int k = Integer.valueOf(tfK.getText());
				if(defaultDistance.isSelected()) {
					controller.setClassifier(k, model.getTrainDataset());
				} else {
					controller.setClassClassifier(null);
					// recuperer la distance manuel
				}
			}
		});
		
		bModify.setOnAction(e -> {
			setEnable(List.of(columnClass, tfK, defaultDistance, bCategorisation), true);
			setEnable(List.of(bModify), false);
			bSetDistance.setDisable(defaultDistance.isSelected());
		});
		
		tfK.textProperty().addListener((obs, oldV, newV) -> {
			if(!newV.isEmpty()) {
				try {
					int k = Integer.valueOf(newV);
					if(k <= 0) tfK.setText(oldV);
				} catch(Exception e) {
					tfK.setText(oldV);
				}
			}
		});
		
		defaultDistance.selectedProperty().addListener((obs, oldV, newV) -> bSetDistance.setDisable(newV));
		
		bSetDistance.setOnAction(e -> new DistanceView(this, new DistanceModel(model)));
	}
	
	@Override
	public void update(AbstractSubject subj) {
		updateRobustesse();
		updateScatterChart();
	}

	@Override
	public void update(AbstractSubject subj, Object data) {
		modelType.setText(modelType.getText() + model.getTrainDataset().getName());
		List<AbstractColumn> columns = model.getTrainDataset().getNormalizableColumns();
		xColumn.getItems().addAll(columns);
		yColumn.getItems().addAll(columns);
		columnClass.getItems().addAll(columns);

		setEnable(List.of(xColumn, yColumn, columnClass, bLoadCSV, bNewPoint, bCategorisation, tfK, defaultDistance), true);
	}
	
	public void updateRobustesse() {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		double robForText = model.getRobustesse()*100.0;
		robustesseLabel.setText("Robustesse : " + df.format(robForText) + " %");
	}
	
	public void updateScatterChart() {
		if(model.haveTrainDatasLoaded()) {
			sc.getData().clear();
			if(model.getxColumn() != null && model.getyColumn() != null) {
				for(AbstractDataset category : model.allCategories()) {
					XYChart.Series<Number, Number> series = new XYChart.Series<>();
					series.setName(category.getName());
					sc.getData().add(series);
					for(IPoint point : category) {
						XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(model.getxColumn().getNormalizedValue(point), model.getyColumn().getNormalizedValue(point));
						series.getData().add(data);
						Tooltip toolTip = new Tooltip(point.toString());
						toolTip.setShowDelay(Duration.millis(10));
						Tooltip.install(data.getNode(), toolTip);
						data.getNode().setOnMouseClicked(e -> new PointView(this, point));
					}
				}
			}
		}
	}
	
	public void setEnable(List<Node> nodes, boolean enable) {
		nodes.forEach(n -> n.setDisable(!enable));
	}
}