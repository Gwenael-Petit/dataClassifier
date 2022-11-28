package fr.groupeh6.sae.model.columns;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringColumnTest {
	
	StringColumn col = new StringColumn("Test");

	@Test
	void should_throw_exception_when_normalize() {
		assertThrows(NotNormalizableException.class, () -> col.normalize("Test"));
	}
	
	@Test
	void should_throw_exception_when_denormalize() {
		assertThrows(NotNormalizableException.class, () -> col.denormalize(0.0));
	}
	
	@Test
	void should_return_not_normalizable() {
		assertFalse(col.isNormalizable());
	}
	
	@Test
	void should_return_not_updatable() {
		assertFalse(col.isUpdatable());
	}
	
	@Test
	void should_return_not_enumerative() {
		assertFalse(col.isEnumerative());
	}
	
	@Test
	void test_toString() {
		assertEquals("String:Test", col.toString());
	}

}
