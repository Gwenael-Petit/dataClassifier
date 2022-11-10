package fr.groupeh6.sae;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.utils.Observer;
import fr.groupeh6.sae.utils.Subject;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Stage implements Observer{
	
	final NumberAxis x = new NumberAxis(0,1,0.1);
	final NumberAxis y = new NumberAxis(0,1,0.1);
	ScatterChart<Number,Number> sc = new ScatterChart<>(x,y);
	ComboBox<Column> xColumn;
	ComboBox<Column> yColumn;
	Model model;
	Controller controller;
	
	public View(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		model.attach(this);
		xColumn = new ComboBox<Column>();
		yColumn = new ComboBox<Column>();
		
		xColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		yColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		
		xColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setXColumn(newV));
		yColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setYColumn(newV));
		
		HBox hb = new HBox();
		hb.getChildren().addAll(xColumn, yColumn);
		VBox vb = new VBox();
		vb.getChildren().addAll(sc,hb);
		Scene scene = new Scene(vb);
		this.setScene(scene);
		this.show();
	}
	

	@Override
	public void update(Subject subj) {
		sc.getData().clear();
		for(Dataset category : model.allCategories()) {
			XYChart.Series<Number, Number> series = new XYChart.Series<>();
			series.setName(category.getName());
			for(IPoint point : category) {
				
				series.getData().add(new XYChart.Data<Number, Number>(model.getxColumn().getNormalizedValue(point), model.getyColumn().getNormalizedValue(point)));
			}
			sc.getData().add(series);
		}
	}

	@Override
	public void update(Subject subj, Object data) {
		update(subj);
		
	}
	
	
}
