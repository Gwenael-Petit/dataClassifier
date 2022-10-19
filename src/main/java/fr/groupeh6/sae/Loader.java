package fr.groupeh6.sae;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import fr.groupeh6.sae.dataset.iris.IrisDataSet;
import fr.groupeh6.sae.points.IPoint;
import fr.groupeh6.sae.points.iris.IrisPoint;

public class Loader {
	
	public static List<IrisPoint> loadIris(String fileName) throws IOException {
		return new CsvToBeanBuilder<IrisPoint>(Files.newBufferedReader(Paths.get(fileName)))
				.withSeparator(',')
				.withType(IrisPoint.class)
				.build().parse();
	}
	
	public static void main(String[] args) {
		List<IPoint> irisPoints = new CsvToBeanBuilder<IPoint>(new InputStreamReader(Loader.class.getResourceAsStream("iris.csv")))
				.withSeparator(',').withType(IrisPoint.class).build().parse();
		IrisDataSet dataSet = new IrisDataSet();
		dataSet.setLines(irisPoints);
		dataSet.getPoints().forEach(p -> System.out.println(p));
	}

}
