package fr.groupeh6.sae;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBeanBuilder;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.dataset.IrisDataSet;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

public class CSVLoader {
	
	private static boolean isValid(File file) {
		return file.exists() && file.isFile() && file.getName().endsWith(".csv");
	}
	
	private static char getDelimiter(String firstLine) {
		int c1 = StringUtils.countMatches(firstLine, ',');
		int c2 = StringUtils.countMatches(firstLine, ';');
		return c1 > c2 ? ',' : ';';
	}
	
	private static String[] getColumnsName(String firstLine, char delimiter) {
		return firstLine.split(""+delimiter);
	}
	
	private static List<Column> getColumns(String[] columnsName) {
		List<Column> res = new ArrayList<>();
		for(String column : columnsName) {
			res.add(Factory.getInstance().getColumn(column));
		}
		return res;
	}
	
	private static List<IPoint> loadDatas(Reader reader, IPoint type, char delimiter) throws IOException {
		return new CsvToBeanBuilder<IPoint>(reader)
				.withSeparator(delimiter)
				.withType(type.getClass()).
				build().
				parse();
	}
	
	public static Dataset load(File file) throws NoSuchElementException, IOException {
		if(!isValid(file)) throw new NoSuchElementException();
		Dataset dataset;
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine().replace("\"", "");
			char delimiter = getDelimiter(line);
			String[] columns = getColumnsName(line, delimiter);
			dataset = Factory.getInstance().getDataset(getColumns(columns));
			IPoint dataType = dataset.getType();
			dataset.setLines(loadDatas(new BufferedReader(new FileReader(file)), dataType, delimiter));
		} catch(IOException e) {
			throw e;
		}
		return dataset;
	}
	
	public static Dataset load(String fileName) throws Exception {
		return load(new File(fileName));
	}
	
	// A delete
	public static List<IrisPoint> loadIris(String fileName) throws IOException {
		return new CsvToBeanBuilder<IrisPoint>(Files.newBufferedReader(Paths.get(fileName)))
				.withSeparator(',')
				.withType(IrisPoint.class)
				.build().parse();
	}
	
	public static void main(String[] args) {
		String sep = System.getProperty("file.separator");
		String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
		try {
			Dataset dataset = CSVLoader.load(path);
			dataset.iterator().forEachRemaining(e -> System.out.println(e));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		List<IPoint> irisPoints = new CsvToBeanBuilder<IPoint>(new InputStreamReader(CSVLoader.class.getResourceAsStream("iris.csv")))
				.withSeparator(',').withType(IrisPoint.class).build().parse();
		IrisDataSet dataSet = new IrisDataSet();
		dataSet.setLines(irisPoints);
		dataSet.iterator().forEachRemaining(e -> System.out.println(e));
	}*/

}
