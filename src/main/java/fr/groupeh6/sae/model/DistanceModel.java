package fr.groupeh6.sae.model;

import java.util.List;

import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceModel {
	
	protected MainModel mainModel;
	
	protected String calcul;
	protected List<AbstractColumn> columns;
	protected List<AbstractColumn> selected;
	
	public DistanceModel(MainModel model) {
		this.mainModel = model;
		columns = model.getTrainDataset().getNormalizableColumns();
		selected = model.getDistance().getColumnsDistance();
		calcul = model.getDistance().name();
	}
	
	public List<AbstractColumn> getColumns() {
		return columns;
	}
	
	public List<AbstractColumn> getSelected() {
		return selected;
	}
	
	public boolean isSelected(AbstractColumn column) {
		return selected.contains(column);
	}
	
	public String getCalcul() {
		return calcul;
	}

}
