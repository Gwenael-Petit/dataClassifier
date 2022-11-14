package fr.groupeh6.sae.views;

import fr.groupeh6.sae.model.IPoint;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PointView extends Stage {
	
	private Label label;
	
	public PointView(IPoint point) {
		label = new Label(point.toString());
		Scene scene = new Scene(label);
		this.setScene(scene);
		this.show();
	}

}
