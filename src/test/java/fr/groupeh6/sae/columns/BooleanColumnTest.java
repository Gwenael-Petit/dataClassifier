package fr.groupeh6.sae.columns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BooleanColumnTest {
	
	BooleanColumn bc = new BooleanColumn("Test");

	@Test
	void should_return_1() {
		assertEquals(1.0, bc.normalize(true));
	}
	
	@Test
	void should_return_0() {
		assertEquals(0.0, bc.normalize(false));
	}
	
	@Test
	void should_return_true() {
		assertEquals(true, bc.denormalize(1.0));
	}
	
	@Test
	void should_return_false() {
		assertEquals(false, bc.denormalize(0.0));
	}
	
	@Test
	void is_Normalizable() {
		assertTrue(bc.isNormalizable());
	}

}
