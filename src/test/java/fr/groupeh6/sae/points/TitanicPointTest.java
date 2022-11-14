package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.BooleanColumn;
import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.EnumColumn;
import fr.groupeh6.sae.columns.NotNormalizableException;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.columns.StringColumn;
import fr.groupeh6.sae.points.titanic.EnumEmbarked;
import fr.groupeh6.sae.points.titanic.EnumSex;

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
	EnumColumn<EnumEmbarked> embarked = new EnumColumn<EnumEmbarked>("Embarked",EnumEmbarked.class);
	
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
		assertEquals(30.0,pers2.getValue(age));
		assertEquals(5.0,pers4.getValue(sibSp));
		assertEquals(0.0,pers1.getValue(parch));
		assertEquals("PP 43224",pers3.getValue(ticket));
		assertEquals(16.7,pers5.getValue(fare));
		assertEquals("",pers4.getValue(cabin));
		assertEquals(EnumEmbarked.Q,pers2.getValue(embarked));
		Column fake = new BooleanColumn("fake");
		assertEquals(null,pers1.getValue(fake));
	}
	
	@Test
	void test_getNormlized() {
		assertThrows(NotNormalizableException.class, () -> pers1.getNormalizedValue(passId));
		assertEquals(1.0,pers4.getNormalizedValue(survived));
		assertEquals(0.5,pers3.getNormalizedValue(pClass));
		assertThrows(NotNormalizableException.class, () -> pers1.getNormalizedValue(name));
		assertEquals(0.0,pers2.getNormalizedValue(sex));
		assertEquals(0.1818,pers4.getNormalizedValue(age),0.0001);
		assertEquals(0.6,pers3.getNormalizedValue(sibSp));
		assertEquals(0.2,pers3.getNormalizedValue(parch));
		assertThrows(NotNormalizableException.class, () -> pers3.getNormalizedValue(ticket));
		assertEquals(0.0421,pers3.getNormalizedValue(fare),0.0001);
		assertThrows(NotNormalizableException.class, () -> pers5.getNormalizedValue(cabin));
		assertEquals(0.5,pers4.getNormalizedValue(embarked));
	}

}
