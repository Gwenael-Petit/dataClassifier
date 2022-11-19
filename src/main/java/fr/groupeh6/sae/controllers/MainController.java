package fr.groupeh6.sae.controllers;

import fr.groupeh6.sae.model.Model;
import fr.groupeh6.sae.model.columns.Column;


public class MainController {
	Model model;
	
	public MainController(Model m) {
		this.model = m;
	}
	
	public void setXColumn(Column column) {
		this.model.setxColumn(column);
	}

	public void setYColumn(Column column) {
		this.model.setyColumn(column);
	}
	
	public void loadCSV(String filePath, char delimiter) {
		try {
			this.model.loadFromFile(filePath, delimiter);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
