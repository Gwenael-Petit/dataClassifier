package fr.groupeh6.sae.views;

import fr.groupeh6.sae.controllers.DistanceController;
import fr.groupeh6.sae.model.DistanceModel;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class DistanceView extends AbstractModalView {
	
	protected DistanceModel model;
	protected DistanceController controller;
	
	protected VBox vbox;
	protected ComboBox<String> type = new ComboBox<String>();
	protected HBox hbox;
	protected Button submit;
	
	public DistanceView(Window owner, DistanceModel model) {
		super(owner);
		this.model = model;
		this.controller = new DistanceController(model);
		
		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		
		type.getItems().add("Euclidienne");
		type.getItems().add("Manhattan");
		type.getSelectionModel().select(model.getCalcul());
		type.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> controller.setCalcul(newV));
		
		initHbox();
		submit = new Button("Valider");
		submit.setOnAction(e -> {
			controller.submit();
			this.close();
		});
		
		vbox.getChildren().addAll(type, hbox, submit);
		
		Scene scene = new Scene(vbox);
		this.setScene(scene);
		this.setTitle("Selection de la distance");
		this.show();
	}
	
	public void initHbox() {
		hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		for(AbstractColumn column : model.getColumns()) {
			hbox.getChildren().add(createColumChoose(column));
		}
	}
	
	public VBox createColumChoose(AbstractColumn column) {
		VBox col = new VBox();
		col.setAlignment(Pos.CENTER);
		col.setSpacing(10);
		Label colLabel = new Label(column.getName());
		CheckBox cb = new CheckBox();
		if(model.isSelected(column)) cb.setSelected(true);
		cb.selectedProperty().addListener(new CheckBoxListener(column));
		col.getChildren().addAll(colLabel, cb);
		return col;
	}
	
	class CheckBoxListener implements ChangeListener<Boolean> {
		
		private AbstractColumn column;
		
		public CheckBoxListener(AbstractColumn column) {
			this.column = column;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> obs, Boolean oldV, Boolean newV) {
			if(newV) controller.addSelected(column);
			else controller.removeSelected(column);
		}
		
	}

}
