package fr.groupeh6.sae.views;

import fr.groupeh6.sae.controllers.NewPointController;
import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.utils.Observer;
import fr.groupeh6.sae.model.utils.Subject;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class NewPointView extends AbstractModalView implements Observer{

	protected NewPointModel npm;
	protected NewPointController npc;
	
	
	public NewPointView(Window owner, NewPointModel npm, NewPointController npc) {
		super(owner);
		this.npm = npm;
		this.npc = npc;
		npm.attach(this);
		
		VBox vBox = new VBox();
		Label title = new Label("Add a Point");
		
		Scene scene = new Scene(vBox, 300, 300);
		this.setScene(scene);
		this.setTitle("Add Point");
		this.show();
	}

	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Subject subj, Object data) {
		// TODO Auto-generated method stub
		
	}

	
	
}
