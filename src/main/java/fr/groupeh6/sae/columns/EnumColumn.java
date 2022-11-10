package fr.groupeh6.sae.columns;

import fr.groupeh6.sae.points.IPoint;

public class EnumColumn<T extends Enum<T>> extends Column {

	private Class<T> enumClass;
	
	public EnumColumn(String name, Class<T> enumClass) {
		super(name);
		this.enumClass = enumClass;
	}
	
	public T[] values() {
		return enumClass.getEnumConstants();
	}
	
	public int enumSize() {
		return values().length;
	}
	
	public T getValue(Object value) {
		for(T t : values()) {
			if(t.equals(t)) return t;
		}
		return null;
	}

	@Override
	public double normalize(Object value) {
		T val = getValue(value);
		return 1.0*(val.ordinal()-1)/(enumSize()-1);
	}

	@Override
	public Object denormalize(double value) {
		return values()[(int) Math.round(value*(enumSize()-1)+1)];
	}

	@Override
	public boolean isNormalizable() {
		return true;
	}

	@Override
	public void newPoint(IPoint point) {
		
	}

}
