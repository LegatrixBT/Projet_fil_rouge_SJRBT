package testGraphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.ControlMoteurActifs;
import model.BaseMoteur;
import model.Moteur;
import model.TypeMoteur;
import model.UsineMoteur;
import vuegraphique.PanelOngletRechercheAvancee;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestCompletFrameBenoit extends JFrame {


	ControlMoteurActifs controlMoteurActifs = new ControlMoteurActifs();
	
	PanelOngletRechercheAvancee panelOngletRechercheAvancee = new PanelOngletRechercheAvancee(); 

	List<Moteur> listeMoteurLueInactif = new ArrayList<Moteur>();
	List<Moteur> listeMoteurLueActif = new ArrayList<Moteur>();
	
	
	List<Moteur> listeMoteurSelectInactif = new ArrayList<Moteur>();
	List<Moteur> listeMoteurSelectActif = new ArrayList<Moteur>();
	
	DefaultListModel modelInactif = new DefaultListModel<String>();
	DefaultListModel modelActif = new DefaultListModel<String>();

	
    public TestCompletFrameBenoit() {

        setSize(new Dimension(385, 352));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());
        

        getContentPane().add(panelOngletRechercheAvancee);
        
 
        this.setVisible(true);
        
        JButton bouton = new JButton();
        	

		listeMoteurLueInactif = controlMoteurActifs.getMoteurInactifs();
		System.out.println("Les moteurs inactifs sont : " + listeMoteurLueInactif.toString());
		JLabel labelInactif = new JLabel("Update");
		

		listeMoteurLueActif = controlMoteurActifs.getMoteursActifs(); 
		System.out.println("Les moteurs inactifs sont : " + listeMoteurLueActif.toString());
		JLabel labelActif = new JLabel("Update");
		
		/*-----------------------------------------------------------------------*/
		
		

		
		JList <Moteur> dataListInactif = new JList();
		   dataListInactif.addListSelectionListener(new ListSelectionListener() {

	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	labelInactif.setText(dataListInactif.getSelectedValue().toString());  //affichage
	                }
	            }
	        });
		  
	
		modelInactif = listToModelInactif(dataListInactif, listeMoteurLueInactif);
        updateJList(dataListInactif, modelInactif);
		getContentPane().add(dataListInactif);
		getContentPane().add(labelInactif);
		
		/*   DEBUG DU CAS DES MOTEUR INACTIFS OU ACTIFS EN PERMANENCE
		for (Moteur moteur : listeMoteurLueInactif) {
			
			if(!moteur.isMoteurActif())
				System.out.println("Syso du moteur apres new " + (!moteur.isMoteurActif()));
		}
*/
		   
		JList <Moteur>  dataListActif = new JList();
		   dataListActif.addListSelectionListener(new ListSelectionListener() {

	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	labelActif.setText(dataListActif.getSelectedValue().toString());  //affichage
	                }
	            }
	        });

		
		modelActif = listToModelActif(dataListActif, listeMoteurLueActif);
        updateJList(dataListActif, modelActif);
        getContentPane().add(dataListActif);
		getContentPane().add(labelActif);     
		
		
		//Bouton 
	       JButton btnActivation = new JButton("Activation");
	        btnActivation.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        	/*	listeMoteurSelectInactif = getJListes(dataListInactif);
	        		majListes(listeMoteurSelectInactif, listeMoteurLueInactif);
	        		affecterListeActif(listeMoteurLueInactif); // ??? juste ?
	        	*/	
	        		System.out.println(listeMoteurLueInactif.toString());
	        		modelInactif = listToModelInactif(dataListInactif, listeMoteurLueInactif);
	                updateJList(dataListInactif, modelInactif);
	        		
	        		
	        	/*	
	        		listeMoteurSelectActif = getJListes(dataListActif);
	        		majListes(listeMoteurSelectActif, listeMoteurLueActif);
	        		affecterListeInactif(listeMoteurLueActif); // ??? juste ?
	        	*/
	        		
	        		System.out.println("actif" + listeMoteurLueActif.toString());
	        		modelInactif = listToModelInactif(dataListInactif, listeMoteurLueInactif);
	                updateJList(dataListInactif, modelInactif);
	        		
	        	}
	        });
	        
	       JButton btnMaj = new JButton("MaJ");
	       btnMaj.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		
	        		listeMoteurSelectInactif = getJListes(dataListInactif);
	        		majListes(listeMoteurSelectInactif, listeMoteurLueInactif);
	        		affecterListeActif(listeMoteurLueInactif); // ??? juste ?
	        		
	        		listeMoteurSelectActif = getJListes(dataListActif);
	        		majListes(listeMoteurSelectActif, listeMoteurLueActif);
	        		affecterListeInactif(listeMoteurLueActif); // ??? juste ?    		
	        	}
	        });
	       panelOngletRechercheAvancee.add(btnMaj);
	        panelOngletRechercheAvancee.add(btnActivation);
	        
        setVisible(true);
        

    }

    private DefaultListModel listToModelInactif(JList dataList, List<Moteur> listeMoteurLue ) {
        
    	DefaultListModel model = new DefaultListModel<String>();
    	int indexliste = 0 ; 
		
 		for (Iterator i = listeMoteurLue.iterator(); i.hasNext(); ) {

 			
 			 //////// Marqueur /////// 
 			System.out.println("list to model inactif " + listeMoteurLue.get(indexliste).isMoteurActif());
 		//	model.addElement(listeMoteurLue.get(indexliste).toString());  visualisation
			if(!(listeMoteurLue.get(indexliste).isMoteurActif())) {
 				model.addElement(listeMoteurLue.get(indexliste));
 			}
 			
 			indexliste ++ ; 
			i.next();
 		}
 		return model;
    }
    
    private DefaultListModel listToModelActif(JList dataList, List<Moteur> listeMoteurLue ) {
        
    	DefaultListModel model = new DefaultListModel<String>();
    	int indexliste = 0 ; 
		
 		for (Iterator i = listeMoteurLue.iterator(); i.hasNext(); ) {

 		//	model.addElement(listeMoteurLue.get(indexliste).toString());  visualisation
 			if(listeMoteurLue.get(indexliste).isMoteurActif()) {
 				model.addElement(listeMoteurLue.get(indexliste));
 			}
 			indexliste ++ ; 
			i.next();
 		}
 		return model;
    }
    
    private void updateJList(JList dataList, DefaultListModel <String> model){
        
        dataList.removeAll(); // Test du bousin 
    	dataList.setModel(model);     
        dataList.setSelectedIndex(0);
        
    }
    
    public List<Moteur> getJListes(JList <Moteur> jlist) {
    	
    	DefaultListModel model = new DefaultListModel<String>();
    	List<Moteur> list = new ArrayList<Moteur>();
    	
    	int[] selectedIx = jlist.getSelectedIndices();
    	
    	for (int i = 0; i < selectedIx.length; i++) {

    	      model.addElement(jlist.getModel().getElementAt(selectedIx[i]));
    	      System.out.println(jlist.getModel().getElementAt(selectedIx[i]));
    	      list.add(jlist.getModel().getElementAt(selectedIx[i]));
    	    }
    	

    	return list;
    }
    
    public void majListes(List<Moteur> listeDonnee, List<Moteur> listeMiseAJour) {
    	
    	listeMiseAJour = listeDonnee;
    	//System.out.println(listeMiseAJour.toString()); // affichage
    	
    }
    
    public void affecterListeActif(List<Moteur> listeAAffecter) {
    	
    	for (Moteur moteur : listeAAffecter) {
			//moteur.setMoteurActif();
    		controlMoteurActifs.setListeMoteurActif(listeAAffecter);

		}
    }
    
    public void affecterListeInactif(List<Moteur> listeAAffecter) {
    	
    	for (Moteur moteur : listeAAffecter) {
			//moteur.unsetMoteurActif();
    		controlMoteurActifs.setListeMoteurInactif(listeAAffecter);
		}
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
    	moteur3.setMoteurActif();
    	moteur4.unsetMoteurActif();
    	moteur5.unsetMoteurActif();
    	
    	BaseMoteur.getInstance().ajouterMoteur(moteur1);
    	BaseMoteur.getInstance().ajouterMoteur(moteur2);
    	BaseMoteur.getInstance().ajouterMoteur(moteur3);
    	BaseMoteur.getInstance().ajouterMoteur(moteur4);
    	BaseMoteur.getInstance().ajouterMoteur(moteur5);
    	
    
    	
        new TestCompletFrameBenoit();
        
    }

}
