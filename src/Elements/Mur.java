package Elements;

import Donjon.Donjon;

/**
 * Élément Mur, non praticable et non déplaçable.
 * @author Quentin VIGNAUD
 *
 */
public class Mur extends Element {
	public static String NOMELEMENT = "Mur";
	
	public Mur(Donjon RefDonjon, int x, int y) {
		super(RefDonjon, x, y, "Mur", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, "Mur");
	}
}
