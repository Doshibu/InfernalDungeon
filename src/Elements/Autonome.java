package Elements;

/**
 * Interface des éléments autonomes.
 * 
 * @author Quentin VIGNAUD
 *
 */
public interface Autonome {
	/**
	 * Indique si l'élément est vivant.
	 * @return true s'il l'est, sinon false.
	 */
	boolean getVivant();
	/**
	 * Tue l'élément.
	 */
	void mourir();
	/**
	 * Effectue un déplacement de l'élément.
	 */
	void seDeplacer();
}
