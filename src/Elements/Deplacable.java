package Elements;

/**
 * Interface des éléments déplaçables.
 * @author Quentin VIGNAUD
 *
 */
public interface Deplacable {
	/**
	 * Déplace l'élément dans la direction indiquée.
	 * Ne fait rien si se déplacement n'est pas possible.
	 * @param direction
	 */
	void deplacer(Direction direction);
	/**
	 * Indique si l'élément est déplaçable dans la direction indiquée.
	 * @param direction
	 * @return true s'il l'est, sinon false.
	 */
	boolean estDeplacable(Direction direction);
}
