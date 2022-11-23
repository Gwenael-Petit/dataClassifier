package fr.groupeh6.sae.model.classifier;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class RandomClassifierTest {
	
	IPoint p1 = new IrisPoint(5.2, 2.3, 1.4, 3.2, EnumVariety.SETOSA);
	IPoint p2 = new IrisPoint(2.2, 1.1, 3.3, 4.4, EnumVariety.SETOSA);
	IPoint p3 = new IrisPoint(1.7, 5.6, 3.2, 1.8, EnumVariety.VIRGINICA);
	IPoint p4 = new IrisPoint(2.5, 3.6, 4.1, 2.3, EnumVariety.VIRGINICA);
	IPoint p5 = new IrisPoint(7.1, 6.5, 2.3, 2.6, EnumVariety.VIRGINICA);
	IPoint p6 = new IrisPoint(6.5, 3.5, 4.5, 3.9, EnumVariety.VERSICOLOR);
	
	IPoint p = new IrisPoint(2, 3, 2, 3, null);
	
	AbstractDataset dataset = new IrisDataset();
	List<EnumVariety> varieties = new ArrayList<>();
	
	
	AbstractColumn spL, spW, ptL, ptW, variety;
	
	@BeforeEach
	void setup() {
		dataset = new IrisDataset();
		spL = dataset.getColumns().get(0);
		spW = dataset.getColumns().get(1);
		ptL = dataset.getColumns().get(2);
		ptW = dataset.getColumns().get(3);
		variety = dataset.getColumns().get(4);
		dataset.addAllLine(List.of(p1, p2, p3, p4, p5, p6));
		
		
		for(EnumVariety e: EnumVariety.values()) {
			varieties.add(e);
		}
	}

	@Test
	void testRandomClassifier() {
		
		RandomClassifier rClassifier = new RandomClassifier();
		assertTrue(rClassifier.classifyPoint(p, variety, dataset.getLines()) != null);
		assertTrue(varieties.contains(rClassifier.classifyPoint(p, variety, dataset.getLines())));
	}

}
