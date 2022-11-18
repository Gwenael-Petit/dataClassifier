package fr.groupeh6.sae.model.datas.pokemon;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

public class PokemonPoint implements IPoint{
	
	@CsvBindByName(column = "name")
	private String name;
	@CsvBindByName(column = "attack")
	private double attack;
	@CsvBindByName(column = "base_egg_steps")
	private double baseEggSteps;
	@CsvBindByName(column = "capture_rate")
	private double captureRate;
	@CsvBindByName(column = "defense")
	private double defense;
	@CsvBindByName(column = "experience_growth")
	private double experienceGrowth;
	@CsvBindByName(column = "hp")
	private double hp;
	@CsvBindByName(column = "sp_attack")
	private double spAttack;
	@CsvBindByName(column = "sp_defense")
	private double spDefense;
	@CsvBindByName(column = "type1")
	private EnumType type1;
	@CsvBindByName(column = "type2")
	private EnumType type2;
	@CsvBindByName(column = "speed")
	private double speed;
	@CsvBindByName(column = "is_legendary")
	private boolean legendary;

	public PokemonPoint(String name, int attack, int baseEggSteps, double captureRate, int defense, int experienceGrowth,
			int hp, int spAttack, int spDefense, EnumType type1, EnumType type2, double speed, boolean legendary) {
		this.name = name;
		this.attack = attack;
		this.baseEggSteps = baseEggSteps;
		this.captureRate = captureRate;
		this.defense = defense;
		this.experienceGrowth = experienceGrowth;
		this.hp = hp;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.type1 = type1;
		this.type2 = type2;
		this.speed = speed;
		this.legendary = legendary;
	}
	public PokemonPoint(String name, int attack, int baseEggSteps, double captureRate, int defense, int experienceGrowth,
			int hp, int spAttack, int spDefense, EnumType type1, double speed, boolean legendary) {
		this.name = name;
		this.attack = attack;
		this.baseEggSteps = baseEggSteps;
		this.captureRate = captureRate;
		this.defense = defense;
		this.experienceGrowth = experienceGrowth;
		this.hp = hp;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.type1 = type1;
		this.type2 = null;
		this.speed = speed;
		this.legendary = legendary;
	}

	public PokemonPoint() {}

	@Override
	public String toString() {
		return "Pokemon [name=" + name + ", attack=" + attack + ", eggSteps=" + baseEggSteps + ", captureRate="
				+ captureRate + ", defense=" + defense + ", experience=" + experienceGrowth + ", hp=" + hp + ", spAttack="
				+ spAttack + ", spDefense=" + spDefense + ", type1=" + type1 + ", type2=" + type2 + ", speed=" + speed
				+ ", legendary=" + legendary + "]";
	}
	
	

	@Override
	public Object getValue(Column col) {
		switch(col.getName()) {
		case "name" :  return name;
		case "attack" : return attack;
		case "base_egg_steps" : return baseEggSteps;
		case "capture_rate" : return captureRate;
		case "defense" : return defense;
		case "experience_growth" : return experienceGrowth;
		case "hp" : return hp;
		case "sp_attack" : return spAttack;
		case "sp_defense" : return spDefense;
		case "type1" : return type1;
		case "type2" : return type2;
		case "speed" : return speed;
		case "is_legendary" : return legendary;
		default : return null;
		}
	}

	@Override
	public double getNormalizedValue(Column col) {
		return col.getNormalizedValue(this);
	}
	
	@Override
	public double distanceTo(IPoint other) {
		List<Column> toDist = new ArrayList<>();
		String[] cols = new String[] {"type2","type1","experience_growth","capture_rate","base_egg_steps"};
		for(String c : cols) toDist.add(Factory.getInstance().getColumn(c));
		return new DistanceEuclidienne().distance(this,other,toDist);
	}

	@Override
	public void setValue(Column col, Object o) {
	switch(col.getName()) {
	case "name" -> this.name = (String) o ;
	case "attack" -> this.attack = (double) o;
	case "base_egg_steps" -> this.baseEggSteps = (double) o;
	case "capture_rate" -> this.captureRate = (double) o;
	case "defense" -> this.defense = (double) o;
	case "experience_growth" -> this.experienceGrowth = (double) o;
	case "hp" -> this.hp = (double) o;
	case "sp_attack" -> this.spAttack = (double) o;
	case "sp_defense" -> this.spDefense = (double) o;
	case "type1" -> this.type1 = (EnumType) o;
	case "type2" -> this.type2 = (EnumType) o;
	case "speed" -> this.speed = (double) o;
	case "is_legendary" -> this.legendary = (boolean) o;
		}
	}
}
