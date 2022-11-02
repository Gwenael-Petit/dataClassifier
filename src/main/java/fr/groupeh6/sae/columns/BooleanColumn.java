package fr.groupeh6.sae.columns;

import fr.groupeh6.sae.columns.normalizer.BooleanNormalizer;

public class BooleanColumn extends Column {

	public BooleanColumn(String name) {
		super(name, new BooleanNormalizer());
	}

}
