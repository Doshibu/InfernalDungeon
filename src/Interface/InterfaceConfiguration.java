package Interface;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JFormattedTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Donjon.Donjon;
import Elements.*;

import java.awt.event.ActionListener;

/**
 * Classe permettant la configuration du Donjon.
 * @author Clément GUIHÉNEUF
 *
 */

public class InterfaceConfiguration extends JDialog implements ActionListener {

	private static final long serialVersionUID = -9005406748854911949L;

	private final JPanel contentPanel = new JPanel();

	private Donjon donjon = null;

	private ArrayList<Element> elements = null;

	private ArrayList<InterfaceElement> obstacles;

	private ArrayList<InterfaceElement> personnages;

	private ArrayList<String> listeMonstres;

	private InterfaceMonstre monstres;

	private JFormattedTextField largeur;

	private JFormattedTextField hauteur;

	public InterfaceConfiguration() {

		setContentPane(contentPanel);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Configuration");
		InterfaceElement IRocher = new InterfaceElement("Rocher");
		InterfaceElement IMur = new InterfaceElement("Mur");
		InterfaceElement IGuerrier = new InterfaceElement("Guerrier");
		InterfaceElement IVillageois = new InterfaceElement("Villageois");
		InterfaceElement IMouton = new InterfaceElement("Mouton");

		obstacles = new ArrayList<InterfaceElement>();
		obstacles.add(IRocher);
		obstacles.add(IMur);

		personnages = new ArrayList<InterfaceElement>();
		personnages.add(IGuerrier);
		personnages.add(IVillageois);
		personnages.add(IMouton);

		listeMonstres = new ArrayList<String>();
		listeMonstres.add("Orc");
		listeMonstres.add("Faucheuse");
		listeMonstres.add("Sorcier");
		listeMonstres.add("Loup");

		monstres = new InterfaceMonstre(listeMonstres);

		getContentPane().setLayout(new GridLayout(5, 1));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(45, 20, 45, 20));
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 4, 5, 5));

		JLabel lblNewLabel = new JLabel("Largeur");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		largeur = new JFormattedTextField();
		largeur.setValue(20);
		panel_1.add(largeur);

		JLabel lblHauteur = new JLabel("Hauteur");
		lblHauteur.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblHauteur);

		hauteur = new JFormattedTextField();
		hauteur.setValue(20);
		panel_1.add(hauteur);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 5, 5));

		for (int i = 0; i < obstacles.size(); i++) {

			panel_2.add(obstacles.get(i));

		}

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 5, 5));

		for (int i = 0; i < personnages.size(); i++) {

			panel_3.add(personnages.get(i));

		}

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 1, 5, 5));
		panel.add(monstres);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(45, 20, 45, 20));
		getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 5, 5));

		JButton valider = new JButton("Valider");
		valider.addActionListener(this);
		panel_4.add(valider);

		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 	{
			System.exit(0);
		}
		});
		panel_4.add(annuler);

		this.pack();
	}

	public void actionPerformed(ActionEvent action) {
		/**
		 * Réaction au clic sur le bouton Valider
		 * Il crée le Donjon
		 */
		donjon = new Donjon(Integer.parseInt(largeur.getText()),
				Integer.parseInt(hauteur.getText()), (float) 1.5);
		elements = new ArrayList<Element>();

		Element tempElement = null;
		switch (monstres.getNom()) {
		case "Orc":
			tempElement = new Orc(donjon, 0, 0);
			break;
		case "Faucheuse":
			tempElement = new Faucheuse(donjon, 0, 0);
			break;
		case "Sorcier":
			tempElement = new Sorcier(donjon, 0, 0);
			break;
		case "Loup":
			tempElement = new Loup(donjon, 0, 0);
			break;
		}
		elements.add(tempElement);

		for (int i = 0; i < obstacles.size(); i++) {
			for (int j = 0; j < obstacles.get(i).getNombre(); j++) {
				Element tempObstacle = null;
				switch (obstacles.get(i).getNom()) {
				case "Rocher":
					tempObstacle = new Rocher(donjon, 0, 0);
					break;
				case "Mur":
					tempObstacle = new Mur(donjon, 0, 0);
					break;
				}
				elements.add(tempObstacle);
			}
		}

		for (int i = 0; i < personnages.size(); i++) {
			for (int j = 0; j < personnages.get(i).getNombre(); j++) {
				Element tempPersonnage = null;
				switch (personnages.get(i).getNom()) {
				case "Guerrier":
					tempPersonnage = new Guerrier(donjon, 0, 0);
					break;
				case "Villageois":
					tempPersonnage = new Villageois(donjon, 0, 0);
					break;
				case "Mouton":
					tempPersonnage = new Mouton(donjon, 0, 0);
					break;
				}
				elements.add(tempPersonnage);
			}
		}
		
		donjon.placerElements(this.elements);
		this.setVisible(false);
	}

	public Donjon getDonjon() {
		
		/**
		 * @return Le Donjon généré.
		 */
		
		return this.donjon;

	}

	public ArrayList<Element> getElements() {

		/**
		 * @return La liste des éléments présents dans le Donjon.
		 */
		
		return this.elements;

	}

}
