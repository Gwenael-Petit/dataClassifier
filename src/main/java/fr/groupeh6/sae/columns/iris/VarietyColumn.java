package fr.groupeh6.sae.columns.iris;

import fr.groupeh6.sae.IDataset;
import fr.groupeh6.sae.IPoint;
import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.normalizer.IValueNormalizer;

public class VarietyColumn implements IColumn {

	public void setNormalizer(IValueNormalizer valueNormalizer) {
		// TODO Auto-generated method stub
		
	}

	public double getNormalizedValue(IPoint point) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getDenormalizedValue(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public IDataset getDataset() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isNormalizable() {
		// TODO Auto-generated method stub
		return false;
	}

}
