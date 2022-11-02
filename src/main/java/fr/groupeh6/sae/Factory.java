package fr.groupeh6.sae;

import fr.groupeh6.sae.classifier.Classifier;
import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;

public class Factory {
	
	private static Factory instance;
	
	private Factory() {}
	
	public Dataset getDataset(String[] columnsName) {
		return null;
	}
	
	public Column getColumn(String columnName) {
		return null;
	}
	
	public Classifier getClassifier(String classifier) {
		return null;
	}
	
	public static Factory getInstance() {
		if(instance == null) instance = new Factory();
		return instance;
	}

}
