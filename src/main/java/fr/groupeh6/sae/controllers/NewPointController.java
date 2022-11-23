package fr.groupeh6.sae.controllers;

import java.io.IOException;

import fr.groupeh6.sae.model.NewPointModel;
import fr.groupeh6.sae.model.TypeNotRegisteredException;

public class NewPointController {
	
	private NewPointModel model;
	
	public NewPointController(NewPointModel model) {
		this.model = model;
	}
	
	public void setPoint(int i, String value) {
		model.setData(i, value);
	}
	
	public void loadPoint() throws IOException, TypeNotRegisteredException {
		model.loadPoint();
	}
}
