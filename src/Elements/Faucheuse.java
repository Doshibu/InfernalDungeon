package Elements;

import Donjon.Donjon;

/**
 * Monstre Faucheuse.
 * @author Quentin VIGNAUD
 *
 */
public class Faucheuse extends Monstre {
	static public String NOMELEMENT = "Faucheuse";
	
	public Faucheuse(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, "Necrophia", loadImage("img/" + NOMELEMENT + ".SUD.png"), false, NOMELEMENT);
	}

	public void mourir() {
		setVivant(false);
		setPraticable(true);
		setSprite(loadImage("img/" + NOMELEMENT + ".MORT.png"));
	}
}
