package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.titanic.EnumEmbarked;
import fr.groupeh6.sae.model.datas.titanic.EnumSex;
import fr.groupeh6.sae.model.datas.titanic.TitanicDataset;
import fr.groupeh6.sae.model.datas.titanic.TitanicPoint;

class TitanicDatasetTest {
	IPoint pers1 = new TitanicPoint("3", 0, 3, "Braun", EnumSex.MALE, 22, 1, 0, "4563221", 5.3, "C54", EnumEmbarked.S);
	IPoint pers2 = new TitanicPoint("12", 1, 1, "Moran", EnumSex.FEMALE, 30, 0, 5, "A/3 5456", 70.567, "", EnumEmbarked.Q);
	IPoint pers3 = new TitanicPoint("6", 0, 2, "Allen", EnumSex.MALE, 4, 3, 1, "PP 43224", 8.05, "G7", EnumEmbarked.S);
	AbstractDataset titanicDs;
	
	@BeforeEach
	void setup() {
		titanicDs = new TitanicDataset();
		titanicDs.addAllLine(List.of(pers1, pers2, pers3));
	}
	
	@Test
	void get_type_test() {
		assertTrue(titanicDs.getType() instanceof TitanicPoint);
	}
	
	@Test
	void test_distance() {
		assertEquals(2.0235,titanicDs.distance(pers1,pers2),0.0001);
		assertEquals(1.9723,titanicDs.distance(pers2,pers3),0.0001);
		assertEquals(0.877,titanicDs.distance(pers1,pers3),0.001);
	}
	
	@Test
	void test_getColumnDistance() {
		List<AbstractColumn> listeDist = new ArrayList<AbstractColumn>();
		listeDist.add(new NumberColumn("Pclass")); 
		listeDist.add(new EnumColumn<EnumSex>("Sex", EnumSex.class));
		listeDist.add(new NumberColumn("Age"));
		listeDist.add(new NumberColumn("Parch"));
		listeDist.add(new EnumColumn<EnumEmbarked>("Embarked", EnumEmbarked.class));
		assertEquals(listeDist, titanicDs.getColumnsDistance());
	}
	
	@Test
	void test_Name() {
		assertEquals("Euclidienne", titanicDs.getDistanceName());
	}

}
