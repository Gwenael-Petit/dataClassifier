package fr.groupeh6.sae.model.columns;

public class StringColumn extends AbstractColumn {

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

	@Override
	public boolean isUpdatable() {
		return false;
	}

}
