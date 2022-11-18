package fr.groupeh6.sae.model;

import java.io.File;

import fr.groupeh6.sae.model.utils.Subject;

public class FileChooserModel extends Subject {
	
	private File file;
	
	private Character delimiter;
	
	public FileChooserModel() {}
	
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
