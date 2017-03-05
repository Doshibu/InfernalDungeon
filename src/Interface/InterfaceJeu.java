package Interface;

import java.awt.Adjustable;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Donjon.*;
import Elements.*;

/**
 * Classe d'interface des contôles du jeu.
 * @author Quentin VIGNAUD
 *
 */
public class InterfaceJeu extends JDialog {

	private static final long serialVersionUID = 6274779418110491911L;
	
	/**
	 * Affichage et gestion de la réglette de vitesse.
	 * @author Quentin VIGNAUD
	 *
	 */
	private class RegletteDelai extends JPanel implements AdjustmentListener {

		private static final long serialVersionUID = -5765542304310529010L;
		
		private Donjon donjon;
		private InterfaceJeu interfaceJeu;
		private JLabel label;
		private JScrollBar delai;
		
		public RegletteDelai(Donjon donjon, InterfaceJeu interfaceJeu) {
			this.donjon = donjon;
			this.interfaceJeu = interfaceJeu;
			
			setLayout(new GridLayout(1, 2, 5, 5));
			label = new JLabel("Vitesse");
			delai = new JScrollBar(Adjustable.HORIZONTAL, (int)(donjon.getVitesseSimulation() * 10), 1, 1, 20);
			delai.addAdjustmentListener(this);
			
			add(label);
			add(delai);
		}

		public void adjustmentValueChanged(AdjustmentEvent arg0) {
			float tempDelai = ((float)20.1 - (float)delai.getValue()) / 10;
			donjon.setVitesseSimulation(tempDelai);
			interfaceJeu.rafraichir();
		}
		
		public void verrouiller() {
			delai.setEnabled(false);
		}
		
	}
	
	/**
	 * Affichage et gestion des boutons play/pause et pas-à-pas.
	 * @author Quentin VIGNAUD
	 *
	 */
	private class JouerPause extends JPanel implements ActionListener {

		private static final long serialVersionUID = -2973314694506644672L;
		
		private Donjon donjon;
		private InterfaceJeu interfaceJeu;
		private JButton jouerPause;
		private JButton pas;
		
		public JouerPause(final Donjon donjon, final InterfaceJeu interfaceJeu) {
			this.donjon = donjon;
			this.interfaceJeu = interfaceJeu;
			
			setLayout(new GridLayout(1, 2, 5, 5));
			jouerPause = new JButton("Jouer");
			jouerPause.addActionListener(this);
			pas = new JButton("Jouer un pas");
			pas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					donjon.jouer();
					interfaceJeu.rafraichir();
				}});
			
			add(jouerPause);
			add(pas);
		}


		public void actionPerformed(ActionEvent arg0) {
			donjon.setPlayed(!donjon.isPlayed());
			if (donjon.isPlayed()) {
				jouerPause.setText("Pause");
				pas.setEnabled(false);
			} else {
				jouerPause.setText("Jouer");
				pas.setEnabled(true);
			}
			interfaceJeu.rafraichir();
		}
		
		public void verrouiller() {
			jouerPause.setEnabled(false);
			pas.setEnabled(false);
		}
		
	}
	
	private Donjon donjon;
	
	private JPanel contentPane = new JPanel();
	private JLabel nbPas = new JLabel();
	private JLabel nbPdvMonstre = new JLabel();
	private JLabel nbPersonnagesRestants = new JLabel();
	private JScrollPane scrollListeElements;
	private JList<String> listeElements = new JList<String>();
	private RegletteDelai regletteDelai;
	private JouerPause jouerPause;
	
	private Audio musique = null;
	private Audio sonEchec = null;
	private Audio sonVictoire = null;
	
	/**
	 * 
	 * @param donjon Donjon à gérer.
	 */
	public InterfaceJeu(Donjon donjon) {
		this.donjon = donjon;
		
		regletteDelai = new RegletteDelai(donjon, this);
		jouerPause = new JouerPause(donjon, this);
		
		setContentPane(contentPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(6, 1, 5, 5));
		add(nbPas);
		add(nbPdvMonstre);
		add(nbPersonnagesRestants);
		
		scrollListeElements = new JScrollPane(listeElements, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollListeElements);
		add(regletteDelai);
		add(jouerPause);
		
		rafraichir();
		pack();
		
		//musique = new Audio("son/Thème principal.wav", true);
		musique = new Audio("son/Powerful.wav", true);
		musique.start();
	}
	
	/**
	 * Rafraichit les informations disponibles dans la fenêtre.
	 */
	public void rafraichir() {
		ArrayList<Element> tempElements = donjon.getElements();
		
		//Rafraichissement de la liste.
		listeElements.removeAll();
		Vector<String> tempListeNoms = new Vector<String>();
		for (int i=0 ; i < tempElements.size() ; i++) {
			if (tempElements.get(i) instanceof ElementAutonome && ((ElementAutonome)tempElements.get(i)).getVivant()) {
				tempListeNoms.add(tempElements.get(i).getNom() + " (" + tempElements.get(i).getNomElement() + ")");
			}
		}
		listeElements.setListData(tempListeNoms);
		
		
		int tempPdvMonstre = -100;
		int tempNbPersonnages = 0;
		
		for (int i=0 ; i < tempElements.size() ; i++) {
			if (tempElements.get(i) instanceof Monstre) {
				tempPdvMonstre = ((Monstre)tempElements.get(i)).getPointsDeVie();
			}
			if (tempElements.get(i) instanceof Personnage && ((Personnage)tempElements.get(i)).getVivant()) {
				tempNbPersonnages++;
			}
		}
		
		if (tempPdvMonstre <= 0 || tempNbPersonnages == 0) {
			if (tempPdvMonstre <= 0) {
				nbPdvMonstre.setText("Le monstre est mort !");
				musique.arret();
				if (sonVictoire == null) {
					sonVictoire = new Audio("son/Jerk it out.wav", true);
					sonVictoire.start();
				}
			} else if (tempNbPersonnages == 0) {
				nbPersonnagesRestants.setText("Les personnages sont tous morts !");
				musique.arret();
				if (sonEchec == null) {
					sonEchec = new Audio("son/HAARH.wav", false);
					sonEchec.start();
				}
			}
			donjon.setPlayed(false);
			regletteDelai.verrouiller();
			jouerPause.verrouiller();
		} else {
			nbPdvMonstre.setText("Points de vie monstre : " + tempPdvMonstre);
			nbPersonnagesRestants.setText("Personnages restants : " + tempNbPersonnages);
		}
		nbPas.setText("Nombre de pas : " + donjon.getPas());
	}
}
