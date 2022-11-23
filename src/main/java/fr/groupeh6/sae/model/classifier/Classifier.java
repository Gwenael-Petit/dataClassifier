package fr.groupeh6.sae.model.classifier;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public interface Classifier {
	
	public Object classifyPoint(IPoint point, AbstractColumn columnClass, List<IPoint> points);

}
