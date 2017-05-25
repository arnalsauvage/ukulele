package musique;

import java.util.ArrayList;
import java.util.Arrays;

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
		for (Integer monInt : degres)
			this.degres.add(monInt);
	}

	public void ajouteDegre(Integer degre) {
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
		Integer[] accordMajeur9 = { 1, 3, 5, 11 };

		monAccord = new AccordNomFamille();
		monAccord.setDegres(new Integer[] { 5, 8 });
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
		monAccord.setNomAccord("7M");
		monAccord.AjouteAlaListe();

		monAccord = new AccordNomFamille("7", accordMajeur7);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("5", powerChord);
		monAccord.AjouteAlaListe();
		monAccord = new AccordNomFamille("5aug", accordMajeurAugmente);
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
		monAccord = new AccordNomFamille("79", accordMajeur9);
		monAccord.AjouteAlaListe();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// AccordNomFamille monAccord = new AccordNomFamille();
		AccordNomFamille.creeCatalogueAccords();
		System.out.println(AccordNomFamille.monDico());
	}
}
