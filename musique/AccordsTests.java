package musique;
public class AccordsTests {

	public static void main(String[] args) {
		int [] accordMajeur = {1,5,8,13};
		int [] accordMineur = {1,4,8,13};
		int [] accordMajeur7 = {1,5,8,11};
		int [] accordMineur7 = {1,4,8,11};
		int [] accordMajeur9 = {1,5,8,15};

		AccordNomFamille.creeCatalogueAccords();
		
		System.out.println("=====Tests du constructeur, de calculeNotes et de affiche");
		testeTypeAccord(accordMajeur,  "1)Affichage accords majeurs");
		testeTypeAccord(accordMajeur7, "2)Affichage accords majeurs 7è");
		testeTypeAccord(accordMineur,  "3)Affichage accords mineurs");
//		testeTypeAccord(accordMineur7, "4)Affichage accords mineurs 7è");
		testeTypeAccord(accordMajeur9, "4)Affichage accords majeurs 9");
		testeConstructeurString();
		System.out.println();

		testeRenverseAccords();

		System.out.println("\n\n7)Test TrouveAccord pour une position uku" ); 
		Ukulele uke;
		uke = new Ukulele();
		uke.testeTrouveAccordPosition(true);

		testeChercheTypeAccord();
		Accord monAccord = new Accord ("Cm7");

		// Tests transposer
		teste_Transposer(monAccord,2,"Dm7");
	}
	
	// Teste la classe en affichant une collection d'accords C D E F G A B
	private static void testeTypeAccord(int[] tabNotes, String message) {
		Accord monAccord;
		NoteNom maNote;
		maNote = new NoteNom("C");
		System.out.println("\n"+ message);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();	
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.afficheConsole();
		System.out.println("");
	}
	
	public static void testeConstructeurString(){
		System.out.println("\n5)Test du constructeur par une chaine : Cm7 C#dim7 Cb7 C5 Fm7-5"); 
		Accord monAccord = new Accord("C");
		monAccord.afficheConsole();
		monAccord = new Accord("D");
		monAccord.afficheConsole();
		monAccord = new Accord("E");
		monAccord.afficheConsole();
		monAccord = new Accord("F");
		monAccord.afficheConsole();
		 monAccord = new Accord("Cm7");
		monAccord.afficheConsole();
		monAccord = new Accord("C#dim7");
		monAccord.afficheConsole();
		monAccord = new Accord("Cb7");
		monAccord.afficheConsole();
		monAccord = new Accord("C5");
		monAccord.afficheConsole();
		monAccord = new Accord("Fm75b");
		monAccord.afficheConsole();
	}

	public static void testeRenverseAccords()
	{
		NoteNom maNote;
		//		int [] accordMajeur = {1,5,8};
		//		int [] accordMineur = {1,4,8};
		//		int [] accordMajeur7 = {1,5,8,11};
		int [] accordMineur7 = {1,4,8,11};

		System.out.println("\n6)Test de la méthode renverse accords avec Am7" ); 

		Accord monAccord;
		maNote = new NoteNom("A");
		monAccord = new Accord(maNote, accordMineur7);
		monAccord.afficheConsole();
		monAccord.renverseAccord();
		monAccord.afficheConsole();
		monAccord.renverseAccord();
		monAccord.afficheConsole();
		monAccord.renverseAccord();
		monAccord.afficheConsole();
	}

	public static void testeChercheTypeAccord(){
		int[] tabDegres = new int[] {1,4,8};
		
		System.out.println("\n8)Chercher les types d'accord" ); 
		teste1ChercheTypeAccord ("C",tabDegres,"Cm");
		tabDegres = new int[] {1,4,8,11};
		teste1ChercheTypeAccord ("C",tabDegres,"Cm7");
		tabDegres = new int[] {1,5,8,11};
		teste1ChercheTypeAccord ("C",tabDegres,"C7");
		tabDegres = new int[] {1,5,8,10};
		teste1ChercheTypeAccord ("C",tabDegres,"C6");
		tabDegres = new int[] {1,4,7,10};
		teste1ChercheTypeAccord ("C",tabDegres,"Cdim7");
		System.out.print("Résultat de chercheTypeAccord sur accord(Am7) : " );
		Accord monAccord = new Accord("C6");
		System.out.println( monAccord.chercheTypeAccord(true));
		monAccord.afficheConsole();
		
	}
	
	public static void teste1ChercheTypeAccord(String nomNote, int[] tabDegres, String resultat){
		NoteNom maNote = new NoteNom (nomNote);
		Accord monAccord = new Accord(maNote, tabDegres);
		if (monAccord.nomAbrege().equals(resultat))
			System.out.println(resultat + " trouvé");
		else
			System.err.println("Erreur pour trouver "+resultat+" ==> " + monAccord.nomAbrege());
	}

	public static void teste_Transposer(Accord monAccord, int transpose, String resultat)
	{
		Accord autreAccord = new Accord(monAccord);
		autreAccord.transpose(transpose);
		if (autreAccord.nomAbrege().equals(resultat))
			System.out.println(resultat + " trouvé");
		else
			System.err.println("Erreur pour trouver " + monAccord.nomAbrege() + " ==>" + resultat  +  " résultat trouvé : " + autreAccord.nomAbrege());
	}

}