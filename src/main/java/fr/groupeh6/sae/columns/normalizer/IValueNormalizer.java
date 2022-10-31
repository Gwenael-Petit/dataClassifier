package fr.groupeh6.sae.columns.normalizer;

/**
 * Decrit un <i>Normaliseur</i>, c'est a dire un objet qui peut transformer une
 * valeur d'une colone en une valeur entre 0 et 1.
 *
 * Chaque <i>normaliseur</i> est classe qui implemente l'interface
 * <i>IValueNormalizer</i>.
 *
 * Cette interface inclus un type enumere <i>NormalizerTypes</i> listant tous
 * les normaliseurs connus.
 */
public interface IValueNormalizer {
	
	/**
	 * Retourne la valeur en parametre normalisee (entre 0 et 1).
	 */
	public double normalize(Object value);

	/**
	 * De-normalise la valeur en parametre (qui est entre 0 et 1) Retourne la «
	 * vraie » valeur correspondante pour la colonne associee au normaliseur
	 */
	public Object denormalize(double value);
}