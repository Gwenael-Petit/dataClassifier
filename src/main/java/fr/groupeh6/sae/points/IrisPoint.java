package fr.groupeh6.sae.points;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.columns.Column;

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
	
	
	public IrisPoint(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String variety) {
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.variety = variety;
	}
	
	public IrisPoint() {}
	
	@Override
	public String toString() {
		return variety + "[" + sepalLength + "," + sepalWidth + "," + petalLength + "," + petalWidth + "]";
	}

	public Object getValue(Column col) {
		switch(col.getName()) {
			case "sepal.length" : return sepalLength;
			case "sepal.width" : return sepalWidth;
			case "petal.length" : return petalLength;
			case "petal.width" : return petalWidth;
			case "variety" : return variety;
			default : return null;
		}
	}

	public double getNormalizedValue(Column col) {
		return col.getNormalizedValue(this);
	}

	@Override
	public double distanceTo(IPoint other) {
		return 0;
	}

	@Override
	public void setValue(Column col, Object o) {
		// TODO Auto-generated method stub
		
	}

}
