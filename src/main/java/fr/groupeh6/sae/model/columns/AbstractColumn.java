package fr.groupeh6.sae.model.columns;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;

public abstract class AbstractColumn implements IValueNormalizer {
	
	protected String name;
	protected AbstractDataset dataset;
	
	public AbstractColumn(String name) {
		this.name = name;
	}
	
	public double getNormalizedValue(IPoint point) {
		return normalize(point.getValue(this));
	}
	
	public Object getDenormalizedValue(double value) {
		return denormalize(value);
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getDistinctValues() {
		List<String> res = new ArrayList<>();
		if(isLinkedToADataset()) {
			for(IPoint point : dataset) {
				String value = "" + point.getValue(this);
				if(!res.contains(value)) res.add(value);
			}
		}
		return res;
	};
	
	public abstract boolean isNormalizable();
	
	public void setDataset(AbstractDataset dataset) {
		this.dataset = dataset;
	}
	
	public boolean isLinkedToADataset() {
		return dataset != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof AbstractColumn)) return false;
		AbstractColumn other = (AbstractColumn) obj;
		return name.equals(other.name);
	}
	
	public abstract boolean isUpdatable();
	
	public abstract boolean isEnumerative();

}
