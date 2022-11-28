package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceManhattan implements Distance {
	
	public final static String NAME = "Manhattan";
	
	protected List<AbstractColumn> columns;
	
	public DistanceManhattan(List<AbstractColumn> columns) {
		this.columns = columns;
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		double somme = 0;
		for(AbstractColumn column : columns) {
			double val1 = p1.getNormalizedValue(column);
			double val2 = p2.getNormalizedValue(column);
			if(column.isEnumerative()) {
				double dist = val1 == val2 ? 0 : 1;
				somme += dist;
			} else {
				somme += Math.abs(val1-val2);
			}
		}
		return somme;
	}

	@Override
	public List<AbstractColumn> getColumnsDistance() {
		return columns;
	}

	@Override
	public String name() {
		return NAME;
	}

}
