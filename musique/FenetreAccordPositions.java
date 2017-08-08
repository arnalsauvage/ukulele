package musique;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAccordPositions extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PanneauInterface panInterface;
	private PanneauMorceau panMorceau;

	public FenetreAccordPositions() {
		this.setTitle("Accords ukulélé");
		setIconImage(new ImageIcon(this.getClass().getResource("ukulele.png")).getImage());
		panInterface = new PanneauInterface();
		panMorceau = new PanneauMorceau();
		panInterface.setPanneauMorceau(panMorceau);
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panMorceau);
		this.getContentPane().add(panInterface);
//		pack();
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
		FenetreAccordPositions fen;

		fen = new FenetreAccordPositions();
		fen.isCursorSet();
	}
}