package fr.groupeh6.sae.controllers;

import java.io.IOException;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.MainModel;
import fr.groupeh6.sae.model.NotSameTypeException;
import fr.groupeh6.sae.model.TypeNotRegisteredException;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.distance.Distance;


public class MainController {
	MainModel model;
	
	public MainController(MainModel m) {
		this.model = m;
	}
	
	public void setXColumn(Column column) {
		this.model.setxColumn(column);
	}

	public void setYColumn(Column column) {
		this.model.setyColumn(column);
	}
	
	public void loadCSV(String filePath, char delimiter, boolean toTrain) throws NotSameTypeException, IOException, TypeNotRegisteredException {
		this.model.loadFromFile(filePath, delimiter, toTrain);
	}
	
	public void setClassClassifier(Column column) {
		this.model.setClassClassifier(column);
	}
	
	public void setClassifier(int k, Distance distance) {
		this.model.setClassifier(Factory.getInstance().knnClassifier(k, distance));
	}
	
}
