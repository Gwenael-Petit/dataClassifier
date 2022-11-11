package fr.groupeh6.sae.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.columns.BooleanColumn;
import fr.groupeh6.sae.columns.EnumColumn;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.columns.StringColumn;

class TitanicPointTest {
	enum EnumTest {
		S,Q,C
	}

	TitanicPoint pers1 = new TitanicPoint(3, 0, 3, "Braun", "male", 22, 1, 0, "4563221", 5.3, "C54", 'S');
	TitanicPoint pers2 = new TitanicPoint(12, 1, 1, "Moran", "female", 30, 0, 5, "A/3 5456", 70.567, "", 'Q');
	TitanicPoint pers3 = new TitanicPoint(6, 0, 2, "Allen", "male", 4, 3, 1, "PP 43224", 8.05, "G7", 'S');
	TitanicPoint pers4 = new TitanicPoint(78, 1, 3, "Johnson", "male", 14, 5, 0, "D446G2", 29.34, "", 'C');
	TitanicPoint pers5 = new TitanicPoint(100, 0, 1, "Anderson", "male", 59, 0, 0, "364234", 16.7, "", 'Q');

	NumberColumn passId = new NumberColumn("PassengerId");
	BooleanColumn survived = new BooleanColumn("Survived");
	NumberColumn pClass = new NumberColumn("Pclass");
	StringColumn name = new StringColumn("Name");
	StringColumn sex = new StringColumn("Sex");
	NumberColumn age = new NumberColumn("Age");
	NumberColumn sibSp = new NumberColumn("SibSp");
	NumberColumn parch = new NumberColumn("Parch");
	StringColumn ticket = new StringColumn("Ticket");
	NumberColumn fare = new NumberColumn("Fare");
	StringColumn cabin = new StringColumn("Cabin");
	EnumColumn<EnumTest> embarked = new EnumColumn<>("Embarked",EnumTest.class);
	
	
	@Test
	void test_toString() {
		assertEquals("TitanicPoint [passengerId=3, survived=0, placeClass=3, name=Braun, sex=male, age=22, sibSp=1, parch=0, ticket=4563221, fare=5.3, cabin=C54, embarked=S]",pers1.toString());
	}
	
	@Test
	void test_getValue() {
		assertEquals(3,pers1.getValue(passId));
		assertEquals(0,pers3.getValue(survived));
		assertEquals(3,pers4.getValue(pClass));
		assertEquals("Braun",pers1.getValue(name));
		assertEquals(30,pers2.getValue(age));
		assertEquals(5,pers4.getValue(sibSp));
		assertEquals(0,pers1.getValue(parch));
		assertEquals("PP 43224",pers3.getValue(ticket));
		assertEquals(16.7,pers5.getValue(fare));
		assertEquals("",pers4.getValue(cabin));
		assertEquals('Q',pers2.getValue(embarked));
	}
	
	@Test
	void test_getNormalized() {
		
	}

}
