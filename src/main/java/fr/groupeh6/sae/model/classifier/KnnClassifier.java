package fr.groupeh6.sae.model.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.distance.Distance;

public class KnnClassifier implements Classifier {
	
	private int k;
	private Distance distance;
	
	public KnnClassifier(int k, Distance distance) {
		this.k = k;
		this.distance = distance;
	}
	
	public List<IPoint> getNeighbours(IPoint point, List<IPoint> points){
		List<IPoint> neighbours = new ArrayList<>();
		for(IPoint p : points) neighbours.add(p);
		neighbours.sort((p1,p2)->Double.compare(distance.distance(p1, point),distance.distance(p2, point)));
		
		return neighbours.subList(0, k);
	}

	@Override
	public Object classifyPoint(IPoint point, AbstractColumn columnClass, List<IPoint> points) {
		List<IPoint> neighbours = getNeighbours(point, points);
		Map<Object, Integer> valueCount = new HashMap<>();
		for(IPoint p: neighbours) {
			Object value = p.getValue(columnClass);
			if(valueCount.containsKey(value)) {
				valueCount.put(value, valueCount.get(value)+1);
			} else {
				valueCount.put(value, 1);
			}
		}
		Entry<Object, Integer> entry = valueCount.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get();
		return entry.getKey();
	}
	
	public String getName() {
		return this.distance.getDistanceName()+" classifier";
	}
}
