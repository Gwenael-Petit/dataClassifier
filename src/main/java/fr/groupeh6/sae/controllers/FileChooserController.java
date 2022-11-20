package fr.groupeh6.sae.controllers;

import java.io.File;
import java.io.IOException;

import fr.groupeh6.sae.model.CSVLoader;
import fr.groupeh6.sae.model.FileChooserModel;
import fr.groupeh6.sae.model.NotSameTypeException;
import fr.groupeh6.sae.model.TypeNotRegisteredException;

public class FileChooserController {
	
	private FileChooserModel model;
	
	public FileChooserController(FileChooserModel model) {
		this.model = model;
	}
	
	public void setFile(File file) {
		if(CSVLoader.isValid(file)) model.setFile(file);
	}
	
	public void setDelimiter(String s) {
		if(!s.isBlank()) model.setDelimiter(s.charAt(0));
	}
	
	public void load() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		model.loadCSV();
	}

}
