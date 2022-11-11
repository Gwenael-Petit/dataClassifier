package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.NotNormalizableException;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.columns.StringColumn;

class IrisPointTest {

	IrisPoint setosa = new IrisPoint(5.1, 3.5, 1.4, .2, "Setosa");
	IrisPoint setosa1 = new IrisPoint(10, 5, 1, 1.2, "Setosa");
	IrisPoint setosa2 = new IrisPoint(3, 3, 0.4, 4.2, "Setosa");
	IrisPoint virginica = new IrisPoint(6.3, 3.3, 6, 2.5, "Virginica");
	IrisPoint versicolor = new IrisPoint(5.7, 2.8, 4.1, 1.3, "Versicolor");
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	StringColumn var = new StringColumn("variety");

	@BeforeEach
	void setUp() {
		spL.update(3);
		spL.update(10);
		
		spW.update(2.8);
		spW.update(5);
		
		ptL.update(0.4);
		ptL.update(6);
		
		ptW.update(0.2);
		ptW.update(4.2);
	}
	
	@Test
	void test_toString() {
		assertEquals("Setosa[5.1,3.5,1.4,0.2]", setosa.toString());
	}

	@Test
	void test_get_value() {
		assertEquals(5.1, setosa.getValue(spL));
		assertEquals(3.5, setosa.getValue(spW));
		assertEquals("Virginica", virginica.getValue(var));
		assertEquals(1.3, versicolor.getValue(ptW));
	}
	
	@Test
	void get_normalized_test() {

		//assertEquals(0.2222, setosa.getNormalizedValue(spL));
		//assertEquals(0.541666, virginica.getNormalizedValue(spW));
		//assertEquals(0.5254237288, versicolor.getNormalizedValue(ptL));
		//assertEquals(0.041666, setosa.getNormalizedValue(ptW));
		
		assertEquals(0.3,setosa.getNormalizedValue(spL));
		assertThrows(NotNormalizableException.class, () -> setosa.getNormalizedValue(var));

	}
	
	@Test
	void test_distanceTo() {
		assertEquals(0.0, setosa.distanceTo(setosa1));
	}

}
