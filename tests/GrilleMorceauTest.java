package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import musique.Accord;
import musique.AccordNomFamille;
import musique.GrilleMorceau;

public class GrilleMorceauTest {
	@Before
	public void initialiser() throws Exception {
		AccordNomFamille.creeCatalogueAccords();
		GrilleMorceau grilleMorceauTest = new GrilleMorceau();
		grilleMorceauTest.ajouteAccords("BbBb");
	}


	@Test
	public void testSetAccords() {
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.ajouteAccords("C Am");	
		maGrille.setAccords("C Am F G");
		assertEquals (4, maGrille.taillePaquet());
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
	public void testAjouteAccord() {
		Accord accord = new Accord ("Bb");
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.ajouteAccord(accord);
		Accord accordTire = maGrille.tireAccord();
		assertEquals(accordTire, accord);
	}

	@Test
	public void testTireAccord() {
		Accord accord = new Accord ("Bb");
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.ajouteAccord(accord);
		Accord accordTire = maGrille.tireAccord();
		assertEquals(accordTire, accord);
		
		accordTire = maGrille.tireAccord();
		assertEquals(accordTire, null);
	}

	@Test
	public void testCalculeLes12Complexites() {
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.setAccords("C Am F G");
		assertEquals ("[1]22-[2]13-[3]19-[4]17-[5]12-[6]21-[7]11-[8]20-[9]13-[10]15-[11]20-", maGrille.calculeLes12Complexites());
	}

	@Test
	public void testComplexiteGrille() {
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.setAccords("C Am F G");
		assertEquals(8, maGrille.complexiteGrille());
		maGrille.setAccords("C");
		assertEquals(2, maGrille.complexiteGrille());
		maGrille.setAccords("Am");
		assertEquals(1, maGrille.complexiteGrille());
		maGrille.setAccords("F");
		assertEquals(2, maGrille.complexiteGrille());
		maGrille.setAccords("G");
		assertEquals(3, maGrille.complexiteGrille());
	}

	@Test
	public void testTranspose() {
		GrilleMorceau maGrille = new GrilleMorceau();
		maGrille.setAccords("C Am F G");
		maGrille.transpose(2);
		assertEquals ("D Bm G A ", maGrille.toString());
	}

}
