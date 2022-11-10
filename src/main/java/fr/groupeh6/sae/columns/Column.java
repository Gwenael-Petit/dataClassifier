package fr.groupeh6.sae.columns;

import java.util.Objects;

import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.points.IPoint;

public abstract class Column implements IValueNormalizer {
	
	private String name;
	protected Dataset dataset;
	
	public Column(String name) {
		this.name = name;
	}
	
	public double getNormalizedValue(IPoint point) {
		return normalize(point.getValue(this));
	}
	
	public Object getDenormalizedValue(double value) {
		return denormalize(value);
	}
	
	public String getName() {
		return name;
	}
	
	public abstract boolean isNormalizable();
	
	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
	
	public boolean isLinkedToADataset() {
		return dataset != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Column other = (Column) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public abstract void updateNewPoint(IPoint point);

}
