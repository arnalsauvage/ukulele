package musique;

public class Note{

	private NoteNom nom; // ex : A, B, C#, DB 
	private int octave; // de zéro à 7
	
	public Note ( String leNom, int lOctave ){
		octave = 4;
		nom = new NoteNom("C");
		setOctave(lOctave);
		setNom(leNom);
	}

	public Note (  int valeurNote ){
		nom = new NoteNom("C");
		setValeur(valeurNote);
	}

	
	public int getValeur() {
		return (nom.getValeur()+12*octave);
	}

	public void setValeur(int valeurDeLaNote) {
		octave = valeurDeLaNote/12;
		if (valeurDeLaNote%12==0)
			octave--;
		nom.setValeur (valeurDeLaNote);
	}

	public void setNomNote(NoteNom nom) {
		this.nom = nom;
	}

	public NoteNom getNomNote() {
		return (nom);
	}

	public String getNom() {
		return nom.getNom();
	}

	public boolean setNom(String nomDeLaNote)
	{	// la longueur de la chaîne doit être de 1 ou 2 (lettre + bémol ou dièse)
		if ((nomDeLaNote.length()>0)&&(nomDeLaNote.length()<3)){
			nomDeLaNote = nomDeLaNote.toUpperCase();
			// On passe en majuscules, et on doit avoir une lettre de AàG et # ou B
			if (nomDeLaNote.matches("[A-G]#?B?")){
				this.nom.setNom(nomDeLaNote);
				this.calculeNumero();
				return true;
			}
		}
		return false;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		// l'octave est entre 0 et 8 inclus
		if ((octave >= 0)&&(octave<8)){
			this.octave = octave;
			calculeNumero();
		}
	}

	// Cette méthode permet de monter ou de baisser la note de n demi-tons
	public boolean monter(int nbreDemisTons)
	{
		int maNouvelleValeur;

		maNouvelleValeur = getValeur() + nbreDemisTons;
		if ((maNouvelleValeur>0)&&(maNouvelleValeur<97)){
			nom.setValeur ( maNouvelleValeur);
			octave = maNouvelleValeur /12;
			if (maNouvelleValeur%12==0)
				octave--;
			return true;
		}
		else
			return false;
	}

	private void calculeNumero()
	{
		int valeur = 1;
		nom.ecritEnDiese();
		switch (nom.getNom())
		{
		case "C" : valeur = 1;break; 
		case "C#" : valeur = 2;break; 
		case "D" : valeur = 3; break;
		case "D#" : valeur = 4; break;
		case "E" : valeur = 5; break;
		case "F" : valeur = 6; break;
		case "F#" : valeur = 7; break;
		case "G" : valeur = 8; break;
		case "G#" : valeur = 9; break;
		case "A" : valeur = 10; break;
		case "A#" : valeur = 11; break;
		case "B" : valeur = 12; break;
		}
		nom.setValeur(valeur);
	}

	public void ecritEnBemol() {
		nom.ecritEnBemol();
	}

	public void ecritEnDiese() {
		nom.ecritEnDiese();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + octave;
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
		Note other = (Note) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (octave != other.octave)
			return false;
		return true;
	}
	
	
	
}