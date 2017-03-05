package Interface;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

/**
 * Classe integrée à la fenêtre de configuration.
 * @author Clément GUIHÉNEUF
 *
 */

public class InterfaceMonstre extends JPanel {

	private static final long serialVersionUID = -846168859746337800L;

	private ArrayList<JRadioButton> monstres;

	private ArrayList<String> noms;

	private ButtonGroup buttongroup = new ButtonGroup();

	public InterfaceMonstre(ArrayList<String> Noms) {

		noms = Noms;

		monstres = new ArrayList<JRadioButton>();

		JPanel Monstre = new JPanel();
		Monstre.setBorder(new EmptyBorder(35, 5, 5, 5));
		add(Monstre);
		Monstre.setLayout(new GridLayout((noms.size() / 2), 2, 5, 5));

		for (int i = 0; i < noms.size(); i++) {

			JRadioButton rdbtnNom = new JRadioButton(noms.get(i));
			Monstre.add(rdbtnNom);
			buttongroup.add(rdbtnNom);
			monstres.add(rdbtnNom);

		}
		
		monstres.get(0).setSelected(true);
		
	}

	public String getNom() {
		
		/**
		 * @return Le nom du monstre selectionné.
		 */

		for (int i = 0; i < monstres.size(); i++) {

			if (monstres.get(i).isSelected()) {

				return monstres.get(i).getText();

			}
		}

		return null;
	}
}
