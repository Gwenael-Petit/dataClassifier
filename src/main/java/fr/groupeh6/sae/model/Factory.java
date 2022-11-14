package fr.groupeh6.sae.model;

import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.datas.iris.IrisDataSet;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

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
