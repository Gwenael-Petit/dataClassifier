package fr.groupeh6.sae.model.datas.iris;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

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
	private EnumVariety variety;
	
	
	public IrisPoint(double sepalLength, double sepalWidth, double petalLength, double petalWidth, EnumVariety variety) {
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

	public Object getValue(AbstractColumn col) {
		switch(col.getName()) {
			case "sepal.length" : return sepalLength;
			case "sepal.width" : return sepalWidth;
			case "petal.length" : return petalLength;
			case "petal.width" : return petalWidth;
			case "variety" : return variety;
			default : return null;
		}
	}

	public double getNormalizedValue(AbstractColumn col) {
		return col.getNormalizedValue(this);
	}

	@Override
	public void setValue(AbstractColumn col, Object o) {
		switch(col.getName()) {
		case "sepal.length" -> this.sepalLength = (double) o;
		case "sepal.width" -> this.sepalWidth = (double) o;
		case "petal.length" -> this.petalLength = (double) o;
		case "petal.width" -> this.petalWidth = (double) o;
		case "variety" -> this.variety = (EnumVariety) o;	
		}
	}

}
