package fr.groupeh6.sae.model.columns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;

class ColumnTest {
	
	Column col = new BooleanColumn("Test");

	@Test
	void should_return_the_name() {
		assertEquals("Test", col.getName());
	}
	
	@Test
	void test_equals() {
		Column col2 = new StringColumn("Test");
		assertTrue(col.equals(col2));
		assertTrue(col.equals(col));
		assertFalse(col.equals(null));
		assertFalse(col.equals("Test"));
	}
	
	@Test
	void should_toString_return_the_name_column() {
		assertEquals("Test", col.toString());
	}
	
	@Test
	void test_link_to_a_dataset() {
		assertFalse(col.isLinkedToADataset());
		Dataset ds = new IrisDataset();
		col.setDataset(ds);
		assertTrue(col.isLinkedToADataset());
	}
	
	@Test
	void should_return_the_value_normalized_by_the_implementation_of_column() {
		Column col2 = new BooleanColumn("is_legendary");
		IPoint p = new PokemonPoint("carapuce", 0, 0, 0, 0, 0, 0, 0, 0, EnumType.WATER, EnumType.FLYING, 0, false);
		assertEquals(0.0, col2.getNormalizedValue(p));
	}
	
	@Test
	void should_return_the_value_denormalized_by_the_implementation_of_column() {
		assertEquals(true, col.getDenormalizedValue(1));
	}

}
