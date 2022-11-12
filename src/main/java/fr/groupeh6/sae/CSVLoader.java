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
	
	public static boolean isValid(File file) {
		return file != null && file.exists() && file.isFile() && file.getName().endsWith(".csv");
	}
	
	public static char getDelimiter(String firstLine) {
		int c1 = StringUtils.countMatches(firstLine, ',');
		int c2 = StringUtils.countMatches(firstLine, ';');
		return c1 > c2 ? ',' : ';';
	}
	
	public static String[] getColumnsName(String firstLine, char delimiter) {
		return firstLine.split(""+delimiter);
	}
	
	public static List<Column> getColumns(String[] columnsName) {
		List<Column> res = new ArrayList<>();
		for(String column : columnsName) {
			res.add(Factory.getInstance().getColumn(column));
		}
		return res;
	}
	
	public static List<IPoint> loadDatas(Reader reader, IPoint type, char delimiter) throws IOException {
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
			dataset = loadFromReader(br);
		} catch(IOException e) {
			throw e;
		}
		return dataset;
	}

	public static Dataset loadFromReader(BufferedReader br) throws IOException, FileNotFoundException {
		Dataset dataset;
		br.mark(1);
		String line = br.readLine().replace("\"", "");
		char delimiter = getDelimiter(line);
		String[] columns = getColumnsName(line, delimiter);
		dataset = Factory.getInstance().getDataset(getColumns(columns));
		IPoint dataType = dataset.getType();
		br.reset();
		dataset.setLines(loadDatas(br, dataType, delimiter));
		return dataset;
	}
	
	public static Dataset load(String fileName) throws Exception {
		return load(new File(fileName));
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

}
