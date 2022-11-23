package fr.groupeh6.sae.model;

import java.io.IOException;

public class NewPointModel {
	
	public static final char DELIMITER = ';';
	
	protected MainModel mainModel;
	protected AbstractDataset type;
	protected String[] datas;
	
	public NewPointModel(MainModel mainModel) {
		this.mainModel = mainModel;
		this.type = mainModel.getTrainDataset();
		this.datas = new String[mainModel.getTrainDataset().columns.size()];
	}
	
	public void setData(int index, String value) {
		this.datas[index] = value;
	}
	
	public void loadPoint() throws IOException, TypeNotRegisteredException {
		String line = datas[0];
		for(int i=1; i<datas.length; i++) {
			line += DELIMITER + datas[i];
		}
		mainModel.loadFromString(line, DELIMITER);
	}
	
	public AbstractDataset getType() {
		return type;
	}
	
}
