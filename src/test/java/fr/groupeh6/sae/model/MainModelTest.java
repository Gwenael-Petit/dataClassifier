package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

class MainModelTest {
	
	IPoint p1 = new IrisPoint(5.1, 3.5, 1.4, 0.2, EnumVariety.SETOSA);
	IPoint p2 = new IrisPoint(6.3, 3.3, 6, 2.5, EnumVariety.VIRGINICA);
	IPoint p3 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	IPoint p4 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	IPoint p5 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	IPoint p6 = new IrisPoint(3.2, 4.2, 4, 1.6, EnumVariety.VIRGINICA);
	
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	
	DistanceEuclidienne dist = new DistanceEuclidienne(null);
	
	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "DataForTest.csv";
	protected File file = new File(path);
	
	MainModel model = new MainModel();
	AbstractColumn classColumn = new StringColumn("variety");
	Classifier classifier = new KnnClassifier(2, new DistanceEuclidienne(List.of(spL,spW,ptL,ptW)));
	IPoint p = new IrisPoint();
	
	@Test
	void test_load_from_file() {
		
	}
	
	@Test
	void test_load_from_String() {
		
	}
	
	@Test 
	void test_set_classifier() { 
		try {
			model.loadFromFile(path, ',', false);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		model.setClassifier(classifier);
		assertEquals(classifier, model.classifier);
	}
	
	@Test
	void test_set_class_classifier() {
		model.setClassClassifier(classColumn);
		assertEquals(classColumn, model.classClassifier);
	}
	
	@Test
	void test_add_point() {
		
		try {
			model.loadFromFile(path, ',', false);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		
		model.addPoint(p);
		assertTrue(model.categories.get(0).getLines().contains(p));
		model.setClassifier(classifier);
		model.setClassClassifier(classColumn);
		//model.addPoint(p);
		//assertTrue(model.getCategory("Iris").getLines().contains(p));
	}
	
	@Test
	void test_create_categories() {
		try {
			model.loadFromFile(path, ',', false);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		model.createCategories();
		assertEquals(2,model.allCategories().size());
	}
	
	@Test
	void test_get_category() {
		
	}
	
	@Test
	void test_add_category_with_String() {
		
	}
	
	@Test
	void test_add_category_with_Dataset() {
		
	}
	
	@Test
	void test_reset_categories() {
		
	}

	@Test
	void test_all_categories() {
		
	}
	
	@Test
	void test_robustesse() {
		
	}
	
	@Test
	void test_get_robustesse() {
		
	}
	
	@Test
	void test_nbColumns() {
		
	}
	
	@Test
	void test_set_X_column() {
		assertEquals(null, model.getxColumn());
		model.setxColumn(ptL);
		assertEquals(ptL, model.getxColumn());
	}
	
	@Test
	void test_set_Y_column() {
		assertEquals(null, model.getyColumn());
		model.setyColumn(ptW);
		assertEquals(ptW, model.getyColumn());
	}
	
	@Test
	void test_have_classifier() {
		try {
			model.loadFromFile(path, ',', false);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		assertFalse(model.haveClassifier());
		model.setClassifier(classifier);
		model.setClassClassifier(classColumn);
		assertTrue(model.haveClassifier());
	}
	
	@Test
	void test_have_train_data_loaded() {
		assertEquals(null,model.getTrainDataset());
		try {
			model.loadFromFile(path, ',', true);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		model.getTrainDataset();
		assertEquals(2,model.getTrainDataset().getNbLines());
	}
	
	@Test
	void test_get_train_dataset() {
		try {
			model.loadFromFile(path, ',', true);
		} catch (NotSameTypeException | IOException | TypeNotRegisteredException e) {
			System.out.println(e.getMessage());
		}
		model.getTrainDataset();
		assertEquals(2,model.getTrainDataset().getNbLines());
	}
	
	@Test
	void test_get_X_column() {
		model.setxColumn(ptL);
		assertEquals(ptL, model.getxColumn());
	}
	
	@Test
	void test_get_Y_column() {
		model.setyColumn(ptW);
		assertEquals(ptW, model.getyColumn());
	}
	
	
}
