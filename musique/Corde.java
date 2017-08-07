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
		if ((numeroFrette<0)||(numeroFrette>24))
			return null;
		laNote.monter (numeroFrette);
		return laNote;
	}
	
	@Override
	public String toString() {
		return "Corde [noteRacine=" + noteRacine + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noteRacine == null) ? 0 : noteRacine.hashCode());
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
		Corde other = (Corde) obj;
		if (noteRacine == null) {
			if (other.noteRacine != null)
				return false;
		} else if (!noteRacine.equals(other.noteRacine))
			return false;
		return true;
	}
}