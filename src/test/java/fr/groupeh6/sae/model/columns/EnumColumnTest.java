package fr.groupeh6.sae.model.columns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnumColumnTest {
	
	enum EnumTest {
		SETOSA, VERSICOLOR, VIRGINICA;
	}
	
	EnumColumn<EnumTest> col = new EnumColumn<EnumTest>("Test", EnumTest.class);

	@Test
	void should_return_values_of_the_enum() {
		assertArrayEquals(EnumTest.values(), col.values());
	}
	
	@Test
	void should_return_number_of_values() {
		assertEquals(3, col.enumSize());
	}
	
	@Test
	void should_return_normalizable() {
		assertTrue(col.isNormalizable());
	}
	
	@Test
	void shoud_return_not_updatable() {
		assertFalse(col.isUpdatable());
	}
	
	@Test
	void should_return_normalize_value() {
		assertEquals(0, col.normalize(EnumTest.SETOSA));
		assertEquals(0.5, col.normalize(EnumTest.VERSICOLOR));
		assertEquals(1, col.normalize(EnumTest.VIRGINICA));
	}
	
	@Test
	void should_return_denormalize_value() {
		assertEquals(EnumTest.SETOSA, col.denormalize(0));
		assertEquals(EnumTest.VERSICOLOR, col.denormalize(0.5));
		assertEquals(EnumTest.VIRGINICA, col.denormalize(1));
	}

}
