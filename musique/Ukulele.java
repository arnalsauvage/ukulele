package musique;

import java.util.ArrayList;

public class Ukulele {
	Corde corde1;
	Corde corde2;
	Corde corde3;
	Corde corde4;

	// Constructeur par défaut
	Ukulele() {
		corde1 = new Corde("G", 4);
		corde2 = new Corde("C", 4);
		corde3 = new Corde("E", 4);
		corde4 = new Corde("A", 4);
	}

	// Cette méthode va chercher l'accord correspondant à une position
	public Accord trouveAccordPosition(int a, int b, int c, int d) {
		Note maNote;
		int[] tabDegres;
//		System.out.println("a : " + a + " b: " + b + " c:" + c + " d:" + d);
		
		if (a==-1 && b==-1 && c==-1 && d==-1)
			return null;
		
		// On met les 4 notes dans un tableau pour les trier
		ArrayList<Integer> notesJouees = new ArrayList<Integer>(4);
		if (a >= 0)
			notesJouees.add(corde1.noteDeLaFrette(a).getValeur());
		if (b >= 0)
			notesJouees.add(corde2.noteDeLaFrette(b).getValeur());
		if (c >= 0)
			notesJouees.add(corde3.noteDeLaFrette(c).getValeur());
		if (d >= 0)
			notesJouees.add(corde4.noteDeLaFrette(d).getValeur());

		notesJouees.sort(null);
		if (notesJouees.size() <2)
			return null;
		

		// On supprime les doublons s'il y en a, du plus aigu vers le plus grave
		if (notesJouees.size()> 3 && notesJouees.get(3) == notesJouees.get(2))
			notesJouees.remove(3);
		if (notesJouees.size()> 2 &&notesJouees.get(2) == notesJouees.get(1))
			notesJouees.remove(2);
		if (notesJouees.size()> 1 &&notesJouees.get(1) == notesJouees.get(0))
			notesJouees.remove(1);

		// On construit le tableau des degrés avec les notes qu'il reste
		tabDegres = new int[notesJouees.size()];
		for (int i = 1; i < notesJouees.size(); i++)
			tabDegres[i] = notesJouees.get(i) - notesJouees.get(0) + 1;
		tabDegres[0] = 1;

		// On construit l'accord et on le renvoie
		maNote = new Note(notesJouees.get(0));
		Accord monAccord = new Accord(maNote.getNomNote(), tabDegres);
		return monAccord;
	}

	// Méthode pour tester TrouveAccords
	public boolean testeTrouveAccordPosition(boolean bAffichageConsole) {
		boolean bRetour = true;

		bRetour = testeUnePositionDaccords(0, 0, 0, 0, "C6;Am7", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(0, 1, 0, 0, "A7", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(2, 3, 2, 2, "B7", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(0, 0, 0, 3, "C", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(2, 2, 2, 0, "D", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(2, 2, 2, 5, "D", bAffichageConsole) && bRetour;
		// Le ré7 hawaïen ne contient pas de Ré...
		// bRetour = testeUnePositionDaccords(2,0,2,0,"D7",
		// bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2, 2, 2, 3, "D7", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(2, 2, 1, 0, "Dm", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(1, 4, 0, 2, "E", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(1, 2, 0, 2, "E7", bAffichageConsole) && bRetour;

		bRetour = testeUnePositionDaccords(2, 0, 1, 0, "F", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(2, 3, 1, 3, "F7", bAffichageConsole) && bRetour;
		bRetour = testeUnePositionDaccords(0, 0, 0, 5, "C9", bAffichageConsole) && bRetour;

		if (bAffichageConsole)
			System.out.println("\nLe résultat des testsTrouveAccordPosition est : " + bRetour);

		return bRetour;
	}

	public boolean testeUnePositionDaccords(int a, int b, int c, int d, String sNom, boolean bAffichageConsole) {
		boolean bRetour = false;
		Accord monAccord;
		String maChaine;

		monAccord = trouveAccordPosition(a, b, c, d);
		maChaine = monAccord.chercheTypeAccord(true);
		if (bAffichageConsole) {
			if (maChaine.equals(sNom)) {
				System.out.print("\nNoms trouvés pour " + a + b + c + d + " --> " + maChaine);
				bRetour = true;
			} else {
				System.err.print("\nNoms trouvés pour " + a + b + c + d + " --> " + maChaine + " attendus : " + sNom);
				bRetour = false;
			}

		}
		return bRetour;
	}
}