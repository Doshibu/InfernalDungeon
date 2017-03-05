package Elements;

import java.util.ArrayList;
import java.util.Random;

import Donjon.Donjon;

/**
 * Personnage Guerrier.
 * @author Quentin VIGNAUD
 *
 */
public class Guerrier extends Personnage {
	public static String NOMELEMENT = "Guerrier";
	
	public static String[] NOMS = {"Warrior", "Henry", "Le Grand", "Kenny", "Jules", "Arthur", "Clement", "Guillaume", "Quentin"};
	
	public Guerrier(Donjon refDonjon, int x, int y) {
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

		tempNom = NOMS[tempRand.nextInt(NOMS.length)] + "-"
				+ NOMS[tempRand.nextInt(NOMS.length)];

		return tempNom;
	}

	/**
	 * Déplacement en fonction de la présence ou non du monstre a proximité, essaye de "se faire suivre" sans être tué.
	 */
	public void seDeplacer() {
		if (!getVivant()) return;
		if (voitMonstre() && directionMonstre() == getDirection()) {
			setDirection(getDirection().next(), loadImage("img/" + getNomElement() + "." + getDirection().next() + ".png"));
			return;
		}
		else if (voitMonstre()) {
			Direction tempDirection = directionMonstre();
			switch (tempDirection) {
			case EST:
				setDirection(Direction.OUEST, loadImage("img/" + NOMELEMENT
						+ ".OUEST.png"));
				break;
			case NORD:
				setDirection(Direction.SUD, loadImage("img/" + NOMELEMENT
						+ ".SUD.png"));
				break;
			case OUEST:
				setDirection(Direction.EST, loadImage("img/" + NOMELEMENT
						+ ".EST.png"));
				break;
			case SUD:
				setDirection(Direction.NORD, loadImage("img/" + NOMELEMENT
						+ ".NORD.png"));
				break;
			}
			return;
		}
		else
		{
			if (!avancer()) {
				
				ArrayList<Element> tempElements = getElementsDevant();
				boolean deplacementPossible = true;
				for (int i=0 ; i < tempElements.size() && deplacementPossible ; i++) {
					if (tempElements.get(i) instanceof Deplacable
							&& ((Deplacable)tempElements.get(i)).estDeplacable(getDirection())) {
						deplacementPossible = true;
						if (tempElements.get(i) instanceof Deplacable && ((Deplacable)tempElements.get(i)).estDeplacable(getDirection())) {
							((Deplacable)tempElements.get(i)).deplacer(getDirection());
						}
					} else deplacementPossible = false;
				}
				
				if (deplacementPossible) {
					avancer();
				} else {
					Direction directionAPrendre = getDirection();
					int tentatives = -1;
					do {
						tentatives++;
						deplacementPossible = true;
						for (int i = (int) (Math.random() * 4); i >= 0; i--) {
							directionAPrendre = directionAPrendre.next();
						}
						setDirection(directionAPrendre, loadImage("img/"
								+ getNomElement() + "." + directionAPrendre
								+ ".png"));
						tempElements = getElementsDevant();
						
						for (int i=0 ; i < tempElements.size() && deplacementPossible ; i++) {
							if (!((tempElements.get(i) instanceof Deplacable && ((Deplacable) tempElements.get(i))
									.estDeplacable(getDirection()))
									|| tempElements.get(i) == null || (tempElements.get(i) != null && tempElements.get(i)
									.isPraticable()))) {
								deplacementPossible = true;
							} else deplacementPossible = false;
						}
						
					} while (!deplacementPossible && tentatives < 5);
				}
			}
		}
	}

}
