package musique;

public class NoteNom{

	private String nomDeLaNote; // ex : A, B, C#, DB 
	private int valeurDeLaNote; // 1 pour C , 2 pour C#, 3 pour D etc

	public NoteNom ( String leNom ){
		nomDeLaNote="C";
		valeurDeLaNote = 1;
		setNom(leNom);
	}

	public NoteNom ( int laValeur){
		setValeur(laValeur);
	}

	public int getValeur() {
		return valeurDeLaNote;
	}

	public void setValeur(int valeurDeLaNote) {

		this.valeurDeLaNote = valeurDeLaNote %12;
		if (this.valeurDeLaNote == 0)
			this.valeurDeLaNote = 12;
		calculeNom();
	}

	public String getNom() {
		return nomDeLaNote;
	}

	public boolean setNom(String nomDeLaNote) {
		if ((nomDeLaNote.length()>0)&&(nomDeLaNote.length()<3)){
			nomDeLaNote = nomDeLaNote.toUpperCase();
			if (nomDeLaNote.matches("[A-G]#?B?")){
				this.nomDeLaNote = nomDeLaNote;
				this.simplifie();
				this.calculeNumero();
				return true;
			}
		}
		return false;
	}

	private void simplifie()
	{
		if (nomDeLaNote.equals("CB"))
			nomDeLaNote = "B";
		if (nomDeLaNote.equals("B#"))
			nomDeLaNote = "C";
		if (nomDeLaNote.equals("FB"))
			nomDeLaNote = "E";
		if (nomDeLaNote.equals("E#"))
			nomDeLaNote = "F";		
	}

	public void ecritEnDiese()
	{
		if (nomDeLaNote.equals("DB"))
			nomDeLaNote = "C#";
		if (nomDeLaNote.equals("EB"))
			nomDeLaNote = "D#";
		if (nomDeLaNote.equals("GB"))
			nomDeLaNote = "F#";
		if (nomDeLaNote.equals("AB"))
			nomDeLaNote = "G#";	
		if (nomDeLaNote.equals("BB"))
			nomDeLaNote = "A#";	
	}

	public void ecritEnBemol()
	{
		if (nomDeLaNote.equals("C#"))
			nomDeLaNote = "DB";
		if (nomDeLaNote.equals("D#"))
			nomDeLaNote = "EB";
		if (nomDeLaNote.equals("F#"))
			nomDeLaNote = "GB";
		if (nomDeLaNote.equals("G#"))
			nomDeLaNote = "AB";	
		if (nomDeLaNote.equals("A#"))
			nomDeLaNote = "BB";	
	}

	public void monter(int nbreDemisTons)
	{
		setValeur(valeurDeLaNote + nbreDemisTons);
	}

	private void calculeNumero()
	{
		ecritEnDiese();
		switch (nomDeLaNote)
		{
		case "C" : valeurDeLaNote = 1;break; 
		case "C#" : valeurDeLaNote = 2;break; 
		case "D" : valeurDeLaNote = 3; break;
		case "D#" : valeurDeLaNote = 4; break;
		case "E" : valeurDeLaNote = 5; break;
		case "F" : valeurDeLaNote = 6; break;
		case "F#" : valeurDeLaNote = 7; break;
		case "G" : valeurDeLaNote = 8; break;
		case "G#" : valeurDeLaNote = 9; break;
		case "A" : valeurDeLaNote = 10; break;
		case "A#" : valeurDeLaNote = 11; break;
		case "B" : valeurDeLaNote = 12; break;
		}
	}

	void calculeNom()
	{
		switch (valeurDeLaNote)
		{
		case 1 : nomDeLaNote = "C";break; 
		case 2 : nomDeLaNote = "C#";break; 
		case 3 : nomDeLaNote = "D"; break;
		case 4 : nomDeLaNote = "D#"; break;
		case 5 : nomDeLaNote = "E" ; break;
		case 6 : nomDeLaNote = "F"; break;
		case 7 : nomDeLaNote = "F#"; break;
		case 8 : nomDeLaNote = "G"; break;
		case 9 : nomDeLaNote = "G#"; break;
		case 10 : nomDeLaNote = "A"; break;
		case 11 : nomDeLaNote = "A#"; break;
		case 12 : nomDeLaNote = "B"; break;		
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomDeLaNote == null) ? 0 : nomDeLaNote.hashCode());
		result = prime * result + valeurDeLaNote;
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
		NoteNom other = (NoteNom) obj;
		if (nomDeLaNote == null) {
			if (other.nomDeLaNote != null)
				return false;
		} else if (!nomDeLaNote.equals(other.nomDeLaNote))
			return false;
		if (valeurDeLaNote != other.valeurDeLaNote)
			return false;
		return true;
	}
	
	
}