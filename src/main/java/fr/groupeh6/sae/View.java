package fr.groupeh6.sae;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.utils.Observer;
import fr.groupeh6.sae.utils.Subject;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
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
	}
	

	@Override
	public void update(Subject subj) {
		xColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		yColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		
		xColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setXColumn(newV));
		yColumn.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setYColumn(newV));
		
		for(int i =0; i < model.allCategories().size(); i++) {
			XYChart.Series series = new XYChart.Series();
			for(int j = 0; j < model.getxColumn(); j++) {
				series.getData().addAll(model);
			}
		}
	}

	@Override
	public void update(Subject subj, Object data) {
		// TODO Auto-generated method stub
		
	}
	
	
}
