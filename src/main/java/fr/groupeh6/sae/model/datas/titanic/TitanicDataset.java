package fr.groupeh6.sae.model.datas.titanic;

import java.util.List;
import fr.groupeh6.sae.model.AbstractDataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.AbstractColumn;
import fr.groupeh6.sae.model.columns.BooleanColumn;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;
import fr.groupeh6.sae.model.distance.DistanceEuclidienne;

public class TitanicDataset extends AbstractDataset{
	
	private static final String NAME = "titanic";
	private AbstractColumn passId = new StringColumn("PassengerId");
	private AbstractColumn survived = new NumberColumn("Survived");
	private AbstractColumn pClass = new NumberColumn("Pclass");
	private AbstractColumn name = new StringColumn("Name");
	private AbstractColumn sex = new EnumColumn<EnumSex>("Sex", EnumSex.class);
	private AbstractColumn age = new NumberColumn("Age");
	private AbstractColumn sibSp = new NumberColumn("SibSp");
	private AbstractColumn parch = new NumberColumn("Parch");
	private AbstractColumn ticket = new StringColumn("Ticket");
	private AbstractColumn fare = new NumberColumn("Fare");
	private AbstractColumn cabin = new StringColumn("Cabin");
	private AbstractColumn embarked = new EnumColumn<EnumEmbarked>("Embarked",EnumEmbarked.class);

	public TitanicDataset() {
		super(NAME);
		setColumns(List.of(passId, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked));
	}

	@Override
	public IPoint getType() {
		return new TitanicPoint();
	}

	@Override
	public double distance(IPoint p1, IPoint p2) {
		return new DistanceEuclidienne(List.of(survived, pClass, sex, age, sibSp, parch, fare, embarked)).distance(p1, p2);
	}

}
