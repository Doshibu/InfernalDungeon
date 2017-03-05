package Elements;

import java.awt.Image;
import java.util.ArrayList;

import Donjon.Donjon;

/**
 * Classe parente de tout monstre.
 * 
 * @author Quentin VIGNAUD
 * 
 */
public abstract class Monstre extends ElementAutonome {
	private int pointsDeVie = 100;

	private int attenteTours = 0;
	private int foisADroite = 0;
	private boolean vientDeManger = false;

	public Monstre(Donjon refDonjon, int x, int y, String nom, Image sprite,
			boolean praticable, String nomElement) {
		super(refDonjon, x, y, nom, sprite, praticable, nomElement);
	}

	/**
	 * 
	 * @return Nombre de points de vie du monstre.
	 */
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	/**
	 * Déplace le monstre, selon les spécifications du cahier des charges.
	 */
	public void seDeplacer() {
		if (!getVivant())
			return;
		if (attenteTours > 0) {
			attenteTours--;
			return;
		}
		if (foisADroite == -1) {
			setDirection(getDirection().next(), loadImage("img/"
					+ getNomElement() + "." + getDirection().next() + ".png"));
			foisADroite++;
			return;
		}
		if (vientDeManger == true) {
			vientDeManger = false;
			attenteTours = 3;
			avancer();
			return;
		}

		Element tempElement = null;
		ArrayList<Element> tempElements = getElementsDevant();
		for (int i = 0; i < tempElements.size() && tempElement == null; i++) {
			if (tempElements.get(i) != null
					&& tempElements.get(i) instanceof Personnage
					&& ((Personnage) tempElements.get(i)).getVivant()) {
				tempElement = tempElements.get(i);
			}
		}

		tempElements = getElementsCote(getDirection().next());
		for (int i = 0; i < tempElements.size() && tempElement == null; i++) {
			if (tempElements.get(i) != null
					&& tempElements.get(i) instanceof Personnage
					&& ((Personnage) tempElements.get(i)).getVivant()) {
				tempElement = tempElements.get(i);
				setDirection(getDirection().next(), loadImage("img/"
						+ getNomElement() + "." + getDirection().next()
						+ ".png"));
			}
		}

		tempElements = getElementsCote(getDirection().before());
		for (int i = 0; i < tempElements.size() && tempElement == null; i++) {
			if (tempElements.get(i) != null
					&& tempElements.get(i) instanceof Personnage
					&& ((Personnage) tempElements.get(i)).getVivant()) {
				tempElement = tempElements.get(i);
				setDirection(getDirection().before(), loadImage("img/"
						+ getNomElement() + "." + getDirection().before()
						+ ".png"));
			}
		}

		if (tempElement != null) {
			tuer();
			return;
		}

		if (avancer()) {
			setPointsDeVie(getPointsDeVie() - 1);
		} else {
			if (foisADroite < 3) {
				setDirection(getDirection().next(), loadImage("img/"
						+ getNomElement() + "." + getDirection().next()
						+ ".png"));
				foisADroite++;
			} else {
				setDirection(getDirection().next(), loadImage("img/"
						+ getNomElement() + "." + getDirection().next()
						+ ".png"));
				foisADroite = -1;
			}
		}
	}

	/**
	 * Spécifie le nouveau score en points de vie du monstre.
	 * Si les points de vie sont égaux ou inférieurs à 0, appelle la méthode mourir().
	 * @param pointsDeVie
	 */
	protected void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
		if (this.pointsDeVie <= 0) {
			this.pointsDeVie = 0;
			this.mourir();
		}
	}

	/**
	 * Tue le personnage situé sur la case devant, et attribue true à vientDeManger.
	 */
	public void tuer() {
		ArrayList<Element> tempElements = getElementsDevant();
		for (int i = 0; i < tempElements.size(); i++) {
			Element tempElement = tempElements.get(i);
			if (tempElement != null && tempElement instanceof Personnage
					&& ((Personnage) tempElement).getVivant()) {
				((Personnage) tempElement).mourir();
				setPointsDeVie(getPointsDeVie() + 20);
				vientDeManger = true;
				return;
			}
		}
	}

}
