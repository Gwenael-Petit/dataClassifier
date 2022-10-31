package fr.groupeh6.sae.distance;

import java.util.List;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.points.IPoint;

public interface Distance {
	
	public double distance(IPoint p1, IPoint p2, List<Column> columns);

}
