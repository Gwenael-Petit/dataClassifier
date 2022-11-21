package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.Updatable;

public abstract class Dataset implements Iterable<IPoint> {
	
	private String name;
	protected List<IPoint> points = new ArrayList<IPoint>();
	protected List<Column> columns = new ArrayList<Column>();
	
	public Dataset(String name) {
		this.name = name;
	}
	
	public void setColumns(List<Column> columns) {
		this.columns = columns;
		this.columns.forEach(column -> column.setDataset(this));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNbLines() {
		return points.size();
	}
	
	public void setLines(List<IPoint> lines) {
		this.points.clear();
		lines.forEach(l -> addLine(l));
	}
	
	public void addAllLine(List<IPoint> elements) {
		elements.forEach(e -> addLine(e));
	}
	
	public void addLine(IPoint element) {
		this.points.add(element);
		for(Column column : this.columns) {
			if(column.isUpdatable()) ((Updatable)column).update(element.getValue(column));
		}
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public List<Column> getNormalizableColumns() {
		List<Column> res = new ArrayList<>();
		for(Column column : this.columns) {
			if(column.isNormalizable()) res.add(column);
		}
		return res;
	}
	
	public Iterator<IPoint> iterator() {
		return points.iterator();
	}
	
	public List<IPoint> getLines() {
		return points;
	}
	
	public double robustesse() {
		return 0.0;
	}
	
	public abstract IPoint getType();

}