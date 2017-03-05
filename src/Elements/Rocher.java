package Elements;

import java.util.ArrayList;

import Donjon.Donjon;

/**
 * Élément Rocher, non praticable mais déplacable.
 * @author Quentin VIGNAUD
 *
 */
public class Rocher extends Element implements Deplacable {
	
	public static String NOMELEMENT = "Rocher";
	
	public Rocher(Donjon RefDonjon, int x, int y) {
		super(RefDonjon, x, y, "Rocher", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, "Rocher");
	}

	/**
	 * Déplace le rocher dans la direction indiquée.
	 * Ne fait rien si se déplacement n'est pas possible.
	 * @param direction
	 */
	public void deplacer(Direction direction) {
		if (estDeplacable(direction)) {
			switch (direction) {
			case NORD :
				setPositionVerticale(getPositionVerticale() - 1);
				break;
			case EST:
				setPositionHorizontale(getPositionHorizontale() + 1);
				break;
			case SUD:
				setPositionVerticale(getPositionVerticale() + 1);
				break;
			case OUEST:
				setPositionHorizontale(getPositionHorizontale() - 1);
				break;
			}
		}
	}

	/**
	 * Indique si le rocher est déplaçable dans la direction indiquée.
	 * @param direction
	 * @return true s'il l'est, sinon false.
	 */
	public boolean estDeplacable(Direction direction) {
		ArrayList<Element> tempElements = getElementsCote(direction);
		boolean libre = true;
		
		for (int i=0 ; i < tempElements.size() && libre ; i++) {
			if (tempElements.get(i) != null) {
				libre = tempElements.get(i).isPraticable();
			}
		}
		
		return libre;
	}
}
