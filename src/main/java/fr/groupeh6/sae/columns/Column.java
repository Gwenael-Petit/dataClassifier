package fr.groupeh6.sae.columns;

import java.util.Objects;

import fr.groupeh6.sae.columns.normalizer.IValueNormalizer;
import fr.groupeh6.sae.points.IPoint;

public abstract class Column {
	
	private String name;
	protected double min;
	protected double max;
	
	protected IValueNormalizer normalizer;
	
	public Column(String name, IValueNormalizer normalizer) {
		this.name = name;
		this.normalizer = normalizer;
	}
	
	public double getNormalizedValue(IPoint point) {
		// traiter si normalizer est null
		return normalizer.normalize(point);
	}
	
	public Object getDenormalizedValue(double value) {
		// traiter si normalizer est null
		return normalizer.denormalize(value);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNormalizable() {
		return normalizer != null;
	}
	
	public void updateMinMax(double value) {
		if(value > max) max = value;
		if(value < min) min = value;
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

}
