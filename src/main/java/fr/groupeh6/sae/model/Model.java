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
	
	public void loadFromFile(String dataFile, char delimiter) throws Exception {
		if(!haveTrainDatasLoaded()) {
			train = CSVLoader.load(dataFile, delimiter);
			notifyObservers();
		} else {
			CSVLoader.load(dataFile, delimiter).getLines();
		}
	}
	
	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
		List<IPoint> points = new ArrayList<>();
		// On récupère nos données actuelles
		for(Dataset category : allCategories()) {
			category.forEach(point -> points.add(point)); 
		}
		//creer 
		//classifier les datas
	}
	
	public void addPoint(IPoint point) {
		
	}
	
	public void addCategory(String name) {
		Dataset set = Factory.getInstance().getDataset(train.columns);
		set.setName(name);
		addCategory(set);
	}
	
	public void addCategory(Dataset category) {
		categories.add(category);
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
		return classifier != null;
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
