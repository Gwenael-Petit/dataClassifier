package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.titanic.EnumEmbarked;
import fr.groupeh6.sae.model.datas.titanic.EnumSex;
import fr.groupeh6.sae.model.datas.titanic.TitanicDataset;
import fr.groupeh6.sae.model.datas.titanic.TitanicPoint;

class TitanicDatasetTest {
	IPoint pers1 = new TitanicPoint("3", 0, 3, "Braun", EnumSex.MALE, 22, 1, 0, "4563221", 5.3, "C54", EnumEmbarked.S);
	IPoint pers2 = new TitanicPoint("12", 1, 1, "Moran", EnumSex.FEMALE, 30, 0, 5, "A/3 5456", 70.567, "", EnumEmbarked.Q);
	IPoint pers3 = new TitanicPoint("6", 0, 2, "Allen", EnumSex.MALE, 4, 3, 1, "PP 43224", 8.05, "G7", EnumEmbarked.S);
	AbstractDataset titanic = new TitanicDataset();
	
	@Test
	void get_type_test() {
		assertEquals("TitanicPoint [passengerId=null, survived=0.0, placeClass=0.0, name=null, sex=null, age=0.0, sibSp=0.0, parch=0.0, ticket=null, fare=0.0, cabin=null, embarked=null]",titanic.getType().toString());
	}
	
	@Test
	void test_distance() {
		assertEquals(0.8333,titanic.distance(pers1,pers2),0.0001);
		assertEquals(0.8333,titanic.distance(pers2,pers3),0.0001);
		assertEquals(0.0,titanic.distance(pers1,pers3),0.0001);
	}

}
