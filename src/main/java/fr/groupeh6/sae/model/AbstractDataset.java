package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.Updatable;
import fr.groupeh6.sae.model.distance.Distance;

public abstract class AbstractDataset implements Iterable<IPoint>, Distance {
	
	private String name;
	protected List<IPoint> points = new ArrayList<IPoint>();
	protected List<AbstractColumn> columns = new ArrayList<AbstractColumn>();
	
	public AbstractDataset(String name) {
		this.name = name;
	}
	
	public void setColumns(List<AbstractColumn> columns) {
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
		for(AbstractColumn column : this.columns) {
			if(column.isUpdatable()) ((Updatable)column).update(element.getValue(column));
		}
	}
	
	public List<AbstractColumn> getColumns() {
		return columns;
	}
	
	public List<AbstractColumn> getNormalizableColumns() {
		List<AbstractColumn> res = new ArrayList<>();
		for(AbstractColumn column : this.columns) {
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
	
	public abstract IPoint getType();
	
	public double robustesse(Classifier classifier, AbstractColumn columnClass) {
		return new Robustesse(this, classifier, columnClass).robustesse();
	}

}