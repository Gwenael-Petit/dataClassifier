package fr.groupeh6.sae.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.CSVLoader;
import fr.groupeh6.sae.dataset.Dataset;

class CSVLoaderTest {

	String sep = System.getProperty("file.separator");
	String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	
	@Test
	void test_valid_should_return_true() {
		assertEquals(true, isValid());
	}
	
	/*@Test
	void test_load() {
		try {
			Dataset dataset = CSVLoader.load(path);
		}
		catch () {
			
		}
	}*/

}
