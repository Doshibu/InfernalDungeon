package Elements;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import AffichageDePlateau.PlateauPiece;
import Donjon.Donjon;

/**
 * Classe d'élément.
 * @author Quentin VIGNAUD
 *
 */
public abstract class Element implements PlateauPiece {
	/**
	 * Direction de l'élément.
	 */
	private Direction direction = Direction.SUD;
	/**
	 * Nom « personnel » de l'élément.
	 */
	private String nom;
	/**
	 * Nom général de l'élément.
	 */
	private String nomElement;
	/**
	 * Indique si l'élément est praticable (càd si on peut passer dessus).
	 */
	private boolean praticable;
	/**
	 * Donjon auquel appartient l'élément.
	 */
	private Donjon refDonjon;
	/**
	 * Image de l'élément.
	 */
	private Image sprite;
	/**
	 * Position en X.
	 */
	private int x;
	/**
	 * Position en Y.
	 */
	private int y;
	
	/**
	 * 
	 * @param refDonjon Donjon auquel l'élément se réfère.
	 * @param x Position en X.
	 * @param y Position en Y.
	 * @param nom Nom « personnel ».
	 * @param sprite Image de représentation.
	 * @param praticable Indique si l'élément est praticable (càd si on peut passer dessus).
	 * @param nomElement Nom général de l'élément.
	 */
	public Element(Donjon refDonjon, int x, int y, String nom, Image sprite, boolean praticable, String nomElement) {
		super();
		this.nom = nom;
		this.praticable = praticable;
		this.refDonjon = refDonjon;
		this.sprite = sprite;
		this.nomElement = nomElement;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return La direction actuelle de l'élément.
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * 
	 * @return Le donjon référent.
	 */
	public Donjon getDonjon() {
		return this.refDonjon;
	}
	
	/**
	 * 
	 * @param direction
	 * @return Les éléments situés sur la case d'a côté l'élément dans la direction spécifiée.
	 */
	public ArrayList<Element> getElementsCote(Direction direction) {
		int positionX = getPositionHorizontale();
		int positionY = getPositionVerticale();
		switch (direction) {
		case NORD :
			positionY--;
			break;
		case EST:
			positionX++;
			break;
		case SUD:
			positionY++;
			break;
		case OUEST:
			positionX--;
			break;
		}
		return getDonjon().getElements(positionX, positionY);
	}
	
	/**
	 * 
	 * @return Les éléments situés sur la case devant l'élément spécifié.
	 * @see getElementsDirection()
	 */
	public ArrayList<Element> getElementsDevant() {
		return getElementsCote(getDirection());
	}
	
	/**
	 * @return L'image représentant actuellement l'élément.
	 */
	public Image getImage() {
		return this.sprite;
	}
	
	/**
	 * Charge l'image indiquée.
	 * @param path Chemin du fichier image.
	 * @return L'image chargée.
	 */
	static public Image loadImage(String path) {
		Image tempImage = null;
		//System.out.println("Chargement de " + path);
		try {
			tempImage = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	/**
	 * 
	 * @return Le nom « personnel » de l'objet.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * @return Le nom général de l'élément.
	 */
	public String getNomElement() {
		return this.nomElement;
	}
	
	/**
	 * @return La position en X.
	 */
	public int getPositionHorizontale() {
		return this.x;
	}
	
	/**
	 * @return La position en Y.
	 */
	public int getPositionVerticale() {
		return this.y;
	}
	
	/**
	 * 
	 * @return Si l'élément est praticable.
	 */
	public boolean isPraticable() {
		return praticable;
	}
	
	/**
	 * Spécifie la direction de l'élément.
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Spécifie la praticabilité de l'élément.
	 * @param praticable
	 */
	public void setPraticable(boolean praticable) {
		this.praticable = praticable;
	}
	
	/**
	 * Spécifie la position en X.
	 * @param x
	 */
	public void setPositionHorizontale(int x) {
		this.x = x;
	}
	
	/**
	 * Spécifie la position en Y.
	 * @param y
	 */
	public void setPositionVerticale(int y) {
		this.y = y;
	}
	
	/**
	 * Spécifie l'image de l'élément.
	 * @param sprite
	 */
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
}
