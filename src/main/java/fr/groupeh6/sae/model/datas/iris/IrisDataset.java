package fr.groupeh6.sae.model.datas.iris;

import java.util.List;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

public class IrisDataset extends AbstractDataset {
	
	private static final String NAME = "Iris";
	
	public final List<AbstractColumn> columns = List.of(
			new NumberColumn("sepal.length"),
			new NumberColumn("sepal.width"),
			new NumberColumn("petal.length"),
			new NumberColumn("petal.width"),
			new EnumColumn<EnumVariety>("variety", EnumVariety.class)
			);
	
	public IrisDataset() {
		super(NAME);
		setColumns(columns);
	}

	@Override
	public IPoint getType() {
		return new IrisPoint();
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		return new DistanceEuclidienne(columns.subList(0, 4)).distance(p1, p2);
	}

}
