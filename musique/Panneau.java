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
import javax.swing.JTextField;

public class Panneau extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	boolean testeGrille = false;
	boolean game_on = false;
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
		boutonAfficheGrille = new JButton("Affiche grille");
		btnTransposePlus = new JButton("+");
		btnTransposeMoins = new JButton("-");

		this.add(boutonAfficheGrille);
		boutonAfficheGrille.addActionListener(this);
		texteGrille = new JTextArea(
				"F6 F Cdim Gm7-5 C7 F FM7 Dm Am7-5 D7 A# A#9 G#dim Gm7-5 F Am7-5 D7 A#9 G#dim C7 C#9 C7 F Cdim C7", 5,
				50);
		texteGrille = new JTextArea(
				"C C Am Am CM7 B7 Cdim Dm \n G7 Dm Dm Bb Bb Dm G7 C \n Cdim Dm G7 C C Am Am Gm7 \n C7 F E7 Am Em Am Em D7 \n G7 C Cdim Dm G7",
				5, 80);
		this.add(texteGrille);
		this.add(btnTransposePlus);
		btnTransposePlus.addActionListener(this);
		this.add(lblTranspose);
		this.add(btnTransposeMoins);
		btnTransposeMoins.addActionListener(this);

		// Gestion du combo "transpose"
		String[] transposeStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
		JComboBox comboTranspose = new JComboBox(transposeStrings);
		comboTranspose.setSelectedItem(transposition);
		this.add(comboTranspose);
		comboTranspose.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == boutonAfficheGrille) {
			while (texteGrille.getText().contains("  "))
				texteGrille.setText(texteGrille.getText().replaceAll("  ", " "));
			texteGrille.setText(texteGrille.getText().replaceAll(" ", "\t"));
			testeGrille = !testeGrille;
		}
		if (evt.getSource() == btnTransposePlus)
			transposition++;
		if (evt.getSource() == btnTransposeMoins)
			transposition--;
		
		if (evt.getSource() == comboTranspose)
			transposition = comboTranspose.getSelectedIndex();
		
		lblTranspose.setText("Transpose : " + transposition);
		repaint();
	}

	public void paintComponent(Graphics g) {
		int maTaillex = 60;
		int maTailley = 80;
		int x = 20;
		int y = 30;
		Random rand = new Random();
		this.setBackground(Color.white);
		Diagramme monDiagramme = new Diagramme(g, x, y, maTaillex, maTailley);
		if (game_on) {
			this.removeAll();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 4; j++) {
					Position maposition = new Position(rand.nextInt(6) - 1, rand.nextInt(6) - 1, rand.nextInt(6) - 1,
							rand.nextInt(6) - 1);
					monDiagramme = new Diagramme(g, x + i * maTaillex + i * maTaillex / 2,
							y + j * maTailley + j * maTailley / 2, maTaillex, maTailley);
					monDiagramme.dessine(maposition);
				}
			}
			game_on = false;
		}
		testeGrille(g, 8, 10, 150, 50, 60);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			testeGrille = !testeGrille;
		}
		if (e.getKeyCode() == KeyEvent.VK_F2)
			game_on = !game_on;
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
			texteAtraiter=(texteAtraiter.replaceAll("  ", " "));
		maGrille.setAccords(texteAtraiter);
		maGrille.transpose(transposition);
		// maGrille.afficheTexte();

		// Le code commenté permettait d'afficher les 12 transpositions
		// for (int boucle= 1; boucle <6; boucle++){

		maGrille.AfficheMorceau(g, accordsParLigne, x, y, maTaillex, maTailley);
		y += maTailley / 2 + maTailley * (nbLignes + 1);
		maGrille.transpose(1);
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