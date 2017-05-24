package musique;
// Cette classe permet de g�rer une corde de ukul�l�
public class Corde {
	Note noteRacine;
	// Constructeur de la corde avec la note de base et son octave
	public Corde (String nomNote, int lOctave){
		noteRacine = new Note (nomNote, lOctave);
	}
	// getteur pour r�cup�rer la note racine de la corde
	public Note getNoteRacine() {
		return noteRacine;
	}
	// d�finit la note racine de la corde
	public void setNoteRacine(Note noteRacine) {
		this.noteRacine = noteRacine;
	}
	
	// renvoie la note jou�e � la frette "numeroFrette"
	public Note noteDeLaFrette(int numeroFrette){
		Note laNote;
		laNote = new Note(noteRacine.getNom(), noteRacine.getValeur());
		laNote.monter (numeroFrette);
		return laNote;
	}
	
	// m�thode de test de la classe
	public static void main(String[] args) {
		// Test du constructeur
		Corde maCorde = new Corde("A",4);
		System.out.println("=====Cr�ation d'un objet corde (note A4) ok.");

		// Test de la m�thode getNoteRacine
		if (maCorde.getNoteRacine().getNom().equals("A"))
			System.out.println("getNoteRacine ok : la m�thode renvoie 'A'");
		else
			System.out.println("getNoteRacine KO !!!  la m�thode renvoie " + maCorde.getNoteRacine().getNom());
			
		Note maNote = new Note("C",4);
		// Test de la m�thode setNoteRacine
		System.out.println("=====On r�initialise la note de base de la corde � Do 4");
		maCorde.setNoteRacine(maNote);	
		if (maCorde.getNoteRacine().getNom().equals("C"))
			System.out.println("test ok : la m�thode renvoie 'C'");
		else
			System.out.println("test KO !!!  la m�thode renvoie " + maCorde.getNoteRacine().getNom());
					
		// test de la m�thode NoteDeLaFrette
		System.out.println("=====Note de la frette 4 en do (mi attendu) : ");
		if (maCorde.noteDeLaFrette(4).getNom().equals("E"))
			System.out.println("test ok : la m�thode renvoie 'E'");
		else
			System.out.println("test KO !!!  la m�thode renvoie " + maCorde.noteDeLaFrette(4).getNom());
	}
}