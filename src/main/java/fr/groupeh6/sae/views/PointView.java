package fr.groupeh6.sae.views;

import fr.groupeh6.sae.model.IPoint;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class PointView extends AbstractModalView {
	
	private Label label;
	
	public PointView(Window owner, IPoint point) {
		super(owner);
		label = new Label(point.toString());
		Scene scene = new Scene(label);
		this.setScene(scene);
		this.show();
	}

}
