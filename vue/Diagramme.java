package vue;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.Random;

import musique.Accord;
import musique.Position;
import musique.Ukulele;

public class Diagramme {
	private Graphics g;
	private int maTaillex , maTailley, x , y;
	
	// Constructeur avec un objet graphique, des coordonn�es et une taille
	public Diagramme(Graphics g, int x, int y, int maTaillex, int maTailley){
		setX (x);
		setY (y);
		setMaTaillex (maTaillex);
		setMaTailley (maTailley);
		this.g = g;
	}
	
	// Cette m�thode permet de randomiser une valeur
	private int modifieur()
	{
		Random ran = new Random();
		int result = ran.nextInt(14);

		switch(result){
		case 0 : return -2*maTailley/100;
		case 1 : case 2 : return -maTailley/100;
		case 6 : case 7: return maTailley/100;
		case 8 : return 2*maTailley/100;
		}
		return 0;
	}
	
	// dessine la position sur un diagramme : grille, points, croix, nom
	public void dessine(Position maPosition, Accord monAccord)
	{
		Diagramme monDessin = new Diagramme(g, x, y, maTaillex, maTailley);
		monDessin.dessinerGrilleVierge();
		
		int fretteDebut = 0;
		
		if (maPosition.getPositionHaute()>4)
			fretteDebut = maPosition.getPositionBasse()-1;
		
		monDessin.dessinerPoint(0, maPosition.getCorde(1)-fretteDebut);
		monDessin.dessinerPoint(1, maPosition.getCorde(2)-fretteDebut);
		monDessin.dessinerPoint(2, maPosition.getCorde(3)-fretteDebut);
		monDessin.dessinerPoint(3, maPosition.getCorde(4)-fretteDebut);

		Ukulele monuke;
		monuke = new Ukulele();
		Accord accordTrouve;
		String NomAccord;

		accordTrouve = monuke.trouveAccordPosition(maPosition.getCorde(1), maPosition.getCorde(2), maPosition.getCorde(3), maPosition.getCorde(4));
		NomAccord = accordTrouve.chercheTypeAccord(false);
		monDessin.ecritNom(NomAccord);
		this.dessineFretteDebut(fretteDebut);
		
	}
	
//	// dessine la position sur un diagramme : grille, points, croix, nom
//		public void dessine(Position maPosition)
//		{
//			Ukulele monuke;
//			monuke = new Ukulele();
//			Accord accordTrouve;
//
//			accordTrouve = monuke.trouveAccordPosition(maPosition.getCorde(1), maPosition.getCorde(2), maPosition.getCorde(3), maPosition.getCorde(4));
//			dessine (maPosition, accordTrouve);
//		}
		
	// dessine une grille vierge pour le diagramme
	private void dessinerGrilleVierge(){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke bs;
		bs = new BasicStroke(maTaillex/20,1,1,2);
		g2.setStroke(bs);
		g2.setColor(Color.black);

		// Dessin d'une grille de uku alambiqu�e
		int col, ligne;
		int tailleCaseX = maTaillex/3;
		int tailleCaseY = maTailley/4;

		//				// Dessin d'une grille de uku simple
		//				g2.drawRect(x, y, maTaillex, maTailley);
		//				g2.drawRect(x + maTaillex / 3, y, maTaillex / 3,  maTailley);
		//				g2.drawRect(x, y + maTailley / 4, maTaillex,  2 * maTailley / 4);
		//				g2.drawRect(x, y + maTailley / 2, maTaillex, maTailley / 4);
		//				


		// Tableau x, y des 20 points de la grille du uku
		int[] tableauX = new int[20];
		int[] tableauY = new int[20];

		// Les points seront d�plac�s � +- 2 pixels
		for (col = 0;col<4;col++){
			for (ligne = 0; ligne<5; ligne++)
			{
				tableauX[5*(col)+ligne] = (col)* tailleCaseX + x + modifieur();//
				tableauY[5*(col)+ligne] = (ligne)* tailleCaseY + y + modifieur();
			}
		}
		int lx, ly;
		int lx2, ly2;
		for (col = 0;col<4;col++){
			for (ligne = 0; ligne<5; ligne++)
			{
				// trait vers la droite si col < 4
				if (col<3)
				{
					lx = tableauX[5*col+ligne];
					ly = tableauY[5*col+ligne];
					lx2 = tableauX[5*(col+1)+ligne];
					ly2 = tableauY[5*(col+1)+ligne];
					g2.drawLine(lx, ly, lx2, ly2);
				}

				// trait vers le bas si ligne < 5
				if (ligne<4)
				{
					lx = tableauX[5*col+ligne];
					ly = tableauY[5*col+ligne];
					lx2 = tableauX[5*col+ligne+1];
					ly2 = tableauY[5*col+ligne+1];
					g2.drawLine(lx, ly, lx2, ly2);
				}
			}
		}
	}
	
