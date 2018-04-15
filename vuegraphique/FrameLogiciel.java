package vuegraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import control.ControlSeConnecter;
import control.ControlVerifierAdmin;
import model.Profil;

public class FrameLogiciel extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2143710836285421637L;
	private JPanel contentPane;
	private PanelOngletAdministrateur panelOngletAdministrateur;
	private PanelOngletRechercheAvancee panelOngletRechercheAvancee;
	private FrameConnexionAdmin frameConnexionAdmin;
	private ControlVerifierAdmin controlVerifierAdmin;
	private ControlSeConnecter controlSeConnecter;
	private JMenuItem mntmRechercheAvancee;
	private JMenu mnRechercheAvancee;
	private JCheckBoxMenuItem chckbxmntmAfficherOnglet;
	private PanelRecherche panelRecherche;
	

	/**
	 * Create the frame.
	 */
	public FrameLogiciel() {
		

		controlSeConnecter = new ControlSeConnecter();
		controlSeConnecter.setObserver(this);
		
		frameConnexionAdmin = new FrameConnexionAdmin(controlSeConnecter);
		frameConnexionAdmin.setVisible(false);
		controlVerifierAdmin = new ControlVerifierAdmin();
		
		this.setSize(new Dimension(900, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministrateur = new JMenu("Administrateur");
		menuBar.add(mnAdministrateur);
		
		JMenuItem mntmSeConnecter = new JMenuItem("Se connecter / se Deconnecter");
		mntmSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controlVerifierAdmin.verifierConnexionAdmin())
					controlSeConnecter.seDeconnecter();
				else
					frameConnexionAdmin.ouvertureFenetre();
			}
		});
		
		mnAdministrateur.add(mntmSeConnecter);
		
		mnRechercheAvancee = new JMenu("Recherche avancee");
		menuBar.add(mnRechercheAvancee);
		
		chckbxmntmAfficherOnglet = new JCheckBoxMenuItem("Afficher onglet");
		mnRechercheAvancee.add(chckbxmntmAfficherOnglet);
		chckbxmntmAfficherOnglet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxmntmAfficherOnglet.isSelected())
					panelOngletRechercheAvancee.setVisible(true);
				else
					panelOngletRechercheAvancee.setVisible(false);
			}
		});

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(20, 10));
		
		panelOngletAdministrateur = new PanelOngletAdministrateur();
		panelOngletAdministrateur.setVisible(false);
		
		panelOngletRechercheAvancee = new PanelOngletRechercheAvancee();
		panelOngletRechercheAvancee.setVisible(false);
		
		panelRecherche = new PanelRecherche();
		panelRecherche.setVisible(true);
		
		contentPane.add(panelOngletRechercheAvancee, BorderLayout.EAST);
		contentPane.add(panelOngletAdministrateur, BorderLayout.WEST);
		contentPane.add(panelRecherche, BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	

	public void afficherOngletAdmin() {
		panelOngletAdministrateur.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Profil) {
			boolean mdpOK = (boolean) arg1;
			if(mdpOK)
				panelOngletAdministrateur.ouvertureOngletAdmin();
			else
				panelOngletAdministrateur.setVisible(false);
		}
	}

}
