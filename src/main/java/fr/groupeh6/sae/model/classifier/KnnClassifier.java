package fr.groupeh6.sae.model.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.distance.Distance;

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
		points.remove(point);
		List<IPoint> neighbours = new ArrayList<>();
		for(IPoint p : points) neighbours.add(p);
		if(distance != null) neighbours.sort((p1,p2)->Double.compare(distance.distance(p1, point, columns),distance.distance(p2, point, columns)));
		else neighbours.sort((p1,p2)->Double.compare(p1.distanceTo(point),p2.distanceTo(point)));
		
		return neighbours.subList(0, k);
	}

	@Override
	public void classifyPoint(IPoint point, Column columnClass, List<IPoint> points, List<Column> columns) {
		points.remove(point);
		List<IPoint> neighbours = getNeighbours(point, points, columns);
		Map<Object, Integer> valueCount = new HashMap<>();
		for(IPoint p: neighbours) {
			Object value = p.getValue(columnClass);
			if(valueCount.containsKey(value)) {
				valueCount.put(value, valueCount.get(value)+1);
			}else {
				valueCount.put(value, 1);
			}
		}
		Entry<Object, Integer> entry = valueCount.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get();
		point.setValue(columnClass, entry.getKey());
	}

	@Override
	public void classifyAllPoint(Column columnClass, List<IPoint> points) {
		// TODO Auto-generated method stub
	}

}
