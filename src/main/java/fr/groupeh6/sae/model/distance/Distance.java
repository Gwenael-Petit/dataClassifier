package fr.groupeh6.sae.model.distance;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public interface Distance {
	
	public double distance(IPoint p1, IPoint p2);
	
	public List<AbstractColumn> getColumnsDistance();
	
	public String name();

}
