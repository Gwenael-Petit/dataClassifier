package fr.groupeh6.sae.columns;

import fr.groupeh6.sae.columns.normalizer.NumberNormalizer;

public class NumberColumn extends Column {

	public NumberColumn(String name) {
		super(name, new NumberNormalizer());
	}

}
