package fr.groupeh6.sae.dataset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.points.IPoint;

public abstract class Dataset implements Iterable<IPoint> {
	
	private String name;
	protected List<IPoint> points;
	protected List<Column> columns;
	
	public Dataset(String name, List<Column> columns) {
		this.name = name;
		this.columns = columns;
		this.points = new ArrayList<IPoint>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getNbLines() {
		return points.size();
	}
	
	public void setLines(List<IPoint> lines) {
		this.points = lines;	
	}
	
	public void addLine(IPoint element) {
		this.points.add(element);
	}
	
	public void addAllLine(List<IPoint> elements) {
		this.points.addAll(elements);
	}
	
	public List<Column> getNormalizableColumns() {
		return null;
	}
	
	public Iterator<IPoint> iterator() {
		return points.iterator();
	}
	
	public List<IPoint> getPoints() {
		return points;
	}

}
