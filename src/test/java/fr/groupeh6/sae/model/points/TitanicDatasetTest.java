package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.datas.titanic.EnumEmbarked;
import fr.groupeh6.sae.model.datas.titanic.EnumSex;
import fr.groupeh6.sae.model.datas.titanic.TitanicDataset;
import fr.groupeh6.sae.model.datas.titanic.TitanicPoint;

class TitanicDatasetTest {
	
	AbstractDataset titanic = new TitanicDataset();
	
	@Test
	void get_type_test() {
		assertEquals("TitanicPoint [passengerId=null, survived=0.0, placeClass=0.0, name=null, sex=null, age=0.0, sibSp=0.0, parch=0.0, ticket=null, fare=0.0, cabin=null, embarked=null]",titanic.getType().toString());
	}

}
