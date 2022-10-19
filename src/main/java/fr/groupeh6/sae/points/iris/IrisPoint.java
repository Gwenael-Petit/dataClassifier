package fr.groupeh6.sae.points.iris;


import com.opencsv.bean.CsvBindByName;
import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.points.IPoint;

public class IrisPoint implements IPoint {
	
	@CsvBindByName(column = "sepal.length")
	private double sepalLength;
	
	@CsvBindByName(column = "sepal.width")
	private double sepalWidth;
	
	@CsvBindByName(column = "petal.length")
	private double petalLength;
	
	@CsvBindByName(column = "petal.width")
	private double petalWidth;
	
	@CsvBindByName(column = "variety")
	private String variety;
	
	
	public IrisPoint() {}

	public Object getValue(IColumn col) {
		switch(col.getName()) {
			case "sepal.length" : return sepalLength;
			case "sepal.width" : return sepalWidth;
			case "petal.length" : return petalLength;
			case "petal.width" : return petalWidth;
			case "variety" : return variety;
			default : return null;
		}
	}

	public double getNormalizedValue(IColumn xcol) {
		switch(xcol.getName()) {
			case "sepal.length" : return sepalLength;
			case "sepal.width" : return sepalWidth;
			case "petal.length" : return petalLength;
			case "petal.width" : return petalWidth;
			default : return 0.0;
		}
	}

}
