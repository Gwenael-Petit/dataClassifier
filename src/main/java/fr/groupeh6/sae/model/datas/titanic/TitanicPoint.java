package fr.groupeh6.sae.model.datas.titanic;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import fr.groupeh6.sae.model.Factory;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

public class TitanicPoint implements IPoint {

	@CsvBindByName(column = "PassengerId")
	private String passengerId;
	
	@CsvBindByName(column = "Survived")
	private double survived;
	
	@CsvBindByName(column = "Pclass")
	private double placeClass;
	
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
	
	public TitanicPoint(String passengerId, double survived, int placeClass, String name, EnumSex sex, int age, int sibSp, int parch, String ticket, double fare, String cabin, EnumEmbarked embarked ) {
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
		List<Column> toDist = new ArrayList<>();
		String[] cols = new String[] {"Survived","Pclass","Sex","Age"};
		for(String c : cols) toDist.add(Factory.getInstance().getColumn(c));
		return new DistanceEuclidienne().distance(this,other,toDist);
	}

	@Override
	public void setValue(Column col, Object o) {
		switch(col.getName()) {
		case "PassengerId" : this.passengerId = (String) o ;
		case "Survived" : this.survived = (double) o;
		case "Pclass" : this.placeClass = (double) o;
		case "Name" : this.name = (String) o;
		case "Sex" : this.sex = (EnumSex) o;
		case "Age" : this.age = (double) o;
		case "SibSp" : this.sibSp = (double) o;
		case "Parch" : this.parch = (double) o;
		case "Ticket" : this.ticket = (String) o;
		case "Fare" : this.fare = (double) o;
		case "Cabin" : this.cabin = (String) o;
		case "Embarked" : this.embarked = (EnumEmbarked) o;
		}
		
	}

}
