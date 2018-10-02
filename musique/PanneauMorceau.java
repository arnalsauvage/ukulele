package musique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanneauMorceau extends JPanel  {
	private static final long serialVersionUID = 1L;

	String texteAtraiter = "";
	GrilleMorceau maGrille;
	int transposition;

	public PanneauMorceau() {
		maGrille = new GrilleMorceau();

	}

	public void paintComponent(Graphics g) {
		Random rand = new Random();

		// Point de départ du dessin
		int x = 10;
		int y = 300;

		// Largeur et hauteur d'un diagramme
		int maTaillex = 60;
		int maTailley = 80;

		// On efface la zone de dessin
		this.setBackground(Color.white);
		g.clearRect(x, y, this.getWidth(), this.getHeight());

		testeGrille(g, 8, x, y, maTaillex, maTailley);
	}

	private void testeGrille(Graphics g, int accordsParLigne, int x, int y, int maTaillex, int maTailley) {
		while (texteAtraiter.contains("  "))
			texteAtraiter = (texteAtraiter.replaceAll("  ", " "));
		if (texteAtraiter.length() > 2) {
			maGrille.setAccords(texteAtraiter);
			maGrille.transpose(transposition);
			maGrille.AfficheMorceau(g, accordsParLigne, x, y, maTaillex, maTailley);
		}
	}

	public String getTexteAtraiter() {
		return texteAtraiter;
	}

	public void setTexteAtraiter(String texteAtraiter) {
		this.texteAtraiter = texteAtraiter;
	}

	public int getTransposition() {
		return transposition;
	}

	public void setTransposition(int transposition) {
		this.transposition = transposition;
	}
}