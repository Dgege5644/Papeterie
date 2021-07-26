package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		// on lance le thread
				SwingUtilities.invokeLater(new Runnable() {// Runnable est une interface
														// dans laquelle on a la méthode run
					@Override
					public void run() {
						// on applique un look and Feel à notre bouton
						
						EcranUser ecranUser = new EcranUser();
					}
				});

	}

}
