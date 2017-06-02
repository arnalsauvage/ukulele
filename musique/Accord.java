package musique;

import java.util.ArrayList;
import java.util.Arrays;


// TODO : personnaliser la boite de dialogue ouvrir
// TODO : personnaliser la boite de dialogue enregistrer
// TODO : uniquement les accords fermés
// TODO : pour un enchainement d'accords, proposer la position la plus proche de la position précédente
// TODO : classer en 3 niveaux : débutants, confirmé et champion
// DONE : débuguer le transpose négatif qui "bloque"
// DONE : gérer dans la fenetre les retours de chariot tab et autres séparateurs
// DONE : créer une Classe familleAccord qui fait correspondre un tableau de degrés et un nom
// DONE : familleAccord .lireDepuisFichTexte, comparer 
// DONE : proposer l'enregistrement dans un fichier texte
// DONE : proposer la lecture depuis un fichier texte

public class Accord {

//	static AccordNomFamille dicoAccords = new AccordNomFamille();

	private NoteNom fondamentale; // Fondamentale de l'accord, ex: "C" pour do
									// majeur 4è octave
	private ArrayList<Integer> degres; // Degrés des accords, exemple 1,5,8 pour
										// accord majeur
	private String nomAccord;

	// Constructeur avec une noteNom fondamentale et des degrés en entrée
	// Testé dans la méthode main de cette classe
	public Accord(NoteNom maFondamentale, int[] mesDegres) {
		fondamentale = new NoteNom(maFondamentale.getNom());
		degres = new ArrayList<Integer>(mesDegres.length);
		setDegres(mesDegres);
	}

