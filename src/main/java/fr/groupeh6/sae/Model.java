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

}
