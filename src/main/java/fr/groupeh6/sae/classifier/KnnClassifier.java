package fr.groupeh6.sae.classifier;

import java.util.List;
import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.distance.Distance;
import fr.groupeh6.sae.points.IPoint;

public class KnnClassifier implements Classifier {
	
	private int k;
	private Distance distance;
	
	public KnnClassifier(int k, Distance distance) {
		this.k = k;
		this.distance = distance;
	}
	
	public KnnClassifier(int k) {
		this(k, null);
	}

	@Override
	public void classifyPoint(IPoint point, Column columnClass, List<IPoint> points) {
		// TODO Auto-generated method stub
	}

	@Override
	public void classifyAllPoint(Column columnClass, List<IPoint> points) {
		// TODO Auto-generated method stub
	}

}
