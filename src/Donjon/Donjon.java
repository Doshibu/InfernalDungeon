package Donjon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import AffichageDePlateau.Plateau;
import Elements.*;
import Interface.*;

/**
 * Classe principale du programme, gère l'affichage des éléments et leur répartition.
 * 
 * @author Quentin VIGNAUD
 */
public class Donjon {

	/**
	 * Procédure d'exécution du programme.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfaceConfiguration interfaceConfig = new InterfaceConfiguration();
			interfaceConfig.setVisible(true);
			
			Donjon theDonjon = interfaceConfig.getDonjon();
			
			/*final Donjon theDonjon = new Donjon(20, 20, (float)1.0);

			ArrayList<Element> theElements = new ArrayList<Element>();
			theElements.add(new Sorcier(theDonjon, 0, 0));
			for (int i = 0; i < 5; i++) {
				theElements.add(new Mur(theDonjon, 0, 0));
				//Personnage tempPersonnage = new Villageois(theDonjon, 0, 0);
				//theElements.add(tempPersonnage);
				//tempPersonnage.seDeplacer();
				theElements.add(new Mouton(theDonjon, 0, 0));
				theElements.add(new Villageois(theDonjon, 0, 0));
				theElements.add(new Guerrier(theDonjon, 0, 0));
				theElements.add(new Rocher(theDonjon, 0, 0));
			}
			theDonjon.placerElements(theElements);
			theDonjon.setPlayed(true);*/

			InterfaceJeu interfaceJeu = new InterfaceJeu(theDonjon);
			interfaceJeu.setVisible(true);

			for (;;) {
				theDonjon.afficher();
				if (theDonjon.isPlayed()) theDonjon.jouer();
				interfaceJeu.rafraichir();
				try {
					Thread.sleep((long) (theDonjon.vitesseSimulation * 100));
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cases du plateau (arrière-plan).
	 */
	private Case[][] cases;
	/**
	 * Liste des éléments du plateau.
	 */
	private ArrayList<Element> elements;
	/**
	 * Plateau.
	 */
	private Plateau fenetrePlateau;
	/**
	 * Objet de représentation du hasard.
	 */
	private Random hasard;
	/**
	 * Indicateur de jeu.
	 * @see setPlayed()
	 */
	private boolean played;
	/**
	 * Nombre de pas exécutés.
	 */
	private int nbPas;
	/**
	 * Dimension du donjon sur l'axe X.
	 */
	private int tailleX;
	/**
	 * Dimension du donjon sur l'axe Y.
	 */
	private int tailleY;
	/**
	 * Vitesse de simulation.
	 */
	private float vitesseSimulation;

	/**
	 * Crée un objet donjon. Par défaut un donjon applique sur ses contours des murs et ne joue pas.
	 * 
	 * @param TailleX Taille sur l'axe X.
	 * @param TailleY Taille sur l'axe Y.
	 * @param VitesseSimulation Vitesse à utiliser pour l'exécution.
	 */
	public Donjon(int TailleX, int TailleY, float VitesseSimulation) {
		tailleX = TailleX;
		tailleY = TailleY;
		vitesseSimulation = VitesseSimulation;

		this.cases = new Case[this.tailleX][this.tailleY];
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				setXY(x, y, new Case(loadImage("img/Sol.png")));
			}
		}

		this.hasard = new Random();
		this.fenetrePlateau = new Plateau(this.tailleX, this.tailleY,
				this.cases);
		this.played = false;
		this.nbPas = 0;

		elements = new ArrayList<Element>();
		for (int x = 0; x < tailleX; x++) {
			for (int y = 0; y < tailleY; y++) {
				if (x == 0 || x == tailleX - 1 || y == 0 || y == tailleY - 1) {
					addElement(new Mur(this, x, y));
				}
			}
		}
	}

	/**
	 * Ajoute un élément au donjon.
	 * 
	 * @param element Élément à ajouter.
	 */
	public void addElement(Element element) {
		if (!this.elements.contains(element)) {
			this.elements.add(element);
		}
		
		fenetrePlateau.placerPiece(element);
	}

