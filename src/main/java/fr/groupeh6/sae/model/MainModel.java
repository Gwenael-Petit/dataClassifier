package fr.groupeh6.sae.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.utils.AbstractSubject;

public class MainModel extends AbstractSubject {
	
	private AbstractDataset train;
	private List<AbstractDataset> categories = new ArrayList<AbstractDataset>();
	private AbstractColumn xColumn;
	private AbstractColumn yColumn;
	
	private Classifier classifier;
	private AbstractColumn classClassifier;
	
	public void loadFromFile(String dataFile, char delimiter, boolean toTrain) throws NotSameTypeException, IOException, TypeNotRegisteredException {
		AbstractDataset loaded = CSVLoader.load(dataFile, delimiter);
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
		for(AbstractDataset category : categories) {
			category.forEach(point -> points.add(point)); 
		}
		resetCategories();
		createCategories();
		for(IPoint point : points) addPoint(point);
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
			if(category.getName().equals(name)) return category;
		return null;
	}
	
	public void addCategory(AbstractDataset category) {
		categories.add(category);
	}
	
	public void resetCategories() {
		categories.clear();
	}
	
	public List<AbstractDataset> allCategories() {
		return categories;
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
