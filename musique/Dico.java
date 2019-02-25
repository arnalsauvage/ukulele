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

	// Renvoie la position pour un accord décrit par une chaîne
	public Position get(String cle) {
		// Si la clé est donnée avec un bémol, on la convertit en dièse
		if ((cle.length()>1)&&(cle.substring(1, 2).equals("b"))){
			NoteNom maNote = new NoteNom(cle);
			maNote.ecritEnDiese();
			cle = maNote.getNom();
		}
		if (leDico.containsKey(cle))
			return leDico.get(cle);
		else
			return null;
	}
	
	public int getSize()
	{
		return leDico.size();
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
								// On récupère tous les accords séparés par des ';'
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

	// Affiche le contenu du dico en mode console
	public void afficheConsole() {
		Position laPosition;
		String snom;
		Set<String> set = leDico.keySet();

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			snom = itr.next();
			laPosition = get(snom);
			System.out.println(snom + laPosition);
		}
	}
}
