package fr.groupeh6.sae.points;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.columns.Column;

public class TitanicPoint implements IPoint {

	@CsvBindByName(column = "PassengerId")
	private int passengerId;
	
	@CsvBindByName(column = "Survived")
	private int survived;
	
	@CsvBindByName(column = "Pclass")
	private int placeClass;
	
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Sex")
	private String sex;
	
	@CsvBindByName(column = "Age")
	private int age;
	
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
	
	public TitanicPoint(int passengerId, int survived, int placeClass, String name, String sex, int age, int sibSp, int parch, String ticket, double fare, String cabin, char embarked ) {
		this.passengerId = passengerId;
		this.survived = survived;
		this.placeClass = placeClass;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.sibSp = sibSp;
		this.parch = parch;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		this.embarked = embarked;
	}
	
	public TitanicPoint(){}

	@Override
	public String toString() {
		return "TitanicPoint [passengerId=" + passengerId + ", survived=" + survived + ", placeClass=" + placeClass
				+ ", name=" + name + ", sex=" + sex + ", age=" + age + ", sibSp=" + sibSp + ", parch=" + parch
				+ ", ticket=" + ticket + ", fare=" + fare + ", cabin=" + cabin + ", embarked=" + embarked + "]";
	}

	public Object getValue(Column col) {
		switch(col.getName()) {
		case "PassengerId" : return passengerId;
		case "Survived" : return survived;
		case "Pclass" : return placeClass;
		case "Name" : return name;
		case "Sex" : return sex;
		case "Age" : return age;
		case "SibSp" : return sibSp;
		case "Parch" : return parch;
		case "Ticket" : return ticket;
		case "Fare" : return fare;
		case "Cabin" : return cabin;
		case "Embarked" : return embarked;
		default : return null;
	}
	}

	public double getNormalizedValue(Column col) {
		return col.getNormalizedValue(this);
	}

	@Override
	public double distanceTo(IPoint other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(Column col, Object o) {
		// TODO Auto-generated method stub
		
	}

}
