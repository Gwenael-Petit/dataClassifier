package fr.groupeh6.sae.dataset;

import java.util.List;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.columns.NumberColumn;
import fr.groupeh6.sae.columns.StringColumn;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

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
