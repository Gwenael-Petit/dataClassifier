package fr.groupeh6.sae.model.datas.titanic;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;

public class TitanicPoint implements IPoint {

	@CsvBindByName(column = "PassengerId")
	private String passengerId;
	
	@CsvBindByName(column = "Survived")
	private double survived;
	
	@CsvBindByName(column = "Pclass")
	private double pClass;
	
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Sex")
	private EnumSex sex;
	
	@CsvBindByName(column = "Age")
	private double age;
	
	@CsvBindByName(column = "SibSp")
	private double sibSp;
	
	@CsvBindByName(column = "Parch")
	private double parch;
	
	@CsvBindByName(column = "Ticket")
	private String ticket;
	
	@CsvBindByName(column = "Fare")
	private double fare;
	
	@CsvBindByName(column = "Cabin")
	private String cabin;
	
	@CsvBindByName(column = "Embarked")
	private EnumEmbarked embarked;
	
	public TitanicPoint(String passengerId, double survived, int pClass, String name, EnumSex sex, int age, int sibSp, int parch, String ticket, double fare, String cabin, EnumEmbarked embarked ) {
		this.passengerId = passengerId;
		this.survived = survived;
		this.pClass = pClass;
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
		return "TitanicPoint [passengerId=" + passengerId + ", survived=" + survived + ", placeClass=" + pClass
				+ ", name=" + name + ", sex=" + sex + ", age=" + age + ", sibSp=" + sibSp + ", parch=" + parch
				+ ", ticket=" + ticket + ", fare=" + fare + ", cabin=" + cabin + ", embarked=" + embarked + "]";
	}

	public Object getValue(AbstractColumn col) {
		switch(col.getName()) {
		case "PassengerId" : return passengerId;
		case "Survived" : return survived;
		case "Pclass" : return pClass;
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

	public double getNormalizedValue(AbstractColumn col) {
		return col.getNormalizedValue(this);
	}

	@Override
	public void setValue(AbstractColumn col, Object o) {
		switch(col.getName()) {
			case "PassengerId" -> this.passengerId = (String) o;
			case "Survived" -> this.survived = (double) o;
			case "Pclass" -> this.pClass = (double) o;
			case "Name" -> this.name = (String) o;
			case "Sex" -> this.sex = (EnumSex) o;
			case "Age" -> this.age = (double) o;
			case "SibSp" -> this.sibSp = (double) o;
			case "Parch" -> this.parch = (double) o;
			case "Ticket" -> this.ticket = (String) o;
			case "Fare" -> this.fare = (double) o;
			case "Cabin" -> this.cabin = (String) o;
			case "Embarked" -> this.embarked = (EnumEmbarked) o;
		}
		
	}

}
