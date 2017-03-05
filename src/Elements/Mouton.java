package Elements;

import java.util.Random;

import Donjon.Donjon;

/**
 * Personnage Mouton.
 * @author Quentin VIGNAUD
 *
 */
public class Mouton extends Personnage {
	public static String NOMELEMENT = "Mouton";
	
	public static String[] NOMS = {"Jonathan", "Moutmout", "Patrice", "Gontran", "Romuald", "Igor", "Norbert", "Eugene", "Medard"};
	
	public Mouton(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, nomAlea(), loadImage("img/" + NOMELEMENT + ".SUD.png"), false, NOMELEMENT);
	}
	
	public void mourir() {
		setVivant(false);
		setPraticable(true);
		setSprite(loadImage("img/" + NOMELEMENT + ".MORT.png"));
	}
	
	public static String nomAlea() {
		Random tempRand = new Random();
		String tempNom = new String();

		tempNom = NOMS[tempRand.nextInt(NOMS.length)];

		return tempNom;
	}

	/**
	 * Déplace le mouton d'une case ou dans une autre direction aléatoirement (sans vérification de présence de monstre).
	 */
	public void seDeplacer() {
		if (!getVivant()) return;
		if ((int)(Math.random() * 4) == 3 || !avancer()) {
			Direction directionAPrendre = Direction.NORD;
			
			for (int i=(int)(Math.random() * 4) ; i >= 0 ; i--) {
				directionAPrendre = directionAPrendre.next();
			}
			
			setDirection(directionAPrendre, loadImage("img/" + getNomElement() + "." + directionAPrendre + ".png"));
		}
	}
}
