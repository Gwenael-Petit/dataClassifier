package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;


import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.datas.StoredDatas;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

class AbstractDatasetTest {

	AbstractDataset irisDataset = new IrisDataset();
	AbstractDataset irisDatasetEnum = StoredDatas.IRIS.dataset();
	AbstractDataset titanicDatasetEnum = StoredDatas.TITANIC.dataset();
	AbstractDataset pokemonDatasetEnum = StoredDatas.POKEMON.dataset();
	IPoint point1 = new IrisPoint(5.1, 3.5, 1.4, .2, EnumVariety.SETOSA);
	IPoint point2 = new IrisPoint(2.6, 3.5, 2.0, .4, EnumVariety.SETOSA);
	IPoint point3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, EnumVariety.VIRGINICA);
	IPoint point4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, EnumVariety.VIRGINICA);
	IPoint point5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, EnumVariety.VIRGINICA);
	IPoint point6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, EnumVariety.VERSICOLOR);
	IPoint point7 = new IrisPoint(4.1, 2.5, 1.6, .3, EnumVariety.VIRGINICA);
	IPoint point8 = new IrisPoint(3.6, 2.5, 2.1, .5, EnumVariety.VERSICOLOR);
	IPoint point9 = new IrisPoint(2.7, 6.6, 3.3, 1.7, EnumVariety.SETOSA);
	IPoint point10 = new IrisPoint(1.5, 4.6, 4.0, 2.1, EnumVariety.VERSICOLOR);
	IPoint point11 = new IrisPoint(8.1, 7.5, 2.2, 2.4, EnumVariety.VIRGINICA);
	IPoint point12 = new IrisPoint(7.5, 4.5, 4.6, 3.8, EnumVariety.SETOSA);
	Classifier knn = new KnnClassifier(1, new DistanceEuclidienne(irisDataset.getColumns()));
	
	@Test
	void test_getName() {
		assertEquals("Iris", irisDataset.getName());
	}
	
	@Test
	void test_setLines() {
		irisDataset.addLine(point1);
		irisDataset.addLine(point3);
		irisDataset.addLine(point3);
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(point1);
		assertEquals(3, irisDataset.getNbLines());
		irisDataset.setLines(e);
		assertEquals(1, irisDataset.getNbLines());
	}
	
	@Test
	void test_addLines() {
		assertEquals(0, irisDataset.getNbLines());
		irisDataset.addLine(point1);
		assertEquals(1, irisDataset.getNbLines());
	}
	
	
	@Test
	void test_addAllLine() {
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(point1);
		e.add(point3);
		irisDataset.addAllLine(e);
		assertEquals(2, irisDataset.getNbLines());
	}
	
	@Test
	void test_getColumn() {
		assertEquals("[sepal.length, sepal.width, petal.length, petal.width, variety]", irisDataset.getColumns().toString());
	}
	
	@Test 
	void test_getNormalizableColumn() {
		assertEquals("[sepal.length, sepal.width, petal.length, petal.width, variety]", irisDataset.getNormalizableColumns().toString());
	}
	
	@Test
	void test_iterator() {
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(point1);
		e.add(point3);
		irisDataset.addAllLine(e);
		List<IPoint> eWithIterator = new ArrayList<IPoint>();
		Iterator<IPoint> iterator = irisDataset.iterator();
		while(iterator.hasNext()) {
			eWithIterator.add(iterator.next());
		}
		assertEquals(e, eWithIterator);
	}
	
	@Test
	void test_robustesse() {
		irisDataset.addAllLine(List.of(point1, point2, point3, point4, point5, point6));
		List<AbstractColumn> listOfIrisColumns = irisDataset.getColumns();
		AbstractColumn spL = listOfIrisColumns.get(0);
		Robustesse robus = new Robustesse(irisDataset, knn, spL);
		assertEquals(0.0, robus.robustesse());
		AbstractColumn var = listOfIrisColumns.get(4);
		Robustesse robusEnum = new Robustesse(irisDataset, knn, var);
		List<Double> rep = new ArrayList<Double>();
		rep.addAll(List.of(0.6, 0.7, 0.8, 0.9));
		assertTrue(rep.contains(robusEnum.robustesse()));
	}

}
