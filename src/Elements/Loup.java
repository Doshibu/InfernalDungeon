package Elements;

import Donjon.Donjon;

/**
 * Monstre Loup.
 * @author Quentin VIGNAUD
 *
 */
public class Loup extends Monstre {
	static public String NOMELEMENT = "Loup";
	
	public Loup(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, "Graou", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, NOMELEMENT);
	}

	public void mourir() {
		setVivant(false);
		setPraticable(true);
		setSprite(loadImage("img/" + NOMELEMENT + ".MORT.png"));
	}
}
