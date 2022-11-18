package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DatasetTest {

	Dataset irisDataset = new IrisDataset();
	IrisPoint setosa = new IrisPoint(2.1, 4.1, 3.0, 1.1, EnumVariety.SETOSA);
	IrisPoint virginica = new IrisPoint(3.1, 4.1, 2.1, 4.2, EnumVariety.VIRGINICA);
	
	@Test
	void test_getName() {
		assertEquals("Iris", irisDataset.getName());
	}
	
	@Test
	void test_setLines() {
		irisDataset.addLine(setosa);
		irisDataset.addLine(virginica);
		irisDataset.addLine(virginica);
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(setosa);
		assertEquals(3, irisDataset.getNbLines());
		irisDataset.setLines(e);
		assertEquals(1, irisDataset.getNbLines());
	}
	
	@Test
	void test_addLines() {
		assertEquals(0, irisDataset.getNbLines());
		irisDataset.addLine(setosa);
		assertEquals(1, irisDataset.getNbLines());
	}
	
	
	@Test
	void test_addAllLine() {
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(setosa);
		e.add(virginica);
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
	void test_setClassifier() {
		KnnClassifier knn = new KnnClassifier(3);
		assertEquals(null, irisDataset.classifier);
		irisDataset.setClassifier(knn);
		assertEquals(knn, irisDataset.classifier);
	}
	
	@Test
	void test_iterator() {
		List<IPoint> e = new ArrayList<IPoint>();
		e.add(setosa);
		e.add(virginica);
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
		//Methode qui n'est pas encore terminée donc test pas définitif
		assertEquals(0.0, irisDataset.robustesse());
	}

}
