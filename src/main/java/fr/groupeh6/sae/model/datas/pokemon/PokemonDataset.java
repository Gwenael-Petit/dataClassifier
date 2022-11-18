package fr.groupeh6.sae.model.datas.pokemon;

import java.util.List;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;

public class PokemonDataset extends Dataset {
	
	private static final String NAME = "Pokemon";
	public static final List<Column> COLUMNS = List.of(
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
			new NumberColumn("capture_rate"),
			new BooleanColumn("is_legendary")
			);

	public PokemonDataset() {
		super(NAME, COLUMNS);
	}

	@Override
	public IPoint getType() {
		return new PokemonPoint();
	}

	
}
