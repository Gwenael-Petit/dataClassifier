package fr.groupeh6.sae.columns.normalizer;

public class NumberNormalizer implements IValueNormalizer {

	@Override
	public double normalize(Object value) {
		return 0.0;
	}

	@Override
	public Object denormalize(double value) {
		return null;
	}

}
