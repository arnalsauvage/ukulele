package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import musique.Accord;
import musique.GrilleMorceau;

public class GrilleMorceauTest {

	@Test
	public void testAjouteAccords() {
	}

	@Test
	public void testSetAccords() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouteAccordAccordString() {
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.ajouteAccords("C Am");
		Accord monAccord = maGrille.tireAccord();
		if (!monAccord.nomAbrege().contentEquals("Am"))
			fail("Am non trouvé");
		 monAccord = maGrille.tireAccord();
			if (!monAccord.nomAbrege().contentEquals("C"))
				fail("C non trouvé");

	}

	@Test
	public void testAjouteAccordAccord() {
		fail("Not yet implemented");
	}

	@Test
	public void testTireAccord() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculeLes12Complexites() {
		fail("Not yet implemented");
	}

	@Test
	public void testComplexiteGrille() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranspose() {
		fail("Not yet implemented");
	}

}
