package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class IrisPointTest {

	IrisPoint setosa = new IrisPoint(5.1, 3.5, 1.4, .2, EnumVariety.SETOSA);
	IrisPoint setosa1 = new IrisPoint(10, 5, 1, 1.2, EnumVariety.SETOSA);
	IrisPoint setosa2 = new IrisPoint(3, 3, 0.4, 4.2, EnumVariety.SETOSA);
	IrisPoint virginica = new IrisPoint(6.3, 3.3, 6, 2.5, EnumVariety.VIRGINICA);
	IrisPoint versicolor = new IrisPoint(5.7, 2.8, 4.1, 1.3, EnumVariety.VERSICOLOR);
	NumberColumn spL = new NumberColumn("sepal.length");
	NumberColumn spW = new NumberColumn("sepal.width");
	NumberColumn ptL = new NumberColumn("petal.length");
	NumberColumn ptW = new NumberColumn("petal.width");
	EnumColumn<EnumVariety> var = new EnumColumn<EnumVariety>("variety", EnumVariety.class);

	@BeforeEach
	void setUp() {
		spL.update(1);
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
		assertEquals("SETOSA[5.1,3.5,1.4,0.2]", setosa.toString());
	}

	@Test
	void test_get_value() {
		assertEquals(5.1, setosa.getValue(spL));
		assertEquals(3.5, setosa.getValue(spW));
		assertEquals(EnumVariety.VIRGINICA, virginica.getValue(var));
		assertEquals(1.4, setosa.getValue(ptL));
		assertEquals(1.3, versicolor.getValue(ptW));
		Column fake = new BooleanColumn("Fake");
		assertEquals(null, setosa.getValue(fake));
	}

	@Test
	void get_normalized_test() {
		assertEquals(0.4555, setosa.getNormalizedValue(spL), 0.0001);
		assertEquals(0.2272, virginica.getNormalizedValue(spW), 0.0001);
		assertEquals(0.6607, versicolor.getNormalizedValue(ptL), 0.0001);
		assertEquals(0, setosa.getNormalizedValue(ptW), 0.0001);
		
	}

	@Test
	void test_distanceTo() {
		assertEquals(1.015, setosa.distanceTo(virginica),0.001);
		assertEquals(1.154,virginica.distanceTo(setosa2),0.001);
		assertEquals(0.511,versicolor.distanceTo(virginica),0.001);
	}
	
	@Test
	void test_iris_point_for_opencsv() {
		IrisPoint p = new IrisPoint();
		assertEquals(0.0, p.getValue(spL));
		assertEquals(0.0, p.getValue(spW));
		assertEquals(0.0, p.getValue(ptL));
		assertEquals(0.0, p.getValue(ptW));
		assertEquals(null, p.getValue(var));
	}
	
	@Test
	void test_setValue() {
		assertEquals(5.1, setosa.getValue(spL));
		setosa.setValue(spL, 5.5);
		assertEquals(5.5, setosa.getValue(spL));
		
		assertEquals(3.5, setosa.getValue(spW));
		setosa.setValue(spW, 3.0);
		assertEquals(3.0, setosa.getValue(spW));
		
		assertEquals(EnumVariety.SETOSA, setosa.getValue(var));
		setosa.setValue(var, EnumVariety.VIRGINICA);
		assertEquals(EnumVariety.VIRGINICA, setosa.getValue(var));
		
		assertEquals(1.4, setosa.getValue(ptL));
		setosa.setValue(ptL, 1.2);
		assertEquals(1.2, setosa.getValue(ptL));
		
		assertEquals(0.2, setosa.getValue(ptW));
		setosa.setValue(ptW, 0.4);
		assertEquals(0.4, setosa.getValue(ptW));
	}

}
