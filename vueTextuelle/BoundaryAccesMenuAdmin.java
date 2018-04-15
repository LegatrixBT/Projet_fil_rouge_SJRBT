package vueTextuelle;

import control.ControlAccesMenuAdmin;

public class BoundaryAccesMenuAdmin {
	
	private ControlAccesMenuAdmin controlAccesMenuAdmin;
	private BoundaryAfficherMenuAdministrateur boundaryAfficherMenuAdministrateur;
	
	public BoundaryAccesMenuAdmin() {
		controlAccesMenuAdmin = new ControlAccesMenuAdmin();
		boundaryAfficherMenuAdministrateur = new BoundaryAfficherMenuAdministrateur();
	}
	
	public BoundaryAccesMenuAdmin(ControlAccesMenuAdmin controlAccesMenuAdmin, BoundaryAfficherMenuAdministrateur boundaryAfficherMenuAdministrateur) {
		this.controlAccesMenuAdmin = controlAccesMenuAdmin;
		this.boundaryAfficherMenuAdministrateur = boundaryAfficherMenuAdministrateur;
	}
	
	public void connexionAdministrateur() {
		String mdp;
		String choix = "0";
		boolean mdpOK = false;
		
		Clavier clavier = new Clavier();
		while((!mdpOK) && !(choix.equals("2"))) {
			choix = "0";
			System.out.println("Saissisez le mot de passe d'administrateur: \n");
			mdp = clavier.entrerClavierString();
			mdpOK = controlAccesMenuAdmin.verifierMdp(mdp);
			if(!mdpOK) {
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
			else {
				System.out.println("Mot de passe correct! \n");
				boundaryAfficherMenuAdministrateur.afficherMenuAdministrateur();
			}
		}
	}
	
}
