package musique;

public class Position {
	// Corde non jouée = -1 sinon, numéro de frette
	private int[] valCorde;

	// On demande la construction d'une position à partir de la
	// manière la plus simple de jouer un accord
	public Position(Accord monAccord){
		valCorde = new int[4];
		chercheAccord(monAccord);
	}

	// Constructeur par les 4 valeurs des cordes 1 à 4
	public Position(int a, int b, int c, int d){
		valCorde = new int[4];
		setPosition(a,b,c,d);
	}

	// initialise la valeur pour une corde
	public void setCorde (int maCorde,int valeur){
		if ((maCorde > 0 && maCorde < 5) && (valeur>=-1 && valeur<16))
			valCorde[maCorde-1] = valeur;
	}

	// renvoie la valeur pour une corde
	public int getCorde (int maCorde){
		if ((maCorde > 0 && maCorde < 5) )
			return(valCorde[maCorde-1]);
		else
			return -2;
	}

	// affecte la position à partir des 4 valeurs des 4 frettes
	public void setPosition(int a, int b, int c, int d){
		setCorde (1,a);
		setCorde (2,b);
		setCorde (3,c);
		setCorde (4,d);
	}

	// évalue la difficulté d'une position
	public int difficulte()
	{
		int ecartement;
		int difficulte;
		int minVal = Integer.MAX_VALUE;
		int maxVal = Integer.MIN_VALUE;

		for(int i = 0; i < valCorde.length; i++){
			if(valCorde[i]>0 && valCorde[i] < minVal)
				minVal = valCorde[i];
			if(valCorde[i] > maxVal)
				maxVal = valCorde[i];
		}
		ecartement = maxVal - minVal +1;
		// Moins il y a de notes de jouées, plus c'est facile !
		int nbreCordesJouees = 0;
		for(int i = 0; i < valCorde.length; i++){
			if(valCorde[i] > 0)
				nbreCordesJouees++;
		}
		difficulte = nbreCordesJouees;
		// Un écartement de + de 3 , c'est dur !
		if (ecartement>3)
			difficulte += ecartement -3;
		// Plus on est loin du début du manche, + ça fait peur !
		if (minVal > 3 && minVal != Integer.MAX_VALUE)
			difficulte += minVal -3;
		return difficulte;
	}

	// cherche la position la plus facile pour un accord
	public void chercheAccord(Accord monAccord){
		int bestA = 999; 
		int bestB = 999;
		int bestC = 999;
		int bestD = 999;
		int bestDifficulte = 999;
		Accord chAccord = new Accord("C");
		Ukulele monUke = new Ukulele();

		// On essayera en position frette 0 à 11
		for (int i = 0; i<12;i++){
			// Espacement de 4 frettes pour toutes les cordes
			for (int a = 0; a<5; a++){
				for (int b = 0; b<5; b++){
					for (int c = 0; c<5; c++){
						for (int d = 0; d<5; d++){
							// On cherche les accords avec ces notes
							chAccord = monUke.trouveAccordPosition(i+a,i+b, i+c, i+d);
							// On teste les 4 renversements
							for (int j = 1; j<5; j++){
								chAccord.renverseAccord();
								// Si l'accord est l'accord cherché
								if (chAccord.compare(monAccord)){
									// On évalue la difficulté
									setPosition(i+a,i+b,i+c,i+d);
									if (difficulte()<bestDifficulte){
										bestA = i+a; 
										bestB = i+b;
										bestC = i+c;
										bestD = i+d;
										bestDifficulte = difficulte();
									}
								}

							}
						}
					}
				}
			}

		}
		setPosition(bestA,bestB,bestC,bestD);
	}

	// Procédure de test
	public static void main(String[] args) {
		Ukulele monuke;
		Position maPosition;
		monuke = new Ukulele();
		Accord accordTrouve;
		String NomAccord;

		for (int a = 0; a<5 ; a++)
			for (int b = 0; b<5 ; b++)
				for (int c = 0; c<5 ; c++)
					for (int d = 0; d<5 ; d++)
					{
						accordTrouve = monuke.trouveAccordPosition(a, b, c, d);
						NomAccord = accordTrouve.chercheTypeAccord(true);
						if (!NomAccord.equals(""))
						{
							// On récupère tous les accords séparés par des ;
							String[] split = NomAccord.split(";");
							for (int i = 0; i < split.length; i++) {
								maPosition = new Position(a,b,c,d);
								System.out.print(split[i] + " " + a+b+c+d+ " diff: " + maPosition.difficulte());
								System.out.println(maPosition.getTexte());
							}
							//						else {
							//							System.out.println(" xx" +a+b+c+d);
							//							accordTrouve.affiche();
							//						}
						}
					}
	}
	public String getTexte(){
		return ("position : "+valCorde[0]+valCorde[1]+valCorde[2]+valCorde[3]);
	}
}