package Elements;

import java.awt.Image;
import java.util.ArrayList;

import Donjon.Donjon;

/**
 * Classe parente de tout élément autonome.
 * @author Quentin VIGNAUD
 *
 */
public abstract class ElementAutonome extends Element implements Autonome {
	private boolean Vivant = true;
	
	public ElementAutonome(Donjon refDonjon, int x, int y, String nom, Image sprite, boolean praticable, String nomElement) {
		super(refDonjon, x, y, nom, sprite, praticable, nomElement);
	}
	
	/**
	 * Fait avancer l'élément d'une case dans sa direction actuelle.
	 * La méthode ne fait rien si un élément non-praticable se trouve devant.
	 * @return true si l'avancement s'est fait, sinon false.
	 */
	public boolean avancer() {
		ArrayList<Element> tempElements = getElementsDevant();
		boolean libre = true;
		for (int i=0 ; i < tempElements.size() && libre; i++) {
			if (tempElements.get(i) != null && !tempElements.get(i).isPraticable()) libre = false;
		}
		
		if (!libre) {
			return false;
		}
		else {
			switch (getDirection()) {
			case NORD :
				setPositionVerticale(getPositionVerticale() - 1);
				break;
			case EST:
				setPositionHorizontale(getPositionHorizontale() + 1);
				break;
			case SUD:
				setPositionVerticale(getPositionVerticale() + 1);
				break;
			case OUEST:
				setPositionHorizontale(getPositionHorizontale() - 1);
				break;
			}
			return true;
		}
	}

	/**
	 * Indique si l'élément est vivant.
	 */
	public boolean getVivant() {
		return Vivant;
	}
	
	/**
	 * Spécifie une nouvelle direction ainsi que l'image y correspondant.
	 * @param direction
	 * @param sprite
	 */
	protected void setDirection(Direction direction, Image sprite) {
		setDirection(direction);
		setSprite(sprite);
	}

	/**
	 * Spécifie si l'élément est vivant.
	 * @param vivant
	 */
	protected void setVivant(boolean vivant) {
		Vivant = vivant;
		
		//On se met en fond.
		if (Vivant == false) getDonjon().addElement(this);
	}
	
}
