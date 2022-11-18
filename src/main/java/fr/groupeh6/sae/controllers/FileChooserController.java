package fr.groupeh6.sae.controllers;

import java.io.File;

import fr.groupeh6.sae.model.FileChooserModel;

public class FileChooserController {
	
	private FileChooserModel model;
	
	public FileChooserController(FileChooserModel model) {
		this.model = model;
	}
	
	public void setFile(File file) {
		model.setFile(file);
	}
	
	public void setDelimiter(String s) {
		if(!s.isBlank()) model.setDelimiter(s.charAt(0));
	}

}
