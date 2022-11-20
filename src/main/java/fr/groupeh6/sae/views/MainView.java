package fr.groupeh6.sae.views;

import java.io.IOException;
import java.util.List;

import fr.groupeh6.sae.controllers.FileChooserController;
import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.FileChooserModel;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.Model;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Observer;
import fr.groupeh6.sae.model.utils.Subject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
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
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainView extends Stage implements Observer {
	
	Model model;
	MainController controller;
	
	@FXML
	NumberAxis xAxis, yAxis;
	@FXML
	ScatterChart<Number,Number> sc;
	@FXML
	ComboBox<Column> xColumn, yColumn, columnClass;
	@FXML
	Button bLoadTrain, bLoadCSV, bSetDistance, bModify, bCategorisation, bNewPoint;
	@FXML
	TextField tfK;
	@FXML
	CheckBox defaultDistance;
	@FXML
	Label modelType, robustesseLabel;
	
	
	Popup pointPopup;
	
	public MainView() {}
	
	public MainView(Model model, MainController controller) throws IOException {
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
			
		});
	}
	
	@Override
	public void update(Subject subj) {
		updateScatterChart();
	}

	@Override
	public void update(Subject subj, Object data) {
		modelType.setText(modelType.getText() + model.getTrainDataset().getName());
		List<Column> columns = model.getTrainDataset().getNormalizableColumns();
		xColumn.getItems().addAll(columns);
		yColumn.getItems().addAll(columns);

		xColumn.setDisable(false);
		yColumn.setDisable(false);
		columnClass.setDisable(false);
		bLoadCSV.setDisable(false);
		bNewPoint.setDisable(false);
		bCategorisation.setDisable(false);
		bModify.setDisable(false);
		bSetDistance.setDisable(false);
		bNewPoint.setDisable(false);
		tfK.setDisable(false);
		defaultDistance.setDisable(false);
	}
	
	public void updateScatterChart() {
		if(model.haveTrainDatasLoaded()) {
			sc.getData().clear();
			if(model.getxColumn() != null && model.getyColumn() != null) {
				for(Dataset category : model.allCategories()) {
					XYChart.Series<Number, Number> series = new XYChart.Series<>();
					series.setName(category.getName());
					for(IPoint point : category) {
						XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(model.getxColumn().getNormalizedValue(point), model.getyColumn().getNormalizedValue(point));
						data.setExtraValue(point);
						series.getData().add(data);
					}
					sc.getData().add(series);
				}
				setEventSCPoints();
			}
		}
	}
	
	public void setEventSCPoints() {
		for(XYChart.Series<Number, Number> serie : sc.getData()) {
			for(XYChart.Data<Number, Number> point : serie.getData()) {
				point.getNode().setOnMouseClicked(e -> {
					new PointView(this, (IPoint) point.getExtraValue());
				});
				point.getNode().setOnMouseEntered(e -> {
					pointPopup = new Popup();
					pointPopup.getContent().add(new Label(point.getExtraValue().toString()));
					Point2D anchor = point.getNode().localToScreen(e.getX(), e.getY());
					pointPopup.setX(anchor.getX());
					pointPopup.setY(anchor.getY());
					pointPopup.show(this);
				});
				point.getNode().setOnMouseExited(e -> {
					pointPopup.hide();
				});
			}
		}
	}
}
