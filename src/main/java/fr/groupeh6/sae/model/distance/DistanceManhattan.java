package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;

public class DistanceManhattan implements Distance {

	@Override
	public double distance(IPoint p1, IPoint p2, List<Column> columns) {
		double somme = 0;
		for(Column column : columns) {
			somme += Math.abs(p1.getNormalizedValue(column)-p2.getNormalizedValue(column));
		}
		return somme;
	}

}
