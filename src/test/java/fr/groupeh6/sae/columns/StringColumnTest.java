package fr.groupeh6.sae.columns;

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
	void should_doing_nothing_when_receive_new_point_value() {
		
	}

}
