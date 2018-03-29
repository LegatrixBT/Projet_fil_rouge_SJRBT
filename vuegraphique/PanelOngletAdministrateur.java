package vuegraphique;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import control.ControlModifierParamIndex;
import control.ControlVisualiserParamIndex;
import model.Moteur;

import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PanelOngletAdministrateur extends JPanel implements Observer{

	//Objets interface
	private JLabel lblNbBitsVal;
	private FrameIndexation frameIndexation;
	private Box boxModifier;
	private Box boxOngletAdmin;
	private ControlModifierParamIndex controlModifierParamIndex;
	
	//Objets metier
	ControlVisualiserParamIndex controlVisualiserParamIndex;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -920996698750322588L;

	/**
	 * Create the panel.
	 */
	public PanelOngletAdministrateur() {
		
		//init objets metier
		controlVisualiserParamIndex = new ControlVisualiserParamIndex();
		frameIndexation = new FrameIndexation();
		frameIndexation.setVisible(false);
		this.controlModifierParamIndex = new ControlModifierParamIndex();
		this.controlModifierParamIndex.setObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.initOngletAdmin();
		
		this.initOngletModif();

	}
	
	public void initOngletAdmin() {
		boxOngletAdmin = Box.createVerticalBox();
		add(boxOngletAdmin);
		
		
		//Titres
		
		Box boxTitre = Box.createHorizontalBox();
		boxOngletAdmin.add(boxTitre);
		
		JLabel lblAdministrateur = new JLabel("Administrateur");
		lblAdministrateur.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxTitre.add(lblAdministrateur);
		lblAdministrateur.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrateur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		Component verticalGlue = Box.createVerticalGlue();
		boxOngletAdmin.add(verticalGlue);
		
		//Debut Partie Parametres
		
		Box BoxParametres = Box.createVerticalBox();
		boxOngletAdmin.add(BoxParametres);
		BoxParametres.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxParametres.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		Box boxTitreParametres = Box.createHorizontalBox();
		boxTitreParametres.setAlignmentY(Component.CENTER_ALIGNMENT);
		BoxParametres.add(boxTitreParametres);
		
		JLabel lblParametres = new JLabel("Parametres");
		lblParametres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxTitreParametres.add(lblParametres);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		BoxParametres.add(verticalStrut);
		
		//Partie image
		
		Box boxParamImage = Box.createVerticalBox();
		boxParamImage.setBorder(null);
		boxParamImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxParametres.add(boxParamImage);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		boxParamImage.add(lblImage);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		boxParamImage.add(verticalStrut_2);
		
		Box boxNbBitsIndex = Box.createHorizontalBox();
		boxParamImage.add(boxNbBitsIndex);
		
		JLabel lblNbBitsDe = new JLabel("Nb bits de quantification :");
		lblNbBitsDe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNbBitsDe.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxNbBitsIndex.add(lblNbBitsDe);
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 0));
		boxNbBitsIndex.add(rigidArea);
		
		lblNbBitsVal = new JLabel("");
		lblNbBitsVal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxNbBitsIndex.add(lblNbBitsVal);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		boxParamImage.add(verticalStrut_4);
		
		//Partie Texte
		
		Box boxParamTexte = Box.createVerticalBox();
		boxParamTexte.setBorder(null);
		boxParamTexte.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxParametres.add(boxParamTexte);
		
		JLabel lblTexte = new JLabel("Texte");
		lblTexte.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTexte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		boxParamTexte.add(lblTexte);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		boxParamTexte.add(verticalStrut_3);
		
		Box boxExempleParamTexte = Box.createHorizontalBox();
		boxParamTexte.add(boxExempleParamTexte);
		
		JLabel lblExemple = new JLabel("Exemple");
		lblExemple.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxExempleParamTexte.add(lblExemple);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 0));
		boxExempleParamTexte.add(rigidArea_1);
		
		JLabel lblDesactive = new JLabel("DESACTIVE");
		lblDesactive.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxExempleParamTexte.add(lblDesactive);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		boxParamTexte.add(verticalStrut_5);
		
		//Partie Son
		
		Box boxParamSon = Box.createVerticalBox();
		boxParamSon.setBorder(null);
		BoxParametres.add(boxParamSon);
		
		JLabel lblSon = new JLabel("Son");
		lblSon.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		boxParamSon.add(lblSon);
		
		Component verticalStrut_6 = Box.createVerticalStrut(10);
		boxParamSon.add(verticalStrut_6);
		
		Box boxExempleParamSon = Box.createHorizontalBox();
		boxParamSon.add(boxExempleParamSon);
		
		JLabel lblExemple_1 = new JLabel("Exemple");
		lblExemple_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxExempleParamSon.add(lblExemple_1);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 0));
		boxExempleParamSon.add(rigidArea_2);
		
		JLabel lblDesactive_1 = new JLabel("DESACTIVE");
		lblDesactive_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxExempleParamSon.add(lblDesactive_1);
		
		Component verticalStrut_8 = Box.createVerticalStrut(20);
		boxParamSon.add(verticalStrut_8);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		boxOngletAdmin.add(verticalGlue_1);
		
		//Fin partie parametres
		
		//Partie boutons
		
		Box boxActions = Box.createVerticalBox();
		boxOngletAdmin.add(boxActions);
		boxActions.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActions.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxActions.add(lblActions);
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		boxActions.add(verticalStrut_7);
		
		JButton btnModifierParametres = new JButton("Modifier parametres");
		btnModifierParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boxOngletAdmin.setVisible(false);
				boxModifier.setVisible(true);
			}
		});
		btnModifierParametres.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxActions.add(btnModifierParametres);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		boxActions.add(verticalStrut_1);
		
		JButton btnIndexer = new JButton("Lancer une indexation");
		btnIndexer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameIndexation.setVisible(true);
			}
		});
		btnIndexer.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxActions.add(btnIndexer);
		
		Component verticalStrut_9 = Box.createVerticalStrut(20);
		boxActions.add(verticalStrut_9);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		boxOngletAdmin.add(verticalGlue_2);
	}
	
	public void initOngletModif() {
		boxModifier = Box.createVerticalBox();
		add(boxModifier);
		
		Box boxTitre1 = Box.createHorizontalBox();
		boxModifier.add(boxTitre1);
		
		JLabel lblModifierLesParametres = new JLabel("Modifier les parametres");
		lblModifierLesParametres.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblModifierLesParametres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxTitre1.add(lblModifierLesParametres);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		boxModifier.add(verticalGlue_3);
		
		Box boxImageModif = Box.createVerticalBox();
		boxImageModif.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		boxImageModif.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxModifier.add(boxImageModif);
		
		JLabel lblImages = new JLabel("Images");
		lblImages.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImages.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxImageModif.add(lblImages);
		
		Component verticalStrut_10 = Box.createVerticalStrut(20);
		boxImageModif.add(verticalStrut_10);
		
		Box boxParamQuantifModif = Box.createHorizontalBox();
		boxImageModif.add(boxParamQuantifModif);
		
		JLabel lbQuantif = new JLabel("bits de quant. :");
		boxParamQuantifModif.add(lbQuantif);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		boxParamQuantifModif.add(horizontalGlue);
		
		JSpinner spinnerQuantifModif = new JSpinner();
		spinnerQuantifModif.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerQuantifModif.setMaximumSize(new Dimension(30,20));
		boxParamQuantifModif.add(spinnerQuantifModif);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		boxImageModif.add(verticalGlue_4);
		
		Box boxTexteModif = Box.createVerticalBox();
		boxTexteModif.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		boxTexteModif.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxModifier.add(boxTexteModif);
		
		JLabel lblTexte_1 = new JLabel("Texte");
		boxTexteModif.add(lblTexte_1);
		lblTexte_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTexte_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		Component verticalStrut_11 = Box.createVerticalStrut(20);
		boxTexteModif.add(verticalStrut_11);
		
		Box boxExempleParamTexteModif = Box.createHorizontalBox();
		boxTexteModif.add(boxExempleParamTexteModif);
		
		JLabel lblExempleTexteModif = new JLabel("Exemple");
		lblExempleTexteModif.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxExempleParamTexteModif.add(lblExempleTexteModif);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		boxExempleParamTexteModif.add(horizontalGlue_1);
		
		JLabel lblDesactive_2 = new JLabel("DESACTIVE");
		lblDesactive_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxExempleParamTexteModif.add(lblDesactive_2);
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		boxTexteModif.add(verticalGlue_5);
		
		Box boxModifSon = Box.createVerticalBox();
		boxModifSon.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		boxModifSon.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxModifier.add(boxModifSon);
		
		JLabel lblSon_1 = new JLabel("Son");
		lblSon_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSon_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxModifSon.add(lblSon_1);
		
		Component verticalStrut_12 = Box.createVerticalStrut(20);
		boxModifSon.add(verticalStrut_12);
		
		Box boxExempleParamSonModif = Box.createHorizontalBox();
		boxModifSon.add(boxExempleParamSonModif);
		
		JLabel label = new JLabel("Exemple");
		boxExempleParamSonModif.add(label);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		boxExempleParamSonModif.add(horizontalGlue_2);
		
		JLabel label_1 = new JLabel("DESACTIVE");
		boxExempleParamSonModif.add(label_1);
		
		Component verticalGlue_6 = Box.createVerticalGlue();
		boxModifSon.add(verticalGlue_6);
		
		Box boxBoutonModif = Box.createVerticalBox();
		boxBoutonModif.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxModifier.add(boxBoutonModif);
		
		Component verticalGlue_8 = Box.createVerticalGlue();
		boxBoutonModif.add(verticalGlue_8);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nbBitsQuantif = (int) spinnerQuantifModif.getValue();
				controlModifierParamIndex.modifierNbBitsIndex(nbBitsQuantif);
				boxModifier.setVisible(false);
				boxOngletAdmin.setVisible(true);
			}
		});
		btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxBoutonModif.add(btnValider);
		
		Component verticalGlue_7 = Box.createVerticalGlue();
		boxBoutonModif.add(verticalGlue_7);
		boxModifier.setVisible(false);
	}
	
	public void ouvertureOngletAdmin() {
		
		Integer nbBitsQuant = controlVisualiserParamIndex.visualiserParamIndex();
		lblNbBitsVal.setText(nbBitsQuant.toString());
		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Moteur) {
			Integer nbBitsQuantif = (Integer) arg;
			lblNbBitsVal.setText(nbBitsQuantif.toString());
		}
	}

}
