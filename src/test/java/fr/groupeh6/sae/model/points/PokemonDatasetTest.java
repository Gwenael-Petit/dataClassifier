package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonDataset;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;

class PokemonDatasetTest {
	IPoint poke1 = new PokemonPoint("Swablu", 40, 5120, 255.0, 60, 600000, 45, 75, 50, EnumType.NORMAL,
			EnumType.FLYING, 1.2, false);
	IPoint poke2 = new PokemonPoint("Azelf", 125, 20480, 3.0, 70, 1250000, 75, 70, 115, EnumType.PSYCHIC, null, 0.3,
			true);
	IPoint poke3 = new PokemonPoint("Gallade", 165, 5120, 45.0, 95, 1250000, 68, 115, 110, EnumType.PSYCHIC,
			EnumType.FIGHTING, 52.0, false);
	AbstractDataset pokemon= new PokemonDataset();

	@Test
	void get_type_test() {
		assertEquals("Pokemon [name=null, attack=0.0, eggSteps=0.0, captureRate=0.0, defense=0.0, experience=0.0, hp=0.0, spAttack=0.0, spDefense=0.0, type1=null, type2=null, speed=0.0, legendary=false]",pokemon.getType().toString());
	}
	
	@Test
	void test_distance() {
		assertEquals(0.4581,pokemon.distance(poke1,poke2),0.0001);
		assertEquals(0.0,pokemon.distance(poke1,poke1),0.0001);
		assertEquals(0.3333,pokemon.distance(poke2,poke3),0.0001);
	}

}
