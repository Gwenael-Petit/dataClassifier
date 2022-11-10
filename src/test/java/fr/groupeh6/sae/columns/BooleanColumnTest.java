package fr.groupeh6.sae.columns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BooleanColumnTest {
	
	BooleanColumn col = new BooleanColumn("Test");

	@Test
	void should_return_1() {
		assertEquals(1.0, col.normalize(true));
	}
	
	@Test
	void should_return_0() {
		assertEquals(0.0, col.normalize(false));
	}
	
	@Test
	void should_return_true() {
		assertEquals(true, col.denormalize(1.0));
	}
	
	@Test
	void should_return_false() {
		assertEquals(false, col.denormalize(0.0));
	}
	
	@Test
	void should_return_normalizable() {
		assertTrue(col.isNormalizable());
	}
	
	@Test
	void should_return_not_updatable() {
		assertFalse(col.isUpdatable());
	}

}
