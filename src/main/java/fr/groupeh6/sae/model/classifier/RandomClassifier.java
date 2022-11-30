package fr.groupeh6.sae.model.classifier;

import java.util.List;
import java.util.Random;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class RandomClassifier implements Classifier {

	@Override
	public Object classifyPoint(IPoint point, AbstractColumn columnClass, List<IPoint> points) {
		int r = new Random().nextInt(points.size());
		Object value = points.get(r).getValue(columnClass);
		return value;
	}

	@Override
	public Object getName() {
		return "Random Classifier";
	}

}
