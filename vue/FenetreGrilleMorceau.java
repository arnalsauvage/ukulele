package vue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import musique.AccordNomFamille;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreGrilleMorceau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PanneauInterface panInterface;
	private PanneauMorceau panMorceau;

	public FenetreGrilleMorceau() {
		this.setTitle("Grilles ukulélé");
		setIconImage(new ImageIcon(this.getClass().getResource("ukulele.png")).getImage());
		panInterface = new PanneauInterface();
		panMorceau = new PanneauMorceau();
		panInterface.setPanneauMorceau(panMorceau);
	}

	public void init() {
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panMorceau);
		this.getContentPane().add(panInterface);
		this.pack();
		this.setVisible(true);
		addKeyListener(panInterface);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccordNomFamille.creeCatalogueAccords();
		FenetreGrilleMorceau fen;

		fen = new FenetreGrilleMorceau();
		fen.init();
		fen.isCursorSet();
	}
}