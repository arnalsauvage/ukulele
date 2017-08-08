package vue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import musique.AccordNomFamille;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FenetreAccordPositions extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PanneauPositionAccord monPanPosition;

	public FenetreAccordPositions() {
		this.setTitle("Accords ukulélé");
		setIconImage(new ImageIcon(this.getClass().getResource("ukulele.png")).getImage());
		monPanPosition = new PanneauPositionAccord();
	}

	public void init() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(monPanPosition);
		this.pack();
		this.setVisible(true);
		addKeyListener(monPanPosition);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent e){
	// TODO Auto-generated method stub
		
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccordNomFamille.creeCatalogueAccords();
		FenetreAccordPositions fen;

		fen = new FenetreAccordPositions();
		fen.init();
	}
}