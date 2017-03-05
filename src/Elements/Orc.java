package Elements;

import Donjon.Donjon;

/**
 * Monstre Orc
 * @author Quentin VIGNAUD
 *
 */
public class Orc extends Monstre {
	static public String NOMELEMENT = "Orc";
	
	public Orc(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, "Schlagwug", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, NOMELEMENT);
	}

	public void mourir() {
		setVivant(false);
		setPraticable(true);
		setSprite(loadImage("img/" + NOMELEMENT + ".MORT.png"));
	}
}
