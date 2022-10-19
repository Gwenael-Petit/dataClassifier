package fr.groupeh6.sae.dataset;

import java.util.Iterator;
import java.util.List;

import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.points.IPoint;

public abstract class DataSet implements IDataset {
	
	protected List<IPoint> points;
	
	public void setLines(List<IPoint> lines) {
		this.points = lines;	
	}
	
	public int getNbLines() {
		return points.size();
	}
	
	public void addLine(IPoint element) {
		this.points.add(element);
	}
	
	public void addAllLine(List<IPoint> elements) {
		this.points.addAll(elements);
	}
	
	public Iterator<IPoint> iterator() {
		return points.iterator();
	}

}
