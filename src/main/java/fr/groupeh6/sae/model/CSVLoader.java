package fr.groupeh6.sae.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBeanBuilder;

import fr.groupeh6.sae.model.columns.Column;

public class CSVLoader {
	
	private CSVLoader() {}
	
	public static boolean isValid(File file) {
		return file != null && file.exists() && file.getName().endsWith(".csv");
	}
	
	public static String[] getColumnsName(String firstLine, char delimiter) {
		return firstLine.split(""+delimiter);
	}
	
	public static List<Column> getColumns(String[] columnsName) {
		List<Column> res = new ArrayList<>();
		for(String column : columnsName) {
			res.add(Factory.getInstance().newColumn(column));
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
	
	public static Dataset load(File file, char delimiter) throws TypeNotRegisteredException, IOException {
		if(!isValid(file)) throw new IOException();
		Dataset dataset;
		BufferedReader br = new BufferedReader(new FileReader(file));
		dataset = loadFromReader(br, delimiter);
		br.close();
		return dataset;
	}

	public static Dataset loadFromReader(BufferedReader br, char delimiter) throws IOException, TypeNotRegisteredException {
		Dataset dataset;
		br.mark(1);
		String line = br.readLine().replace("\"", "");
		String[] columns = getColumnsName(line, delimiter);
		dataset = Factory.getInstance().newDataset(getColumns(columns));
		if(dataset == null) throw new TypeNotRegisteredException();
		IPoint dataType = dataset.getType();
		br.reset();
		dataset.setLines(loadDatas(br, dataType, delimiter));
		return dataset;
	}
	
	public static Dataset load(String fileName, char delimiter) throws IOException, TypeNotRegisteredException {
		return load(new File(fileName), delimiter);
	}
	
	public static void main(String[] args) {
		String sep = System.getProperty("file.separator");
		String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "fr" + sep + "groupeh6" + sep + "sae" + sep + "iris.csv";
		try {
			Dataset dataset = CSVLoader.load(path, ',');
			dataset.iterator().forEachRemaining(e -> System.out.println(e));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
