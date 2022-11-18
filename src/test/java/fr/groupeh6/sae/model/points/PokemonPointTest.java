package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;

class PokemonPointTest {
	
	PokemonPoint poke1 = new PokemonPoint("Swablu",40,5120,	255.0,60,600000,45,75,50,EnumType.NORMAL,EnumType.FLYING,1.2,false);
	PokemonPoint poke2 = new PokemonPoint("Azelf",125,20480,3.0,70,1250000,75,70,115,EnumType.PSYCHIC,0.3,true);
	PokemonPoint poke3 = new PokemonPoint("Gallade",165,5120,45.0,95,1250000,68,115,110,EnumType.PSYCHIC,EnumType.FIGHTING,52.0,false);
	
	StringColumn name = (StringColumn) Factory.getInstance().getColumn("name");
	NumberColumn attack = (NumberColumn) Factory.getInstance().getColumn("attack");
	NumberColumn baseEggSteps = (NumberColumn) Factory.getInstance().getColumn("base_egg_steps");
	NumberColumn captureRate = (NumberColumn) Factory.getInstance().getColumn("capture_rate");
	NumberColumn defense= (NumberColumn) Factory.getInstance().getColumn("defense");
	NumberColumn expGrowth = (NumberColumn) Factory.getInstance().getColumn("experience_growth");
	NumberColumn hp = (NumberColumn) Factory.getInstance().getColumn("hp");
	NumberColumn spAttack = (NumberColumn) Factory.getInstance().getColumn("sp_attack");
	NumberColumn spDefense = (NumberColumn) Factory.getInstance().getColumn("sp_defense");
	EnumColumn<EnumType> type1 = (EnumColumn<EnumType>) Factory.getInstance().getColumn("type1");
	EnumColumn<EnumType> type2 = (EnumColumn<EnumType>) Factory.getInstance().getColumn("type2");
	NumberColumn speed = (NumberColumn) Factory.getInstance().getColumn("speed");
	BooleanColumn lengendary = (BooleanColumn) Factory.getInstance().getColumn("is_legendary");

	


	@Override
	public String toString() {
		return "PokemonPointTest [poke1=" + poke1 + ", poke2=" + poke2 + ", poke3=" + poke3 + ", name=" + name
				+ ", attack=" + attack + ", baseEggSteps=" + baseEggSteps + ", captureRate=" + captureRate
				+ ", defense=" + defense + ", expGrowth=" + expGrowth + ", hp=" + hp + ", spAttack=" + spAttack
				+ ", spDefense=" + spDefense + ", type1=" + type1 + ", type2=" + type2 + ", speed=" + speed
				+ ", lengendary=" + lengendary + "]";
	}
	
	@Test
	void test_getValue() {
		assertEquals("Swablu",poke1.getValue(name));
		assertEquals(125.0,poke2.getValue(attack));
		assertEquals(5120.0,poke3.getValue(baseEggSteps));
		assertEquals(255.0,poke1.getValue(captureRate));
		assertEquals(70.0,poke2.getValue(defense));
		assertEquals(1250000.0,poke3.getValue(expGrowth));
		assertEquals(45.0,poke1.getValue(hp));
		assertEquals(70.0,poke2.getValue(spAttack));
		assertEquals(110.0,poke3.getValue(spDefense));
		assertEquals("Swablu",poke1.getValue(name));
		assertEquals(EnumType.NORMAL,poke1.getValue(type1));
		assertEquals(null,poke2.getValue(type2));
		assertEquals(52.0,poke3.getValue(speed));
		assertEquals(false,poke1.getValue(lengendary));
	}
	
	

}
