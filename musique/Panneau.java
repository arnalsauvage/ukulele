package musique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Panneau extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	boolean testeGrille = false;
	private JButton boutonAfficheGrille;
	private JButton btnTransposePlus;
	private JButton btnTransposeMoins;
	private JTextArea texteGrille;
	private int transposition = 0;
	private JLabel lblTranspose = new JLabel("Transpose");
	private JComboBox comboTranspose;

	GrilleMorceau maGrille;

	public Panneau() {
		maGrille = new GrilleMorceau();
		requestFocusInWindow();
		addKeyListener(this);

		// Affichage des boutons
		boutonAfficheGrille = new JButton("Affiche grille");
		boutonAfficheGrille.addActionListener(this);
		this.add(boutonAfficheGrille);

		btnTransposePlus = new JButton("+");
		btnTransposePlus.addActionListener(this);
		this.add(btnTransposePlus);

		btnTransposeMoins = new JButton("-");
		btnTransposeMoins.addActionListener(this);
		this.add(btnTransposeMoins);

		this.add(lblTranspose);

		// Cheek to cheek
		// texteGrille = new JTextArea(
		// "F6 F Cdim Gm7-5 C7 F FM7 Dm Am7-5 D7 A# A#9 G#dim Gm7-5 F Am7-5 D7
		// A#9 G#dim C7 C#9 C7 F Cdim C7", 5,
		// 50);

		// Hello Dolly
		texteGrille = new JTextArea(
				"C C Am Am CM7 B7 Cdim Dm \n G7 Dm Dm Bb Bb Dm G7 C \n Cdim Dm G7 C C Am Am Gm7 \n C7 F E7 Am Em Am Em D7 \n G7 C Cdim Dm G7",
				5, 80);
		this.add(texteGrille);

		// Gestion du combo "transpose"
		String[] transposeStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
		comboTranspose = new JComboBox(transposeStrings);
		comboTranspose.setSelectedItem(transposition);
		this.add(comboTranspose);
		comboTranspose.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == boutonAfficheGrille) {
			// on enlève les fioritures de la zone de texte avant de traiter
			while (texteGrille.getText().contains("  "))
				texteGrille.setText(texteGrille.getText().replaceAll("  ", " "));
			texteGrille.setText(texteGrille.getText().replaceAll(" ", "\t"));
		}
		if (evt.getSource() == btnTransposePlus)
			transposition++;
		if (evt.getSource() == btnTransposeMoins)
			transposition--;

		if (transposition > 11)
			transposition -= 12;
		if (transposition < 0)
			transposition += 12;

		if (evt.getSource() == comboTranspose)
			transposition = comboTranspose.getSelectedIndex();
		comboTranspose.setSelectedIndex(transposition);
		lblTranspose.setText("Transpose : " + transposition);
		repaint();
	}

	public void paintComponent(Graphics g) {
		// Largeur et hauteur d'un diagramme
		int maTaillex = 60;
		int maTailley = 80;
		// Point de départ du dessin
		int x = 20 + texteGrille.getX();
		int y = 30 + texteGrille.getY() + texteGrille.getHeight();
		Random rand = new Random();
		this.setBackground(Color.white);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Diagramme monDiagramme = new Diagramme(g, x, y, maTaillex,
		// maTailley);
		// for (int i = 0; i < 10; i++) {
		// for (int j = 0; j < 4; j++) {
		// Position maposition = new Position(rand.nextInt(6) - 1,
		// rand.nextInt(6) - 1, rand.nextInt(6) - 1,
		// rand.nextInt(6) - 1);
		// monDiagramme = new Diagramme(g, x + i * maTaillex + i * maTaillex /
		// 2,
		// y + j * maTailley + j * maTailley / 2, maTaillex, maTailley);
		// monDiagramme.dessine(maposition);
		// }
		// }
		testeGrille(g, 8, 10, 150, maTaillex, maTailley);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {

		}
	}

	private void testeGrille(Graphics g, int accordsParLigne, int x, int y, int maTaillex, int maTailley) {

		int nbLignes = 3;
		String texteAtraiter;
		texteAtraiter = texteGrille.getText();
		texteAtraiter = texteAtraiter.replaceAll("\t", " ");
		texteAtraiter = texteAtraiter.replaceAll("\r", " ");
		texteAtraiter = texteAtraiter.replaceAll("\n", " ");
		texteAtraiter = texteAtraiter.replaceAll("  ", " ");
		while (texteAtraiter.contains("  "))
			texteAtraiter = (texteAtraiter.replaceAll("  ", " "));
		maGrille.setAccords(texteAtraiter);
		maGrille.transpose(transposition);
		// maGrille.afficheTexte();

		// Le code commenté permettait d'afficher les 12 transpositions
		// for (int boucle= 1; boucle <6; boucle++){

		maGrille.AfficheMorceau(g, accordsParLigne, x, y, maTaillex, maTailley);
		y += maTailley / 2 + maTailley * (nbLignes + 1);
		// maGrille.transpose(1);
		// }
		//
		// maGrille.afficheTexte();
		//
		// x += 700;
		// y = 50;
		// for (int boucle= 1; boucle <6; boucle++){
		// maGrille.AfficheMorceau(g, accordsParLigne, x, y, maTaillex,
		// maTailley);
		// y += maTailley/2 + maTailley*(nbLignes+1);
		// maGrille.transpose(1); }
		//
		// maGrille.afficheTexte();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}