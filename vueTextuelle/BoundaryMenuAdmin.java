package vueTextuelle;

import control.ControlVerifierAdmin;
import control.ControlVisualiserParamIndex;

public class BoundaryMenuAdmin {
	
	private ControlVerifierAdmin controlVerifierAdmin;
	private BoundarySeConnecterAdmin boundarySeConnecterAdmin;
	private BoundaryVisualiserParamIndex boundaryVisualiserParamIndex;
	private BoundaryModifierParamIndex boundaryModifierParamIndex;
	private BoundaryLancerIndexation boundaryLancerIndexation;
	private BoundaryChangerMdpAdmin boundaryChangerMdpAdmin;
	
	public BoundaryMenuAdmin() {
		controlVerifierAdmin = new ControlVerifierAdmin();
		boundarySeConnecterAdmin = new BoundarySeConnecterAdmin();
		boundaryVisualiserParamIndex = new BoundaryVisualiserParamIndex(new ControlVisualiserParamIndex());
		boundaryModifierParamIndex = new BoundaryModifierParamIndex();
		boundaryLancerIndexation = new BoundaryLancerIndexation();
		boundaryChangerMdpAdmin = new BoundaryChangerMdpAdmin();
	}
	
	public void menuAdmin() {
		if(!controlVerifierAdmin.verifierConnexionAdmin()) {
			boolean connecte = boundarySeConnecterAdmin.seConnecter();
			if(connecte) {
				this.choixMenu();
			}
		}
		else {
			this.choixMenu();
		}
	}
	
	private void afficherMenuAdmin() {
		System.out.println("Menu d'aministration \n");
		System.out.println("Choissisez la fonction souhaitée: \n");
		System.out.println("	(1) Visualiser les paramètres d'indexation \n");
		System.out.println("	(2) Modifier les paramètres d'indexation \n");
		System.out.println("	(3) Lancer l'indexation des fichiers \n");
		System.out.println("	(4) Changer le mot de passe \n");
		System.out.println("	(5) Se deconnecter \n");
		System.out.println("	(6) Quitter le menu administrateur\n");
	}
	
	private void choixMenu() {
		String choix;
		Clavier clavier = new Clavier();
		do {
			do {
				this.afficherMenuAdmin();
				choix = clavier.entrerClavierString();
				if(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")) && !(choix.equals("5")) && !choix.equals("6"))
					System.out.println("Choix incorrect \n");
			}while(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")) && !(choix.equals("5")) && !choix.equals("6"));
				
			switch(choix)
			{
				case "1":
					boundaryVisualiserParamIndex.visualiserParamIndex();
					choix = "0";
					break;
				case "2":
					boundaryModifierParamIndex.modifierParamIndex();
					choix = "0";
					break;
				case "3":
					boundaryLancerIndexation.lancerIndexation();
					choix = "0";
					break;
				case "4":
					boundaryChangerMdpAdmin.changerMDP();
					choix = "0";
					break;
				case "5":
					boundarySeConnecterAdmin.seDeconnecter();
					System.out.println("Deconnexion et sortie du menu administrateur... \n");
					break;
				default :
					System.out.println("Sortie du menu administrateur... \n");
					break;
			}
		}while(!choix.equals("5") && !choix.equals("6"));
	}
	
}
