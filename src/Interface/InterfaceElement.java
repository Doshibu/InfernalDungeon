package Interface;

import javax.swing.*;

import java.awt.GridLayout;

import javax.swing.border.EmptyBorder;

/**
 * Classe integrée la fenêtre de configuration.
 * @author Clément GUIHÉNEUF
 *
 */

public class InterfaceElement extends JPanel {

	private static final long serialVersionUID = 8117406942854031119L;
	
	private String Nom;
	
	private JFormattedTextField Nombre;	
	
	public InterfaceElement(String NomElement) {
		
		Nom = NomElement;
		
		JPanel Element = new JPanel();
		Element.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(Element);
		Element.setLayout(new GridLayout(1,3, 5, 5));
		
		JLabel lblNewLabel = new JLabel(Nom);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("img/"+Nom+".SUD.png"));
		Element.add(lblNewLabel);
		
		Nombre = new JFormattedTextField();
		Nombre.setValue(1);
		Element.add(Nombre);

	}
	
	
	public String getNom() {
		
		/**
		 * @return Le nom de l'élément concerné.
		 */
		
		return Nom;
		
	}

	public int getNombre() {
		
		/**
		 * @return Le nombre d'exemplaires de cet élément que l'on souhaite.
		 */
		
		return Integer.parseInt(Nombre.getText());
		
	}
	
	
}
