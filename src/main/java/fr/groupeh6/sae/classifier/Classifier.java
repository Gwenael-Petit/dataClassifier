package fr.groupeh6.sae.classifier;

import java.util.List;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.points.IPoint;

public interface Classifier {
	
	public void classifyPoint(IPoint point, Column columnClass, List<IPoint> points);
	
	public void classifyAllPoint(Column columnClass, List<IPoint> points);

}
