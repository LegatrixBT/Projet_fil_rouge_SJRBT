package vueTextuelle;

import control.ControlAfficherMenuAdmin;
import control.ControlVisualiserParamIndex;

public class BoundaryAfficherMenuAdministrateur {
	
	private BoundaryVisualiserParamIndex boundaryVisualiserParamIndex;
	private BoundaryModifierParamIndex boundaryModifierParamIndex;
	private BoundaryLancerIndexation boundaryLancerIndexation;
	
	public BoundaryAfficherMenuAdministrateur() {
		controlAfficherMenuAdmin = new ControlAfficherMenuAdmin();
		boundaryVisualiserParamIndex = new BoundaryVisualiserParamIndex(new ControlVisualiserParamIndex());
		boundaryModifierParamIndex = new BoundaryModifierParamIndex();
		boundaryLancerIndexation = new BoundaryLancerIndexation();
	}
	
	public BoundaryAfficherMenuAdministrateur(ControlAfficherMenuAdmin controlAfficherMenuAdmin) {
		this.controlAfficherMenuAdmin = controlAfficherMenuAdmin;
		this.boundaryVisualiserParamIndex = new BoundaryVisualiserParamIndex(new ControlVisualiserParamIndex());
	}
	
	private ControlAfficherMenuAdmin controlAfficherMenuAdmin;
	
	public void afficherMenuAdministrateur() {
		String choix = "0";
		Clavier clavier = new Clavier();
		while(!choix.equals("5")) {
			while(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")) && !(choix.equals("5"))) {
				System.out.println("Menu d'aministration \n");
				System.out.println("Choissisez la fonction souhaitée: \n");
				System.out.println("	(1) Visualiser les paramètres d'indexation \n");
				System.out.println("	(2) Modifier les paramètres d'indexation \n");
				System.out.println("	(3) Lancer l'indexation des fichiers \n");
				System.out.println("	(4) Changer le mot de passe \n");
				System.out.println("	(5) Quitter le menu administrateur\n");
				choix = clavier.entrerClavierString();
				if(!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3")) && !(choix.equals("4")) && !(choix.equals("5"))){
					System.out.println("Choix incorrect \n");
				}
			}
			
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
				this.changerMDP();
				choix = "0";
				break;
			default :
				System.out.println("Sortie du menu administrateur... \n");
				break;
			}
		}
	}
	
	void changerMDP() {
		Clavier clavier = new Clavier();
		String mdp;
		String choix="0";
		boolean mdpOK = false;
		
		while(!mdpOK && !(choix.equals("2"))){
			choix = "0";
			System.out.println("Saissisez le mot de passe actuel: \n");
			mdp=clavier.entrerClavierString();
			mdpOK = controlAfficherMenuAdmin.verifierMdp(mdp);
			if(mdpOK) {
				System.out.println("Saissisez le nouveau mot de passe :");
				mdp = clavier.entrerClavierString();
				controlAfficherMenuAdmin.changerMdp(mdp);
			}
			else {
				System.out.println("Mot de passe incorrect! \n");
				while(!(choix.equals("1")) && !(choix.equals("2"))) {
					System.out.println("Voulez vous resaisir le mot de passe? (1) Oui (2) Non \n");
					choix = clavier.entrerClavierString();
					if(!(choix.equals("1")) && !(choix.equals("2")))
					{
						System.out.println("Choix saisi incorrect! \n");
					}
				}
			}
		}
	}
	
}
