package vueTextuelle;

import control.ControlChangerMdpAdmin;

public class BoundaryChangerMdpAdmin {
	
	private ControlChangerMdpAdmin controlChangerMdpAdmin;
	
	public BoundaryChangerMdpAdmin() {
		controlChangerMdpAdmin = new ControlChangerMdpAdmin();
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
			mdpOK = controlChangerMdpAdmin.verifierMdp(mdp);
			if(mdpOK) {
				System.out.println("Saissisez le nouveau mot de passe :");
				mdp = clavier.entrerClavierString();
				controlChangerMdpAdmin.changerMdp(mdp);
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
