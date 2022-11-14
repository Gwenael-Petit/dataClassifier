package fr.groupeh6.sae.classifier;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.dataset.IrisDataSet;
import fr.groupeh6.sae.distance.DistanceEuclidienne;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

class KnnClassifierTest {

	IPoint p1 = new IrisPoint(5.2, 2.3, 1.4, 3.2, "Setosa");
	IPoint p2 = new IrisPoint(2.2, 1.1, 3.3, 4.4, "Setosa");
	IPoint p3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, "Virginica");
	IPoint p4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, "Virginica");
	IPoint p5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, "Virginica");
	IPoint p6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, "Versicolor");
	
	
	List<Column> columns = IrisDataSet.COLUMNS;
	List<IPoint> points = new ArrayList<>();
	List<IPoint> neighbours = new ArrayList<>();
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	Column variety = IrisDataSet.COLUMNS.get(4);
	
	
	KnnClassifier classifier = new KnnClassifier(3, new DistanceEuclidienne());
	
	@Test
	void test() {
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		
		neighbours = classifier.getNeighbours(p1, points, List.of(spL,spW,ptL,ptW));
		System.out.println(neighbours);
		
		classifier.classifyPoint(p1, variety, points, List.of(spL,spW,ptL,ptW));
		System.out.println(p1.getValue(variety));
	}

}
