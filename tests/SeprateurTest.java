package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import musique.Separateur;

public class SeprateurTest {
	@Test
	public void testeSetter()
	{
		Separateur monSeparateur = new Separateur();
		
		monSeparateur.setLibelle("\n");
		assertEquals("\n", monSeparateur.getLibelle());
		

		monSeparateur.setLibelle("\t");
		assertEquals(" ", monSeparateur.getLibelle());
		

		monSeparateur.setLibelle("");
		assertEquals("", monSeparateur.getLibelle());
		

		monSeparateur.setLibelle("autre");
		assertEquals("", monSeparateur.getLibelle());

		 ArrayList<Separateur> listeSeparateurs = new ArrayList<Separateur>();
		 String grilleCorrigee = "";
		 grilleCorrigee = Separateur.getListeSeparateurs("C/Am7 F\tG\n",listeSeparateurs);
		
		 assertEquals("C/Am7 F G\n", grilleCorrigee);
		 assertEquals(4, listeSeparateurs.size());
	}
	
}