	// Constructeur avec une chaîne de caractères contenant le nom de l'acord
	// Testé dans la méthode main de la classe AccordsTest
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
		if (nomFondamentale.length()<nom.length())
			description = nom.substring(nomFondamentale.length());

		
		degres = AccordNomFamille.getDegres(description);
		if ((degres==null))
			degres = new ArrayList<Integer>();
		if (degres.isEmpty())
			degres.add(1);
//		// Le Top 11 des accords les plus utilisés
//		int[] powerChord = { 1, 8 };
//		int[] accordMajeur = { 1, 5, 8 };
//		int[] accordMajeurAugmente = { 1, 5, 9 };
//		int[] accordMajeur6 = { 1, 5, 8, 10 };
//		int[] accordMineur = { 1, 4, 8 };
//		int[] accordMineur6 = { 1, 4, 8, 10 };
//		int[] accordDiminue = { 1, 4, 7 };
//		int[] accordDiminue7 = { 1, 4, 7, 10 };
//		int[] accordMajeur7 = { 1, 5, 8, 11 };
//		int[] accordMajeur7maj = { 1, 5, 8, 12 };
//		int[] accordMineur7 = { 1, 4, 8, 11 };
//		int[] accordMineur7quinteB = { 1, 4, 7, 11 };
//		int[] accordMajeur9 = { 1, 3, 5, 11 };
//
//		switch (description) {
//		case "":
//			setDegres(accordMajeur);
//			break;
//		case "5":
//			setDegres(powerChord);
//			break;
//		case "aug":
//			setDegres(accordMajeurAugmente);
//			break;
//		case "6":
//			setDegres(accordMajeur6);
//			break;
//		case "m":
//			setDegres(accordMineur);
//			break;
//		case "m6":
//			setDegres(accordMineur6);
//			break;
//		case "dim":
//			setDegres(accordDiminue);
//			break;
//		case "dim7":
//			setDegres(accordDiminue7);
//			break;
//		case "7":
//			setDegres(accordMajeur7);
//			break;
//		case "M7":
//			setDegres(accordMajeur7maj);
//			break;
//		case "m7":
//			setDegres(accordMineur7);
//			break;
//		case "m7-5":
//			setDegres(accordMineur7quinteB);
//			break;
//		case "9":
//			setDegres(accordMajeur9);
//			break;
//		}
	}

	// Constructeur par copie
	// Testé dans la méthode main de cette classe
	public Accord(Accord monAccord) {
		fondamentale = new NoteNom(monAccord.fondamentale.getNom());
		degres = new ArrayList<Integer>(monAccord.degres.size());
		setDegres(monAccord);
	}

	// Méthode d'affichage de l'accord en console pour tests
	// Testée dans la méthode main de cette classe
	public void afficheConsole() {
		Note maNote;
		System.out.print("Accord : " + this.chercheTypeAccord(true) + " - ");

		for (int i = 0; i < degres.size(); i++) {
			maNote = calculeNote(i);
			System.out.print(maNote.getNom() + maNote.getOctave() + " ");
		}
		System.out.print("\t");
	}

	// On va chercher pour chaque renversement le/les nom(s) de l'accord
	// Testée dans la méthode main de cette classe via afficheConsole
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

			// copieAccord.afficheConsole();
			if ((tousLesAccords == false) && (chaine.length() > 0))
				return chaine;
		}
		return chaine;
	}

	// Cherche le type d'accord pour le renversement en cours
	// Testée dans la méthode main de cette classe via afficheConsole
	public String nomAbrege() {
//		String noms = "";

//		int[] powerChord = { 1, 8 };
//		int[] accordMajeur = { 1, 5, 8 };
//		int[] accordMajeurAugmente = { 1, 5, 9 };
//		int[] accordMajeur6 = { 1, 5, 8, 10 };
//		int[] accordMineur = { 1, 4, 8 };
//		int[] accordMineur6 = { 1, 4, 8, 10 };
//		int[] accordDiminue = { 1, 4, 7 };
//		int[] accordDiminue7 = { 1, 4, 7, 10 };
//		int[] accordMajeur7 = { 1, 5, 8, 11 };
//		int[] accordMajeur7maj = { 1, 5, 8, 12 };
//		int[] accordMineur7 = { 1, 4, 8, 11 };
//		int[] accordMineur7quinteB = { 1, 4, 7, 11 };
//		int[] accordMajeur9 = { 1, 3, 5, 11 };

		Accord maCopie = new Accord(this);
		maCopie.simplifie();
		String noms ="";
		if (!AccordNomFamille.getNomFamille(maCopie.degres).equals("ko"))
			noms = rajoute(noms, fondamentale.getNom() + AccordNomFamille.getNomFamille(maCopie.degres), ";");
		return noms;
//		if (maCopie.egale(accordMineur7quinteB)) {
//			noms = rajoute(noms, fondamentale.getNom() + "m7-5", ";");
//		}
//		if (maCopie.egale(powerChord)) {
//			noms = rajoute(noms, fondamentale.getNom() + "5", ";");
//		}
//		if (maCopie.egale(accordMajeur)) {
//			noms = rajoute(noms, fondamentale.getNom(), ";");
//		}
//		if (maCopie.egale(accordMajeurAugmente)) {
//			noms = rajoute(noms, fondamentale.getNom() + "aug", ";");
//		}
//		if (maCopie.egale(accordMajeur6)) {
//			noms = rajoute(noms, fondamentale.getNom() + "6", ";");
//		}
//		if (maCopie.egale(accordMineur)) {
//			noms = rajoute(noms, fondamentale.getNom() + "m", ";");
//		}
//		if (maCopie.egale(accordMineur6)) {
//			noms = rajoute(noms, fondamentale.getNom() + "m6", ";");
//		}
//		if (maCopie.egale(accordMajeur7)) {
//			noms = rajoute(noms, fondamentale.getNom() + "7", ";");
//		}
//		if (maCopie.egale(accordMajeur7maj)) {
//			noms = rajoute(noms, fondamentale.getNom() + "M7", ";");
//		}
//		if (maCopie.egale(accordMineur7)) {
//			noms = rajoute(noms, fondamentale.getNom() + "m7", ";");
//		}
//		if (maCopie.egale(accordDiminue)) {
//			noms = rajoute(noms, fondamentale.getNom() + "dim", ";");
//		}
//		if (maCopie.egale(accordDiminue7)) {
//			noms = rajoute(noms, fondamentale.getNom() + "dim7", ";");
//		}
//		if (maCopie.egale(accordMajeur9)) {
//			noms = rajoute(noms, fondamentale.getNom() + "9", ";");
//		}
//		return (noms);
	}

	// Le renversement monte la base actuelle de 1 octave :
	// ré fa la do devient fa la do ré
	// Testé dans la méthode main de la classe AccordsTest
	public void renverseAccord() {
		degres.remove(0);
		degres.add(13);
		fondamentale.monter(degres.get(0) - 1);
		tasse();
	}

	// transpose un accord avec un ecart de n demi-tons
	// Testé dans la méthode main de la classe AccordsTest
	public void transpose(int ecart) {
		fondamentale.monter(ecart);
	}

	// compare l'accord en cours avec un autre accord
	// Testé dans la méthode main de cette classe
	public boolean compare(Accord AutreAccord) {
		if (AutreAccord.fondamentale.getValeur() != fondamentale.getValeur())
			return false;
		if (AutreAccord.degres==null)
			return false;
		if (AutreAccord.degres.size() != degres.size())
			return false;
		for (int i = 0; i < AutreAccord.degres.size() - 1; i++)
			if (AutreAccord.degres.get(i) != degres.get(i))
				return false;
		return true;
	}

	// Simplifie un accord en supprimant les notes présentes à deux octaves
	// différentes, on ne garde que l'octave inférieur
	// Testé dans la méthode main de cette classe
	public void simplifie() {
		// Comme les degrés sont classés par odre croissant, on part du haut
		for (int i = degres.size() - 1; i > 0; i--)
			// Si on trouve en dessous un de gré à l'octave inférieur,
			for (int j = i - 1; j >= 0; j--) {
				if (degres.get(i) == 12 + degres.get(j)) {
					// On supprime le degré en cours
					degres.remove(i--);
					j = i - 1;
				}
			}
	}

	// compare les degrés de l'accord avec un tableau de degrés
	// Testé dans la méthode main de cette classe
	private boolean egale(int[] tabDegres) {
		// Si différence de taille : on renvoie faux
		if (tabDegres.length != degres.size())
			return false;
		// Si différence d'un element : on renvoie faux
		for (int i = 0; i < degres.size(); i++)
			if (degres.get(i) != tabDegres[i])
				return false;
		// Sinon on renvoie vrai
		return true;
	}

	// initialise les degres avec un tableau d'entiers
	// Testé via les appels aux constructeurs
	private void setDegres(int[] tabDegres) {
		// on vide notre tableau
		degres.clear();
		// On trie le tableau passé en paramètres
		Arrays.sort(tabDegres);
		// On ajoute tous les composants un par un
		for (int i = 0; i < tabDegres.length; i++)
			degres.add(tabDegres[i]);
		// On "tasse" le tableau sur une base de 1
		tasse();
	}

	// initialise les degres avec ceux d'un autre accord
	// Testé via le constructeur par copie d'accord
	private void setDegres(Accord autreAccord) {
		// on vide notre tableau
		degres.clear();
		// On recopie les degres de l'autre accord
		for (int i = 0; i < autreAccord.degres.size(); i++) {
			degres.add(autreAccord.degres.get(i));
		}
	}

	// Tasse le tableau des degrés : si l'élément 0 > 1, on baisse tout
	// ex : 4 8 11 ==> 1 5 8
	// Testé via le renverseAccord
	private void tasse() {
		int decalage;
		decalage = 1 - degres.get(0);
		for (int i = 0; i < degres.size(); i++) {
			degres.set(i, degres.get(i) + decalage);
		}
	}

	// Rajoute chaine2 à chaine1, avec le séparateur si chaine1 non nulle
	// testé via la méthode chercheTypeAccord
	private String rajoute(String chaine1, String chaine2, String separateur) {
		if (chaine1.length() > 0)
			return chaine1.concat(separateur).concat(chaine2);
		else
			return chaine2;
	}

	// Méthode qui renvoie la nième note de l'accord selon la fondamentale et
	// les degrés
	// testé via la méthode afficheConsole
	private Note calculeNote(int rang) {
		Note maNote;
		// La première note est la fondamentale
		maNote = new Note(fondamentale.getNom(), 4);
		maNote.monter(degres.get(rang) - 1);
		return maNote;
	}

	// Test des méthodes "
	public static void main(String[] args) {
		int[] accordMajeur = { 1, 5, 8, 13 };
		Accord monAccord;
		NoteNom maNote;

		AccordNomFamille.creeCatalogueAccords();
		
		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur);
		
		// CalculeNote est validé par l'appel de AfficheConsole

		
		
		// Teste la méthode egale
		System.out.println("///// Test de la méthode egale /////");
		if (monAccord.egale(accordMajeur))
			System.out.println("Fonction egale ok pour un accord identique");
		else
			System.err.println("Fonction egale KO pour un accord identique !!!");

		int[] autreAccord = { 1, 5, 8 };
		if (!monAccord.egale(autreAccord))
			System.out.println("Fonction egale ok pour un accord avec un nombre différent de degrés");
		else
			System.err.println("Fonction egale KO pour un accord avec un nombre différent de degrés !!!");

		int[] accord3 = { 1, 3, 8, 13 };
		if (!monAccord.egale(accord3))
			System.out.println("Fonction egale ok pour un accord avec un degré différent");
		else
			System.err.println("Fonction egale KO pour un accord avec un degré différent !!!");

		// Teste méthode compare
		Accord mon2emeAccord = new Accord(maNote, accordMajeur);
		System.out.println("\n///// Test de la méthode compare /////");
		if (monAccord.compare(mon2emeAccord))
			System.out.println("Fonction egale ok pour un accord identique");
		else
			System.err.println("Fonction egale KO pour un accord identique !!!");

		mon2emeAccord = new Accord("C");
		if (!monAccord.compare(mon2emeAccord))
			System.out.println("Fonction egale ok pour un accord avec un nombre différent de degrés");
		else
			System.err.println("Fonction egale KO pour un accord avec un nombre différent de degrés !!!");

		mon2emeAccord = new Accord(maNote, accord3);
		if (!monAccord.compare(mon2emeAccord))
			System.out.println("Fonction egale ok pour un accord avec un degré différent");
		else
			System.err.println("Fonction egale KO pour un accord avec un degré différent !!!");

		// Teste le simplifie accord
		System.out.println("\n///// Test de la méthode simplifie /////");
		System.out.print("Simplifie C : do mi sol do :");
		monAccord = new Accord(maNote, accordMajeur);
		monAccord.simplifie();
		monAccord.afficheConsole();
		System.out.print("\nSimplifie C : do mi sol :");
		monAccord.simplifie();
		monAccord.afficheConsole();

		// teste le constructeur par accord et la méthode compare par accord
		System.out.println("\n\n///// Test du constructeur par accord et de la méthode compare /////");
		Accord mon3emeAccord = new Accord(mon2emeAccord);
		System.out.print("Copie de accord2 vers Accord3 :");
		mon3emeAccord.afficheConsole();
		if (mon3emeAccord.compare(mon2emeAccord))
			System.out.println("\nFonction compare ok pour un accord égal");
		else
			System.err.println("\nFonction egale KO pour un accord égal !!!");
	}
}