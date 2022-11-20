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
	
	public void loadCSV(String filePath, char delimiter, boolean toTrain) {
		try {
			this.model.loadFromFile(filePath, delimiter, toTrain);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setClassClassifier(Column column) {
		this.model.setClassClassifier(column);
	}
	
}
