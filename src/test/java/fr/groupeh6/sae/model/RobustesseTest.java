package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.classifier.KnnClassifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

class RobustesseTest {

	AbstractDataset dataset = new IrisDataset();
	AbstractColumn spL, spW, ptL, ptW, variety;
	IPoint point1 = new IrisPoint(5.1, 3.5, 1.4, .2, EnumVariety.SETOSA);
	IPoint point2 = new IrisPoint(2.6, 3.5, 2.0, .4, EnumVariety.SETOSA);
	IPoint point3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, EnumVariety.VIRGINICA);
	IPoint point4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, EnumVariety.VIRGINICA);
	IPoint point5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, EnumVariety.VIRGINICA);
	IPoint point6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, EnumVariety.VERSICOLOR);
	IPoint point7 = new IrisPoint(4.1, 2.5, 1.6, .3, EnumVariety.VIRGINICA);
	IPoint point8 = new IrisPoint(3.6, 2.5, 2.1, .5, EnumVariety.VERSICOLOR);
	IPoint point9 = new IrisPoint(2.7, 6.6, 3.3, 1.7, EnumVariety.SETOSA);
	IPoint point10 = new IrisPoint(1.5, 4.6, 4.0, 2.1, EnumVariety.VERSICOLOR);
	IPoint point11 = new IrisPoint(8.1, 7.5, 2.2, 2.4, EnumVariety.VIRGINICA);
	IPoint point12 = new IrisPoint(7.5, 4.5, 4.6, 3.8, EnumVariety.SETOSA);
	Classifier classifier;
	Robustesse robusPtL;
	
	@BeforeEach
	void setup() {
		dataset = new IrisDataset();
		spL = dataset.getColumns().get(0);
		spW = dataset.getColumns().get(1);
		ptL = dataset.getColumns().get(2);
		ptW = dataset.getColumns().get(3);
		variety = dataset.getColumns().get(4);
		dataset.addAllLine(List.of(point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12));
		classifier = new KnnClassifier(1, new DistanceEuclidienne(List.of(spL,spW,ptL,ptW)));
		robusPtL = new Robustesse(dataset, classifier, ptL);
	}
	
	@Test
	void test_makeGroups() {
		AbstractDataset[] res = new AbstractDataset[Robustesse.NUMBER_GROUPS];
		for(int i = 0; i < Robustesse.NUMBER_GROUPS; i++) {
			res[i] = Factory.getInstance().newDataset(dataset.columns);
		}
		assertEquals(res.length, robusPtL.makeGroups().length);
	}
	
	@Test
	void test_addDatasInGroups() {
		AbstractDataset[] groups = robusPtL.makeGroups();
		assertEquals(0, groups[0].getNbLines());
		robusPtL.addDatasInGroups(groups);
		assertEquals(3,groups[0].getNbLines());
		assertEquals(3,groups[1].getNbLines());
		assertEquals(2,groups[2].getNbLines());
		assertEquals(2,groups[3].getNbLines());
		assertEquals(2,groups[4].getNbLines());
	}
	
	@Test
	void test_rate() {
		AbstractDataset[] groups = robusPtL.makeGroups();
		robusPtL.addDatasInGroups(groups);
		assertEquals(1.0, robusPtL.rate(groups[0], groups[0]));
		assertEquals(0.0, robusPtL.rate(groups[0], groups[1]));
	}

	@Test
	void test_rateOfGroup() {
		AbstractDataset[] groups = robusPtL.makeGroups();
		robusPtL.addDatasInGroups(groups);
		assertEquals(0.0,robusPtL.rateOfGroup(groups, 4));
	}
	
	@Test
	void test_calculRatePerGroup() {
		AbstractDataset[] groups = robusPtL.makeGroups();
		robusPtL.addDatasInGroups(groups);
		//double[] ratePerGroup  = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};
		assertEquals(5, robusPtL.calculRatePerGroup(groups).length);
		//assertEquals(ratePerGroup, robusPtL.calculRatePerGroup(groups));
	}
	
	@Test
	void test_calculRobustesse() {
		AbstractDataset[] groups = robusPtL.makeGroups();
		robusPtL.addDatasInGroups(groups);
		assertEquals(0.0, robusPtL.calculRobustesse(robusPtL.calculRatePerGroup(groups)));
	}
	
	@Test
	void test_robustesse() {
		assertEquals(0.0, robusPtL.robustesse());
	}
}
