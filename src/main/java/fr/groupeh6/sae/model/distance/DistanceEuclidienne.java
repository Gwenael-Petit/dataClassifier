package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class DistanceEuclidienne implements Distance {
	
	protected List<AbstractColumn> columns;
	
	public DistanceEuclidienne(List<AbstractColumn> columns) {
		this.columns = columns;
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		double somme = 0;
		for(AbstractColumn column : columns) {
			somme += Math.pow(1.0*p1.getNormalizedValue(column)-p2.getNormalizedValue(column), 2);
		}
		return Math.sqrt(somme);
	}

	@Override
	public List<AbstractColumn> getColumnsDistance() {
		return columns;
	}

}
