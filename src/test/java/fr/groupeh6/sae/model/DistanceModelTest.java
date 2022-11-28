package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistanceModelTest {
	MainModel model = new MainModel();
	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	
	
	
	@BeforeEach
	void setUp() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		model.loadFromFile(path, ',', false);
	}
	
	@Test
	void test_getColumns() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals("[sepal.length, sepal.width, petal.length, petal.width, variety]",distModel.getColumns().toString());
	}
	
	@Test
	void test_getSelected() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals(null,distModel.getSelected());
	}

}
