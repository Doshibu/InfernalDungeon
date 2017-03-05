package Donjon;

import java.awt.Image;

import AffichageDePlateau.PlateauCase;

/**
 * Représente une case du plateau.
 * 
 * @author quentin
 *
 */
public class Case implements PlateauCase {
	/**
	 * Image de la case
	 */
	private Image sprite;
	
	/**
	 * 
	 * @param Sprite Spécifie l'image à utiliser.
	 */
	public Case(Image Sprite) {
		this.sprite = Sprite;
	}

	/**
	 * @return L'image de la case.
	 */
	public Image getImage() {
		return sprite;
	}

	/**
	 * Fixe l'image de la case.
	 * @param sprite Image à utiliser.
	 */
	public void setImage(Image sprite) {
		this.sprite = sprite;
	}
	
}
