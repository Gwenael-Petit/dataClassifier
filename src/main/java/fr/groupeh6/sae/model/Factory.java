package fr.groupeh6.sae.model;

import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.datas.StoredDatas;

public class Factory {
	
	private static Factory instance;
	
	private Factory() {}
	
	public Dataset getDataset(List<Column> columns) {
		for(StoredDatas data : StoredDatas.values()) {
			if(data.dataset().getColumns().equals(columns)) return data.dataset();
		}
		//if(IrisDataset.COLUMNS.equals(columns)) return new IrisDataset();
		return null;
	}
	
	public Column getColumn(String columnName) {
		for(StoredDatas data : StoredDatas.values()) {
			for(Column column : data.dataset().getColumns()) {
				if(column.getName().equals(columnName)) return column;
			}
		}
		/*for(Column column : IrisDataset.COLUMNS) {
			if(column.getName().equals(columnName)) return column;
		}*/
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
