package vuegraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlSeConnecter;
import control.ControlVerifierAdmin;
import model.Profil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class FrameLogiciel extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2143710836285421637L;
	private JPanel contentPane;
	private PanelOngletAdministrateur panelOngletAdministrateur;
	private FrameConnexionAdmin frameConnexionAdmin;
	private ControlVerifierAdmin controlVerifierAdmin;
	private ControlSeConnecter controlSeConnecter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogiciel frame = new FrameLogiciel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelOngletAdministrateur = new PanelOngletAdministrateur();
		panelOngletAdministrateur.setVisible(false);
		contentPane.add(panelOngletAdministrateur, BorderLayout.WEST);
		
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