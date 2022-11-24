package fr.groupeh6.sae.model.columns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.datas.iris.EnumVariety;
import fr.groupeh6.sae.model.datas.iris.IrisDataset;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;
import fr.groupeh6.sae.model.datas.pokemon.EnumType;
import fr.groupeh6.sae.model.datas.pokemon.PokemonPoint;

class ColumnTest {
	
	AbstractColumn col = new BooleanColumn("Test");

	@Test
	void should_return_the_name() {
		assertEquals("Test", col.getName());
	}
	
	@Test
	void test_equals() {
		AbstractColumn col2 = new StringColumn("Test");
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
		AbstractDataset ds = new IrisDataset();
		col.setDataset(ds);
		assertTrue(col.isLinkedToADataset());
	}
	
	@Test
	void should_return_the_value_normalized_by_the_implementation_of_column() {
		AbstractColumn col2 = new BooleanColumn("is_legendary");
		IPoint p = new PokemonPoint("carapuce", 0, 0, 0, 0, 0, 0, 0, 0, EnumType.WATER, EnumType.FLYING, 0, false);
		assertEquals(0.0, col2.getNormalizedValue(p));
	}
	
	@Test
	void should_return_the_value_denormalized_by_the_implementation_of_column() {
		assertEquals(true, col.getDenormalizedValue(1));
	}

	@Test
	void test_getDistinctValues() {
		List<String> response = new ArrayList();
		assertEquals(response ,col.getDistinctValues());
		
		AbstractColumn col2 = new NumberColumn("sepal.width");
		AbstractColumn col3 = new NumberColumn("sepal.length");
		AbstractColumn col4 = new NumberColumn("petal.width");
		AbstractColumn col5 = new NumberColumn("petal.width");
		AbstractColumn col6 = new EnumColumn("variety", EnumVariety.class);
		List<AbstractColumn> listColumns = new ArrayList();
		listColumns.add(col2);
		listColumns.add(col3);
		listColumns.add(col4);
		listColumns.add(col5);
		listColumns.add(col6);
		AbstractDataset dataset = new IrisDataset();
		IPoint point = new IrisPoint(5.1, 3.5, 1.4, .2, EnumVariety.SETOSA);
		IPoint point2 = new IrisPoint(2.6, 3.5, 2.0, .4, EnumVariety.SETOSA);
		List<String> response2 = new ArrayList();
		dataset.addLine(point);
		dataset.addLine(point2);
		dataset.setColumns(listColumns);
		col2.setDataset(dataset);
		col3.setDataset(dataset);
		col4.setDataset(dataset);
		col5.setDataset(dataset);
		col6.setDataset(dataset);		
		response.add("3.5");
		response2.add("5.1");
		response2.add("2.6");
		assertEquals(response, col2.getDistinctValues());
		assertEquals(response2, col3.getDistinctValues());
	}
}
