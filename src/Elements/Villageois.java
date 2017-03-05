package Elements;

import java.util.ArrayList;
import java.util.Random;

import Donjon.Donjon;

/**
 * Personnage Villageois.
 * 
 * @author Quentin VIGNAUD
 * 
 */
public class Villageois extends Personnage {
	public static String NOMELEMENT = "Villageois";

	public static String[] NOMS = { "Jean", "Patrick", "Kevin", "Flibantin",
			"Michel", "Fabien", "Remy", "Antoine", "Julien" };

	private boolean aVuMonstre = false;

	public Villageois(Donjon refDonjon, int x, int y) {
		super(refDonjon, x, y, nomAlea(), loadImage("img/" + NOMELEMENT
				+ ".SUD.png"), false, NOMELEMENT);

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
	 * Déplace le villageois en fonction de la présence ou non du monstre,
	 * l'évite à tout prix.
	 */
	public void seDeplacer() {
		if (!getVivant())
			return;
		if (voitMonstre() && !aVuMonstre) {
			aVuMonstre = true;
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
		} else if (!voitMonstre() && aVuMonstre) {
			aVuMonstre = false;
		}

		if (!avancer()) {

			ArrayList<Element> tempElements = getElementsDevant();
			boolean deplacementPossible = true;
			for (int i = 0; i < tempElements.size() && deplacementPossible; i++) {
				if (tempElements.get(i).isPraticable()
						|| (tempElements.get(i) instanceof Deplacable && ((Deplacable) tempElements
								.get(i)).estDeplacable(getDirection()))) {
					deplacementPossible = true;
					if (tempElements.get(i) instanceof Deplacable
							&& ((Deplacable) tempElements.get(i))
									.estDeplacable(getDirection())) {
						((Deplacable) tempElements.get(i))
								.deplacer(getDirection());
					}
				} else
					deplacementPossible = false;
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

					for (int i = 0; i < tempElements.size()
							&& deplacementPossible; i++) {
						if (!((tempElements.get(i) instanceof Deplacable && ((Deplacable) tempElements
								.get(i)).estDeplacable(getDirection()))
								|| tempElements.get(i) == null || (tempElements
								.get(i) != null && tempElements.get(i)
								.isPraticable()))) {
							deplacementPossible = true;
						} else
							deplacementPossible = false;
					}

				} while (!deplacementPossible && tentatives < 5);
			}
		}
	}
}
