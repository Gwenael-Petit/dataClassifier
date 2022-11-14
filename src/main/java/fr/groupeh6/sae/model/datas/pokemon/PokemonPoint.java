package fr.groupeh6.sae.model.datas.pokemon;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;

public class PokemonPoint implements IPoint{
	
	@CsvBindByName(column = "name")
	private String name;
	@CsvBindByName(column = "attack")
	private int attack;
	@CsvBindByName(column = "base_egg_steps")
	private int baseEggSteps;
	@CsvBindByName(column = "capture_rate")
	private double captureRate;
	@CsvBindByName(column = "defense")
	private int defense;
	@CsvBindByName(column = "experience_growth")
	private int experienceGrowth;
	@CsvBindByName(column = "hp")
	private int hp;
	@CsvBindByName(column = "sp_attack")
	private int spAttack;
	@CsvBindByName(column = "sp_defense")
	private int spDefense;
	@CsvBindByName(column = "type1")
	private String type1;
	@CsvBindByName(column = "type2")
	private String type2;
	@CsvBindByName(column = "speed")
	private double speed;
	@CsvBindByName(column = "is_legendary")
	private boolean legendary;

	public PokemonPoint(String name, int attack, int baseEggSteps, double captureRate, int defense, int experienceGrowth,
			int hp, int spAttack, int spDefense, String type1, String type2, double speed, boolean legendary) {
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
		// TODO Auto-generated method stub
		return col.getNormalizedValue(this);
	}
	
	@Override
	public double distanceTo(IPoint other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(Column col, Object o) {
		// TODO Auto-generated method stub
		
	}

}
