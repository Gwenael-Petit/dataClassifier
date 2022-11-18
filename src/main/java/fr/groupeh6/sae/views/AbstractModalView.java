package fr.groupeh6.sae.views;

import fr.groupeh6.sae.model.utils.Observer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class AbstractModalView extends Stage implements Observer {
	
	public AbstractModalView(Window owner) {
		this.initModality(Modality.APPLICATION_MODAL);
		this.initOwner(owner);
	}

}
