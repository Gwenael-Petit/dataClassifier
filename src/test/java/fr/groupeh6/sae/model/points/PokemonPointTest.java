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

	PokemonPoint poke1 = new PokemonPoint("Swablu", 40, 5120, 255.0, 60, 600000, 45, 75, 50, EnumType.NORMAL,
			EnumType.FLYING, 1.2, false);
	PokemonPoint poke2 = new PokemonPoint("Azelf", 125, 20480, 3.0, 70, 1250000, 75, 70, 115, EnumType.PSYCHIC, 0.3,
			true);
	PokemonPoint poke3 = new PokemonPoint("Gallade", 165, 5120, 45.0, 95, 1250000, 68, 115, 110, EnumType.PSYCHIC,
			EnumType.FIGHTING, 52.0, false);

	StringColumn name = new StringColumn("name");
	NumberColumn attack = new NumberColumn("attack");
	NumberColumn baseEggSteps = new NumberColumn("base_egg_steps");
	NumberColumn captureRate = new NumberColumn("capture_rate");
	NumberColumn defense = new NumberColumn("defense");
	NumberColumn expGrowth = new NumberColumn("experience_growth");
	NumberColumn hp = new NumberColumn("hp");
	NumberColumn spAttack = new NumberColumn("sp_attack");
	NumberColumn spDefense = new NumberColumn("sp_defense");
	EnumColumn<EnumType> type1 = new EnumColumn<EnumType>("type1", EnumType.class);
	EnumColumn<EnumType> type2 = new EnumColumn<EnumType>("type2", EnumType.class);
	NumberColumn speed = new NumberColumn("speed");
	BooleanColumn lengendary = new BooleanColumn("is_legendary");

	
	
	
	@Test
	void toStringTest() {
		assertEquals("Pokemon [name=Swablu, attack=40.0, eggSteps=5120.0, captureRate=255.0, defense=60.0, experience=600000.0, hp=45.0, spAttack=75.0, spDefense=50.0, type1=NORMAL, type2=FLYING, speed=1.2, legendary=false]",poke1.toString());
	}

	@Test
	void test_getValue() {
		assertEquals("Swablu", poke1.getValue(name));
		assertEquals(125.0, poke2.getValue(attack));
		assertEquals(5120.0, poke3.getValue(baseEggSteps));
		assertEquals(255.0, poke1.getValue(captureRate));
		assertEquals(70.0, poke2.getValue(defense));
		assertEquals(1250000.0, poke3.getValue(expGrowth));
		assertEquals(45.0, poke1.getValue(hp));
		assertEquals(70.0, poke2.getValue(spAttack));
		assertEquals(110.0, poke3.getValue(spDefense));
		assertEquals("Swablu", poke1.getValue(name));
		assertEquals(EnumType.NORMAL, poke1.getValue(type1));
		assertEquals(null, poke2.getValue(type2));
		assertEquals(52.0, poke3.getValue(speed));
		assertEquals(false, poke1.getValue(lengendary));
	}

	@Test
	void getNormalizedValueTest() {
		//assertThrows(,poke1.getNormalizedValue(name));
	}

}
