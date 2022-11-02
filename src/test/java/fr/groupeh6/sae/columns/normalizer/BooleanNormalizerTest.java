package fr.groupeh6.sae.columns.normalizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BooleanNormalizerTest {
	
	BooleanNormalizer bn = new BooleanNormalizer();

	@Test
	void should_return_1() {
		assertEquals(1.0, bn.normalize(true));
	}
	
	@Test
	void should_return_0() {
		assertEquals(0.0, bn.normalize(false));
	}
	
	@Test
	void should_return_true() {
		assertEquals(true, bn.denormalize(1.0));
	}
	
	@Test
	void should_return_false() {
		assertEquals(false, bn.denormalize(0.0));
	}

}
