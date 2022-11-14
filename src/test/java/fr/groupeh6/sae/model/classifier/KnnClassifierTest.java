package fr.groupeh6.sae.model.classifier;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;
import fr.groupeh6.sae.model.distance.DistanceManhattan;

class KnnClassifierTest {

	IPoint p1 = new IrisPoint(5.2, 2.3, 1.4, 3.2, "Setosa");
	IPoint p2 = new IrisPoint(2.2, 1.1, 3.3, 4.4, "Setosa");
	IPoint p3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, "Virginica");
	IPoint p4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, "Virginica");
	IPoint p5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, "Virginica");
	IPoint p6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, "Versicolor");
	
	
	List<Column> columns = IrisDataset.COLUMNS;
	List<IPoint> points = new ArrayList<>();
	List<IPoint> neighbours = new ArrayList<>();
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	Column variety = IrisDataset.COLUMNS.get(4);

	
	KnnClassifier classifier1 = new KnnClassifier(3, new DistanceEuclidienne());
	KnnClassifier classifier2 = new KnnClassifier(3, new DistanceManhattan());
	KnnClassifier classifier3 = new KnnClassifier(3);
	
	@Test
	void test() {
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		
		neighbours = classifier1.getNeighbours(p1, points, List.of(spL,spW,ptL,ptW));
		System.out.println(neighbours);
		
		classifier1.classifyPoint(p1, variety, points, List.of(spL,spW,ptL,ptW));
		System.out.println(p1.getValue(variety));
		
		neighbours = classifier2.getNeighbours(p1, points, List.of(spL,spW,ptL,ptW));
		System.out.println(neighbours);
		
		classifier2.classifyPoint(p1, variety, points, List.of(spL,spW,ptL,ptW));
		System.out.println(p1.getValue(variety));
		
		neighbours = classifier3.getNeighbours(p1, points, List.of(spL,spW,ptL,ptW));
		System.out.println(neighbours);
		
		classifier3.classifyPoint(p1, variety, points, List.of(spL,spW,ptL,ptW));
		System.out.println(p1.getValue(variety));
	}

}
