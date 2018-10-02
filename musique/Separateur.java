package musique;

import java.util.ArrayList;

public class Separateur {

	static String valeursAutorisees = " /\n";
	String libelle;

	public Separateur() {
		libelle = "";
	}

	public Separateur(String libelle) {
		libelle = "";
		setLibelle(libelle);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		if (libelle.equals("\t"))
			libelle = " ";
		if (libelle.equals(""))
			this.libelle = " ";
		if (valeursAutorisees.contains(libelle))
			this.libelle = libelle;
		else
			System.err.println("Separateur d'accords '" + libelle + "' non reconnu.");
	}

	// Cette fonction permet d'extraire tous les séparateurs dans une chaîne
	// de type : "Am\tC/F G\n" renvoie les séparateurs " ", "/", " ", "\n" dans
	// une arrayList
	public static String getListeSeparateurs(String chaineAccord, ArrayList<Separateur> listeSeparateurs) {
		// remplacer les tab par des espaces
		chaineAccord = chaineAccord.replace("\t", " ");
		// gérer les '/'
		chaineAccord = chaineAccord.replace(" /", "/");
		chaineAccord = chaineAccord.replace("/ ", "/");

		// gérer les '\n'
		chaineAccord = chaineAccord.replace(" \n", "\n");
		chaineAccord = chaineAccord.replace("\n ", "\n");

		// enlever les doublons
		chaineAccord = chaineAccord.replace("  ", " ");

		listeSeparateurs.clear();
		// On parcours la chaine pour ajouter les séparateurs
		for (int i = 0; i < chaineAccord.length(); i++) {
			if (valeursAutorisees.contains(chaineAccord.substring(i, i+1))){
				listeSeparateurs.add(new Separateur(chaineAccord.substring(i, i+1)));
			}
				
		}

		return chaineAccord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Separateur other = (Separateur) obj;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Separateur [libelle=" + libelle + "]";
	}
}