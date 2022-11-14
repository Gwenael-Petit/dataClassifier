package fr.groupeh6.sae.model.columns;

public class NotNormalizableException extends RuntimeException {
	
	public NotNormalizableException() {
		super("La colonne n'est pas normalizable");
	}

}
