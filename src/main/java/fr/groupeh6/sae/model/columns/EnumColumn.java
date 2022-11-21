package fr.groupeh6.sae.model.columns;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.IPoint;

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

	@Override
	public double normalize(Object value) {
		T val = (T) value;
		return 1.0*(val.ordinal())/(enumSize()-1);
	}

	@Override
	public Object denormalize(double value) {
		return values()[(int) Math.round(value*(enumSize()-1))];
	}

	@Override
	public boolean isNormalizable() {
		return true;
	}

	@Override
	public boolean isUpdatable() {
		return false;
	}

}
