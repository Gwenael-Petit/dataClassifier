package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.controllers.NewPointController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EnumListener extends AbstractListener implements ChangeListener<String>{

	public EnumListener(NewPointController npc, int i) {
		super(npc, i);
	}

	@Override
	public void changed(ObservableValue<? extends String> obs, String oldV, String newV) {
		setValue(i, newV);
	}

}
