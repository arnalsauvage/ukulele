package musique;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreUku extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PanneauInterface panInterface;
	private PanneauMorceau panMorceau;

	public FenetreUku() {
		this.setTitle("Accords ukulélé");
		panInterface = new PanneauInterface();
		panMorceau = new PanneauMorceau();
		panInterface.setPanneauMorceau(panMorceau);
		this.setSize(1400, 1250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(panInterface);
		this.add(panMorceau);
		this.setVisible(true);
		addKeyListener(panInterface);
		// pan.paintComponent(this.getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccordNomFamille.creeCatalogueAccords();
		FenetreUku fen;

		fen = new FenetreUku();
		fen.isCursorSet();
	}
}