package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import musique.AccordNomFamille;

public class AccordNomDeFamilleTest {

	public AccordNomDeFamilleTest(){
		AccordNomFamille.creeCatalogueAccords();
	}
	
	@Test
	public void testeConstructeurParDefaut(){
		AccordNomFamille anf = new AccordNomFamille();
		assertEquals("Accord X[1]", anf.toString());
	}

	@Test
	public void testeConstructeurParCopie(){
		Integer[] accordMajeur = { 1, 5, 8 };
		AccordNomFamille sibemol = new AccordNomFamille("Bb", accordMajeur);

		AccordNomFamille anf = new AccordNomFamille(sibemol);
		assertEquals(sibemol.toString(), anf.toString());
	}
	
	@Test
	// Teste la fonction GetNomFamilleAccord
	public  void testeGetNomFamilleAccord() {
		
		assertEquals ("m7", AccordNomFamille.getNomDeFamilleAccord("Cm7"));
		assertEquals ("dim", AccordNomFamille.getNomDeFamilleAccord("D#dim"));
		assertEquals ("7M", AccordNomFamille.getNomDeFamilleAccord("Gb7M"));
		assertEquals ("dim7", AccordNomFamille.getNomDeFamilleAccord("Adim7"));
		assertEquals ("6", AccordNomFamille.getNomDeFamilleAccord("G6"));
	}

	
	@Test
	// Teste la fonction getNom
	public void testeGetNomFamille() {
		Integer[] powerChord = { 1, 8 };
		Integer[] accordMajeur = { 1, 5, 8 };
		Integer[] accordMajeurAugmente = { 1, 5, 9 };
		Integer[] accordMajeur6 = { 1, 5, 8, 10 };

//		AccordNomFamille.creeCatalogueAccords();
		AccordNomFamille monANF = new AccordNomFamille();

		monANF.setDegres(powerChord);
		assertEquals("5",AccordNomFamille.getNomFamille(monANF.getDegres()));

		monANF.setDegres(accordMajeur);
		assertEquals("",AccordNomFamille.getNomFamille(monANF.getDegres()));

		monANF.setDegres(accordMajeurAugmente);
		assertEquals("aug",AccordNomFamille.getNomFamille(monANF.getDegres()));

		monANF.setDegres(accordMajeur6);
		assertEquals("6",AccordNomFamille.getNomFamille(monANF.getDegres()));

	}

	@Test
	// Teste la fonction ajouteDegre
	public  void testeAjouteDegre() {
		Integer[] accordMajeur = { 1, 5, 8 };
		AccordNomFamille sibemol = new AccordNomFamille("Bb", accordMajeur);
		sibemol.ajouteDegre(11);
		sibemol.setNomAccord(AccordNomFamille.getNomFamille(sibemol.getDegres()));
		assertEquals ("7",sibemol.getNomAccord());
	}
}
