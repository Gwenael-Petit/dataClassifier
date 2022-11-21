package fr.groupeh6.sae.model;

import fr.groupeh6.sae.model.columns.Column;

/**
 * Decrit un Point (ou donnee, ou ligne) dans un DataSet.
 */
public interface IPoint {
	/**
	 * Retourne la valeur de ce point pour la colonne en parametre.
	 *
	 * Note, on aurait pu utiliser une interface generique (parametree avec un
	 * type), mais cela complique significativement d'autres parties du code.
	 */
	public Object getValue(Column col);

	/**
	 * Retourne la valeur de ce point normalisee pour la colonne en parametre.
	 *
	 * La normalisation se fait avec le <i>normaliseur</i> de la colonne. Si la
	 * colonne n'est pas normalisable, le comportement n'est pas defini.
	 */
	public double getNormalizedValue(Column xcol);
	
	/**
	 * Modifie la valeur du point pour la colonne en parametre
	 */
	
	public void setValue(Column col, Object o);
}
