package fr.groupeh6.sae.model;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.model.classifier.Classifier;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.utils.Subject;

public class Model extends Subject {
	
	private Dataset dataset;
	private List<Dataset> categories = new ArrayList<Dataset>();
	private Column xColumn;
	private Column yColumn;
	
	public void loadFromFile(String dataFile, char delimiter) {
		if(!haveDatasetLoaded()) {
			try {
				dataset = CSVLoader.load(dataFile, delimiter);
				addCategory(dataset);
				notifyObservers(dataset);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void loadFromString(String data) {
		
	}
	
	public void classify(Classifier classifier) {
		
	}
	
	public void addCategory(Dataset category) {
		categories.add(category);
	}
	
	public List<Dataset> allCategories() {
		return categories;
	}
	
	public int nbColumns() {
		return dataset.getColumns().size();
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public Column getxColumn() {
		return xColumn;
	}

	public Column getyColumn() {
		return yColumn;
	}
	
	public void setxColumn(Column xColumn) {
		this.xColumn = xColumn;
		notifyObservers();
	}

	public void setyColumn(Column yColumn) {
		this.yColumn = yColumn;
		notifyObservers();
	}
	
	public boolean haveDatasetLoaded() {
		return dataset != null;
	}
}
