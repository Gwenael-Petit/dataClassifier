package fr.groupeh6.sae.classifier;

import java.util.ArrayList;
import java.util.Collections;
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
		for(int i = 0; i < k; i++) {
			neighbours.add(points.get(i));
		}
		for(IPoint p : points) { 
			int j =0;
			while(distance.distance(point, neighbours.get(j), columns) < distance.distance(p, point, columns) && j < neighbours.size()) {
				j++;
			}
			if(distance.distance(point, neighbours.get(j), columns) > distance.distance(p, point, columns)) {
				neighbours.remove(j); neighbours.add(p);
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
