package fr.groupeh6.sae.views;

import fr.groupeh6.sae.model.AbstractDataset;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class DistanceView extends AbstractModalView {
	
	protected AbstractDataset train;
	
	protected HBox hbox;
	
	public DistanceView(Window owner, AbstractDataset train) {
		super(owner);
		this.train = train;
		
		initHbox();
		
		Scene scene = new Scene(hbox);
		this.setScene(scene);
		this.setTitle("Selection de la distance");
		this.show();
		
		
	}
	
	public void initHbox() {
		hbox = new HBox();
		
	}
	
	public VBox createColumChoose() {
		VBox vbox = new VBox();
		return vbox;
	}

}
