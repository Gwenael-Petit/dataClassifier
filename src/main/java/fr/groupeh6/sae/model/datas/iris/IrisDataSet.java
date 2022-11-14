package fr.groupeh6.sae.model.datas.iris;

import java.util.List;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;

public class IrisDataSet extends Dataset {
	
	private static final String NAME = "Iris";
	public static final List<Column> COLUMNS = List.of(
			new NumberColumn("sepal.length"),
			new NumberColumn("sepal.width"),
			new NumberColumn("petal.length"),
			new NumberColumn("petal.width"),
			new StringColumn("variety")
			);
	
	public IrisDataSet() {
		super(NAME, COLUMNS);
	}

	@Override
	public IPoint getType() {
		return new IrisPoint();
	}

}
