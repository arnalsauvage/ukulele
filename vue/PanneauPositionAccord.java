package vue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import musique.Accord;
import musique.Position;
import musique.Ukulele;

public class PanneauPositionAccord extends JPanel implements KeyListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	String nomAccord = "";
	Accord monAccord;
	Position maPosition;
	Ukulele monUke;
	
	int x, y, maTaillex, maTailley;
	int transposition;
	Diagramme monDiagramme = null;
	Canvas monCanvas;
	private JTextArea texte;

	public PanneauPositionAccord() {
		requestFocusInWindow();

		monAccord = new Accord("C");
		maPosition = new Position(0, 0, 0, 3);
		this.addMouseListener(this);
		// Point de départ du dessin
		x = 30;
		y = 50;

		// Largeur et hauteur d'un diagramme
		maTaillex = 240;
		maTailley = 300;

		this.setSize(maTaillex, maTailley);
		monCanvas = new Canvas();
		monCanvas.setSize(maTaillex + x, (int) (maTailley * 1.33));
		monCanvas.setBounds(x, y, maTaillex + x,(int) (maTailley * 1.33));
		monCanvas.addMouseListener(this);
		texte = new JTextArea();
		this.add(texte);
		this.add(monCanvas);
		monUke = new Ukulele();
	}

	public void paintComponent(Graphics g) {

		// On efface la zone de dessin
		monCanvas.getGraphics().setColor(Color.white);
		monCanvas.getGraphics().clearRect(0,0, monCanvas.getWidth(), monCanvas.getHeight());
		if (monDiagramme == null)
			monDiagramme = new Diagramme(monCanvas.getGraphics(), x, y + maTailley/4 , maTaillex,  (int) ( maTailley * 0.75));
		monDiagramme.dessine(maPosition, monAccord);
		// System.out.println("Coucou !");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int monx, mony;
		monx = e.getX();
		mony = e.getY();

		// Clic dans le diagramme
//		if ((monx >= monCanvas.getX()) && (monx <= (monCanvas.getX() + monCanvas.getWidth()))) {
//			if ((mony >= monCanvas.getY()) && (mony <= (monCanvas.getY() + monCanvas.getHeight()))){
			int maColonne = 1 + (monx - x) / (maTaillex / 4);
			int maLigne =  (mony - y + maTailley / 10) / (maTailley / 5);

			maPosition.setCorde(maColonne, maLigne);
			monDiagramme.dessine(maPosition, monAccord);
			texte.setText("Monx : " + monx + " mony : " + mony + " col :" + maColonne + " ligne : " + maLigne);
			monAccord = monUke.trouveAccordPosition(maPosition);
			texte.setText("Accord(s) : " + monAccord.chercheTypeAccord(true));
			repaint();
//			}
//		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}