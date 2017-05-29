package musique;

import java.util.ArrayList;

public class AccordNomFamille {
	// Liste pour stocker l'ensemble des accords
	static ArrayList<AccordNomFamille> mesAccords = new ArrayList<AccordNomFamille>();
	private ArrayList<Integer> degres; // Degrés des accords, exemple 1,5,8 pour
										// accord majeur
	private String nomAccord;

	///////////////////////// Constructeurs /////////////////////////////////
	// constructeur par défaut
	public AccordNomFamille() {
		degres = new ArrayList<Integer>();
		degres.add(1);
		nomAccord = "X";
	}

	// constructeur par copie
	public AccordNomFamille(AccordNomFamille autreAccord) {
		degres = new ArrayList<Integer>();
		for (Integer monInt : autreAccord.getDegres())
			degres.add(monInt);
		nomAccord = autreAccord.getNomAccord();
	}

	// Constructeur par nom + tableau d'entiers
	public AccordNomFamille(String nom, Integer[] degres) {
		this.degres = new ArrayList<Integer>();
		setDegres(degres);
		setNomAccord(nom);
	}

	@Override
	public String toString() {
		return "Accord " + nomAccord + degres;
	}

	///////////////////////// Getters & Setters
	///////////////////////// /////////////////////////////////

	public ArrayList<Integer> getDegres() {
		return degres;
	}

	public void setDegres(ArrayList<Integer> degres) {
		this.degres = degres;
	}

	public void setDegres(Integer[] degres) {
		this.degres = new ArrayList<Integer>();
		for (Integer monInt : degres)
			this.degres.add(monInt);
	}

	public void ajouteDegre(Integer degre) {
		if (!degres.contains(degres))
			this.degres.add(degre);
	}

	public String getNomAccord() {
		return nomAccord;
	}

	public void setNomAccord(String nomAccord) {
		this.nomAccord = nomAccord;
	}

	public void AjouteAlaListe() {
		mesAccords.add(this);
	}

	///////////////////////// Statique /////////////////////////////////

	public static String monDico() {
		String dico = "";

		for (AccordNomFamille monAccord : AccordNomFamille.mesAccords) {
			dico += monAccord + "\n";
		}
		return dico;
	}

	// Fait une occurrence de test pur nom de famille
	private static void testeUnGetNomFamille(String Accord, String resultat) {
		if (getNomDeFamilleAccord(Accord).equals(resultat))
			System.out.println("Test " + Accord + " ok");
		else
			System.err.println("Test " + Accord + " ko : retourne " + getNomDeFamilleAccord(Accord));
	}

	// Teste le nom de famille
	public static void testeNomDeFamille() {
		System.out.println("=== Démarrage des tests pour la fonction nomDeFamille ===");

		testeUnGetNomFamille("Cm7", "m7");
		testeUnGetNomFamille("D#dim", "dim");
		testeUnGetNomFamille("Gb7M", "7M");
		testeUnGetNomFamille("Adim7", "dim7");
		testeUnGetNomFamille("G6", "6");

		System.out.println("=== Fin des tests pour la fonction nomDeFamille ===");
	}

	// Renvoie les degrés de l'accord désigné par son nom de famille
	public static String getNomDeFamilleAccord(String nom) {
		String nomDeFamille;
		int debut = 1;
		if (nom.length() > 1) {
			String Car2 = nom.substring(1, 2);
			if ((Car2.toLowerCase().equals("b")) || Car2.equals("#"))
				debut = 2;
		}
		nomDeFamille = nom.substring(debut, nom.length());
		return nomDeFamille;
	}

	// Teste la fonction getDegres
	public static void testeGetDegres() {
		Integer[] powerChord = { 1, 8 };
		Integer[] accordMajeur = { 1, 5, 8 };
		Integer[] accordMajeurAugmente = { 1, 5, 9 };
		Integer[] accordMajeur6 = { 1, 5, 8, 10 };

		AccordNomFamille monANF = new AccordNomFamille();
		System.out.println("\n=== Démarrage des tests pour la fonction getDegres ===");

		monANF.setDegres(powerChord);
		testeUnGetDegres("C5", monANF.getDegres());

		monANF.setDegres(accordMajeur);
		testeUnGetDegres("G", monANF.getDegres());

		monANF.setDegres(accordMajeurAugmente);
		testeUnGetDegres("Bbaug", monANF.getDegres());

		monANF.setDegres(accordMajeur6);
		testeUnGetDegres("D6", monANF.getDegres());

		System.out.println("=== Fin des tests pour la fonction getDegres ===");
	}

