package fr.groupeh6.sae.dataset.iris;

import java.util.Arrays;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.columns.StringColumn;
import fr.groupeh6.sae.dataset.DataSet;

public class IrisDataSet extends DataSet {
	
	private static final String NAME = "Iris";
	private static final Column[] COLUMNS = new Column[] {
			new NumberColumn("sepal.length"),
			new NumberColumn("sepal.width"),
			new NumberColumn("petal.length"),
			new NumberColumn("petal.width"),
			new StringColumn("variety")
	};
	
	public IrisDataSet() {
		super(NAME, Arrays.asList(COLUMNS));
	}

}
