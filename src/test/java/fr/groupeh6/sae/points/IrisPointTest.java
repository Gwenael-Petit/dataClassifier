package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.StringColumn;

class IrisPointTest {

	@Test
	void Test() {
		IrisPoint iris = new IrisPoint(5.1,3.5,1.4,.2,"Setosa");
		StringColumn col = new StringColumn("sepal.length");
		iris.getValue(col);
	}

}
