package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;

public class DistanceEuclidienne implements Distance {

	@Override
	public double distance(IPoint p1, IPoint p2, List<Column> columns) {
		double somme = 0;
		for(Column column : columns) {
			somme += Math.pow(1.0*p1.getNormalizedValue(column)-p2.getNormalizedValue(column), 2);
		}
		return Math.sqrt(somme);
	}

}
