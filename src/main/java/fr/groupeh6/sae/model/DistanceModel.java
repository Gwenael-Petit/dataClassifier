package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.distance.Distance;

public class DistanceModel {
	
	protected MainModel mainModel;
	
	protected String calcul;
	protected List<AbstractColumn> columns;
	protected List<AbstractColumn> selected;
	
	public DistanceModel(MainModel model) {
		this.mainModel = model;
		columns = new ArrayList<AbstractColumn>(model.getTrainDataset().getNormalizableColumns());
		selected = new ArrayList<AbstractColumn>(model.getDistance().getColumnsDistance());
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
	
	public void setCalcul(String calcul) {
		this.calcul = calcul;
	}
	
	public String getCalcul() {
		return calcul;
	}
	
	public void submit() {
		Distance dist = Factory.getInstance().distanceFromName(calcul, selected);
		mainModel.setDistance(dist);
	}

}
