package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonDataset;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;
import fr.groupeh6.sae.model.datas.titanic.EnumEmbarked;
import fr.groupeh6.sae.model.datas.titanic.EnumSex;

class PokemonDatasetTest {
	IPoint poke1 = new PokemonPoint("Swablu", 40, 5120, 255.0, 60, 600000, 45, 75, 50, EnumType.NORMAL,
			EnumType.FLYING, 1.2, false);
	IPoint poke2 = new PokemonPoint("Azelf", 125, 20480, 3.0, 70, 1250000, 75, 70, 115, EnumType.PSYCHIC, null, 0.3,
			true);
	IPoint poke3 = new PokemonPoint("Gallade", 165, 5120, 45.0, 95, 1250000, 68, 115, 110, EnumType.PSYCHIC,
			EnumType.FIGHTING, 52.0, false);
	AbstractDataset pokemonDs;

	@BeforeEach
	void setup() {
		pokemonDs = new PokemonDataset();
		pokemonDs.addAllLine(List.of(poke1, poke2, poke3));
	}
	
	@Test
	void get_type_test() {
		assertTrue(pokemonDs.getType() instanceof PokemonPoint);
	}
	
	@Test
	void test_distance() {
		assertEquals(2.7489,pokemonDs.distance(poke1,poke2),0.0001);
		assertEquals(0.0,pokemonDs.distance(poke1,poke1),0.0001);
		assertEquals(2.1681,pokemonDs.distance(poke2,poke3),0.0001);
	}
	
	@Test
	void test_getColumnDistance() {
		List<AbstractColumn> listeDist = new ArrayList<AbstractColumn>();
		listeDist.add(new NumberColumn("attack")); 
		listeDist.add(new NumberColumn("base_egg_steps")); 
		listeDist.add(new NumberColumn("capture_rate")); 
		listeDist.add(new NumberColumn("defense"));
		listeDist.add(new NumberColumn("experience_growth"));
		listeDist.add(new NumberColumn("hp"));
		listeDist.add(new NumberColumn("sp_attack"));
		listeDist.add(new NumberColumn("sp_defense"));
		listeDist.add(new EnumColumn<EnumType>("type1", EnumType.class));
		listeDist.add(new EnumColumn<EnumType>("type2", EnumType.class));
		listeDist.add(new NumberColumn("speed"));
		assertEquals(listeDist, pokemonDs.getColumnsDistance());
	}
	
	@Test
	void test_Name() {
		assertEquals("Euclidienne", pokemonDs.getDistanceName());
	}

}
