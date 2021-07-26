package fr.eni.papeterie.ihm;

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EcranUser extends JFrame{
	// on déclare l'attribut qui correspond à notre composant Jbutton
			
			JLabel lblReference;
			JLabel lblDesignation;
			JLabel lblMarque;
			JLabel lblStock;
			JLabel lblPrix;
			JLabel lblType;
			JLabel lblGrammage;
			JLabel lblCouleur;
			JTextField txtReference;
			JTextField txtDesignation;
			JTextField txtMarque;
			JTextField txtStock;
			JTextField txtPrix;
			JRadioButton radbRamette;
			JRadioButton radbStylo;
			JCheckBox chbGram80;
			JCheckBox chbGram100;
			JComboBox<String> combCouleur;
			JButton btnFlecheGauche;
			JButton btnCreer;
			JButton btnSauvergarder;
			JButton btnSupprimer;
			JButton btnFlecheDroite;
			Icon imageFlecheGauche = new ImageIcon(this.getClass().getResource("images/Back24.gif"));
			
			Icon imageCreer = new ImageIcon(this.getClass().getResource("images/New24.gif"));
			Icon imageSauvegarder = new ImageIcon(this.getClass().getResource("images/Save24.gif"));
			Icon imageSupprimer = new ImageIcon(this.getClass().getResource("images/Delete24.gif"));
			Icon imageFlecheDroite = new ImageIcon(this.getClass().getResource("images/Forward24.gif"));
			
			
			//ImageIcon image = new ImageIcon(this.getClass().getResource("images/Back24.gif"));
			//btn.setIcon(image)


			
			//JButton btn = new JButton(icon);
			
			// Constructor ecranUser
			public EcranUser() {
				//JFrame frame = new JFrame("Demonstration"); // lance la JFrame, mais par défaut elle 
				//n'est pas visible c'est ce qu'on fait avec setVisible
				
			// on lui donne une taille
			this.setSize(400, 500);  
			//on centre le JFrame
			this.setLocationRelativeTo(null);  
			
			// on lui demande de fermer le programme quand on ferme la JFrame
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			
			// on ajoute le panneau en bois sur lequel on va coller les affiches
			JPanel panel = new JPanel();// je colle le bouton sur le panneau en bois
			
			// on lui donne le format du layout
			panel.setLayout(new GridBagLayout()); // on choisit le GridBagLayout
			
			// on définit les contraintes pour pouvoir positionner les composants exactement où on veut
			GridBagConstraints gbc = new GridBagConstraints(); // on pourra définir le "x" et le "y"
			//==> on ajoute gbc dans les 3 lignes qui suivent
			
			// Ligne référence label à gauche texteField à droite, avec une marge pour le label
			gbc.gridx = 0; // 1ère colone
			gbc.gridy = 0; // 1ère ligne
			gbc.insets = new Insets(0, 0, 5, 5);
			panel.add(this.getLblReference(), gbc); // je colle le label sur le panneau en bois
			
			gbc.gridx = 1; // 2ème colonne		
			panel.add(this.getTxtReference(), gbc); // je colle le texteField sur le panneau en bois
			
			//ligne Désignation
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 1; // 2ème ligne
			gbc.insets = new Insets(0, 0, 5, 5);
			panel.add(this.getLblDesignation(), gbc); // je colle le label sur le panneau en bois
			
			gbc.gridx = 1; // 2ème colonne				
			panel.add(this.getTxtDesignation(), gbc); // je colle le texteField sur le panneau en bois
			
			//ligne Marque
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 2; // 2ème ligne
			gbc.insets = new Insets(0, 0, 5, 5);
			panel.add(this.getLblMarque(), gbc); // je colle le label sur le panneau en bois
			
			gbc.gridx = 1; // 2ème colonne				
			panel.add(this.getTxtMarque(), gbc); // je colle le texteField sur le panneau en bois
			
			//ligne Stock
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 3; // 2ème ligne
			gbc.insets = new Insets(0, 0, 5, 5);
			panel.add(this.getLblStock(), gbc); // je colle le label sur le panneau en bois
			
			gbc.gridx = 1; // 2ème colonne				
			panel.add(this.getTxtStock(), gbc); // je colle le texteField sur le panneau en bois
			
			//ligne Prix
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 4; // 2ème ligne
			gbc.insets = new Insets(0, 0, 5, 5);
			panel.add(this.getLblPrix(), gbc); // je colle le label sur le panneau en bois
			
			gbc.gridx = 1; // 2ème colonne				
			panel.add(this.getTxtPrix(), gbc); // je colle le texteField sur le panneau en bois
			
			
			//ligne Type
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 5; // 2ème ligne
			
			gbc.insets = new Insets(0, 0, 5, 20);
			panel.add(this.getLblType(), gbc); // je colle le label sur le panneau en bois
			
			// création d'un panelType pour gérer les 2 boutons
			JPanel panelType = new JPanel();
			// Positionnement de ce panelType dans le panel de départ			
			gbc.gridx = 1; // 2ème colonne
			gbc.gridy = 5; // 2ème ligne
			panel.add(panelType, gbc);
			
			// on lui donne le format du layout
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS)); // alignement en vertical
			// on définit les boutons
			// bouton Ramette
			panelType.add(this.getRadbRamette()); 	
			// Bouton Stylo
			panelType.add(this.getRadbStylo()); 
			
			// définition du groupe Bouton radio pour qu'un seul puisse être sélectionné
			ButtonGroup btgType = new ButtonGroup();
			btgType.add(getRadbRamette());
			btgType.add(getRadbStylo());
			
			
			// même démarche pour grammage
			// Ligne Grammage
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 7; // 2ème ligne
			gbc.insets = new Insets(0, 0, 5, 20);
			panel.add(this.getLblGrammage(), gbc);
			
			// définition d'un panelGrammage pour les 2 checkbox
			JPanel panelGrammage = new JPanel();
			// Positionnement de ce panelType dans le panel de départ			
			gbc.gridx = 1; // 2ème colonne
			gbc.gridy = 7; // 2ème ligne
			panel.add(panelGrammage, gbc);
			
			// on lui donne le format du layout
			panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS)); // alignement en vertical
			// on définit les boutons
			// bouton 80 grammes
			panelGrammage.add(this.getChbGram80(), gbc); 		
			// Bouton 100 grammes
			panelGrammage.add(this.getChbGram100(), gbc); 
			
			ButtonGroup btgGrammage = new ButtonGroup();
			btgGrammage.add(getChbGram80());
			btgGrammage.add(getChbGram100());
			
			
			// ligne Couleur
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 9; // 2ème ligne
			panel.add(getLblCouleur(), gbc);
			gbc.insets = new Insets(0, 0, 5, 20);
			gbc.gridx = 1; // 2ème colonne
			panel.add(this.getCombCouleur(), gbc);
			
			// Ligne avec les 5 boutons
			// nouveau Jpanel collé sur le premier pour les 5 boutons du bas
			JPanel panelDuBas = new JPanel();// je colle une 2ème affiche sur mon panneau en bois
			
			gbc.gridx = 0; // 1ère colonne
			gbc.gridy = 10; // 2ème ligne
			gbc.gridwidth = 2;
			panel.add(panelDuBas, gbc);		
			
			panelDuBas.add(this.getBtnFlecheGauche()); 
			panelDuBas.add(this.getBtnCreer());
			panelDuBas.add(this.getBtnSauvergarder());
			panelDuBas.add(this.getBtnSupprimer());
			panelDuBas.add(this.getBtnFlecheDroite());
			

			
		
			
			

			// on veut dire que sur notre JFrame, le contenu principal c'est le panneau en bois
			this.setContentPane(panel); // Pour info, on peut coller plusieurs affiches sur le panneau en bois
			
			this.setAlwaysOnTop(true);
			
			//On rend la JFrame visible
			this.setVisible(true); // par défaut, elle n'a pas de taille ==> on le fait avant de la rendre visible avec this.setSize
			
			}	
			
			
			public JLabel getLblReference() {
				if (this.lblReference == null) {
					this.lblReference = new JLabel("Référence");
				}
				return this.lblReference;
			}
			
			public JLabel getLblDesignation() {
				if (this.lblDesignation == null) {
					this.lblDesignation = new JLabel("Désignation");
				}
				return this.lblDesignation;
			}
			public JLabel getLblMarque() {
				if (this.lblMarque == null) {
					this.lblMarque = new JLabel("Marque");
				}
				return this.lblMarque;
			}
			public JLabel getLblStock() {
				if (this.lblStock == null) {
					this.lblStock = new JLabel("Stock");
				}
				return this.lblStock;
			}
			public JLabel getLblPrix() {
				if (this.lblPrix == null) {
					this.lblPrix = new JLabel("Prix");
				}
				return this.lblPrix;
			}
			public JLabel getLblType() {
				if (this.lblType == null) {
					this.lblType = new JLabel("Type");
				}
				return this.lblType;
			}
			public JLabel getLblGrammage() {
				if (this.lblGrammage == null) {
					this.lblGrammage = new JLabel("Grammage");
				}
				return this.lblGrammage;
			}
			public JLabel getLblCouleur() {
				if (this.lblCouleur == null) {
					this.lblCouleur = new JLabel("Couleur");
				}
				return this.lblCouleur;
			}
			public JTextField getTxtReference() {
				if (this.txtReference == null) {
					this.txtReference = new JTextField(20);
				}
				return this.txtReference;
			}
			public JTextField getTxtDesignation() {
				if (this.txtDesignation == null) {
					this.txtDesignation = new JTextField(20);
				}
				return this.txtDesignation;
			}
			public JTextField getTxtMarque() {
				if (this.txtMarque == null) {
					this.txtMarque = new JTextField(20);
				}
				return this.txtMarque;
			}
			public JTextField getTxtStock() {
				if (this.txtStock == null) {
					this.txtStock = new JTextField(20);
				}
				return this.txtStock;
			}
			public JTextField getTxtPrix() {
				if (this.txtPrix == null) {
					this.txtPrix = new JTextField(20);
				}
				return this.txtPrix;
			}
			
			public JRadioButton getRadbRamette() {
				if (this.radbRamette == null) {
					this.radbRamette = new JRadioButton("Ramette");
				}
				return radbRamette;
			}
			public JRadioButton getRadbStylo() {
				if (this.radbStylo == null) {
					this.radbStylo = new JRadioButton("Stylo");
				}
				return this.radbStylo;
			}
			public JCheckBox getChbGram80() {
				if (this.chbGram80 == null) {
					this.chbGram80 = new JCheckBox("80 grammes");
				}
				return this.chbGram80;
			}
			public JCheckBox getChbGram100() {
				if (this.chbGram100 == null) {
					this.chbGram100 = new JCheckBox("100 grammes");
				}
				return this.chbGram100;
			}
			public JComboBox<String> getCombCouleur() {
				if (this.combCouleur == null) {
					this.combCouleur = new JComboBox<String>();
				}
				return this.combCouleur;
			}
			public JButton getBtnFlecheGauche() {
				if (this.btnFlecheGauche == null) {
					this.btnFlecheGauche = new JButton(imageFlecheGauche);
				}
				return this.btnFlecheGauche;
			}
			public JButton getBtnCreer() {
				if (this.btnCreer == null) {
					this.btnCreer = new JButton(imageCreer);
				}
				return btnCreer;
			}
			public JButton getBtnSauvergarder() {
				if (this.btnSauvergarder == null) {
					this.btnSauvergarder = new JButton(imageSauvegarder);
				}
				return btnSauvergarder;
			}
			public JButton getBtnSupprimer() {
				if (this.btnSupprimer == null) {
					this.btnSupprimer = new JButton(imageSupprimer);
				}
				return btnSupprimer;
			}
			public JButton getBtnFlecheDroite() {
				if (this.btnFlecheDroite == null) {
					this.btnFlecheDroite = new JButton(imageFlecheDroite);
				}
				return btnFlecheDroite;
			}
		
			
			
}
