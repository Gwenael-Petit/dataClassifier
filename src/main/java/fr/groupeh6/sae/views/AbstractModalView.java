package fr.groupeh6.sae.views;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class AbstractModalView extends Stage {
	
	public AbstractModalView(Window owner) {
		this.initModality(Modality.APPLICATION_MODAL);
		this.initOwner(owner);
	}

}
