package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


	@Test
	void get_value_test() {

		assertEquals(5.1, setosa.getValue(spL));
		assertEquals(3.5, setosa.getValue(spW));
		assertEquals("Virginica", virginica.getValue(var));
		assertEquals(1.3, versicolor.getValue(ptW));

	}
	
	@Test
	void get_normalized_test() {
		
		spL.updateNewPoint(setosa);
		spL.updateNewPoint(setosa1);
		spL.updateNewPoint(setosa2);
		//assertEquals(0.2222, setosa.getNormalizedValue(spL));
		//assertEquals(0.541666, virginica.getNormalizedValue(spW));
		//assertEquals(0.5254237288, versicolor.getNormalizedValue(ptL));
		//assertEquals(0.041666, setosa.getNormalizedValue(ptW));
		
		assertEquals(0.3,setosa.getNormalizedValue(spL));
		assertThrows(NotNormalizableException.class, () -> setosa.getNormalizedValue(var));

	}

}
