package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.Updatable;
import fr.groupeh6.sae.model.distance.Distance;

public class Resistance {
	
	protected AbstractDataset train;
	protected Classifier classifier;
	protected AbstractColumn columnClass;
	
	public Resistance(AbstractDataset train, Classifier classifier, AbstractColumn columnClass) {
		this.train = train;
		this.classifier = classifier;
		this.columnClass = columnClass;
	}
	
	public int groupDivison() {
		return 0; //datas.getNbLines()/
	}
	
	public double resistance() {
		AbstractDataset ds = Factory.getInstance().newDataset(train.getName());
		ds.setLines(train.getLines());
		
		
		List<List<IPoint>> groups = getSubsGroups();
		List<AbstractColumn> columns = train.getColumns();
		List<Double> rateOfGroupe = new ArrayList<Double>();
		
		
		for(int i = 0; i < groups.size(); i++) {
			List<IPoint> testGroup = groups.get(i);
			int rightClassified = 0;
			train.points.removeAll(testGroup);
			for(IPoint p: train.points) {
				for(AbstractColumn c: columns) {
					if(c.isUpdatable()) ((Updatable)c).update(p.getValue(c));
				}
			}
			
			for(IPoint p: testGroup) {
				Object pointClass = classifier.classifyPoint(p, columnClass, train.points);
				if(p.getValue(columnClass) == pointClass) rightClassified++;
			}
			
			rateOfGroupe.add(1.0*rightClassified/testGroup.size());
			train.points.addAll(testGroup);
			
		}
		double resistance = calculResistance(rateOfGroupe);
		return resistance;
	}
	
	private List<List<IPoint>> getSubsGroups() {
		int nbPointinGroup = groupDivison();
		int nbGroup = train.getNbLines()/nbPointinGroup;
		
		Collections.shuffle(train.points);
		
		List<List<IPoint>> groups = new ArrayList<List<IPoint>>();
		for(int i = 0; i < nbGroup; i++) {
			groups.add(new ArrayList<IPoint>(train.points.subList(i*nbPointinGroup, (i+1)*nbPointinGroup)));
		}
		return groups;
	}
	
	private double calculResistance(List<Double> rateOfGroupe) {
		double sum = 0;
		for(Double n : rateOfGroupe) sum += n;
		double resistance = sum/rateOfGroupe.size();
		return resistance;
	}
}
