package fr.groupeh6.sae.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.datas.iris.IrisPoint;

class CSVLoaderTest {

	protected String sep = System.getProperty("file.separator");
	protected String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "DataForTest.csv";
	protected File file = new File(path);
	
	@Test
	void test_file_is_null() {
		assertFalse(CSVLoader.isValid(null));
	}
	
	@Test
	void test_isValid_should_return_false_because_not_exist() {
		File fakeFile = new File(System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "pokemon.csv");
		assertFalse(CSVLoader.isValid(fakeFile));
	}
	
	@Test
	void test_isValid_should_return_false_because_not_csv() {
		File wrongExtentionFile = new File((System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "java" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "model" + sep + "utils" + sep + "Observer.java"));
		assertFalse(CSVLoader.isValid(wrongExtentionFile));
	}
	
	@Test
	void test_isValid_should_return_true() {
		assertTrue(CSVLoader.isValid(file));
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
		res.add(Factory.getInstance().newColumn("sepal.length"));
		res.add(Factory.getInstance().newColumn("sepal.width"));
		res.add(Factory.getInstance().newColumn("petal.length"));
		res.add(Factory.getInstance().newColumn("petal.width"));
		res.add(Factory.getInstance().newColumn("variety"));
		assertTrue(res.equals(CSVLoader.getColumns(CSVLoader.getColumnsName(line, ','))));
	}
	
	@Test
	void test_loadDatas_should_return_true() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		br.mark(1);
		String line = br.readLine().replace("\"", "");
		br.reset();
		List<IPoint> datas =CSVLoader.loadDatas(br, new IrisPoint(), ',');
		assertEquals("[SETOSA[3.2,2.3,1.1,0.1], VIRGINICA[7.1,3.3,6.2,2.0]]",datas.toString());
	}
	
	@Test
	void test_loadFromReader_should_return_true() throws Exception {	
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		Dataset dataset = CSVLoader.loadFromReader(br, ',');
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals("SETOSA[3.2,2.3,1.1,0.1]VIRGINICA[7.1,3.3,6.2,2.0]", sb.toString());
	}
	
	@Test
	void test_load_from_file() throws NoSuchElementException, IOException, TypeNotRegisteredException {
		StringBuilder sb = new StringBuilder();
		Dataset dataset = CSVLoader.load(file, ',');
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals("SETOSA[3.2,2.3,1.1,0.1]VIRGINICA[7.1,3.3,6.2,2.0]", sb.toString());
	}
	
	@Test
	void test_load_file_not_exist() {
		assertThrows(IOException.class, () -> CSVLoader.load("", ','));
	}
	
	@Test
	void test_load_from_file_name() throws Exception {
		StringBuilder sb = new StringBuilder();
		Dataset dataset = CSVLoader.load(path, ',');
		dataset.iterator().forEachRemaining(e -> sb.append(e));
		assertEquals("SETOSA[3.2,2.3,1.1,0.1]VIRGINICA[7.1,3.3,6.2,2.0]", sb.toString());
	}
	
}