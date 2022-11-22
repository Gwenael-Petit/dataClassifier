package fr.groupeh6.sae.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Subject;

public class MainModel extends Subject {
	
	private Dataset train;
	private List<Dataset> categories = new ArrayList<Dataset>();
	private Column xColumn;
	private Column yColumn;
	
	private Classifier classifier;
	private Column classClassifier;
	
	public void loadFromFile(String dataFile, char delimiter, boolean toTrain) throws NotSameTypeException, IOException, TypeNotRegisteredException {
		Dataset loaded = CSVLoader.load(dataFile, delimiter);
		if(!haveTrainDatasLoaded()) {
			train = loaded;
			createCategories();
			notifyObservers(loaded);
		} else {
			if(!loaded.getName().equals(train.getName())) throw new NotSameTypeException();
			if(toTrain) train.addAllLine(loaded.getLines());
			else loaded.getLines().forEach(l -> addPoint(l));
			notifyObservers();
		}
	}
	
	public void loadFromString(String line, char delimiter) {
		
	}
	
	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
		List<IPoint> points = new ArrayList<>();
		for(Dataset category : categories) {
			category.forEach(point -> points.add(point)); 
		}
		resetCategories();
		createCategories();
		for(IPoint point : points) addPoint(point);
		notifyObservers();
	}
	
	public void setClassClassifier(Column classClassifier) {
		this.classClassifier = classClassifier;
	}
	
	public void addPoint(IPoint point) {
		if(haveClassifier()) {
			Object clazz = classifier.classifyPoint(point, classClassifier, train.getLines());
			Dataset categorie = getCategory(""+clazz);
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
		Dataset set = Factory.getInstance().newDataset(train.columns);
		set.setName(name);
		addCategory(set);
	}
	
	public Dataset getCategory(String name) {
		for(Dataset category : allCategories()) 
			if(category.getName().equals(name)) return category;
		return null;
	}
	
	public void addCategory(Dataset category) {
		categories.add(category);
	}
	
	public void resetCategories() {
		categories.clear();
	}
	
	public List<Dataset> allCategories() {
		return categories;
	}
	
	public int nbColumns() {
		return train.getColumns().size();
	}
	
	public void setxColumn(Column xColumn) {
		this.xColumn = xColumn;
		notifyObservers();
	}

	public void setyColumn(Column yColumn) {
		this.yColumn = yColumn;
		notifyObservers();
	}
	
	public boolean haveClassifier() {
		return classifier != null && classClassifier != null;
	}
	
	public boolean haveTrainDatasLoaded() {
		return train != null;
	}
	
	public Dataset getTrainDataset() {
		return this.train;
	}

	public Column getxColumn() {
		return xColumn;
	}

	public Column getyColumn() {
		return yColumn;
	}
}
