package fr.groupeh6.sae.columns;

public class StringColumn extends Column {

	public StringColumn(String name) {
		super(name);
	}

	@Override
	public double normalize(Object value) {
		throw new NotNormalizableException();
	}

	@Override
	public Object denormalize(double value) {
		throw new NotNormalizableException();
	}

	@Override
	public boolean isNormalizable() {
		return false;
	}

}
