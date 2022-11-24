package fr.groupeh6.sae.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.datas.pokemon.PokemonDataset;

class PokemonDatasetTest {
	
	AbstractDataset pokemon= new PokemonDataset();

	@Test
	void get_type_test() {
		assertEquals("Pokemon [name=null, attack=0.0, eggSteps=0.0, captureRate=0.0, defense=0.0, experience=0.0, hp=0.0, spAttack=0.0, spDefense=0.0, type1=null, type2=null, speed=0.0, legendary=false]",pokemon.getType().toString());
	}

}
