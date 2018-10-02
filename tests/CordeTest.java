package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import musique.Corde;
import musique.Note;

public class CordeTest {

	@Test
	public void testeNoteDeLaFrette() {
		// Test du constructeur
				Corde maCorde = new Corde("A",4);
				// test de la méthode NoteDeLaFrette
				assertEquals("C#",maCorde.noteDeLaFrette(4).getNom());
	}
	
	@Test
	public void testeGetNoteRacine() {
		// Test du constructeur
				Corde maCorde = new Corde("A",4);
				// Test de getNoteRacine
				assertEquals("A",maCorde.getNoteRacine().getNom());
	}
	
	@Test
	public void testeSetNoteRacine() {
		Corde maCorde = new Corde("A",4);
		Note maNote = new Note("C",4);
				// Test de la méthode setNoteRacine
				maCorde.setNoteRacine(maNote);	
				assertEquals("C",maCorde.getNoteRacine().getNom());
	}
	
	@Test
	public void testNoteDeLaFrette() {
		// Test du constructeur
				Corde maCorde = new Corde("C",4);
							
				// test de la méthode NoteDeLaFrette
				assertEquals("E", maCorde.noteDeLaFrette(4).getNom());
				assertEquals("G", maCorde.noteDeLaFrette(7).getNom());
				assertEquals("A", maCorde.noteDeLaFrette(9).getNom());
	}
	
}
