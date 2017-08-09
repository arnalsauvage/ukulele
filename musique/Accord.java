package musique;

import java.util.ArrayList;
import java.util.Arrays;

// TODO : personnaliser la boite de dialogue ouvrir
// TODO : personnaliser la boite de dialogue enregistrer
// TODO : uniquement les accords ferm�s
// TODO : pour un enchainement d'accords, proposer la position la plus proche de la position pr�c�dente
// TODO : classer en 3 niveaux : d�butants, confirm� et champion
// DONE : d�buguer le transpose n�gatif qui "bloque"
// DONE : g�rer dans la fenetre les retours de chariot tab et autres s�parateurs
// DONE : cr�er une Classe familleAccord qui fait correspondre un tableau de degr�s et un nom
// DONE : familleAccord .lireDepuisFichTexte, comparer 
// DONE : proposer l'enregistrement dans un fichier texte
// DONE : proposer la lecture depuis un fichier texte

public class Accord {

	// static AccordNomFamille dicoAccords = new AccordNomFamille();
	private NoteNom fondamentale; // Fondamentale de l'accord, ex: "C" pour do
	// majeur 4� octave
	private ArrayList<Integer> degres; // Degr�s des accords, exemple 1,5,8 pour
	// accord majeur

	// Constructeur avec une noteNom fondamentale et des degr�s en entr�e
	// Test� dans la m�thode main de cette classe
	public Accord(NoteNom maFondamentale, int[] mesDegres) {
		fondamentale = new NoteNom(maFondamentale.getNom());
		degres = new ArrayList<Integer>(mesDegres.length);
		setDegres(mesDegres);
	}

	// Constructeur avec une cha�ne de caract�res contenant le nom de l'acord
	// Test� dans la m�thode main de la classe AccordsTest
	public Accord(String nom) {
		String nomFondamentale;
		String description = "";

		if (nom.length() == 1)
			nomFondamentale = nom.substring(0, 1).toUpperCase();
		else {
			if (nom.substring(1, 2).equals("#") || nom.substring(1, 2).toUpperCase().equals("B"))
				nomFondamentale = nom.substring(0, 2);
			else
				nomFondamentale = nom.substring(0, 1);
		}

		fondamentale = new NoteNom(nomFondamentale);
		if (nomFondamentale.length() < nom.length())
			description = nom.substring(nomFondamentale.length());

		degres = AccordNomFamille.getDegres(description);
		if ((degres == null))
			degres = new ArrayList<Integer>();
		if (degres.isEmpty())
			degres.add(1);
	}

	// Constructeur par copie
	// Test� dans la m�thode main de cette classe
	public Accord(Accord monAccord) {
		fondamentale = new NoteNom(monAccord.fondamentale.getNom());
		degres = new ArrayList<Integer>(monAccord.degres.size());
		setDegres(monAccord);
	}

	// M�thode d'affichage de l'accord en console pour tests
	public String toString() {
		Note maNote;
		String chaine = "Accord : " + this.chercheTypeAccord(true) + " - ";

		for (int i = 0; i < degres.size(); i++) {
			maNote = calculeNote(i);
			chaine += maNote.getNom() + maNote.getOctave() + " ";
		}
		return (chaine + "\t");
	}

	// On va chercher pour chaque renversement le/les nom(s) de l'accord
	// Test�e dans la m�thode main de cette classe via afficheConsole
	public String chercheTypeAccord(boolean tousLesAccords) {
		String chaine = "";
		// On se fait une copie de travail de l'accord pour ne pas modifier le
		// renversement de notre accord
		Accord copieAccord = new Accord(this);
		for (int i = 0; i < copieAccord.degres.size(); i++) {
			if (copieAccord.nomAbrege().length() > 0) {
				chaine = rajoute(chaine, copieAccord.nomAbrege(), ";");
			}
			copieAccord.renverseAccord();
			if ((tousLesAccords == false) && (chaine.length() > 0))
				return chaine;
		}
		return chaine;
	}