	/**
	 * Rafraichit l'afficgae du plateau.
	 */
	public void afficher() {
		this.fenetrePlateau.rafraichir();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return La case à la position spécifiée.
	 */
	public Case getCase(int x, int y) {
		return this.cases[x][y];
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return Les éléments à la position spécifiée.
	 */
	public ArrayList<Element> getElements(int x, int y) {
		ArrayList<Element> tempElements = new ArrayList<Element>();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getPositionHorizontale() == x
					&& elements.get(i).getPositionVerticale() == y)
				tempElements.add(elements.get(i));
		}
		return tempElements;
	}

	/**
	 * 
	 * @return Tous les éléments du plateau.
	 */
	public ArrayList<Element> getElements() {
		return this.elements;
	}

	/**
	 * 
	 * @return Nombre de pas joués dpuis le début de l'exécution.
	 */
	public int getPas() {
		return this.nbPas;
	}

	/**
	 * 
	 * @return Taille sur l'axe X.
	 */
	public int getTailleX() {
		return this.tailleX;
	}

	/**
	 * 
	 * @return Taille sur l'axe Y.
	 */
	public int getTailleY() {
		return this.tailleY;
	}

	/**
	 * 
	 * @return Vitesse de simulation.
	 */
	public float getVitesseSimulation() {
		return this.vitesseSimulation;
	}

	/**
	 * Indique si le jeu est en cours.
	 * @return true si le jeu est en cours, ou faux s'il est en pause.
	 */
	public boolean isPlayed() {
		return this.played;
	}

	/**
	 * Effectue un tour de jeu.
	 * @see isPlayed()
	 */
	public void jouer() {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) instanceof Autonome)
				((Autonome) elements.get(i)).seDeplacer();
		}
		nbPas++;
	}

	/**
	 * Charge l'image indiquée.
	 * @param path Chemin du fichier image.
	 * @return L'image chargée.
	 */
	static public Image loadImage(String path) {
		Image tempImage = null;
		try {
			tempImage = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	/**
	 * Place un élément au hasard dans le donjon.
	 * @param element Élément à placer.
	 */
	public void placerElement(Element element) {
		int tempX = 0;
		int tempY = 0;
		boolean libre;
		do {
			libre = true;
			tempX = hasard.nextInt(tailleX);
			tempY = hasard.nextInt(tailleY);
			
			ArrayList<Element> tempElements = getElements(tempX, tempY);
			for (int i=0 ; i < tempElements.size() && libre ; i++) {
				libre = (tempElements.get(i) == null || (tempElements.get(i) != null && tempElements.get(i).isPraticable()));
			}
		} while (!libre);
		element.setPositionHorizontale(tempX);
		element.setPositionVerticale(tempY);
		addElement(element);
	}

	/**
	 * Place des éléments au hasard sur le plateau.
	 * @param elements Éléments à placer.
	 */
	public void placerElements(ArrayList<Element> elements) {
		for (int i = 0; i < elements.size(); i++) {
			placerElement(elements.get(i));
		}
	}

	/**
	 * 
	 * @return Le nombre de personnages vivants restants.
	 */
	public int personnageRestants() {
		int tempNb = 0;

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) instanceof Personnage
					&& ((Autonome) elements.get(i)).getVivant())
				tempNb++;
		}

		return tempNb;
	}

	/**
	 * Spécifie si le jeu est en cours ou non.
	 * @param played true pour jouer, false pour la pause.
	 */
	public void setPlayed(boolean played) {
		this.played = played;
	}

	/**
	 * Spécifie la vitesse de simulation.
	 * @param VitesseSimulation
	 */
	public void setVitesseSimulation(float VitesseSimulation) {
		this.vitesseSimulation = VitesseSimulation;
	}

	/**
	 * Assigne une case au donjon à la position indiquée.
	 * @param x
	 * @param y
	 * @param Case
	 */
	public void setXY(int x, int y, Case Case) {
		this.cases[x][y] = Case;
	}

}
