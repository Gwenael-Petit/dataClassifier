package fr.groupeh6.sae.model.datas.pokemon;

import java.util.List;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

public class PokemonDataset extends AbstractDataset {
	
	private static final String NAME = "Pokemon";
	public final List<AbstractColumn> columns = List.of(
			new StringColumn("name"),
			new NumberColumn("attack"),
			new NumberColumn("base_egg_steps"),
			new NumberColumn("capture_rate"),
			new NumberColumn("defense"),
			new NumberColumn("experience_growth"),
			new NumberColumn("hp"),
			new NumberColumn("sp_attack"),
			new NumberColumn("sp_defense"),
			new EnumColumn<EnumType>("type1", EnumType.class),
			new EnumColumn<EnumType>("type2", EnumType.class),
			new NumberColumn("speed"),
			new BooleanColumn("is_legendary")
			);

	public PokemonDataset() {
		super(NAME);
		setColumns(columns);
	}

	@Override
	public IPoint getType() {
		return new PokemonPoint();
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		return new DistanceEuclidienne(columns.subList(1, 12)).distance(p1, p2);
	}

	
}
