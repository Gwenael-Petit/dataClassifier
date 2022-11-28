package fr.groupeh6.sae.views;

import java.io.IOException;

import fr.groupeh6.sae.controllers.NewPointController;
import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.TypeNotRegisteredException;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.views.listeners.BooleanListener;
import fr.groupeh6.sae.views.listeners.EnumListener;
import fr.groupeh6.sae.views.listeners.NumberListener;
import fr.groupeh6.sae.views.listeners.StringListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class NewPointView extends AbstractModalView {

	protected NewPointModel npm;
	protected NewPointController npc;
	
	protected VBox root;
	
	protected Button submit;
	
	public NewPointView(Window owner, NewPointModel npm, NewPointController npc) {
		super(owner);
		this.npm = npm;
		this.npc = npc;
		
		root = new VBox();
		root.setMinWidth(400);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		int i = 0;
		for(AbstractColumn column : npm.getType().getColumns()) {
			root.getChildren().add(createField(column, i));
			i++;
		}
		submit = new Button("Add a Point");
		submit.setOnAction(e -> load());
		root.getChildren().add(submit);
		
		Scene scene = new Scene(root);
		this.setScene(scene);
		this.setTitle("Add Point");
		this.show();
	}

	public HBox createField(AbstractColumn column, int i) {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		hBox.getChildren().add(new Label(column.getName()));
		hBox.getChildren().add(createNode(column, i));
		return hBox;
	}
	
	public Node createNode(AbstractColumn column, int i) {
		Node node = null;
		switch(column.toString().split(":")[0]) {
		case "Boolean" :
			node = new CheckBox();
			((CheckBox)node).selectedProperty().addListener(new BooleanListener(npc, i));
			break;
		case "Number" :
			node = new TextField();
			((TextField)node).textProperty().addListener(new NumberListener(npc, i, (TextField) node));
			break;
		case "Enum" :
			node = new ComboBox<String>();
			((ComboBox<String>)node).getItems().addAll(column.getDistinctValues());
			((ComboBox<String>)node).getSelectionModel().selectedItemProperty().addListener(new EnumListener(npc, i));
			break;
		default :
			node = new TextField();
			((TextField)node).textProperty().addListener(new StringListener(npc, i, (TextField) node));
			break;
		}
		return node;
	}
	
	public void load() {
		try {
			npc.loadPoint();
			this.close();
		} catch (IOException | TypeNotRegisteredException e) {
			new ErrorView(this, "Veuillez vérifier les types des données.");
		}
	}
	
}
