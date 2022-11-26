package fr.groupeh6.sae.model.distance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DistanceManhattanTest {
	
	final double DELTA = 0.001;
	
	IPoint p1 = new IrisPoint(5.1, 3.5, 1.4, 0.2, EnumVariety.SETOSA);
	IPoint p2 = new IrisPoint(6.3, 3.3, 6, 2.5, EnumVariety.VIRGINICA);
	IPoint p3 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	
	DistanceManhattan dist = new DistanceManhattan(null);
	
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
		assertEquals(2, dist.distance(p2, p3), DELTA);
		assertEquals(1.390, dist.distance(p1, p3), DELTA);
		assertEquals(0.609, dist.distance(p1, p2), DELTA);
		dist.columns = List.of(spL,ptW);
		assertEquals(1.387, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_distance_3_column() {
		dist.columns = List.of(spL,spW,ptL);
		assertEquals(2.434, dist.distance(p2, p3), DELTA);
		assertEquals(1.955, dist.distance(p1, p3), DELTA);
		assertEquals(1.609, dist.distance(p1, p2), DELTA);
		dist.columns = List.of(spL,ptL,ptW);
		assertEquals(2.387, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_distance_4_column() {
		dist.columns = List.of(spL,spW,ptL,ptW);
		assertEquals(2.826, dist.distance(p2, p3), DELTA);
		assertEquals(2.564, dist.distance(p1, p3), DELTA);
		assertEquals(2.609, dist.distance(p1, p2), DELTA);
	}
	
	@Test
	void test_get_columns_distance() {
		assertEquals(null,dist.getColumnsDistance());
		dist.columns = List.of(spL,ptL,ptW);
		assertEquals("[sepal.length, petal.length, petal.width]",dist.getColumnsDistance().toString());
		dist.columns = List.of(spL,ptW);
		assertEquals("[sepal.length, petal.width]",dist.getColumnsDistance().toString());
		
	}

}
