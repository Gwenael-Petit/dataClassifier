package fr.groupeh6.sae.model.classifier;

import java.util.List;
import java.util.Random;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;

public class RandomClassifier implements Classifier {

	@Override
	public void classifyAllPoint(Column columnClass, List<IPoint> points) {
		
	}

	@Override
	public Object classifyPoint(IPoint point, Column columnClass, List<IPoint> points, List<Column> columns) {
		points.remove(point);
		int r = new Random().nextInt(points.size());
		Object value = points.get(r).getValue(columnClass);
		return value;
	}

}
