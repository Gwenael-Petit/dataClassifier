package fr.groupeh6.sae.columns;

public class NumberColumn extends Column {

	public NumberColumn(String name) {
		super(name);
	}

	@Override
	public double normalize(Object value) {
		return 0;
	}

	@Override
	public Object denormalize(double value) {
		return null;
	}

	@Override
	public boolean isNormalizable() {
		return true;
	}

}
