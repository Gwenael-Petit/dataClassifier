package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.distance.Distance;
import fr.groupeh6.sae.model.distance.DistanceManhattan;

class FactoryTest {
	AbstractColumn abCol = new NumberColumn("sepal.length");
	AbstractColumn abCol1 = new NumberColumn("sepal.width");
	List<AbstractColumn> list = new ArrayList<AbstractColumn>();
	
	@BeforeEach
	void setup() {
		list.add(abCol);
		list.add(abCol1);
	}
	Distance distManhattan = new DistanceManhattan(list);
	
	@Test
	void test_new_dataset() {
		assertEquals("Iris",Factory.getInstance().newDataset("IRIS").getName());
	}
	
	@Test
	void test_knn_classifier() {
		assertEquals("Manhattan classifier",Factory.getInstance().knnClassifier(1,distManhattan).getName());
	}
	
	@Test
	void test_euclidienne() {
		assertEquals("Euclidienne",Factory.getInstance().euclidienne(list).getDistanceName());
	}

	@Test
	void test_random_classifier() {
		assertEquals("Random Classifier",Factory.getInstance().randomClassifier().getName());
	}
	
	@Test
	void test_distance_from_name() {
		assertEquals("Manhattan",Factory.getInstance().distanceFromName("manhattan", list).getDistanceName());
		assertEquals("Euclidienne",Factory.getInstance().distanceFromName("Euclidienne", list).getDistanceName());
		assertEquals(null,Factory.getInstance().distanceFromName("test", list));
	}
}
