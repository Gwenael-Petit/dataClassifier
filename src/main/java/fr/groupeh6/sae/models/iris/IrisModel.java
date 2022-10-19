package fr.groupeh6.sae.models.iris;

import java.util.Collection;
import java.util.List;

import fr.groupeh6.sae.ICategory;
import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.dataset.iris.IrisDataSet;
import fr.groupeh6.sae.models.IMVCModel;

public class IrisModel extends IrisDataSet implements IMVCModel {

	public void loadFromFile(String datafile) {
		// TODO Auto-generated method stub
	}

	public void loadFromString(String data) {
		// TODO Auto-generated method stub
		
	}

	public IColumn defaultXCol() {
		return null;
	}

	public IColumn defaultYCol() {
		return null;
	}

	public void addCategory(ICategory classe) {
		
	}

	public Collection<ICategory> allCategories() {
		return null;
	}

	public int nbColumns() {
		return 0;
	}

	public List<IColumn> getNormalizableColumns() {
		return null;
	}

	

}
