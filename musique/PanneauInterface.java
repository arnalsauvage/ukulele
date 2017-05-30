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

public class PanneauInterface extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	boolean testeGrille = false;
	private JButton boutonAfficheGrille;
	private JButton btnTransposePlus;
	private JButton btnTransposeMoins;
	private JTextArea texteGrille;
	private int transposition = 0;
	private JLabel lblTranspose = new JLabel("Transpose");
	private JComboBox comboTranspose;
	// Create a file chooser
	private JButton choixOpenFic;

	GrilleMorceau maGrille;

	private PanneauMorceau panneauMorceau;
	
	public PanneauInterface() {
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
		// Gestion du combo "transpose"
		String[] transposeStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
		comboTranspose = new JComboBox(transposeStrings);
		comboTranspose.setSelectedItem(transposition);
		this.add(comboTranspose);
		comboTranspose.addActionListener(this);

		texteGrille = new JTextArea(
				"C C Am Am CM7 B7 Cdim Dm \n G7 Dm Dm Bb Bb Dm G7 C \n Cdim Dm G7 C C Am Am Gm7 \n C7 F E7 Am Em Am Em D7 \n G7 C Cdim Dm G7",
				5, 80);
		this.add(texteGrille);

		// Gestion du bouton Open
		choixOpenFic = new JButton("Ouvrir...");

		this.add(choixOpenFic);
		choixOpenFic.addActionListener(this);

	}
	
	public String lireFichierTexte(File fichier){
		String machaine = "";
		//lecture du fichier texte	
				try{
					InputStream ips=new FileInputStream(fichier); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						System.out.println(ligne);
						machaine+=ligne+"\n";
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				return machaine;
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == boutonAfficheGrille) {
			// on enlève les fioritures de la zone de texte avant de traiter
			while (texteGrille.getText().contains("  "))
				texteGrille.setText(texteGrille.getText().replaceAll("  ", " "));
			texteGrille.setText(texteGrille.getText().replaceAll(" ", "\t"));
			panneauMorceau.setTexteAtraiter(texteGrille.getText());
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

		if (evt.getSource() == choixOpenFic) {
//			int returnVal = showOpenDialog(PanneauInterface.this);
//
//			if (returnVal == JFileChooser.APPROVE_OPTION) {
//				File file = choixOpenFic.getSelectedFile();
//				texteGrille.setText(lireFichierTexte(file));
//			}
		}

		comboTranspose.setSelectedIndex(transposition);
		lblTranspose.setText("Transpose : " + transposition);
		repaint();
	}



	public JPanel getPanneauMorceau() {
		return panneauMorceau;
	}

	public void setPanneauMorceau(PanneauMorceau panneauMorceau) {
		this.panneauMorceau = panneauMorceau;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {

		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}