package fr.groupeh6.sae.columns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class NumberColumnTest {
	
	NumberColumn col = new NumberColumn("Test");
	
	@BeforeEach
	void setUp() {
		col.min = 0.0;
		col.max = 10.0;
	}

	@Test
	void should_return_normalizable() {
		assertTrue(col.isNormalizable());
	}
	
	@Test
	void should_return_normalizable_value() {
		assertEquals(0, col.normalize(0));
		assertEquals(1, col.normalize(10));
		assertEquals(0.5, col.normalize(5));
	}
	
	@Test
	void should_return_denormalizable_value() {
		assertEquals(0.0, col.denormalize(0));
		assertEquals(10.0, col.denormalize(1));
		assertEquals(5.0, col.denormalize(0.5));
	}
	
	@Test
	void should_return_updatable() {
		assertTrue(col.isUpdatable());
	}
	
	@Test
	void should_update_min_and_max() {
		col.update(15.0);
		assertEquals(15.0, col.max);
		col.update(-3.0);
		assertEquals(-3.0, col.min);
		col.update(5.0);
		assertEquals(15.0, col.max);
		assertEquals(-3.0, col.min);
	}

}
