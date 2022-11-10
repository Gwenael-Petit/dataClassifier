package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.NotNormalizableException;
import fr.groupeh6.sae.columns.StringColumn;

class IrisPointTest {
	
	IrisPoint setosa = new IrisPoint(5.1, 3.5, 1.4, .2, "Setosa");
	IrisPoint virginica = new IrisPoint(6.3, 3.3, 6, 2.5, "Virginica");
	IrisPoint versicolor = new IrisPoint(5.7, 2.8, 4.1, 1.3, "Versicolor");
	StringColumn spL = new StringColumn("sepal.length");
	StringColumn spW = new StringColumn("sepal.width");
	StringColumn ptL = new StringColumn("petal.length");
	StringColumn ptW = new StringColumn("petal.width");
	StringColumn var = new StringColumn("variety");

	@Test
	void get_value_test() {

		assertEquals(5.1, setosa.getValue(spL));
		assertEquals(3.5, setosa.getValue(spW));
		assertEquals("Virginica", virginica.getValue(var));
		assertEquals(1.3, versicolor.getValue(ptW));

	}
	
	@Test
	void get_normalized_test() {
		/*
		 * assertEquals(0.2222,setosa.getNormalizedValue(spL));
		 * assertEquals(0.541666,virginica.getNormalizedValue(spW));
		 * assertEquals(0.5254237288,versicolor.getNormalizedValue(ptL));
		 * assertEquals(0.041666,setosa.getNormalizedValue(ptW));
		 */
		assertThrows(NotNormalizableException.class, () -> setosa.getNormalizedValue(var));

	}

}
