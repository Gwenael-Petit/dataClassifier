package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.StringColumn;

class IrisPointTest {

	@Test
	void Test() {
		IrisPoint iris = new IrisPoint(5.1,3.5,1.4,.2,"Setosa");
		StringColumn col1 = new StringColumn("sepal.length");
		StringColumn col2 = new StringColumn("sepal.width");
		assertEquals(5.1,iris.getValue(col1));
		assertEquals(3.5,iris.getValue(col2));
	}

}
