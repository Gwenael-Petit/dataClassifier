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
		// TODO Auto-generated method stub
		return null;
	}

	public double getNormalizedValue(IColumn xcol) {
		// TODO Auto-generated method stub
		return 0;
	}

}
