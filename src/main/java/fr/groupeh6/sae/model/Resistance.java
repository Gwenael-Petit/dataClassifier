package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.Updatable;
import fr.groupeh6.sae.model.distance.Distance;

public class Resistance {
	
	Dataset datas;
	Classifier classifier;
	
	public Resistance(Dataset datas, Classifier classifier) {
		this.datas = datas;
		this.classifier = classifier;
	}
	
	int groupDivison() {
		return 0; //datas.getNbLines()/
	}
	
	private List<List<IPoint>> getSubsGroups() {
		int nbPointinGroup = groupDivison();
		int nbGroup = datas.getNbLines()/nbPointinGroup;
		
		Collections.shuffle(datas.points);
		
		List<List<IPoint>> groups = new ArrayList<List<IPoint>>();
		for(int i = 0; i < nbGroup; i++) {
			groups.add(new ArrayList<IPoint>(datas.points.subList(i*nbPointinGroup, (i+1)*nbPointinGroup)));
		}
		return groups;
	}
	
	private double calculResistance(List<Double> rateOfGroupe) {
		double sum = 0;
		for(Double n : rateOfGroupe) sum += n;
		double resistance = sum/rateOfGroupe.size();
		return resistance;
	}
	
	public double resistance(Distance distance, Column columnClass) {
		List<List<IPoint>> groups = getSubsGroups();
		List<Column> columns = datas.getColumns();
		List<Double> rateOfGroupe = new ArrayList<Double>();
		
		
		for(int i = 0; i < groups.size(); i++) {
			List<IPoint> testGroup = groups.get(i);
			int rightClassified = 0;
			datas.points.removeAll(testGroup);
			for(IPoint p: datas.points) {
				for(Column c: columns) {
					if(c.isUpdatable()) ((Updatable)c).update(p.getValue(c));
				}
			}
			
			for(IPoint p: testGroup) {
				Object pointClass = classifier.classifyPoint(p, columnClass, datas.points, columns);
				if(p.getValue(columnClass) == pointClass) rightClassified++;
			}
			
			rateOfGroupe.add(1.0*rightClassified/testGroup.size());
			datas.points.addAll(testGroup);
			
		}
		double resistance = calculResistance(rateOfGroupe);
		return resistance;

	}

	
	
	public double resistance(Column columnClass) {
		return resistance(null,columnClass);
	}
}
