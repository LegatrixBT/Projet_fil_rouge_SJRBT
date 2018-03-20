package vueTextuelle;

import control.ControlLancerIndexation;

public class BoundaryLancerIndexation {
	
	ControlLancerIndexation controlLancerIndexation;
	
	public BoundaryLancerIndexation() {
		controlLancerIndexation = new ControlLancerIndexation();
	}
	
	public void lancerIndexation() {
		Clavier clavier = new Clavier();
		String choix;
		boolean indexImageOK;
		boolean indexTexteOK;
		
		do {
			do {
				System.out.println("Choissisez le type de fichier à indexer\n");
				System.out.println("  (1) Images\n");
				System.out.println("  (2) Texte \n");
				System.out.println("  (3) Tous \n");
				System.out.println("  (4) Revenir au menu administrateur \n");
				choix = clavier.entrerClavierString();
				if(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")))
					System.out.println("Choix incorrect!\n");
			}while(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")));
			
			switch(choix) {
			case "1":
				indexImageOK= controlLancerIndexation.lancerIndexationImage();
				if(indexImageOK)
					System.out.println("Indexation terminée avec succés!\n");
				else
					System.out.println("Erreur lors de l'indexation!\n");
				break;
				
			case "2":
				indexTexteOK = controlLancerIndexation.lancerIndexationTexte();
				if(indexTexteOK)
					System.out.println("Indexation terminée avec succés!\n");
				else
					System.out.println("Erreur lors de l'indexation!\n");
				break;
				
			case "3":
				indexImageOK = controlLancerIndexation.lancerIndexationImage();
				indexTexteOK = controlLancerIndexation.lancerIndexationTexte();
				if(indexImageOK && indexTexteOK)
					System.out.println("Indexation terminée avec succés!\n");
				else
					if(!indexImageOK)
						System.out.println("Erreur lors de l'indexation image!\n");
					else
						System.out.println("Erreur lors de l'indexation texte!\n");
				break;
			}
			
		}while(!(choix.equals("4")));
	}
	
	
}
