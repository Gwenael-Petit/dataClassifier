package fr.groupeh6.sae.views;

import fr.groupeh6.sae.controllers.NewPointController;
import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Observer;
import fr.groupeh6.sae.model.utils.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class NewPointView extends AbstractModalView {

	protected NewPointModel npm;
	protected NewPointController npc;
	
	VBox root;
	
	Button submit;
	
	public NewPointView(Window owner, NewPointModel npm, NewPointController npc) {
		super(owner);
		this.npm = npm;
		this.npc = npc;
		
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		int i = 0;
		for(Column column : npm.getType().getColumns()) {
			root.getChildren().add(createField(column.getName(), i++));
		}
		submit = new Button("Add a Point");
		root.getChildren().add(submit);
		
		Scene scene = new Scene(root, 300, 300);
		this.setScene(scene);
		this.setTitle("Add Point");
		this.show();
	}

	public HBox createField(String name, int i) {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		hBox.getChildren().add(new Label(name));
		TextField tf = new TextField();
		tf.textProperty().addListener(new TextFieldListener(tf, i));
		hBox.getChildren().add(tf);
		return hBox;
	}
	
	class TextFieldListener implements ChangeListener<String> {

		TextField tf;
		int i;
		
		public TextFieldListener(TextField tf, int i) {
			this.tf = tf;
			this.i = i;
		}
		
		@Override
		public void changed(ObservableValue<? extends String> obs, String oldV, String newV) {
			if(newV.contains(""+NewPointModel.delimiter)) {
				tf.setText(oldV);
			}
		}
		
	}
	
}
