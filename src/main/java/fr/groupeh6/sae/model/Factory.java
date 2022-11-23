package fr.groupeh6.sae.model;

import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.classifier.RandomClassifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.datas.StoredDatas;
import fr.groupeh6.sae.model.distance.Distance;

public class Factory {
	
	private static Factory instance;
	
	private Factory() {}
	
	public AbstractDataset newDataset(List<AbstractColumn> columns) {
		for(StoredDatas data : StoredDatas.values()) {
			if(data.dataset().getColumns().equals(columns)) return data.dataset();
		}
		return null;
	}
	
	public AbstractColumn newColumn(String columnName) {
		for(StoredDatas data : StoredDatas.values()) {
			for(AbstractColumn column : data.dataset().getColumns()) {
				if(column.getName().equals(columnName)) return column;
			}
		}
		return null;
	}
	
	public Classifier knnClassifier(int k, Distance distance) {
		return new KnnClassifier(k, distance);
	}
	
	public Classifier randomClassifier() {
		return new RandomClassifier();
	}
	
	public static Factory getInstance() {
		if(instance == null) instance = new Factory();
		return instance;
	}

}
