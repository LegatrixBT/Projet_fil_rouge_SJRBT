package vueTextuelle;

import control.ControlModifierParamIndex;

public class BoundaryModifierParamIndex {
	
	ControlModifierParamIndex controlModifierParamIndex;
	
	public BoundaryModifierParamIndex() {
		controlModifierParamIndex = new ControlModifierParamIndex();
	}
	
	public void modifierParamIndex() {
		Clavier clavier = new Clavier();
		String choix;
		int nbBits;
		
		do{
			do {
				System.out.println("Choissisez le paramètre à changer :\n");
				System.out.println(" Image :\n");
				System.out.println("    (1) Nombre de bits de quantification\n");
				System.out.println(" Autres :\n");
				System.out.println("    (2) Quitter menu\n");
				
				choix = clavier.entrerClavierString();
				if(!(choix.equals("1")) && !(choix.equals("2")))
					System.out.println("Choix incorrect!\n");
			}while(!(choix.equals("1")) && !(choix.equals("2")));
			
			if(choix.equals("1")) {
				do {
					System.out.println("Saisissez le nombre de bits de quantification voulu (entre 1 et 5):\n");
					nbBits = clavier.entrerClavierInt();
					if((nbBits < 1) || (nbBits > 5))
						System.out.println("Valeur saisie incorrecte! Veuillez écrire une valeur entre 1 et 5...\n");
				}while((nbBits < 1) || (nbBits > 5));
					controlModifierParamIndex.modifierNbBitsIndex(nbBits);
					break;
			}
		}while(!(choix.equals("2")));
	}
	
}
