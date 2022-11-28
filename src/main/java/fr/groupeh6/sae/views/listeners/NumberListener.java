package fr.groupeh6.sae.views.listeners;

import fr.groupeh6.sae.controllers.NewPointController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NumberListener extends AbstractListener implements ChangeListener<String>{
	
	protected TextField tf;

	public NumberListener(NewPointController npc, int i, TextField tf) {
		super(npc, i);
		this.tf = tf;
	}

	@Override
	public void changed(ObservableValue<? extends String> obs, String oldV, String newV) {
		if(!newV.isEmpty()) {
			try {
				double value = Double.valueOf(newV);
				setValue(i, ""+value);
			} catch(Exception e) {
				tf.setText(oldV);
			}
		}
	}

}
