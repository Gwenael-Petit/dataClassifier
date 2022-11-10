package fr.groupeh6.sae.classifier;

import java.util.ArrayList;
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
	
	public List<IPoint> getNeighbours(IPoint point, List<IPoint> points, List<Column> columns){
		List<IPoint> neighbours = new ArrayList<>();
		List<IPoint> tmp = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			tmp.add(points.get(i));
		}
		for(IPoint p : points) { 
			for(int j = 0; j < tmp.size(); j++) {
				if((distance.distance(point, p, columns))<=(distance.distance(point, tmp.get(j), columns))) {
					
				}
			}
		}
		
		return neighbours;
	}

	@Override
	public void classifyPoint(IPoint point, Column columnClass, List<IPoint> points) {
			
	}

	@Override
	public void classifyAllPoint(Column columnClass, List<IPoint> points) {
		// TODO Auto-generated method stub
	}

}
