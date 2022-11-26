package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NotNormalizableException;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.datas.titanic.EnumEmbarked;
import fr.groupeh6.sae.model.datas.titanic.EnumSex;
import fr.groupeh6.sae.model.datas.titanic.TitanicPoint;

class TitanicPointTest {

	TitanicPoint pers1 = new TitanicPoint("3", 0, 3, "Braun", EnumSex.MALE, 22, 1, 0, "4563221", 5.3, "C54", EnumEmbarked.S);
	TitanicPoint pers2 = new TitanicPoint("12", 1, 1, "Moran", EnumSex.FEMALE, 30, 0, 5, "A/3 5456", 70.567, "", EnumEmbarked.Q);
	TitanicPoint pers3 = new TitanicPoint("6", 0, 2, "Allen", EnumSex.MALE, 4, 3, 1, "PP 43224", 8.05, "G7", EnumEmbarked.S);
	TitanicPoint pers4 = new TitanicPoint("78", 1.0, 3, "Johnson", EnumSex.MALE, 14, 5, 0, "D446G2", 29.34, "", EnumEmbarked.C);
	TitanicPoint pers5 = new TitanicPoint("100", 0, 1, "Anderson", EnumSex.MALE, 59, 0, 0, "364234", 16.7, "", EnumEmbarked.Q);

	StringColumn passId = new StringColumn("PassengerId");
	NumberColumn survived = new NumberColumn("Survived");
	NumberColumn pClass = new NumberColumn("Pclass");
	StringColumn name = new StringColumn("Name");
	EnumColumn<EnumSex> sex = new EnumColumn<EnumSex>("Sex", EnumSex.class);
	NumberColumn age = new NumberColumn("Age");
	NumberColumn sibSp = new NumberColumn("SibSp");
	NumberColumn parch = new NumberColumn("Parch");
	StringColumn ticket = new StringColumn("Ticket");
	NumberColumn fare = new NumberColumn("Fare");
	StringColumn cabin = new StringColumn("Cabin");
	EnumColumn<EnumEmbarked> embarked = new EnumColumn<EnumEmbarked>("Embarked", EnumEmbarked.class);
	
	@BeforeEach
	void setUp (){
		pClass.update(1);
		pClass.update(3);
		
		age.update(4);
		age.update(59);
		
		sibSp.update(0);
		sibSp.update(5);
		
		parch.update(0);
		parch.update(5);
		
		fare.update(5.3);
		fare.update(70.567);
	}
	@Test
	void test_toString() {
		assertEquals("TitanicPoint [passengerId=3, survived=0.0, placeClass=3.0, name=Braun, sex=MALE, age=22.0, sibSp=1.0, parch=0.0, ticket=4563221, fare=5.3, cabin=C54, embarked=S]",pers1.toString());
	}
	
	@Test
	void test_getValue() {
		assertEquals("3",pers1.getValue(passId));
		assertEquals(0.0,pers3.getValue(survived));
		assertEquals(3.0,pers4.getValue(pClass));
		assertEquals("Braun",pers1.getValue(name));
		assertEquals(EnumSex.MALE, pers1.getValue(sex));
		assertEquals(30.0,pers2.getValue(age));
		assertEquals(5.0,pers4.getValue(sibSp));
		assertEquals(0.0,pers1.getValue(parch));
		assertEquals("PP 43224",pers3.getValue(ticket));
		assertEquals(16.7,pers5.getValue(fare));
		assertEquals("",pers4.getValue(cabin));
		assertEquals(EnumEmbarked.Q,pers2.getValue(embarked));
		AbstractColumn fake = new BooleanColumn("fake");
		assertEquals(null,pers1.getValue(fake));
	}
	
	@Test
	void test_getNormlized() {
		assertThrows(NotNormalizableException.class, () -> pers1.getNormalizedValue(passId));
		assertEquals(1.0,pers4.getNormalizedValue(survived));
		assertEquals(0.5,pers3.getNormalizedValue(pClass));
		assertThrows(NotNormalizableException.class, () -> pers1.getNormalizedValue(name));
		assertEquals(0.5,pers2.getNormalizedValue(sex));
		assertEquals(0.1818,pers4.getNormalizedValue(age),0.0001);
		assertEquals(0.6,pers3.getNormalizedValue(sibSp));
		assertEquals(0.2,pers3.getNormalizedValue(parch));
		assertThrows(NotNormalizableException.class, () -> pers3.getNormalizedValue(ticket));
		assertEquals(0.0421,pers3.getNormalizedValue(fare),0.0001);
		assertThrows(NotNormalizableException.class, () -> pers5.getNormalizedValue(cabin));
		assertEquals(0.666,pers4.getNormalizedValue(embarked),0.001);
	}
	
	
	@Test
	void test_titanic_point_for_opencsv() {
		TitanicPoint p = new TitanicPoint();
		assertEquals(null, p.getValue(passId));
		assertEquals(0.0, p.getValue(survived));
		assertEquals(0.0, p.getValue(pClass));
		assertEquals(null, p.getValue(name));
		assertEquals(null, p.getValue(sex));
		assertEquals(0.0, p.getValue(age));
		assertEquals(0.0, p.getValue(sibSp));
		assertEquals(0.0, p.getValue(parch));
		assertEquals(null, p.getValue(ticket));
		assertEquals(0.0, p.getValue(fare));
		assertEquals(null, p.getValue(cabin));
		assertEquals(null, p.getValue(embarked));
	}
	
	@Test
	void test_setValue() {
		assertEquals("3",pers1.getValue(passId));

		pers1.setValue(passId,"1.0");
		assertEquals("1.0",pers1.getValue(passId));
		
		assertEquals(0.0,pers1.getValue(survived));
		pers1.setValue(survived, 1.0);
		assertEquals(1.0,pers1.getValue(survived));
		
		assertEquals(3.0,pers1.getValue(pClass));
		pers1.setValue(pClass, 1.0);
		assertEquals(1.0,pers1.getValue(pClass));
		
		assertEquals("Braun",pers1.getValue(name));
		pers1.setValue(name, "Reiner");
		assertEquals("Reiner",pers1.getValue(name));
		
		assertEquals(EnumSex.MALE, pers1.getValue(sex));
		pers1.setValue(sex, EnumSex.FEMALE);
		assertEquals(EnumSex.FEMALE, pers1.getValue(sex));
		
		assertEquals(22.0,pers1.getValue(age));
		pers1.setValue(age, 18.0);
		assertEquals(18.0,pers1.getValue(age));
		
		assertEquals(1.0,pers1.getValue(sibSp));
		pers1.setValue(sibSp, 0.0);
		assertEquals(0.0,pers1.getValue(sibSp));
		
		assertEquals(0.0,pers1.getValue(parch));
		pers1.setValue(parch, 1.0);
		assertEquals(1.0,pers1.getValue(parch));
		
		assertEquals("4563221",pers1.getValue(ticket));
		pers1.setValue(ticket, "4655555");
		assertEquals("4655555",pers1.getValue(ticket));
		
		assertEquals(5.3,pers1.getValue(fare));
		pers1.setValue(fare, 2.1);
		assertEquals(2.1,pers1.getValue(fare));
		
		assertEquals("C54",pers1.getValue(cabin));
		pers1.setValue(cabin, "C66");
		assertEquals("C66",pers1.getValue(cabin));
		
		assertEquals(EnumEmbarked.S,pers1.getValue(embarked));
		pers1.setValue(embarked, EnumEmbarked.Q);
		assertEquals(EnumEmbarked.Q,pers1.getValue(embarked));
	}

}
