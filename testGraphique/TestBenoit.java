package testGraphique;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.ControlMoteurActifs;
import model.Moteur;
import model.TypeMoteur;
import model.UsineMoteur;
import test.TestMoteur;
import vueTextuelle.BoundaryChoisirMoteur;
import vuegraphique.PanelOngletRechercheAvancee;

public class TestBenoit extends JFrame {

	ControlMoteurActifs controlMoteurActifs = new ControlMoteurActifs();
	PanelOngletRechercheAvancee panelOngletRechercheAvancee = new PanelOngletRechercheAvancee(); 

	
    public TestBenoit() {

        setSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        

        this.add(panelOngletRechercheAvancee);
        this.setVisible(true);
        
        JButton bouton = new JButton();
        
		ControlMoteurActifs controlMoteurActifs = new ControlMoteurActifs();
		
		List<Moteur> listeMoteurLueInactif = new ArrayList<Moteur>();
		listeMoteurLueInactif = controlMoteurActifs.getMoteurInactifs();
		System.out.println("Les moteurs inactifs sont : " + listeMoteurLueInactif.toString());
		JLabel labelInactif = new JLabel("Update");
		
		List<Moteur> listeMoteurLueActif = new ArrayList<Moteur>();
		listeMoteurLueActif = controlMoteurActifs.getMoteurInactifs();
		System.out.println("Les moteurs inactifs sont : " + listeMoteurLueActif.toString());
		JLabel labelActif = new JLabel("Update");
		
		/*-----------------------------------------------------------------------*/
		
		
		List<Moteur> listeMoteurSelectInactif = new ArrayList<Moteur>();
		
		List<Moteur> listeMoteurSelectActif = new ArrayList<Moteur>();

		
		
		JList dataListInactif = new JList();
		   dataListInactif.addListSelectionListener(new ListSelectionListener() {

	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	labelInactif.setText(dataListInactif.getSelectedValue().toString());
	                }
	            }
	        });
		  
		DefaultListModel modelInactif = new DefaultListModel<String>();
		modelInactif = listToModel(dataListInactif, listeMoteurLueInactif);
        updateJList(dataListInactif, modelInactif);
		this.add(dataListInactif);
		this.add(labelInactif);

		   
		JList dataListActif = new JList();
		   dataListActif.addListSelectionListener(new ListSelectionListener() {

	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	labelActif.setText(dataListActif.getSelectedValue().toString());
	                }
	            }
	        });
		DefaultListModel modelActif = new DefaultListModel<String>();
		this.add(dataListActif);
		modelActif = listToModel(dataListActif, listeMoteurLueActif);
        updateJList(dataListActif, modelActif);
		this.add(labelActif);     

        setVisible(true);
        

    }

    private DefaultListModel listToModel(JList dataList, List<Moteur> listeMoteurLue ) {
        
    	DefaultListModel model = new DefaultListModel<String>();
    	int indexliste = 0 ; 
		
 		for (Iterator i = listeMoteurLue.iterator(); i.hasNext(); ) {

 			model.addElement(listeMoteurLue.get(indexliste).toString());
 			indexliste ++ ; 
			i.next();
 		}
 		return model;
    }
    
    private void updateJList(JList dataList, DefaultListModel <String> model){
        
        dataList.setModel(model);     
        dataList.setSelectedIndex(0);
        
    }
    
    public void majListes() {
    	
    	
    	
    }


    
	private static UsineMoteur usineMoteur = new UsineMoteur();
	public static Moteur moteur1 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR1);
	public static Moteur moteur2 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR2);
	public static Moteur moteur3 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR2);
	public static Moteur moteur4 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR1);
	public static Moteur moteur5 = usineMoteur.formerMoteur(TypeMoteur.MOTEUR2);

	
    public static void main(String args[]) {
    	
    	moteur1.setMoteurPrincipal();
    	moteur2.unsetMoteurActif();
    	moteur3.unsetMoteurActif();
    	moteur4.unsetMoteurActif();
    	moteur5.unsetMoteurActif();

        new TestBenoit();
        
    }

}