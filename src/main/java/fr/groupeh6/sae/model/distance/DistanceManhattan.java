package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceManhattan implements Distance {
	
	protected List<AbstractColumn> columns;
	
	public DistanceManhattan(List<AbstractColumn> columns) {
		this.columns = columns;
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		double somme = 0;
		for(AbstractColumn column : columns) {
			somme += Math.abs(p1.getNormalizedValue(column)-p2.getNormalizedValue(column));
		}
		return somme;
	}

}
