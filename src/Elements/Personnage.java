package Elements;

import java.awt.Image;
import java.util.ArrayList;

import Donjon.Donjon;

/**
 * Classe parente de tout personnage.
 * @author Quentin VIGNAUD
 *
 */
public abstract class Personnage extends ElementAutonome {

	/**
	 * Classe de représentation de coordonnées (pour les algorithmes de positionnement et de détection).
	 * @author Quentin VIGNAUD
	 *
	 */
	private class Coor {
		public int x;
		public int y;

		public Coor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public Personnage(Donjon refDonjon, int x, int y, String nom, Image sprite,
			boolean praticable, String nomElement) {
		super(refDonjon, x, y, nom, sprite, praticable, nomElement);
	}

	/**
	 * Indique si le personnage voit un monstre.
	 * @return true s'il le voit, sinon false.
	 */
	protected boolean voitMonstre() {
		ArrayList<Coor> tempEmplacements = new ArrayList<Coor>();
		tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
				getPositionVerticale() + 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
				getPositionVerticale() + 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
				getPositionVerticale() - 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
				getPositionVerticale() - 1));
		switch (getDirection()) {
		case NORD:
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			break;
		case EST:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			break;
		case SUD:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			break;
		case OUEST:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			break;
		}

		// System.out.println("Coordonnées : " + getPositionHorizontale() + " "
		// + getPositionVerticale());
		for (int i = 0; i < tempEmplacements.size(); i++) {
			ArrayList<Element> tempElements = getDonjon().getElements(
					tempEmplacements.get(i).x, tempEmplacements.get(i).y);
			for (int j = 0; j < tempElements.size(); j++) {
				Element tempElement = tempElements.get(j);
				// System.out.println("Coordonnées de vision : " +
				// tempEmplacements.get(i).x + " " + tempEmplacements.get(i).y);
				if (tempElement != null && tempElement instanceof Monstre) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Indique la direction du monstre en vue.
	 * @return La direction du monstre, cette valeur est indéterminée si aucun monstre n'est en vue.
	 */
	protected Direction directionMonstre() {
		ArrayList<Coor> tempEmplacements = new ArrayList<Coor>();
		tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
				getPositionVerticale() + 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
				getPositionVerticale() + 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
				getPositionVerticale() - 1));
		tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
				getPositionVerticale() - 1));
		switch (getDirection()) {
		case NORD:
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			break;
		case EST:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			break;
		case SUD:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() + 2));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			break;
		case OUEST:
			tempEmplacements.add(new Coor(getPositionHorizontale() + 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() + 2,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 1));
			tempEmplacements.add(new Coor(getPositionHorizontale(),
					getPositionVerticale() - 2));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 1,
					getPositionVerticale()));
			tempEmplacements.add(new Coor(getPositionHorizontale() - 2,
					getPositionVerticale()));
			break;
		}

		for (int i = 0; i < tempEmplacements.size(); i++) {
			ArrayList<Element> tempElements = getDonjon().getElements(
					tempEmplacements.get(i).x, tempEmplacements.get(i).y);
			for (int j = 0; j < tempElements.size(); j++) {
				Element tempElement = tempElements.get(j);
				if (tempElement != null && tempElement instanceof Monstre) {
					if (tempElement.getPositionHorizontale() > this
							.getPositionHorizontale())
						return Direction.EST;
					else if (tempElement.getPositionHorizontale() < this
							.getPositionHorizontale())
						return Direction.OUEST;
					else if (tempElement.getPositionVerticale() > this
							.getPositionVerticale())
						return Direction.SUD;
					else if (tempElement.getPositionVerticale() < this
							.getPositionVerticale())
						return Direction.NORD;
				}
			}
		}
		return Direction.NORD;
	}
}
