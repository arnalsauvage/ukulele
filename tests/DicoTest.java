package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import musique.AccordNomFamille;
import musique.Corde;
import musique.Dico;
import musique.Position;

public class DicoTest {
	public static Dico dicoTest;

	public DicoTest() {
		AccordNomFamille.creeCatalogueAccords();
		dicoTest = new Dico();
	}

	@Test
	public void testeGetEtRemplace() {
		Position laPosition = new Position(0, 0, 0, 0);
		dicoTest.ajouteAccord("Am7", laPosition);
		laPosition = new Position(0, 0, 0, 3);
		dicoTest.ajouteAccord("C", laPosition);
		laPosition = new Position(2, 0, 1, 0);
		dicoTest.ajouteAccord("F", laPosition);

		/////////////////////////// Test get Am7 /////////////////////
		laPosition = dicoTest.get("Am7");
		assertEquals("position : 0000",laPosition.toString());

		/////////////////////////// Test get C /////////////////////
		laPosition = dicoTest.get("C");
		assertEquals("position : 0003",laPosition.toString());

		/////////////////////////// Test Remplace /////////////////////
		laPosition = new Position(5, 4, 3, 3);
		dicoTest.ajouteAccord("C", laPosition);
		Position laPosition2 = new Position(0, 0, 0, 0);
		dicoTest.remplace("C", laPosition);
		laPosition2 = dicoTest.get("C");
		assertEquals(laPosition, laPosition2);
	}

	@Test
	public void testeAjouteAccord() {
		Position laPosition = new Position(0, 0, 0, 0);
		dicoTest.ajouteAccord("Am7", laPosition);
		laPosition = new Position(0, 0, 0, 0);
		dicoTest.ajouteAccord("C6", laPosition);
		laPosition = new Position(2, 0, 1, 3);
		dicoTest.ajouteAccord("F", laPosition);
		laPosition = new Position(2, 0, 1, 0);
		dicoTest.ajouteAccord("F", laPosition);
		laPosition = new Position(3, 0, 0, 5);
		dicoTest.ajouteAccord("C9", laPosition);

		laPosition = dicoTest.get("Am7");
		assertEquals("position : 0000",laPosition.toString());

		laPosition = dicoTest.get("C6");
		assertEquals("position : 0000",laPosition.toString());

		laPosition = dicoTest.get("F");
		assertEquals("position : 2010",laPosition.toString());

		laPosition = dicoTest.get("C9");
		assertEquals("position : 3005",laPosition.toString());
	}
}
