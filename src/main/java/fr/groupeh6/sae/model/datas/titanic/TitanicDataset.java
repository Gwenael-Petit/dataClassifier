package fr.groupeh6.sae.model.datas.titanic;

import java.util.List;

import fr.groupeh6.sae.model.Dataset;
import fr.groupeh6.sae.model.IPoint;
import fr.groupeh6.sae.model.columns.Column;
import fr.groupeh6.sae.model.columns.EnumColumn;
import fr.groupeh6.sae.model.columns.NumberColumn;
import fr.groupeh6.sae.model.columns.StringColumn;

public class TitanicDataset extends Dataset{
	
	private static final String NAME = "titanic";
	public final List<Column> COLUMNS = List.of(
			new StringColumn("PassengerId"),
			new NumberColumn("Survived"),
			new NumberColumn("Pclass"),
			new StringColumn("Name"),
			new EnumColumn<EnumSex>("Sex", EnumSex.class),
			new NumberColumn("Age"),
			new NumberColumn("SibSp"),
			new NumberColumn("Parch"),
			new StringColumn("Ticket"),
			new NumberColumn("Fare"),
			new StringColumn("Cabin"),
			new EnumColumn<EnumEmbarked>("Embarked",EnumEmbarked.class)
			);

	public TitanicDataset() {
		super(NAME);
		setColumns(COLUMNS);
	}

	@Override
	public IPoint getType() {
		return new TitanicPoint();
	}

}
