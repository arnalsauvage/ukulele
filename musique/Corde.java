package musique;
// Cette classe permet de gérer une corde de ukulélé
public class Corde {
	Note noteRacine;
	// Constructeur de la corde avec la note de base et son octave
	public Corde (String nomNote, int lOctave){
		noteRacine = new Note (nomNote, lOctave);
	}
	// getteur pour récupérer la note racine de la corde
	public Note getNoteRacine() {
		return noteRacine;
	}
	// définit la note racine de la corde
	public void setNoteRacine(Note noteRacine) {
		this.noteRacine = noteRacine;
	}
	
	// renvoie la note jouée à la frette "numeroFrette"
	public Note noteDeLaFrette(int numeroFrette){
		Note laNote;
		laNote = new Note(noteRacine.getNom(), noteRacine.getValeur());
		laNote.monter (numeroFrette);
		return laNote;
	}
	
	// méthode de test de la classe
	public static void main(String[] args) {
		// Test du constructeur
		Corde maCorde = new Corde("A",4);
		System.out.println("=====Création d'un objet corde (note A4) ok.");

		// Test de la méthode getNoteRacine
		if (maCorde.getNoteRacine().getNom().equals("A"))
			System.out.println("getNoteRacine ok : la méthode renvoie 'A'");
		else
			System.out.println("getNoteRacine KO !!!  la méthode renvoie " + maCorde.getNoteRacine().getNom());
			
		Note maNote = new Note("C",4);
		// Test de la méthode setNoteRacine
		System.out.println("=====On réinitialise la note de base de la corde à Do 4");
		maCorde.setNoteRacine(maNote);	
		if (maCorde.getNoteRacine().getNom().equals("C"))
			System.out.println("test ok : la méthode renvoie 'C'");
		else
			System.out.println("test KO !!!  la méthode renvoie " + maCorde.getNoteRacine().getNom());
					
		// test de la méthode NoteDeLaFrette
		System.out.println("=====Note de la frette 4 en do (mi attendu) : ");
		if (maCorde.noteDeLaFrette(4).getNom().equals("E"))
			System.out.println("test ok : la méthode renvoie 'E'");
		else
			System.out.println("test KO !!!  la méthode renvoie " + maCorde.noteDeLaFrette(4).getNom());
	}
}