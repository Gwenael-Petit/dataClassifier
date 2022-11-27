package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.Test;

class TypeNotRegistredExceptionTest {

	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "DataUnknownType.csv";
	protected File file = new File(path);
	
	@Test
	void test_to_throw_TypeNotRegistredException() throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		assertThrows(TypeNotRegisteredException.class, () -> CSVLoader.loadFromReader(br, ','));
	}

}
