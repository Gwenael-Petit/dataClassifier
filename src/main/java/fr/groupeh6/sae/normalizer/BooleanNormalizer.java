package fr.groupeh6.sae.normalizer;

public class BooleanNormalizer implements IValueNormalizer {

	@Override
	public double normalize(Object value) {
		return value.equals(true) ? 1.0 : 0.0;
	}

	@Override
	public Object denormalize(double value) {
		return value == 1.0 ? true : false;
	}

}
