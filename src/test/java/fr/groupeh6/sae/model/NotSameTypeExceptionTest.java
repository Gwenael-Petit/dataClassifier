package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class NotSameTypeExceptionTest {

	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	protected String otherPath = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "pokemon_train.csv";
	MainModel model = new MainModel();
	
	@Test
	void test_throw_NotSameTypeException() throws NotSameTypeException, IOException, TypeNotRegisteredException {
		model.loadFromFile(path, ',', false);
		assertThrows(NotSameTypeException.class, () -> model.loadFromFile(otherPath, ',', true));
	}

}
