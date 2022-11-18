package fr.groupeh6.sae.model.datas.iris;

import java.util.List;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;

public class IrisDataset extends Dataset {
	
	private static final String NAME = "Iris";
	public static final List<Column> COLUMNS = List.of(
			new NumberColumn("sepal.length"),
			new NumberColumn("sepal.width"),
			new NumberColumn("petal.length"),
			new NumberColumn("petal.width"),
			new EnumColumn<EnumVariety>("variety", EnumVariety.class)
			);
	
	public IrisDataset() {
		super(NAME, COLUMNS);
	}

	@Override
	public IPoint getType() {
		return new IrisPoint();
	}

}
