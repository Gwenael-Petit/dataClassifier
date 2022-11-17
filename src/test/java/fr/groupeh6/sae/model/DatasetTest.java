package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class DatasetTest {

	Dataset irisDataset = new IrisDataset();
	
	@Test
	void test_getName() {
		assertEquals("Iris", irisDataset.getName());
	}
	
	@Test
	void test_getNbLines_before_adding() {
		assertEquals(0, irisDataset.getNbLines());
	}
	
	@Test
	void test_addAllLine() {
		List<IrisPoint> elements;		
	}
	
	@Test 
	void test_setClassifier() {
		irisDataset.setClassifier(new KnnClassifier(3));
		assertEquals("Knn", irisDataset.classifier);
	}

}
