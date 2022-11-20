package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Subject;

public class Model extends Subject {
	
	private Dataset train;
	private List<Dataset> categories = new ArrayList<Dataset>();
	private Column xColumn;
	private Column yColumn;
	
	private Classifier classifier;
	private Column classClassifier;
	
	public void loadFromFile(String dataFile, char delimiter, boolean toTrain) throws Exception {
		Dataset loaded = CSVLoader.load(dataFile, delimiter);
		if(toTrain) {
			if(!haveTrainDatasLoaded()) {
				train = loaded;
				createCategories();
				notifyObservers(train);
			} else {
				train.addAllLine(loaded.getLines());
			}
		} else {
			loaded.getLines().forEach(l -> addPoint(l));
			notifyObservers();
		}
	}
	
	public void setClassifier(Classifier classifier, Column classClassifier) {
		this.classifier = classifier;
		this.classClassifier = classClassifier;
		List<IPoint> points = new ArrayList<>();
		for(Dataset category : allCategories()) {
			category.forEach(point -> points.add(point)); 
		}
		resetCategories();
		createCategories();
		for(IPoint point : points) addPoint(point);
	}
	
	public void addPoint(IPoint point) {
		if(haveClassifier()) {
			Object clazz = classifier.classifyPoint(point, classClassifier, train.getLines(), train.columns);
			Dataset categorie = getCategory((String)clazz);
			point.setValue(classClassifier, clazz);
			categorie.addLine(point);
		} else {
			this.categories.get(0).addLine(point);
		}
	}
	
	public void createCategories() {
		if(haveClassifier()) classClassifier.getDistinctValues().forEach(v -> addCategory(v));
		else addCategory(train.getName());
	}
	
	public void addCategory(String name) {
		Dataset set = Factory.getInstance().getDataset(train.columns);
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
	
	public void setClassClassifier(Column classClassifier) {
		this.classClassifier = classClassifier;
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
