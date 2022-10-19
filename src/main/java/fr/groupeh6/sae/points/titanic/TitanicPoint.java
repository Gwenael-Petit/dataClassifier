package fr.groupeh6.sae.points.titanic;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.columns.IColumn;
import fr.groupeh6.sae.points.IPoint;

public class TitanicPoint implements IPoint {

	@CsvBindByName(column = "PassengerId")
	private int passengerId;
	
	@CsvBindByName(column = "Survived")
	private boolean survived;
	
	@CsvBindByName(column = "Pclass")
	private int placeClass;
	
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Sex")
	private boolean sex;
	
	@CsvBindByName(column = "Age")
	private double age;
	
	@CsvBindByName(column = "SibSp")
	private int sibSp;
	
	@CsvBindByName(column = "Parch")
	private int parch;
	
	@CsvBindByName(column = "Ticket")
	private String ticket;
	
	@CsvBindByName(column = "Fare")
	private double fare;
	
	@CsvBindByName(column = "Cabin")
	private String cabin;
	
	@CsvBindByName(column = "Embarked")
	private char embarked;
	
	public TitanicPoint() {}

	public Object getValue(IColumn col) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getNormalizedValue(IColumn xcol) {
		// TODO Auto-generated method stub
		return 0;
	}

}
