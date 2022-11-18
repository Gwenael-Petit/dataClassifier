package fr.groupeh6.sae.model.classifier;

import java.util.List;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;

public interface Classifier {
	
	public Object classifyPoint(IPoint point, Column columnClass, List<IPoint> points, List<Column> columns);
	
	public void classifyAllPoint(Column columnClass, List<IPoint> points);

}
