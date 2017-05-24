package musique;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Dico {
	Hashtable<String, Position> leDico;

	// Constructeur
	public Dico() {
		leDico = new Hashtable<String, Position>();
	}

	// On ajoute l'accord au dico s'il est plus simple que l'existant
	public void ajouteAccord(String nom, Position laPosition) {
		Position autrePosition;

		if (nom.length() == 0 || laPosition == null)
			return;

		autrePosition = leDico.get(nom);
		if (autrePosition == null)
			leDico.put(nom, laPosition);
		else {
			if (autrePosition.difficulte() > laPosition.difficulte()) {
				leDico.put(nom, laPosition);
			}
		}
	}

	// Remplit le dico avec l'accord le plus simple à jouer pour tous les types
	// d'accords et toutes les notes
	public void remplitDico() {
		Position maPosition = new Position(0, 0, 0, 0);
		Ukulele monUku = new Ukulele();
		;
		Accord monAccord = new Accord("Am7");
		String machaine = new String();

		for (int i = 0; i < 11; i++) {
			for (int a = 0; a < 6; a++) {
				for (int b = 0; b < 6; b++) {
					for (int c = 0; c < 6; c++) {
						for (int d = 0; d < 6; d++) {
							maPosition = new Position(i + a, i + b, i + c, i + d);
							monAccord = monUku.trouveAccordPosition(i + a, i + b, i + c, i + d);
							machaine = new String(monAccord.chercheTypeAccord(true));

							if (!machaine.equals("")) {
								// On récupère tous les accords séparés par des
								// ;
								String[] split = machaine.split(";");
								for (int index = 0; index < split.length; index++) {
									ajouteAccord(split[index], maPosition);
									// System.out.println("Dico : ajoute
									// "+split[index]+"
									// :"+(i+a)+(i+b)+(i+c)+(i+d)+"-");
								}
							}
						}
					}
				}
			}
		}
	}

	// Renvoie la position pour un acord décrit par une chaîne
	public Position get(String cle) {
		if (leDico.containsKey(cle))
			return leDico.get(cle);
		else
			return null;
	}

	// Supprime une entrée du dico
	public void supprime(String cle) {
		if (leDico.containsKey(cle))
			leDico.remove(cle);
	}

	// Ecrit / remplace une entrée dans le dico
	public void remplace(String cle, Position maPosition) {
		leDico.put(cle, maPosition);
	}

	// Procédure de test
	public static void main(String[] args) {
		Dico dicoTest = testeGetEtRemplace();

		dicoTest.remplitDico();

		dicoTest.afficheConsole();

		if (testeAjouteAccord())
			System.out.println("testeAjouteAccord : ok ");
		else
			System.out.println("!!! testeAjouteAccord : KO !!! ");
	}

	private static Dico testeGetEtRemplace() {
		Dico dicoTest = new Dico();
		Position laPosition = new Position(0, 0, 0, 0);
		dicoTest.ajouteAccord("Am7", laPosition);
		laPosition = new Position(0, 0, 0, 3);
		dicoTest.ajouteAccord("C", laPosition);
		laPosition = new Position(2, 0, 1, 0);
		dicoTest.ajouteAccord("F", laPosition);

		System.out.println("----- teste Get et Remplace -----");

		/////////////////////////// Test get Am7 /////////////////////
		laPosition = dicoTest.get("Am7");
		System.out.println("Position de Am7 :" + laPosition.getTexte());

		if (!laPosition.getTexte().equals("position : 0000"))
			System.out.println("!!! Test get Am7 ko !!!");

		/////////////////////////// Test get C /////////////////////
		laPosition = dicoTest.get("C");
		System.out.println("Position de C :" + laPosition.getTexte());

		if (!laPosition.getTexte().equals("position : 0003"))
			System.out.println("!!! Test get C ko !!!");

		/////////////////////////// Test Remplace /////////////////////

		laPosition = new Position(5, 4, 3, 3);
		dicoTest.ajouteAccord("C", laPosition);
		dicoTest.remplace("C", laPosition);
		Position laPosition2 = new Position(0, 0, 0, 0);

		laPosition2 = dicoTest.get("C");
		System.out.println("Position de C :" + laPosition.getTexte());

		if (!laPosition.equals(laPosition2))
			System.out.println("!!! Test Remplace ko !!!");

		return dicoTest;
	}

	// Affiche le contenu du dico en mode console
	private void afficheConsole() {
		Position laPosition;
		String snom;
		Set<String> set = leDico.keySet();

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			snom = itr.next();
			laPosition = get(snom);
			System.out.println(snom + laPosition.getTexte());
		}
	}

	public static boolean testeAjouteAccord() {
		System.out.println("///// Test de ajouteAccord + Constructeur /////");
		Dico dicoTest = new Dico();
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

		dicoTest.afficheConsole();
		System.out.println("On a ajouté un F 2013 puis 2010, il doit rester le F 2010");
		System.out.println("On a ajouté un Am7 0000 et un C6 0000, il doit y avoir deux entrées dans le dico");
		if (dicoTest.leDico.size() == 4)
			return true;
		else
			return false;
	}

}
