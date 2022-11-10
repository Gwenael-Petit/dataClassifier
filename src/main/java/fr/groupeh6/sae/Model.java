package fr.groupeh6.sae;

import java.util.ArrayList;
import java.util.List;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.utils.Subject;

public class Model extends Subject {
	
	private Dataset dataset;
	private List<Dataset> categories = new ArrayList<Dataset>();
	private Column xColumn;
	private Column yColumn;
	
	public void loadFromFile(String dataFile) {
		try {
			dataset = CSVLoader.load(dataFile);
			categories.add(dataset);
			notifyObservers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadFromString(String data) {
		
	}
	
	public void addCategory(Dataset category) {
		
	}
	
	public List<Dataset> allCategories() {
		return categories;
	}
	
	public int nbColumns() {
		return 0;
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public Column getxColumn() {
		return xColumn;
	}

	public void setxColumn(Column xColumn) {
		this.xColumn = xColumn;
		notifyObservers();
	}

	public Column getyColumn() {
		return yColumn;
	}

	public void setyColumn(Column yColumn) {
		this.yColumn = yColumn;
		notifyObservers();
	}
}
