package fr.groupeh6.sae.controllers;

import fr.groupeh6.sae.model.Model;
import fr.groupeh6.sae.model.columns.Column;


public class Controller {
	Model model;
	
	public Controller(Model m) {
		this.model = m;
	}
	
	public void setXColumn(Column column) {
		this.model.setxColumn(column);
	}

	public void setYColumn(Column column) {
		this.model.setyColumn(column);
	}
}
