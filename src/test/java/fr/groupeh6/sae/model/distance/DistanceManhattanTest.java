package fr.groupeh6.sae.model.distance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DistanceManhattanTest {

Distance dist = new DistanceManhattan();
	
	IPoint p1 = new IrisPoint(5.1, 3.5, 1.4, 0.2, "Setosa");
	IPoint p2 = new IrisPoint(6.3, 3.3, 6, 2.5, "Virginica");
	IPoint p3 = new IrisPoint(3.2, 4.2, 4, 1.6, "Virginica");
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	
	@BeforeEach
	void setUp() {
		spL.update(3.2);
		spL.update(6.3);
		spW.update(4.2);
		spW.update(3.3);
		ptL.update(1.4);
		ptL.update(6);
		ptW.update(0.2);
		ptW.update(2.5);
	}

	@Test
	void test_distance_a_lui_meme() {
		assertEquals(0.0, dist.distance(p1, p1, List.of(spL, spW, ptL, ptW)));
	}
	
	@Test
	void test_distance_1_column() {
		assertEquals(1.0, dist.distance(p2, p3, List.of(spL)));
		assertEquals(0.613, dist.distance(p1, p3, List.of(spL)),0.001);
		assertEquals(0.387, dist.distance(p1, p2, List.of(spL)),0.001);
		assertEquals(0.222, dist.distance(p1, p2, List.of(spW)),0.001);
	}
	
	@Test
	void test_distance_2_column() {
		assertEquals(2, dist.distance(p2, p3, List.of(spL,spW)),0.001);
		assertEquals(1.390, dist.distance(p1, p3, List.of(spL,spW)),0.001);
		assertEquals(0.609, dist.distance(p1, p2, List.of(spL,spW)),0.001);
		assertEquals(1.387, dist.distance(p1, p2, List.of(spL,ptW)),0.001);
	}
	
	@Test
	void test_distance_3_column() {
		assertEquals(2.434, dist.distance(p2, p3, List.of(spL,spW,ptL)),0.001);
		assertEquals(1.955, dist.distance(p1, p3, List.of(spL,spW,ptL)),0.001);
		assertEquals(1.609, dist.distance(p1, p2, List.of(spL,spW,ptL)),0.001);
		assertEquals(2.387, dist.distance(p1, p2, List.of(spL,ptL,ptW)),0.001);
	}
	
	@Test
	void test_distance_4_column() {
		assertEquals(2.826, dist.distance(p2, p3, List.of(spL,spW,ptL,ptW)),0.001);
		assertEquals(2.564, dist.distance(p1, p3, List.of(spL,spW,ptL,ptW)),0.001);
		assertEquals(2.609, dist.distance(p1, p2, List.of(spL,spW,ptL,ptW)),0.001);
	}

}
