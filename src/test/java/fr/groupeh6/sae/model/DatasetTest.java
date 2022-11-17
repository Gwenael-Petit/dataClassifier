package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DatasetTest {

	Dataset irisDataset = new IrisDataset();
	IrisPoint setosa = new IrisPoint(2.1, 4.1, 3.0, 1.1, "Setosa");
	IrisPoint virginica = new IrisPoint(3.1, 4.1, 2.1, 4.2, "Virgnica");
	
	@Test
	void test_getName() {
		assertEquals("Iris", irisDataset.getName());
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
	void test_setClassifier() {
		KnnClassifier knn = new KnnClassifier(3);
		assertEquals(null, irisDataset.classifier);
		irisDataset.setClassifier(knn);
		assertEquals(knn, irisDataset.classifier);
	}

}
