package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceModel {
	
	protected MainModel mainModel;
	
	protected List<AbstractColumn> columns;
	protected List<AbstractColumn> selected;
	
	public DistanceModel(MainModel model) {
		this.mainModel = model;
		columns = model.getTrainDataset().getNormalizableColumns();
		//selected = model.getColumnsDistance();
	}
	
	public List<AbstractColumn> getColumns() {
		return columns;
	}
	
	public List<AbstractColumn> getSelected() {
		return selected;
	}

}
