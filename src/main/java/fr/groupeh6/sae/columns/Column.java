package fr.groupeh6.sae.columns;

import fr.groupeh6.sae.columns.normalizer.IValueNormalizer;
import fr.groupeh6.sae.points.IPoint;

public abstract class Column {
	
	private String name;
	private double min;
	private double max;
	
	protected IValueNormalizer normalizer;
	
	public Column(String name, IValueNormalizer normalizer) {
		
	}
	
	public Column(String name) {
		this(name, null);
	}
	
	public double getNormalizedValue(IPoint point) {
		return 0.0;
	}
	
	public Object getDenormalizedValue(double value) {
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNormalizable() {
		return false;
	}
	
	public void updateMinMax(double value) {
		
	}

}
