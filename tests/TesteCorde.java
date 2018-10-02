package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import musique.Corde;
import musique.Note;

public class TesteCorde {

	@Test
	public void jouerSecondeFretteSurCordeG4donneA4() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when play 2nd fret
		Note noteJouee = corde1.noteDeLaFrette(2);
		// then note = A4
		
		if ((noteJouee.getNom() != "A")
			
			|| (noteJouee.getOctave() != 4))
		fail("Probleme jeu frette 2");
	}

	@Test
	public void jouerDouziemeFretteSurCordeG4donneG5() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when play 2nd fret
		Note noteJouee = corde1.noteDeLaFrette(12);
		// then note = A4
		
		if ((noteJouee.getNom() != "G")
			
			|| (noteJouee.getOctave() != 5))
		fail("Probleme jeu frette 12");
	}


	@Test
	public void jouerCordeG4aVidedonneG4() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when play 2nd fret
		Note noteJouee = corde1.noteDeLaFrette(0);
		// then note = A4
		
		if ((noteJouee.getNom() != "G")
			
			|| (noteJouee.getOctave() != 4))
		fail("Probleme corde a vide");
	}

	@Test
	public void jouerCordeFretteNegativeDonneNull() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when play -1 fret
		Note noteJouee = corde1.noteDeLaFrette(-1);
		// then note = null
		
		if (null != noteJouee)
		fail("Probleme frette 0 ");
	}

	@Test
	public void jouerCordeFrette25DonneNull() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when play -1 fret
		Note noteJouee = corde1.noteDeLaFrette(25);
		// then note = null
		
		if (null != noteJouee)
		fail("Probleme frette 25");
	}
	
	@Test
	public void testeEquals() {
		//given  corde G4
		
		Corde corde1 = new Corde("G",4);
		
		// when autre corde identique
		Corde corde2 = new Corde("G",4);
		// then corde1 = corde2
		
		if (corde1.equals(corde2) == false)
			fail("Probleme equals");
	}
}
