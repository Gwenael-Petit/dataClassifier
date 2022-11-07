package fr.groupeh6.sae;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.utils.Observer;
import fr.groupeh6.sae.utils.Subject;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class View extends Stage implements Observer{
	
	final NumberAxis x = new NumberAxis(0,1,0.1);
	final NumberAxis y = new NumberAxis(0,1,0.1);
	ScatterChart<Number,Number> sc = new ScatterChart<>(x,y);
	ComboBox<Column> xColumn;
	ComboBox<Column> yColumn;
	Model model;
	
	public View(Model model) {
		this.model = model;
		model.attach(this);
		xColumn = new ComboBox<Column>();
		yColumn = new ComboBox<Column>();
	}
	

	@Override
	public void update(Subject subj) {
		xColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		yColumn.getItems().addAll(model.getDataset().getNormalizableColumns());
		
	}

	@Override
	public void update(Subject subj, Object data) {
		// TODO Auto-generated method stub
		
	}
	
	
}
