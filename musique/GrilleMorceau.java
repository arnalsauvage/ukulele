package musique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import vue.Diagramme;

// TODO : ajouter la gestion du séparateur : /

public class GrilleMorceau {
	ArrayList<Accord> pileDeAccords;
	Dico monDico;

	// Constructeur par défaut
	public GrilleMorceau() {
		monDico = new Dico();
		monDico.remplitDico();
		pileDeAccords = new ArrayList<Accord>();
	}

	// Cette méthode permet de passer en paramètre une liste de noms d'accords
	// à ajouter au morceau ex : "C Am7 F G7"
	public void ajouteAccords(String sAccords) {
		Accord monAccord;
		// On explose les sous-chaînes dans un tableau
		String[] tabAccords = sAccords.split("(/|\\s)");
		// On ajoute chaque élément du tableau au morceau
		for (int i = 0; i < tabAccords.length; i++) {
			monAccord = new Accord(tabAccords[i]);
			ajouteAccord(monAccord );
		}
	}

	// Cette méthode permet de passer en paramètre une liste de noms d'accords
	// à utiliser pour le morceau ex : "C Am7 F G7"
	public void setAccords(String sAccords) {
		pileDeAccords = new ArrayList<Accord>();
		ajouteAccords(sAccords);
	}

	// Ajoute un accord dans le morceau
	public void ajouteAccord(Accord monAccord, String stringSeparateur) {
		pileDeAccords.add(monAccord);
		// Position maPosition = new Position(monAccord);
		// System.out.println(monAccord.chercheTypeAccord(false)+" -
		// "+maPosition);
	}	
	
	// Ajoute un accord dans le morceau
	public void ajouteAccord(Accord monAccord) {
		pileDeAccords.add(monAccord);
	}

	// retire le dernier accord de la pile et le renvoie
	public Accord tireAccord() {
		Accord monAccord;
		monAccord = (Accord) pileDeAccords.get(pileDeAccords.size() - 1);
		pileDeAccords.remove(pileDeAccords.size() - 1);
		return monAccord;
	}

	// Renvoie la taille de la pile : nombre d'accords dans le morceau
	public int taillePaquet() {
		return pileDeAccords.size();
	}

	// Prépare une chaine affichant la complexité du morceau dans les 12
	// transpositions
	public String calculeLes12Complexites() {
		String ChaineRetour = "";
		for (int i = 1; i < 12; i++) {
			this.transpose(1);
			ChaineRetour = ChaineRetour + "[" + i + "]" + complexiteGrille() + "-";
		}
		this.transpose(1);
		return ChaineRetour;
	}

	// Donne un score de complexité du morceau = somme de la difficulté des
	// positions
	// TODO : étudier les différences de position d'un accord à l'autre
	// pour réduire la
	// complexité : F ==> Dm complexité 2 + 3 = 5 à corriger en 3, car juste un
	// doigt à poser
	public int complexiteGrille() {
		int complexite = 0;
		Position maPosition;
		for (Accord monAccord : pileDeAccords) {
			maPosition = monDico.get(monAccord.chercheTypeAccord(false));
			if (maPosition != null)
				complexite += maPosition.difficulte();
		}
		return complexite;
	}

	// Transpose le morceau du nombre de demi-tons
	public void transpose(int demiTons) {
		// Position maPosition;
		for (Accord monAccord : pileDeAccords) {
			monAccord.transpose(demiTons);
		}
	}

	// Pour afficher le morceau en mode graphique,
	// précisant le nombre d'accords par ligne
	public void AfficheMorceau(Graphics g, int accordsParLigne, int x, int y, int maTaillex, int maTailley) {
		Accord monAccord = new Accord("C");
		Position maPosition = new Position(0, 0, 0, 0);
		int col = 0;
		int ligne = 0;
		String maChaine = new String();
		Diagramme monDiagramme = new Diagramme(g, x, y, maTaillex, maTailley);
		g.clearRect(x, y - maTailley / 2, accordsParLigne * (maTaillex + maTaillex / 2),
				(1 + taillePaquet() / accordsParLigne) * (maTailley + maTailley / 2));

		// Pour chaque accord de la grille, on affiche sur n accords par ligne
		for (int i = 0; i < pileDeAccords.size(); i++) {
			
			// Calcule ligne / colonne d'affichage
			col = (i) % accordsParLigne;
			ligne = (i) / accordsParLigne;

			// On pioche l'accord
			monAccord = pileDeAccords.get(i);
			monDiagramme = new Diagramme(g, x + col * (maTaillex + maTaillex / 2),
					y + ligne * (maTailley + maTailley / 2), maTaillex, maTailley);
			maChaine = monAccord.chercheTypeAccord(false);
			maPosition = monDico.get(maChaine);

			if (maPosition != null) {
				monDiagramme.dessine(maPosition, maChaine);
			}
		}

		String chaineComplexite = "Complexite : " + complexiteGrille() + ". Alternatives en transposant : " + calculeLes12Complexites();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Définition de la police
		Font font = new Font("Serif", Font.PLAIN, maTailley);
		Font font24 = font.deriveFont(16.0f);
		g2.setFont(font24);
			
		// Ecriture en rouge et en noir
		g2.setColor(Color.red);
		g2.drawString(chaineComplexite, x, y-35);
		g2.setColor(Color.black);
		g2.drawString(chaineComplexite, 1 + x, y-36);
	}

	// Affiche les accords du morceau en mode texte
	public void afficheTexte() {
		for (Accord monAccord : pileDeAccords) {
			System.out.println(monAccord);
		}
	}

	// Procédure de test
	public static void main(String[] args) {
		AccordNomFamille.creeCatalogueAccords();

		GrilleMorceau song = new GrilleMorceau();
		Accord monAccord = new Accord("Gm7");

		song.ajouteAccord(monAccord);
		song.ajouteAccord(new Accord("C"));
		song.ajouteAccord(new Accord("Am7"));
		song.ajouteAccord(new Accord("F7"));
		song.tireAccord();
		song.ajouteAccord(new Accord("F"));
		song.ajouteAccord(new Accord("G7"));
		song.ajouteAccords("C C#dim Dm7 G7");

		System.out.println("Difficulte :" + song.complexiteGrille());
		song.afficheTexte();

		song.transpose(2);

		System.out.println("\nDifficulte :" + song.complexiteGrille());
		song.afficheTexte();

	}
}