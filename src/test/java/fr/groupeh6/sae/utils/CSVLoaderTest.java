package fr.groupeh6.sae.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.CSVLoader;
import fr.groupeh6.sae.Factory;
import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

class CSVLoaderTest {

	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "DataForTest.csv";
	protected File file = new File(path);

	@Test
	void test_isValid_should_return_true() {
		assertEquals(true, CSVLoader.isValid(file));
	}
	
	@Test
	void test_isValid_should_return_false_because_not_exist() {
		File fakeFile = new File(System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "pokemon.csv");
		assertFalse(CSVLoader.isValid(fakeFile));
	}
	
	@Test
	void test_isValid_should_return_false_because_not_csv() {
		File wrongExtentionFile = new File((System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "java" + sep + "fr.groupeh6.sae" + sep + "utils" + sep + "Observer.java"));
		assertFalse(CSVLoader.isValid(wrongExtentionFile));
	}
	
	@Test
	void test_isValid_should_return_false_because_not_file() {
		File notAFile = new File(System.getProperty("user.dir") + sep + "src" + sep + "main" + sep);
		assertFalse(CSVLoader.isValid(notAFile));
	}
	
	@Test
	void test_getDelimiter_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		br.close();
		char delimiter = CSVLoader.getDelimiter(line);
		assertEquals(',', delimiter);
	}
	
	@Test
	void test_getDelimiter_should_return_false() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		br.close();
		char delimiter = CSVLoader.getDelimiter(line);
		assertNotEquals(';', delimiter);
	}
	
	@Test
	void test_getColumnsName_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		br.close();
		String[] columnsName = new String[] {"sepal.length", "sepal.width", "petal.length", "petal.width", "variety"};
		assertArrayEquals(columnsName, CSVLoader.getColumnsName(line, ','));
	}
	
	@Test
	void test_getColumns_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine().replace("\"", "");
		br.close();
		List<Column> res = new ArrayList<Column>();
		res.add(Factory.getInstance().getColumn("sepal.length"));
		res.add(Factory.getInstance().getColumn("sepal.width"));
		res.add(Factory.getInstance().getColumn("petal.length"));
		res.add(Factory.getInstance().getColumn("petal.width"));
		res.add(Factory.getInstance().getColumn("variety"));
		assertTrue(res.equals(CSVLoader.getColumns(CSVLoader.getColumnsName(line, ','))));
	}
	
	@Test
	void test_loadDatas_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv")));
		String line = br.readLine().replace("\"", "");
		br.close();
		List<IPoint> datas =CSVLoader.loadDatas(br, new IrisPoint(), CSVLoader.getDelimiter(line));
		assertEquals("",datas.toString());
	}
	
	@Test
	void test_loadFromReader_should_return_true() throws Exception {	
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		Dataset dataset = CSVLoader.loadFromReader(br);
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals("Setosa[3.2,2.3,1.1,0.1]Virginica[7.1,3.3,6.2,2.0]", sb.toString());
	}
	
	@Test
	void test_load_should_return_true() throws NoSuchElementException, IOException {
		StringBuilder sb = new StringBuilder();
		Dataset dataset = CSVLoader.load(file);
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals("Setosa[3.2,2.3,1.1,0.1]Virginica[7.1,3.3,6.2,2.0]", sb.toString());
	}
	
	@Test
	void test_load_should_return_false_because_file_not_valid() {
		File fakeFile = new File(System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "pokemon.csv");
		Dataset dataset;
		String s = "";
		try {
			dataset = CSVLoader.load(fakeFile);
		} catch (NoSuchElementException | IOException e1) {
			s = s + "File is not valid";
		}
		assertEquals(s,"File is not valid");
	}
	
}