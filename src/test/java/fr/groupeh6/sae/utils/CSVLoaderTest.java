package fr.groupeh6.sae.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.CSVLoader;
import fr.groupeh6.sae.dataset.Dataset;

class CSVLoaderTest {

	String sep = System.getProperty("file.separator");
	String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
	File file = new File(path);
	
	@Test
	void test_load() throws Exception {	
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		/*for(String l : line.) {
			line = br.readLine().replace("\"", "");
		}*/
		br.close();
		Dataset dataset = CSVLoader.load(path);
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals(line, sb);
	}

	@Test
	void test_valid_should_return_true() {
		assertEquals(true, CSVLoader.isValid(file));
	}
	
	@Test
	void test_getDelimiter_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		br.close();
		char delimiter = CSVLoader.getDelimiter(line);
		assertEquals(',', delimiter);
	}
	
	
	
}