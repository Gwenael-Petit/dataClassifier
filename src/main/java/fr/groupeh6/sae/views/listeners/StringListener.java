package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.controllers.NewPointController;
import fr.groupeh6.sae.model.NewPointModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class StringListener extends AbstractListener implements ChangeListener<String> {
	
	protected TextField tf;

	public StringListener(NewPointController npc, int i, TextField tf) {
		super(npc, i);
		this.tf = tf;
	}

	@Override
	public void changed(ObservableValue<? extends String> obs, String oldV, String newV) {
		if(newV.contains(""+NewPointModel.DELIMITER)) {
			tf.setText(oldV);
		} else {
			setValue(i, newV);
		}
	}

}
