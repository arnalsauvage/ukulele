package musique;

public class NoteTest {
	public static void testeMajNote(Note maNote, String nomDeLaNote)
	{
		System.out.print("Test de maj de la note pour " + nomDeLaNote + "==>");
		if (maNote.setNom(nomDeLaNote))
			System.out.println("Note modifiée : " + maNote.getNom() + maNote.getOctave());
		else
			System.out.println("Erreur de mise à jour pour " + nomDeLaNote);
	}
	
	public static void testeEcritBemol(Note maNote)
	{
		maNote.ecritEnBemol();
		System.out.println("Note en bémol : " + maNote.getNom());
	}

	public static void testeEcritDiese(Note maNote)
	{
		maNote.ecritEnDiese();
		System.out.println("Note en dièse : " + maNote.getNom());
	}
	
	public static void testeMonterNote(Note maNote, int decalage)
	{
		if (maNote.monter(decalage))
			System.out.println("Note décalée de " + decalage + " 1/2 ton(s) : " + maNote.getNom() + " -" + maNote.getOctave());
		else
			System.out.println("Impossible de décaler de " + decalage + " 1/2 ton(s) !!!");		
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Note maNote;

		maNote = new Note("A",3);
		// Tests MajNote
		testeMajNote (maNote,"A#b");
		testeMajNote (maNote,"R");
		testeMajNote (maNote,"a");
		testeMajNote (maNote,"A#");
		testeMajNote (maNote,"b");
		testeMajNote (maNote,"B#");
		testeMajNote (maNote,"F#");
		// Tests EcritBemol & EcritDiese
		testeEcritBemol (maNote);
		testeEcritBemol (maNote);
		testeEcritDiese (maNote);
		testeEcritDiese (maNote);
		testeMajNote (maNote,"H");
		testeMajNote (maNote,"G");

		// Tests monterNote
		testeMonterNote(maNote, 12);
		testeMonterNote(maNote, 36);
		testeMonterNote(maNote, 12);
		testeMonterNote(maNote, -72);
		testeMonterNote(maNote, -12);
		testeMonterNote(maNote, -12);
		testeMonterNote(maNote, 36);
		testeMonterNote(maNote, 7);	}	
}