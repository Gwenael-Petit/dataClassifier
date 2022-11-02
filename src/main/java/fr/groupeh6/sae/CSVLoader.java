package fr.groupeh6.sae;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBeanBuilder;

import fr.groupeh6.sae.columns.Column;
import fr.groupeh6.sae.dataset.Dataset;
import fr.groupeh6.sae.dataset.IrisDataSet;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.IrisPoint;

public class CSVLoader {
	
	private static boolean isValid(String fileName) {
		return false;
	}
	
	private static char getDelimiter(String firstLine) {
		int c1 = StringUtils.countMatches(firstLine, ',');
		int c2 = StringUtils.countMatches(firstLine, ';');
		return c1 > c2 ? ',' : ';';
	}
	
	private static String[] getColumnsName() {
		return null;
	}
	
	private static List<Column> getColumns() {
		return null;
	}
	
	private static IPoint getDataClass() {
		return null;
	}
	
	private static List<IPoint> loadDatas(IPoint type) {
		return null;
	}
	
	private static Dataset createDataset() {
		return null;
	}
	
	public static Dataset load(String fileName) {
		return null;
	}
	
	public static Dataset load(File file) {
		return null;
	}
	
	// A delete
	public static List<IrisPoint> loadIris(String fileName) throws IOException {
		return new CsvToBeanBuilder<IrisPoint>(Files.newBufferedReader(Paths.get(fileName)))
				.withSeparator(',')
				.withType(IrisPoint.class)
				.build().parse();
	}
	
	public static void main(String[] args) {
		List<IPoint> irisPoints = new CsvToBeanBuilder<IPoint>(new InputStreamReader(CSVLoader.class.getResourceAsStream("iris.csv")))
				.withSeparator(',').withType(IrisPoint.class).build().parse();
		IrisDataSet dataSet = new IrisDataSet();
		dataSet.setLines(irisPoints);
		dataSet.iterator().forEachRemaining(e -> System.out.println(e));
	}

}
