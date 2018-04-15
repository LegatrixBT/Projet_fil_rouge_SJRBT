package vuegraphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.ControlGestionMoteurs;
import model.Moteur;
import javax.swing.border.EtchedBorder;

public class PanelOngletRechercheAvancee extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5719846563498324287L;
	private ControlGestionMoteurs controlGestionMoteurs;
	private DefaultListModel<Moteur> modeleMoteursActifs;
	private JList<Moteur> listActifs;
	private DefaultListModel<Moteur> modeleMoteursInactifs;
	private JList<Moteur> listInactifs;
	private JComboBox<Moteur> comboBoxMoteurPrincipal;
	private DefaultComboBoxModel<Moteur> modelComboBoxActifs;
	
	/**
	 * Create the panel.
	 */
	public PanelOngletRechercheAvancee() {
		
		controlGestionMoteurs = new ControlGestionMoteurs();
		controlGestionMoteurs.setObserver(this);
		/*-------------------------------------------------------------*/
		
		/*-------------------------------------------------------------*/
		/*-------------------------------------------------------------*/
		/*-------------------------------------------------------------*/
		
		modeleMoteursInactifs = new DefaultListModel<>();
		for (Moteur moteur : controlGestionMoteurs.getListeMoteursInactifs()) {
			modeleMoteursInactifs.addElement(moteur);
		}
		
		modeleMoteursActifs = new DefaultListModel<>();
		modelComboBoxActifs = new DefaultComboBoxModel<>();
		for (Moteur moteur : controlGestionMoteurs.getListeMoteursActifs()) {
			modeleMoteursActifs.addElement(moteur);
			modelComboBoxActifs.addElement(moteur);
		}
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box boxOngletRechercheAvancee = Box.createVerticalBox();
		add(boxOngletRechercheAvancee);
		Box boxTitre = Box.createHorizontalBox();
		boxOngletRechercheAvancee.add(boxTitre);
		
		JLabel lblRechercheAvancee = new JLabel("Recherche Avancee");
		lblRechercheAvancee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxTitre.add(lblRechercheAvancee);
		

		
		Box boxPanneauListeControleMoteur= Box.createVerticalBox();
		boxPanneauListeControleMoteur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		boxOngletRechercheAvancee.add(boxPanneauListeControleMoteur);
		
		Box boxTitreActivDesactivMoteur = Box.createHorizontalBox();
		boxPanneauListeControleMoteur.add(boxTitreActivDesactivMoteur);
		
		JLabel lblActiverdsactiverLesMoteurs = new JLabel("Activer/D\u00E9sactiver les moteurs");
		lblActiverdsactiverLesMoteurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxTitreActivDesactivMoteur.add(lblActiverdsactiverLesMoteurs);
		lblActiverdsactiverLesMoteurs.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component verticalStrut_1 = Box.createVerticalStrut(15);
		boxPanneauListeControleMoteur.add(verticalStrut_1);
		
		Box boxListes = Box.createHorizontalBox();
		boxPanneauListeControleMoteur.add(boxListes);
		
		Box boxListeInactif = Box.createVerticalBox();
		boxListes.add(boxListeInactif);
		
		JLabel lblTitreInactif = new JLabel("Inactif");
		lblTitreInactif.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxListeInactif.add(lblTitreInactif);
		listInactifs = new JList<>(modeleMoteursInactifs);
		JScrollPane scrollPaneMoteursInactifs = new JScrollPane(listInactifs);
		boxListeInactif.add(scrollPaneMoteursInactifs);
		scrollPaneMoteursInactifs.setPreferredSize(new Dimension(70,150));
		
		Box boxListeActionCentrale = Box.createVerticalBox();
		boxListes.add(boxListeActionCentrale);
		
		JButton btnBasculerActif = new JButton(" > ");
		boxListeActionCentrale.add(btnBasculerActif);
		
		JButton btnBasculerInactif = new JButton(" < ");
		boxListeActionCentrale.add(btnBasculerInactif);
		
		Box boxListeActif = Box.createVerticalBox();
		boxListes.add(boxListeActif);
		
		JLabel lblNewLabel = new JLabel("Actif");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxListeActif.add(lblNewLabel);
		listActifs = new JList<>(modeleMoteursActifs);
		listActifs.setVisibleRowCount(2);
		JScrollPane scrollPaneMoteursActifs = new JScrollPane(listActifs);
		boxListeActif.add(scrollPaneMoteursActifs);
		scrollPaneMoteursActifs.setPreferredSize(new Dimension(70,150));
		
		Box boxSetMoteurPrincipal = Box.createVerticalBox();
		boxSetMoteurPrincipal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		boxOngletRechercheAvancee.add(boxSetMoteurPrincipal);
		
		JLabel lblChangerLeMoteur = new JLabel("Changer le moteur principal");
		lblChangerLeMoteur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChangerLeMoteur.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxSetMoteurPrincipal.add(lblChangerLeMoteur);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		boxSetMoteurPrincipal.add(verticalStrut);
		
		comboBoxMoteurPrincipal = new JComboBox<Moteur>();
		comboBoxMoteurPrincipal.setMaximumSize(new Dimension(90, 30));
		boxSetMoteurPrincipal.add(comboBoxMoteurPrincipal);
		comboBoxMoteurPrincipal.setModel(modelComboBoxActifs);
		
		Component verticalStrut_2 = Box.createVerticalStrut(15);
		boxSetMoteurPrincipal.add(verticalStrut_2);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBoxMoteurPrincipal.getSelectedIndex();
				modelComboBoxActifs.getElementAt(i).setMoteurPrincipal();
			}
		});
		btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxSetMoteurPrincipal.add(btnValider);
		btnBasculerInactif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indicesMoteursSelection = listActifs.getSelectedIndices();
				for (int i : indicesMoteursSelection) {
					modeleMoteursActifs.get(i).unsetMoteurActif();
				}
			}
		});
		btnBasculerActif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indicesMoteursSelection = listInactifs.getSelectedIndices();
				for (int i : indicesMoteursSelection) {
					modeleMoteursInactifs.get(i).setMoteurActif();
				}
			}
		});
		
		/*------------------------------------------------------------*/
		this.repaint();
	}


	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Moteur) {
			if(arg instanceof Integer[]) {
				Integer[] parametres = (Integer[]) arg;
				if(parametres[0] == 2) {
					modeleMoteursActifs.clear();
					modeleMoteursInactifs.clear();
					modelComboBoxActifs.removeAllElements();
					for (Moteur	moteur : controlGestionMoteurs.getListeMoteursActifs()) {
						modeleMoteursActifs.addElement(moteur);
						modelComboBoxActifs.addElement(moteur);
					}
					for (Moteur	moteur : controlGestionMoteurs.getListeMoteursInactifs()) {
						modeleMoteursInactifs.addElement(moteur);
					}
				}
			}
		}
		this.repaint();
	}

}
