package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;

class DistanceModelTest {
	MainModel model = new MainModel();
	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	AbstractColumn abCol = new NumberColumn("test");
	
	
	@BeforeEach
	void setUp() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		model.loadFromFile(path, ',', false);
	}
	
	@Test
	void test_getColumns() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals("[Number:sepal.length, Number:sepal.width, Number:petal.length, Number:petal.width, Enum:variety]",distModel.getColumns().toString());
	}
	
	@Test
	void test_getSelected() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals("[Number:sepal.length, Number:sepal.width, Number:petal.length, Number:petal.width]",distModel.getSelected().toString());
	}
	
	@Test
	void test_is_selected() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals(false,distModel.isSelected(abCol));
	}
	
	@Test
	void test_get_calcul() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals("Euclidienne",distModel.getCalcul());
	}
	
	@Test
	void test_set_calcul() {
		DistanceModel distModel = new DistanceModel(model);
		assertEquals("Euclidienne",distModel.getCalcul());
		distModel.setCalcul("Manhattan");
		assertEquals("Manhattan",distModel.getCalcul());
	}
	
	@Test
	void test_submit() {
		DistanceModel distModel = new DistanceModel(model);
		
		assertEquals("Euclidienne",model.getDistance().name());
		distModel.setCalcul("Manhattan");
		distModel.submit();
		assertEquals("Manhattan",model.getDistance().name());
	}
	
	

}
