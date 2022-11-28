package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.controllers.NewPointController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class BooleanListener extends AbstractListener implements ChangeListener<Boolean> {

	public BooleanListener(NewPointController npc, int i) {
		super(npc, i);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> obs, Boolean oldV, Boolean newV) {
		setValue(i, ""+newV);
	}

}
