package fr.groupeh6.sae.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		for(IPoint p : points) neighbours.add(point);
		neighbours.sort((p1,p2)->Double.compare(distance.distance(p1, point, columns),distance.distance(p2, point, columns)));
		
		return neighbours.subList(0, k);
	}

	@Override
	public void classifyPoint(IPoint point, Column columnClass, List<IPoint> points, List<Column> columns) {
		List<IPoint> neighbours = getNeighbours(point, points, columns);
		Map<Object, Integer> pointClass = new HashMap<>();
		pointClass.put(points.get(0).getValue(columnClass), 1);
		for(int i = 1; i < points.size(); i++) {
			for(int j = 0; j < pointClass.size(); j++) {
				if(points.get(i).getValue(columnClass).equals(pointClass.keySet().toArray()[j])) {
					pointClass.merge(pointClass.keySet().toArray()[j], 1, Integer::sum);
				}else {
					pointClass.put(points.get(i).getValue(columnClass),1);
				}
			}
			
		}
		Entry<Object, Integer> entry = pointClass.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get();
		//point.setValue(columnClass, entry.getKey());
	}

	@Override
	public void classifyAllPoint(Column columnClass, List<IPoint> points) {
		// TODO Auto-generated method stub
	}

}
