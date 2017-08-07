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
//		degres = new ArrayList<Integer>();
//		for (Integer monInt : autreAccord.getDegres())
//			degres.add(monInt);
		degres = (ArrayList<Integer>) autreAccord.getDegres().clone();
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

	private void AjouteAlaListe() {
		mesAccords.add(this);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degres == null) ? 0 : degres.hashCode());
		result = prime * result + ((nomAccord == null) ? 0 : nomAccord.hashCode());
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
		AccordNomFamille other = (AccordNomFamille) obj;
		if (degres == null) {
			if (other.degres != null)
				return false;
		} else if (!degres.equals(other.degres))
			return false;
		if (nomAccord == null) {
			if (other.nomAccord != null)
				return false;
		} else if (!nomAccord.equals(other.nomAccord))
			return false;
		return true;
	}

	
	///////////////////////// Statique /////////////////////////////////

	
	public static String monDico() {
		String dico = "";

		for (AccordNomFamille monAccord : AccordNomFamille.mesAccords) {
			dico += monAccord + "\n";
		}
		return dico;
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

	// Renvoie les degrés de l'accord désigné par son nom de famille
	public static ArrayList<Integer> getDegres(String nom) {
		for (AccordNomFamille monANF : AccordNomFamille.mesAccords)
			if (monANF.nomAccord.equals(nom))
				return (monANF.degres);
		return null;
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
		monAccord.setDegres(accordMajeur);
		monAccord.setNomAccord("");
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille("m", accordMineur);
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille("M7", accordMajeur7maj);
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
}
