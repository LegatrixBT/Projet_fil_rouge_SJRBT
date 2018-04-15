package vuegraphique;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.ListModel;

import control.ControlMoteurActifs;
import model.Moteur;
import vueTextuelle.BoundaryModifierMoteursActifs;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelOngletRechercheAvancee extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelOngletRechercheAvancee() {
		
		Box boxOngletRechercheAvancee = Box.createVerticalBox();
		add(boxOngletRechercheAvancee);
		/*-------------------------------------------------------------*/
		Box boxTitre = Box.createHorizontalBox();
		boxOngletRechercheAvancee.add(boxTitre);
		
		JLabel lblRechercheAvancee = new JLabel("Recherche Avancee");
		boxTitre.add(lblRechercheAvancee);
		
		/*-------------------------------------------------------------*/
		Box boxChoixMoteurActifs = Box.createVerticalBox();
		boxOngletRechercheAvancee.add(boxChoixMoteurActifs);
		/*-------------------------------------------------------------*/
		Box boxLabelChoixMoteurActifs = Box.createHorizontalBox();
		boxChoixMoteurActifs.add(boxLabelChoixMoteurActifs);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		boxChoixMoteurActifs.add(verticalStrut);
		
		Box boxLblListeMoteur = Box.createHorizontalBox();
		boxChoixMoteurActifs.add(boxLblListeMoteur);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		boxLblListeMoteur.add(horizontalStrut_1);
		
		Label lblListeMoteur = new Label("Liste des moteurs");
		boxLblListeMoteur.add(lblListeMoteur);
		lblListeMoteur.setAlignment(Label.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		boxLblListeMoteur.add(horizontalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		boxChoixMoteurActifs.add(verticalStrut_1);
		
		Box boxLblListeMoteurActifDesactif = Box.createHorizontalBox();
		boxChoixMoteurActifs.add(boxLblListeMoteurActifDesactif);
		
		Label lblMoteurInactifs = new Label("Inactifs");
		boxLblListeMoteurActifDesactif.add(lblMoteurInactifs);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		boxLblListeMoteurActifDesactif.add(horizontalGlue);
		
		Label lblMoteurActifs = new Label("Actifs :");
		lblMoteurActifs.setAlignment(Label.RIGHT);
		boxLblListeMoteurActifDesactif.add(lblMoteurActifs);
		/*-------------------------------------------------------------*/
		

		
		Box boxPanneauListeControleMoteur= Box.createVerticalBox();
		boxOngletRechercheAvancee.add(boxPanneauListeControleMoteur);
		
		Box boxListeControleMoteur = Box.createHorizontalBox();
		boxPanneauListeControleMoteur.add(boxListeControleMoteur);
		
		Box boxListeInactif = Box.createHorizontalBox();
		boxPanneauListeControleMoteur.add(boxListeInactif);
		/*------------------------------------------------------------*/ 
		// Contenu de la liste inactive // 
		
		JList list = new JList();
		boxListeInactif.add(list);
		
		JButton btnBasculerActif = new JButton(" > ");
		boxListeInactif.add(btnBasculerActif);
		
		JButton btnBasculerInactif = new JButton(" < ");
		boxListeInactif.add(btnBasculerInactif);
		
		JScrollBar scrollBar = new JScrollBar();
		boxListeInactif.add(scrollBar);
		
		Box boxListeActionCentrale = Box.createVerticalBox();
		boxPanneauListeControleMoteur.add(boxListeActionCentrale);
		
		Box boxListeActif = Box.createHorizontalBox();
		boxPanneauListeControleMoteur.add(boxListeActif);
		
		/*------------------------------------------------------------*/

		
		// test 
		
		
		

		

	}

}