	// Fait une occurrence de test pour getDegres
	private static void testeUnGetDegres(String nom, ArrayList<Integer> resultat) {
		nom = getNomDeFamilleAccord(nom);
		if (getDegres(nom).equals(resultat))
			System.out.println("Test " + nom + " ok : " + resultat);
		else
			System.err.println(" !!! Test " + nom + " ko : retourne " + getDegres(nom) + "(" + resultat + " attendu)");
	}

	// Renvoie les degrés de l'accord désigné par son nom de famille
	public static ArrayList<Integer> getDegres(String nom) {
		for (AccordNomFamille monANF : AccordNomFamille.mesAccords)
			if (monANF.nomAccord.equals(nom))
				return (monANF.degres);
		return null;
	}

	// Teste la fonction getNom
	public static void testeGetNomFamille() {
		Integer[] powerChord = { 1, 8 };
		Integer[] accordMajeur = { 1, 5, 8 };
		Integer[] accordMajeurAugmente = { 1, 5, 9 };
		Integer[] accordMajeur6 = { 1, 5, 8, 10 };

		AccordNomFamille monANF = new AccordNomFamille();
		System.out.println("\n=== Démarrage des tests pour la fonction getNom ===");

		monANF.setDegres(powerChord);
		testeUnGetNom(monANF.getDegres(), "5");

		monANF.setDegres(accordMajeur);
		testeUnGetNom(monANF.getDegres(), "");

		monANF.setDegres(accordMajeurAugmente);
		testeUnGetNom(monANF.getDegres(), "aug");

		monANF.setDegres(accordMajeur6);
		testeUnGetNom(monANF.getDegres(), "6");

		System.out.println("=== Fin des tests pour la fonction getNom ===");
	}

	// Fait une occurrence de test pour getDegres
	private static void testeUnGetNom(ArrayList<Integer> degres, String resultat) {
		if (getNomFamille(degres).equals(resultat))
			System.out.println("Test " + degres + " ok : " + resultat);
		else
			System.err.println(
					" !!! Test " + degres + " ko : retourne " + getNomFamille(degres) + "(" + resultat + " attendu)");
	}

	// Renvoie le nom de famille de l'accord identifié par ses degrés
	public static String getNomFamille(ArrayList<Integer> degres) {
		for (AccordNomFamille monANF : AccordNomFamille.mesAccords)
			if (monANF.degres.equals(degres))
				return (monANF.nomAccord);
		return "ko";
	}

	public static void creeCatalogueAccords() {
		AccordNomFamille monAccord = new AccordNomFamille();

		Integer[] powerChord = { 1, 8 };
		Integer[] accordMajeur = { 1, 5, 8 };
		Integer[] accordMajeurAugmente = { 1, 5, 9 };
		Integer[] accordMajeur6 = { 1, 5, 8, 10 };
		Integer[] accordMineur = { 1, 4, 8 };
		Integer[] accordMineur6 = { 1, 4, 8, 10 };
		Integer[] accordDiminue = { 1, 4, 7 };
		Integer[] accordDiminue7 = { 1, 4, 7, 10 };
		Integer[] accordMajeur7 = { 1, 5, 8, 11 };
		Integer[] accordMajeur7maj = { 1, 5, 8, 12 };
		Integer[] accordMineur7 = { 1, 4, 8, 11 };
		Integer[] accordMineur7quinteB = { 1, 4, 7, 11 };
		Integer[] accordMajeur79 = { 1, 3, 5, 11 };
		Integer[] accordMajeur9 = { 1, 5, 8, 15 };

		monAccord = new AccordNomFamille();
		monAccord.setDegres(new Integer[] { 1, 5, 8 });
		monAccord.setNomAccord("");
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille();
		monAccord.ajouteDegre(4);
		monAccord.ajouteDegre(8);
		monAccord.setNomAccord("m");
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille();
		monAccord.ajouteDegre(5);
		monAccord.ajouteDegre(8);
		monAccord.ajouteDegre(12);
		monAccord.setNomAccord("M7");
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille("7", accordMajeur7);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("5", powerChord);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("aug", accordMajeurAugmente);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("6", accordMajeur6);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("m6", accordMineur6);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("dim", accordDiminue);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("dim7", accordDiminue7);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("m7", accordMineur7);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("m75b", accordMineur7quinteB);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("m6", accordMineur6);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("9", accordMajeur9);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("79", accordMajeur79);
		monAccord.AjouteAlaListe();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// AccordNomFamille monAccord = new AccordNomFamille();
		AccordNomFamille.creeCatalogueAccords();
		System.out.println(AccordNomFamille.monDico());

		testeNomDeFamille();
		testeGetDegres();
		testeGetNomFamille();
	}
}
