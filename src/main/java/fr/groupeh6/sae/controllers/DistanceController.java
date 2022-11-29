package fr.groupeh6.sae.controllers;

import fr.groupeh6.sae.model.DistanceModel;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceController {
	
	private DistanceModel model;
	
	public DistanceController(DistanceModel model) {
		this.model = model;
	}
	
	public void addSelected(AbstractColumn column) {
		model.getSelected().add(column);
	}
	
	public void removeSelected(AbstractColumn column) {
		model.getSelected().remove(column);
	}
	
	public void setCalcul(String calcul) {
		model.setCalcul(calcul);
	}
	
	public void submit() {
		model.submit();
	}

}
