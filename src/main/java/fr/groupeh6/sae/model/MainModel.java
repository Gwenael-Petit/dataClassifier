package fr.groupeh6.sae.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.distance.Distance;
import fr.groupeh6.sae.model.utils.AbstractSubject;

public class MainModel extends AbstractSubject {
	
	private AbstractDataset train;
	protected List<AbstractDataset> categories = new ArrayList<AbstractDataset>();
	private AbstractColumn xColumn;
	private AbstractColumn yColumn;
	protected AbstractColumn classClassifier;
	protected Classifier classifier;
	
	protected Distance distance;
	
	protected double robustesse = 0.0;
	
	public void loadFromFile(String dataFile, char delimiter, boolean toTrain) throws NotSameTypeException, IOException, TypeNotRegisteredException {
		AbstractDataset loaded = CSVLoader.load(dataFile, delimiter);
		if(!haveTrainDatasLoaded()) {
			train = loaded;
			createCategories();
			notifyObservers(loaded);
		} else {
			if(!loaded.getName().equals(train.getName())) throw new NotSameTypeException();
			if(toTrain) train.addAllLine(loaded.getLines());
			else loaded.forEach(l -> addPoint(l));
			notifyObservers();
		}
	}
	
	public void loadFromString(String line, char delimiter) throws IOException, TypeNotRegisteredException {
		String columns = train.columns.get(0).getName();
		for(int i=1; i<train.columns.size(); i++) columns += delimiter + train.columns.get(i).getName();
		columns += "\n";
		AbstractDataset loaded = CSVLoader.loadFromReader(new BufferedReader(new StringReader(columns + line)), delimiter);
		loaded.forEach(l -> addPoint(l));
		notifyObservers();
	}
	
	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
		List<IPoint> points = new ArrayList<>();
		for(AbstractDataset category : categories) {
			category.forEach(point -> points.add(point)); 
		}
		categories.clear();
		createCategories();
		for(IPoint point : points) addPoint(point);
		robustesse();
		notifyObservers();
	}
	
	public void setClassClassifier(AbstractColumn classClassifier) {
		this.classClassifier = classClassifier;
	}
	
	public void addPoint(IPoint point) {
		if(haveClassifier()) {
			Object clazz = classifier.classifyPoint(point, classClassifier, train.getLines());
			AbstractDataset categorie = getCategory(""+clazz);
			point.setValue(classClassifier, clazz);
			categorie.addLine(point);
		} else {
			this.categories.get(0).addLine(point);
		}
	}
	
	public void createCategories() {
		if(haveClassifier()) {
			classClassifier.getDistinctValues().forEach(v -> addCategory(v));
		}
		else addCategory(train.getName());
	}
	
	public void addCategory(String name) {
		AbstractDataset set = Factory.getInstance().newDataset(train.columns);
		set.setName(name);
		addCategory(set);
	}
	
	public AbstractDataset getCategory(String name) {
		for(AbstractDataset category : allCategories()) 
			if(category.getName().equalsIgnoreCase(name)) return category;
		return null;
	}
	
	public void addCategory(AbstractDataset category) {
		categories.add(category);
	}
	
	public List<AbstractDataset> allCategories() {
		return categories;
	}
	
	public void robustesse() {
		if(haveClassifier()) {
			this.robustesse = train.robustesse(classifier, classClassifier);
		}
		//notifyObservers();
	}
	
	public double getRobustesse() {
		return robustesse;
	}
	
	public int nbColumns() {
		return train.getColumns().size();
	}
	
	public void setxColumn(AbstractColumn xColumn) {
		this.xColumn = xColumn;
		notifyObservers();
	}

	public void setyColumn(AbstractColumn yColumn) {
		this.yColumn = yColumn;
		notifyObservers();
	}
	
	public boolean haveClassifier() {
		return classifier != null && classClassifier != null;
	}
	
	public boolean haveTrainDatasLoaded() {
		return train != null;
	}
	
	public AbstractDataset getTrainDataset() {
		return this.train;
	}

	public AbstractColumn getxColumn() {
		return xColumn;
	}

	public AbstractColumn getyColumn() {
		return yColumn;
	}
}
