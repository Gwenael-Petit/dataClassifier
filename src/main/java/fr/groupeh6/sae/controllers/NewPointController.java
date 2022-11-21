package fr.groupeh6.sae.controllers;

import fr.groupeh6.sae.model.FileChooserModel;

public class NewPointController {
	
	private NewPointModel model;
	
	public NewPointController(FileChooserModel model) {
		this.model = model;
	}
	
	public void SetPoint(String s) {
		if(!s.isBlank()) model.setPoint())
	}
	
	public void loadPoint(String string) {
		model.loadPoint();
	}
}
