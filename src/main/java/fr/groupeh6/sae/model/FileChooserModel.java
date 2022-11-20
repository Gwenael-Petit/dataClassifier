package fr.groupeh6.sae.model;

import java.io.File;
import java.io.IOException;

import fr.groupeh6.sae.controllers.MainController;
import fr.groupeh6.sae.model.utils.Subject;

public class FileChooserModel extends Subject {
	
	private File file;
	
	private Character delimiter;
	
	private boolean toTrain;
	private MainController mainController;
	
	public FileChooserModel(MainController mainController, boolean toTrain) {
		this.mainController = mainController;
		this.toTrain = toTrain;
	}
	
	public void loadCSV() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		mainController.loadCSV(file.getAbsolutePath(), delimiter, toTrain);
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
		notifyObservers();
	}
	
	public void setDelimiter(Character delimiter) {
		this.delimiter = delimiter;
		notifyObservers();
	}
	
	public Character getDelimiter() {
		return delimiter;
	}

}
