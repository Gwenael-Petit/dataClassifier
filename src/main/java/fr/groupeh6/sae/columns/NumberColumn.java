package fr.groupeh6.sae.columns;

import fr.groupeh6.sae.points.IPoint;

public class NumberColumn extends Column implements Updatable {
	
	protected double max;
	protected double min;

	public NumberColumn(String name) {
		super(name);
	}

	@Override
	public double normalize(Object value) {
		return ((double)value-min)/(max-min);
	}

	@Override
	public Object denormalize(double value) {
		return value*(max-min)+min;
	}

	@Override
	public boolean isNormalizable() {
		return true;
	}

	@Override
	public void update(IPoint point) {
		double value = (double) point.getValue(this);
		if(value > max) max = value;
		if(value < min) min = value;
	}

	@Override
	public boolean isUpdatable() {
		return true;
	}

}
