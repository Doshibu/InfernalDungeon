package Elements;

import Donjon.Donjon;

/**
 * Monstre Sorcier.
 * @author Quentin VIGNAUD
 *
 */
public class Sorcier extends Monstre {
	static public String NOMELEMENT = "Sorcier";
	
	public Sorcier(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, "Nabuchodonosor", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, NOMELEMENT);
	}

	public void mourir() {
		setVivant(false);
		setPraticable(true);
		setSprite(loadImage("img/" + NOMELEMENT + ".MORT.png"));
	}
}
