package fr.groupeh6.sae;

import java.util.List;

import fr.groupeh6.sae.classifier.Classifier;
import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.dataset.IrisDataSet;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

public class Factory {
	
	private static Factory instance;
	
	private Factory() {}
	
	public Dataset getDataset(List<Column> columns) {
		if(IrisDataSet.COLUMNS.equals(columns)) return new IrisDataSet();
		return null;
	}
	
	public Column getColumn(String columnName) {
		for(Column column : IrisDataSet.COLUMNS) {
			if(column.getName().equals(columnName)) return column;
		}
		return null;
	}
	
	public Classifier getClassifier(String classifier) {
		return null;
	}
	
	public IPoint getType(List<Column> columns) {
		if(IrisDataSet.COLUMNS.equals(columns)) return new IrisPoint();
		return null;
	}
	
	public static Factory getInstance() {
		if(instance == null) instance = new Factory();
		return instance;
	}

}