	// Cherche le type d'accord pour le renversement en cours
	// Test�e dans la m�thode main de cette classe via afficheConsole
	public String nomAbrege() {

		Accord maCopie = new Accord(this);
		maCopie.simplifie();
		String noms = "";
		if (!AccordNomFamille.getNomFamille(maCopie.degres).equals("ko"))
			noms = rajoute(noms, fondamentale.getNom() + AccordNomFamille.getNomFamille(maCopie.degres), ";");
		return noms;
	}

	// Le renversement monte la base actuelle de 1 octave :
	// r� fa la do devient fa la do r�
	// Test� dans la m�thode main de la classe AccordsTest
	public void renverseAccord() {
		degres.remove(0);
		degres.add(13);
		fondamentale.monter(degres.get(0) - 1);
		tasse();
	}

	// transpose un accord avec un ecart de n demi-tons
	// Test� dans la m�thode main de la classe AccordsTest
	public void transpose(int ecart) {
		fondamentale.monter(ecart);
	}

	// Simplifie un accord en supprimant les notes pr�sentes � deux octaves
	// diff�rentes, on ne garde que l'octave inf�rieur
	// Test� dans la m�thode main de cette classe
	public void simplifie() {
		// Comme les degr�s sont class�s par odre croissant, on part du haut
		for (int i = degres.size() - 1; i > 0; i--)
			// Si on trouve en dessous un degr� valant m�me note,
			for (int j = i - 1; j >= 0; j--) {
				if (degres.get(i) == degres.get(j)%12) {
					// On supprime le degr� en cours
					degres.remove(i--);
					j = i - 1;
				}
			}
	}

	// compare les degr�s de l'accord avec un tableau de degr�s
	// Test� dans la m�thode main de cette classe
	public boolean equals(int[] tabDegres) {
		// Si diff�rence de taille : on renvoie faux
		if (tabDegres.length != degres.size())
			return false;
		// Si diff�rence d'un element : on renvoie faux
		for (int i = 0; i < degres.size(); i++)
			if (degres.get(i) != tabDegres[i])
				return false;
		// Sinon on renvoie vrai
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degres == null) ? 0 : degres.hashCode());
		result = prime * result + ((fondamentale == null) ? 0 : fondamentale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accord other = (Accord) obj;
		
		if (fondamentale == null) {
			if (other.fondamentale != null)
				return false;
		} else if (!fondamentale.equals(other.fondamentale))
			return false;
		
		if (degres == null) {
			if (other.degres != null)
				return false;
		} else if (!this.degres.equals(other.degres))
			return false;
		return true;
	}

	// initialise les degres avec un tableau d'entiers
	// Test� via les appels aux constructeurs
	public void setDegres(int[] tabDegres) {
		// on vide notre tableau
		degres.clear();
		// On trie le tableau pass� en param�tre
		Arrays.sort(tabDegres);
		// On ajoute tous les composants un par un
		for (int i = 0; i < tabDegres.length; i++)
			degres.add(tabDegres[i]);
		// On "tasse" le tableau sur une base de 1
		tasse();
	}

	// initialise les degres avec ceux d'un autre accord
	// Test� via le constructeur par copie d'accord
	public void setDegres(Accord autreAccord) {
		degres = (ArrayList<Integer>) autreAccord.degres.clone();
	}

	// Tasse le tableau des degr�s : si l'�l�ment 0 > 1, on baisse tout
	// ex : 4 8 11 ==> 1 5 8
	// Test� via le renverseAccord
	private void tasse() {
		int decalage;
		decalage = 1 - degres.get(0);
		for (int i = 0; i < degres.size(); i++) {
			degres.set(i, degres.get(i) + decalage);
		}
	}

	// Rajoute chaine2 � chaine1, avec le s�parateur si chaine1 non nulle
	// test� via la m�thode chercheTypeAccord
	private String rajoute(String chaine1, String chaine2, String separateur) {
		if (chaine1.length() > 0)
			return chaine1.concat(separateur).concat(chaine2);
		else
			return chaine2;
	}

	// M�thode qui renvoie la ni�me note de l'accord selon la fondamentale et
	// les degr�s, test� via la m�thode toString
	private Note calculeNote(int rang) {
		Note maNote;
		// La premi�re note est la fondamentale
		maNote = new Note(fondamentale.getNom(), 4);
		maNote.monter(degres.get(rang) - 1);
		return maNote;
	}
}