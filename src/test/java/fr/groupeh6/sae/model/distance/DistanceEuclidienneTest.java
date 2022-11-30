package fr.groupeh6.sae.model.distance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DistanceEuclidienneTest {
	
	final double DELTA = 0.001;
	
	IPoint p1 = new IrisPoint(5.1, 3.5, 1.4, 0.2, EnumVariety.SETOSA);
	IPoint p2 = new IrisPoint(6.3, 3.3, 6, 2.5, EnumVariety.VIRGINICA);
	IPoint p3 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	
	DistanceEuclidienne dist = new DistanceEuclidienne(null);
	
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
		dist.columns = List.of(spL, spW, ptL, ptW);
		assertEquals(0.0, dist.distance(p1, p1));
	}
	
	@Test
	void test_distance_1_column() {
		dist.columns = List.of(spL);
		assertEquals(1.0, dist.distance(p2, p3));
		assertEquals(0.613, dist.distance(p1, p3), DELTA);
		assertEquals(0.387, dist.distance(p1, p2), DELTA);
		dist.columns = List.of(spW);
		assertEquals(0.222, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_distance_2_column() {
		dist.columns = List.of(spL,spW);
		assertEquals(1.414, dist.distance(p2, p3), DELTA);
		assertEquals(0.990, dist.distance(p1, p3), DELTA);
		assertEquals(0.446, dist.distance(p1, p2), DELTA);
		dist.columns = List.of(spL,ptW);
		assertEquals(1.072, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_distance_3_column() {
		dist.columns = List.of(spL,spW,ptL);
		assertEquals(1.479, dist.distance(p2, p3), DELTA);
		assertEquals(1.140, dist.distance(p1, p3), DELTA);
		assertEquals(1.095, dist.distance(p1, p2), DELTA);
		dist.columns = List.of(spL,ptL,ptW);
		assertEquals(1.466, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_distance_4_column() {
		dist.columns = List.of(spL,spW,ptL,ptW);
		assertEquals(1.530, dist.distance(p2, p3), DELTA);
		assertEquals(1.292, dist.distance(p1, p3), DELTA);
		assertEquals(1.482, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_get_columns_distance() {
		assertEquals(null,dist.getColumnsDistance());
		dist.columns = List.of(spL,ptL,ptW);
		assertEquals("[Number:sepal.length, Number:petal.length, Number:petal.width]",dist.getColumnsDistance().toString());
		dist.columns = List.of(spL,ptW);
		assertEquals("[Number:sepal.length, Number:petal.width]",dist.getColumnsDistance().toString());
	}
	
	@Test 
	void test_Name() {
		assertEquals("Euclidienne", dist.name());
	}

}
