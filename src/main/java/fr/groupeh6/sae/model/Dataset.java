package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.Updatable;

public abstract class Dataset implements Iterable<IPoint> {
	
	private String name;
	protected List<IPoint> points;
	protected List<Column> columns;
	//protected Classifier classifier;
	
	public Dataset(String name, List<Column> columns) {
		this.name = name;
		this.columns = columns;
		this.columns.forEach(column -> column.setDataset(this));
		this.points = new ArrayList<IPoint>();
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
	
	public void addLine(IPoint element) {
		this.points.add(element);
		for(Column column : this.columns) {
			if(column.isUpdatable()) ((Updatable)column).update(element.getValue(column));
		}
	}
	
	public void addAllLine(List<IPoint> elements) {
		elements.forEach(e -> addLine(e));
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
	/*public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}*/
	
	public double robustesse() {
		return 0.0;
	}
	
	public abstract IPoint getType();

}