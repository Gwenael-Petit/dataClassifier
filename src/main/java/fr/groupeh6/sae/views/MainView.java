package fr.groupeh6.sae.views;

import java.util.List;

import fr.groupeh6.sae.controllers.Controller;
import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.Model;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Observer;
import fr.groupeh6.sae.model.utils.Subject;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainView extends Stage implements Observer {
	
	Model model;
	Controller controller;
	
	final NumberAxis x = new NumberAxis(0,1,0.1);
	final NumberAxis y = new NumberAxis(0,1,0.1);
	ScatterChart<Number,Number> sc = new ScatterChart<>(x,y);
	ComboBox<Column> xColumn = new ComboBox<Column>();
	ComboBox<Column> yColumn = new ComboBox<Column>();
	
	Popup pointPopup;
	
	public MainView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		model.attach(this);
		
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
	
	public boolean isOnPoint(Data<Number, Number> data, double clicX, double clicY) {
		Scene dataScene = data.getNode().getScene();
		System.out.println(dataScene.getX() + " " + dataScene.getWidth() + " " + dataScene.getY() + " " + dataScene.getHeight());
		boolean inX = clicX >= dataScene.getX() && clicX <= dataScene.getX()+dataScene.getWidth();
		boolean inY = clicY >= dataScene.getY() && clicY <= dataScene.getY()+dataScene.getHeight();
		return inX && inY;
	}
	
	@Override
	public void update(Subject subj) {
		updateScatterChart();
	}

	@Override
	public void update(Subject subj, Object data) {
		List<Column> columns = model.getDataset().getNormalizableColumns();
		xColumn.getItems().addAll(columns);
		yColumn.getItems().addAll(columns);
	}
	
	public void updateScatterChart() {
		if(model.haveDatasetLoaded()) {
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
					new PointView((IPoint) point.getExtraValue());
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
