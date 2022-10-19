package fr.groupeh6.sae.columns.iris;

import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.dataset.IDataset;
import fr.groupeh6.sae.normalizer.IValueNormalizer;
import fr.groupeh6.sae.points.IPoint;

public class SepalWidthColumn implements IColumn {

	public void setNormalizer(IValueNormalizer valueNormalizer) {
	}

	public double getNormalizedValue(IPoint point) {
		return 0;
	}

	public Object getDenormalizedValue(double value) {
		return null;
	}

	public String getName() {
		return "sepal.width";
	}

	public IDataset getDataset() {
		return null;
	}

	public boolean isNormalizable() {
		return true;
	}

}