	// Ecrit le nom de l'accord au dessus de la grille
	public void ecritNom(String nom){
		Graphics2D g2 = (Graphics2D) g;

		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// D�finition de la police
		Font font = new Font("Serif", Font.PLAIN, maTailley);
		Font font24 = font.deriveFont(18.0f*maTaillex/50);
		g2.setFont(font24);
		Hashtable<TextAttribute, Float> attributes = new Hashtable<TextAttribute, Float>();
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		Font font24bold = font24.deriveFont(attributes);
		g2.setFont(font24bold);	
		
		// Pour centrer le texte
		FontMetrics metrics = g.getFontMetrics(font24);
		int posx, posy;
		posx = this.x + (this.maTaillex - metrics.stringWidth(nom))/2;
		posy = this.y - metrics.getAscent()/4;
		
		// Ecriture en rouge et en noir
		g2.setColor(Color.black);
		g2.drawString(nom, posx, posy);
		g2.setColor(Color.red);
		g2.drawString(nom, 1 + posx, 1+posy);
	}
	
	// Dessine un point sur la corde i � la frette j
	private void dessinerPoint(int corde, int frette){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke bs;
		bs = new BasicStroke(maTaillex/30,1,1,2);
		g2.setStroke(bs);
		// Dessin d'une grille de uku
		if (frette==-1){
			dessineCroix(g2, corde);
		}
		else if (frette >0){
			int nx = x -1 + modifieur();
			int ny = y +1 + modifieur();
			g2.setColor(Color.black);
			g2.drawOval(nx + maTaillex/3 * corde - maTaillex/14, ny + maTailley/22 + maTailley/4 * (frette-1), maTaillex/6, maTaillex/6);
			g2.setColor(Color.red);
			g2.fillOval(nx + maTaillex/3 * corde - maTaillex/14, ny + maTailley/22 + maTailley/4 * (frette-1), maTaillex/6, maTaillex/6);
		}
	}
	
	// Dessine une croix pour une corde non jou�e
	private void dessineCroix(Graphics2D g2, int corde)
	{
		BasicStroke bs;

		int monX1, monX2, monY1, monY2;
		monX1 = x + maTaillex/3 * corde - maTaillex/12 + modifieur();
		monY1 = y - maTailley/12 + modifieur();
		monX2 = x + maTaillex/3 * corde + maTaillex/12 + modifieur();
		monY2 = y + maTailley/12 + modifieur();

		bs = new BasicStroke(maTaillex/20,1,1,2);
		g2.setStroke(bs);
		g2.setColor(Color.black);
		// Dessiner une diagonale avec un rectangle 
		g2.drawLine(monX1 , monY1, monX2, monY2);

		// Dessiner une diagonale avec un rectangle 
		g2.drawLine(monX2 , monY1, monX1, monY2);

		bs = new BasicStroke(maTaillex/30,1,1,2);
		g2.setStroke(bs);
		g2.setColor(Color.red);
		// Dessiner une diagonale avec un rectangle 
		g2.drawLine(monX1 , monY1, monX2, monY2);

		// Dessiner une diagonale avec un rectangle 
		g2.drawLine(monX2 , monY1, monX1, monY2);
	}

	// Affiche le num�ro de la frette de d�but, si diff�rent de 0
	private void dessineFretteDebut(int nFrette)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		if (nFrette == 0)
			return;
		String nom = "" + (nFrette+1) ;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, maTailley);

		Font font24 = font.deriveFont(12.0f);
		g2.setFont(font24);
		Hashtable<TextAttribute, Float> attributes = new Hashtable<TextAttribute, Float>();
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		Font font24bold = font24.deriveFont(attributes);
		g2.setFont(font24bold);
		g2.setColor(Color.black);
		g2.drawString(nom, x -8, y+12);
		g2.drawString(nom, x -6, y+12);
		g2.setColor(Color.red);
		g2.drawString(nom, x-7, y+12);
	}
	
	
	// Getters et Setters
	public int getMaTaillex() {
		return maTaillex;
	}
	public void setMaTaillex(int maTaillex) {
		if (maTaillex>=0)
			this.maTaillex = maTaillex;
	}
	public int getMaTailley() {
		return maTailley;
	}
	public void setMaTailley(int maTailley) {
		if (maTailley>=0)
			this.maTailley = maTailley;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if (x>=0)
			this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if (y>=0)
			this.y = y;
	}
}