package musique;
import java.util.ArrayList;

public class Ukulele {
	Corde corde1;
	Corde corde2;
	Corde corde3;
	Corde corde4;

	// Constructeur par d�faut
	Ukulele()
	{
		corde1 = new Corde("G",4);
		corde2 = new Corde("C",4);
		corde3 = new Corde("E",4);
		corde4 = new Corde("A",4);
	}

	// Cette m�thode va chercher l'accord corespondant � une position
	public Accord trouveAccordPosition(int a, int b, int c, int d)
	{
		Note maNote;
		int[] tabDegres;
		
		// On met les 4 notes dans un tableau pour les trier
		ArrayList<Integer> notesJouees = new ArrayList<Integer>(4);

		notesJouees.add(corde1.noteDeLaFrette(a).getValeur());
		notesJouees.add(corde2.noteDeLaFrette(b).getValeur());
		notesJouees.add(corde3.noteDeLaFrette(c).getValeur());
		notesJouees.add(corde4.noteDeLaFrette(d).getValeur());

		notesJouees.sort(null);

		// On supprime les doublons s'il y en a, du plus aigu vers le plus grave
		if (notesJouees.get(3)==notesJouees.get(2))
			notesJouees.remove(3);
		if (notesJouees.get(2)==notesJouees.get(1))
			notesJouees.remove(2);
		if (notesJouees.get(1)==notesJouees.get(0))
			notesJouees.remove(1);

		// On construit le tableau des degr�s avec les notes qu'il reste
		tabDegres = new int[notesJouees.size()];
		for (int i=1; i<notesJouees.size() ;i++)
			tabDegres[i] = notesJouees.get(i) - notesJouees.get(0) + 1;
		tabDegres[0] = 1;

		// On construit l'accord et on le renvoie
		maNote = new Note(notesJouees.get(0));
		Accord monAccord = new Accord(maNote.getNomNote(),tabDegres);
		return monAccord;
	}

	// M�thode pour tester TrouveAccords
	public boolean testeTrouveAccordPosition(boolean bAffichageConsole)
	{
		boolean bRetour = true;

		bRetour = testeUnePositionDaccords(0,0,0,0,"Am7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(0,1,0,0,"A7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,3,2,2,"B7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(0,0,0,3,"C", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,2,2,0,"D", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,2,2,5,"D", bAffichageConsole)&&bRetour;
		//  Le r�7 hawa�en ne contient pas de R�...
		//	bRetour = testeUnePositionDaccords(2,0,2,0,"D7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,2,2,3,"D7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,2,1,0,"Dm", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(1,4,0,2,"E", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(1,2,0,2,"E7", bAffichageConsole)&&bRetour;

		bRetour = testeUnePositionDaccords(2,0,1,0,"F", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(2,3,1,3,"F7", bAffichageConsole)&&bRetour;
		bRetour = testeUnePositionDaccords(3,0,0,5,"C9", bAffichageConsole)&&bRetour;

		if (bAffichageConsole)
			System.out.println("\nLe r�sultat des testsTrouveAccordPosition est : " + bRetour);

		return bRetour;
	}

	public boolean testeUnePositionDaccords(int a, int b, int c, int d, String sNom, boolean bAffichageConsole)
	{
		boolean bRetour = false;
		Accord monAccord;
		String maChaine;

		monAccord = trouveAccordPosition(a,b,c,d);
		maChaine = monAccord.chercheTypeAccord(true);
		if (bAffichageConsole)
			System.out.print("\nNoms trouv�s pour "+a+b+c+d+" --> " + maChaine);

		// On r�cup�re tous les accords s�par�s par des ";"
		String[] split = maChaine.split(";");
		for (int index = 0; index < split.length; index++) {
			// Si un des accords est le bon...
			if(split[index].equals(sNom))
				bRetour = true;
		}	
		if (bAffichageConsole && !bRetour){
			System.out.print("\nErreur pour trouver "+a+b+c+d+" --> " + sNom);
			System.out.print(" accord trouv� : " + maChaine + " * ");
			monAccord.afficheConsole();
		}
		return bRetour;
	}
}