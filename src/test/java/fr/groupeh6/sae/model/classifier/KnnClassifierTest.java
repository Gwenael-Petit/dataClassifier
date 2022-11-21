package fr.groupeh6.sae.model.classifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;
import fr.groupeh6.sae.model.distance.DistanceManhattan;

class KnnClassifierTest {

	IPoint p1 = new IrisPoint(5.2, 2.3, 1.4, 3.2, EnumVariety.SETOSA);
	IPoint p2 = new IrisPoint(2.2, 1.1, 3.3, 4.4, EnumVariety.SETOSA);
	IPoint p3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, EnumVariety.VIRGINICA);
	IPoint p4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, EnumVariety.VIRGINICA);
	IPoint p5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, EnumVariety.VIRGINICA);
	IPoint p6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, EnumVariety.VERSICOLOR);
	
	Dataset dataset = new IrisDataset();
	
	List<IPoint> neighbours = new ArrayList<>();
	
	Column spL, spW, ptL, ptW, variety;
	
	@BeforeEach
	void setup() {
		dataset = new IrisDataset();
		spL = dataset.getColumns().get(0);
		spW = dataset.getColumns().get(1);
		ptL = dataset.getColumns().get(2);
		ptW = dataset.getColumns().get(3);
		variety = dataset.getColumns().get(4);
		dataset.addAllLine(List.of(p1, p2, p3, p4, p5, p6));
	}
	
	@Test
	void test_Classify_With_Euclidienne() {
		KnnClassifier classifier = new KnnClassifier(3, new DistanceEuclidienne(List.of(spL,spW,ptL,ptW)));
		assertEquals(List.of(p5, p2, p6), classifier.getNeighbours(p1, dataset.getLines()));
		assertEquals(EnumVariety.VERSICOLOR, classifier.classifyPoint(p1, variety, dataset.getLines()));
	}
	
	@Test
	void test_Classify_With_Manhattan() {
		KnnClassifier classifier = new KnnClassifier(3, new DistanceManhattan(List.of(spL,spW,ptL,ptW)));
		assertEquals(List.of(p5, p6, p2), classifier.getNeighbours(p1, dataset.getLines()));
		assertEquals(EnumVariety.VERSICOLOR, classifier.classifyPoint(p1, variety, dataset.getLines()));
	}
	
	@Test
	void test_Classify_Default() {
		KnnClassifier classifier = new KnnClassifier(3, dataset);
		assertEquals(List.of(p5, p2, p6), classifier.getNeighbours(p1, dataset.getLines()));
		assertEquals(EnumVariety.VERSICOLOR, classifier.classifyPoint(p1, variety, dataset.getLines()));
	}
}
