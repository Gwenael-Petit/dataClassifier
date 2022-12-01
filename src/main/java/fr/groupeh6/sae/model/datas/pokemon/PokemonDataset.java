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
	
	private AbstractColumn name = new StringColumn("name");
	private AbstractColumn attack = new NumberColumn("attack");
	private AbstractColumn eggSteps = new NumberColumn("base_egg_steps");
	private AbstractColumn captureRate = new NumberColumn("capture_rate");
	private AbstractColumn defense = new NumberColumn("defense");
	private AbstractColumn expGrowth = new NumberColumn("experience_growth");
	private AbstractColumn hp = new NumberColumn("hp");
	private AbstractColumn spAttack = new NumberColumn("sp_attack");
	private AbstractColumn spDefense = new NumberColumn("sp_defense");
	private AbstractColumn type1 = new EnumColumn<EnumType>("type1", EnumType.class);
	private AbstractColumn type2 = new EnumColumn<EnumType>("type2", EnumType.class);
	private AbstractColumn speed = new NumberColumn("speed");
	private AbstractColumn legendary = new BooleanColumn("is_legendary");

	public PokemonDataset() {
		super(NAME);
		setColumns(List.of(name, attack, eggSteps, captureRate, defense, expGrowth, hp, spAttack, spDefense, type1, type2, speed, legendary));
	}

	@Override
	public IPoint getType() {
		return new PokemonPoint();
	}
	
	@Override
	public List<AbstractColumn> getColumnsDistance() {
		return columns.subList(1, 12);
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		return new DistanceEuclidienne(columns.subList(1, 12)).distance(p1, p2);
	}

	@Override
	public String name() {
		return DistanceEuclidienne.NAME;
	}
	
}
