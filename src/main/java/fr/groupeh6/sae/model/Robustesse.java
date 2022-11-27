package fr.groupeh6.sae.model;

import java.util.Collections;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class Robustesse {
	
	protected static int NUMBER_GROUPS = 5;
	
	protected AbstractDataset train;
	protected Classifier classifier;
	protected AbstractColumn columnClass;
	
	public Robustesse(AbstractDataset train, Classifier classifier, AbstractColumn columnClass) {
		this.train = Factory.getInstance().newDataset(train.columns);
		this.train.setLines(train.getLines());
		this.classifier = classifier;
		this.columnClass = columnClass;
	}
	
	public double robustesse() {
		AbstractDataset[] groups = makeGroups();
		addDatasInGroups(groups);
		double[] ratePerGroups = calculRatePerGroup(groups);
		return calculRobustesse(ratePerGroups);
	}
	
	public AbstractDataset[] makeGroups() {
		AbstractDataset[] groups = new AbstractDataset[NUMBER_GROUPS];
		for(int gi=0; gi<NUMBER_GROUPS; gi++) {
			groups[gi] = Factory.getInstance().newDataset(train.columns);
		}
		return groups;
	}
	
	public void addDatasInGroups(AbstractDataset[] groups) {
		Collections.shuffle(train.getLines());
		int i=0;
		for(IPoint point : train.getLines()) {
			groups[i].addLine(point);
			i = (i+1)%NUMBER_GROUPS;
		}
	}
	
	public double[] calculRatePerGroup(AbstractDataset[] groups) {
		double[] rates = new double[groups.length];
		for(int i=0; i<groups.length; i++) {
			rates[i] = rateOfGroup(groups, i);
		}
		return rates;
	}
	
	public double rateOfGroup(AbstractDataset[] groups, int index) {
		AbstractDataset others = Factory.getInstance().newDataset(train.columns);
		for(int i=0; i<groups.length; i++) {
			if(i != index) others.addAllLine(groups[i].getLines());
		}
		return rate(groups[index], others);
	}
	
	public double rate(AbstractDataset group, AbstractDataset others) {
		int wellPlace = 0;
		for(IPoint point : group) {
			String baseValue = String.valueOf(point.getValue(columnClass));
			String res = String.valueOf(classifier.classifyPoint(point, columnClass, others.getLines()));
			if(baseValue.equals(res)) wellPlace++;
		}
		return 1.0*wellPlace/group.getNbLines();
	}
	
	public double calculRobustesse(double[] rateOfGroupe) {
		double sum = 0;
		for(double n : rateOfGroupe) {
			sum += n;
		}
		return 1.0*sum/rateOfGroupe.length;
	}
}