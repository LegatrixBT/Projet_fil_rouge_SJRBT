package vuegraphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.ControlLancerRecherche;
import control.ControlRecherche;
import model.EntreeRecherche;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelRechercheInt extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209829585835617324L;

	/**
	 * Create the panel.
	 */
	
	private ControlRecherche controlRecherche;
	private Box boxRechercheImage;
	private Box boxRechercheTexte;
	private JTextField textFieldMotInclure;
	private JTextField textFieldMotExclure;
	private JTextField textFieldCheminTexte;
	private JList<EntreeRecherche> listeResultats;
	private DefaultListModel<EntreeRecherche> modeleResultatsRecherche;
	private JTextField textFieldCheminImage;
	
	private JFileChooser chooserTexte= new JFileChooser(System.getProperty("user.dir"));
	private FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Fichiers XML (*.xml)", "xml");
	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Fichiers image (*.jpg, *.bmp)", "jpg", "bmp");
	
	public PanelRechercheInt() {
		
		controlRecherche = new ControlRecherche();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ButtonGroup groupeChoixTypeRecherche = new ButtonGroup();
		ButtonGroup groupeChoixCouleurs = new ButtonGroup();
		
		Box boxChoixTypeRecherche = Box.createVerticalBox();
		boxChoixTypeRecherche.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(boxChoixTypeRecherche);
		
		JLabel lblTitreChoixType = new JLabel("Choissisez le type de fichier \u00E0 rechercher :");
		lblTitreChoixType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitreChoixType.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxChoixTypeRecherche.add(lblTitreChoixType);
		
		Box boxBoutonsChoixType = Box.createHorizontalBox();
		boxChoixTypeRecherche.add(boxBoutonsChoixType);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		boxBoutonsChoixType.add(horizontalGlue);
		
		JRadioButton rdbtnImage = new JRadioButton("Image");
		rdbtnImage.setSelected(true);
		rdbtnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnImage.isSelected()) {
					boxRechercheImage.setVisible(true);
					boxRechercheTexte.setVisible(false);
				}
			}
		});
		boxBoutonsChoixType.add(rdbtnImage);
		groupeChoixTypeRecherche.add(rdbtnImage);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		boxBoutonsChoixType.add(horizontalGlue_1);
		
		JRadioButton rdbtnTexte = new JRadioButton("Texte");
		rdbtnTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTexte.isSelected()) {
					boxRechercheImage.setVisible(false);
					boxRechercheTexte.setVisible(true);
				}
			}
		});
		boxBoutonsChoixType.add(rdbtnTexte);
		groupeChoixTypeRecherche.add(rdbtnTexte);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		boxBoutonsChoixType.add(horizontalGlue_2);
		
		JRadioButton rdbtnSon = new JRadioButton("Son (Desactive)");
		rdbtnSon.setEnabled(false);
		boxBoutonsChoixType.add(rdbtnSon);
		groupeChoixTypeRecherche.add(rdbtnSon);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		boxBoutonsChoixType.add(horizontalGlue_3);
		
		Box boxRecherche = Box.createVerticalBox();
		add(boxRecherche);
		
		boxRechercheImage = Box.createVerticalBox();
		boxRecherche.add(boxRechercheImage);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		boxRechercheImage.add(tabbedPane);
		
		JPanel panelImageCouleur = new JPanel();
		tabbedPane.addTab("Recherche par couleur", null, panelImageCouleur, null);
		panelImageCouleur.setLayout(new BoxLayout(panelImageCouleur, BoxLayout.Y_AXIS));
		
		Box boxCouleursPrimaires = Box.createHorizontalBox();
		boxCouleursPrimaires.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelImageCouleur.add(boxCouleursPrimaires);
		
		JRadioButton rdbtnBleu = new JRadioButton("Bleu");
		rdbtnBleu.setSelected(true);
		boxCouleursPrimaires.add(rdbtnBleu);
		groupeChoixCouleurs.add(rdbtnBleu);
		
		JRadioButton rdbtnRouge = new JRadioButton("Rouge");
		boxCouleursPrimaires.add(rdbtnRouge);
		groupeChoixCouleurs.add(rdbtnRouge);
		
		JRadioButton rdbtnVert = new JRadioButton("Vert");
		boxCouleursPrimaires.add(rdbtnVert);
		groupeChoixCouleurs.add(rdbtnVert);
		
		Box boxCouleursAdditionnels = Box.createHorizontalBox();
		boxCouleursAdditionnels.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelImageCouleur.add(boxCouleursAdditionnels);
		
		JRadioButton rdbtnJaune = new JRadioButton("Jaune");
		boxCouleursAdditionnels.add(rdbtnJaune);
		groupeChoixCouleurs.add(rdbtnJaune);
		
		JRadioButton rdbtnOrange = new JRadioButton("Orange");
		boxCouleursAdditionnels.add(rdbtnOrange);
		groupeChoixCouleurs.add(rdbtnOrange);
		
		JRadioButton rdbtnMarron = new JRadioButton("Marron");
		boxCouleursAdditionnels.add(rdbtnMarron);
		groupeChoixCouleurs.add(rdbtnMarron);
		
		Box boxNB = Box.createHorizontalBox();
		boxNB.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelImageCouleur.add(boxNB);
		
		JRadioButton rdbtnNoir = new JRadioButton("Noir");
		boxNB.add(rdbtnNoir);
		groupeChoixCouleurs.add(rdbtnNoir);
		
		JRadioButton rdbtnGris = new JRadioButton("Gris");
		boxNB.add(rdbtnGris);
		groupeChoixCouleurs.add(rdbtnGris);
		
		Box boxBtnRechImageCouleur = Box.createHorizontalBox();
		boxBtnRechImageCouleur.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelImageCouleur.add(boxBtnRechImageCouleur);
		
		JButton btnRechImageCouleur = new JButton("Lancer la recherche");
		btnRechImageCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boxBtnRechImageCouleur.add(btnRechImageCouleur);
		
		Component verticalStrut_4 = Box.createVerticalStrut(15);
		panelImageCouleur.add(verticalStrut_4);
		
		JPanel panelImageFichier = new JPanel();
		tabbedPane.addTab("Recherche par fichier", null, panelImageFichier, null);
		panelImageFichier.setLayout(new BoxLayout(panelImageFichier, BoxLayout.Y_AXIS));
		
		Box boxImageChoixFicher = Box.createHorizontalBox();
		panelImageFichier.add(boxImageChoixFicher);
		
		JButton btnChoixFichierImage = new JButton("Choisir le fichier");
		boxImageChoixFicher.add(btnChoixFichierImage);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		boxImageChoixFicher.add(horizontalStrut_3);
		
		textFieldCheminImage = new JTextField();
		boxImageChoixFicher.add(textFieldCheminImage);
		textFieldCheminImage.setMaximumSize(new Dimension(300, 20));
		textFieldCheminImage.setEditable(false);
		btnChoixFichierImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooserTexte.setFileFilter(imageFilter);
				int choice = chooserTexte.showOpenDialog(chooserTexte);
				if (choice != JFileChooser.APPROVE_OPTION) 
					return;
				File chosenFile = chooserTexte.getSelectedFile();
				textFieldCheminImage.setText(chosenFile.getName());
			}
		});
		
		Box boxBtnRechImage = Box.createHorizontalBox();
		panelImageFichier.add(boxBtnRechImage);
		
		JButton btnRechImage = new JButton("Lancer la recherche");
		boxBtnRechImage.add(btnRechImage);
		
		Component verticalStrut_2 = Box.createVerticalStrut(15);
		panelImageFichier.add(verticalStrut_2);
		btnRechImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cheminImage = textFieldCheminImage.getText();
				if(cheminImage.contains(".jpg") || cheminImage.contains(".bmp")) {
					cheminImage = cheminImage.substring(0, cheminImage.length()-4);
					cheminImage = cheminImage + ".txt";
					
					ControlLancerRecherche controlLancerRecherche = new ControlLancerRecherche();
	// Multi moteur	TreeSet<EntreeRecherche> resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheImage(cheminImage);
					TreeSet<EntreeRecherche> resRech = new TreeSet<>();
					try {
						resRech = (TreeSet<EntreeRecherche>) controlRecherche.lancerRechercheImage(cheminImage);
					} catch (FileNotFoundException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					modeleResultatsRecherche.clear();
					for (EntreeRecherche entreeRecherche : resRech) {
						modeleResultatsRecherche.addElement(entreeRecherche);
					}
					repaint();
				}
			}
		});
		
		boxRechercheTexte = Box.createVerticalBox();
		boxRecherche.add(boxRechercheTexte);
		
		JTabbedPane tabPanTexte = new JTabbedPane(JTabbedPane.TOP);
		boxRechercheTexte.add(tabPanTexte);
		
		JPanel panelTexteMotCle = new JPanel();
		tabPanTexte.addTab("Recherche par mot-cle", null, panelTexteMotCle, null);
		panelTexteMotCle.setLayout(new BoxLayout(panelTexteMotCle, BoxLayout.Y_AXIS));
		
		Box boxMotInclure = Box.createHorizontalBox();
		panelTexteMotCle.add(boxMotInclure);
		
		JLabel lblMotsAInclure = new JLabel("Mots a inclure");
		lblMotsAInclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxMotInclure.add(lblMotsAInclure);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		boxMotInclure.add(horizontalStrut);
		
		textFieldMotInclure = new JTextField();
		textFieldMotInclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMotInclure.setMaximumSize(new Dimension(120, 20));
		boxMotInclure.add(textFieldMotInclure);
		
		Box boxMotExclure = Box.createHorizontalBox();
		panelTexteMotCle.add(boxMotExclure);
		
		JCheckBox chckbxMotsAExclure = new JCheckBox("Mots a exclure");
		chckbxMotsAExclure.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckbxMotsAExclure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMotsAExclure.isSelected()) {
					textFieldMotExclure.setEnabled(true);
				}
				else
					textFieldMotExclure.setEnabled(false);
			}
		});
		chckbxMotsAExclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boxMotExclure.add(chckbxMotsAExclure);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		boxMotExclure.add(horizontalStrut_1);
		
		textFieldMotExclure = new JTextField();
		textFieldMotExclure.setEnabled(false);
		textFieldMotExclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMotExclure.setMaximumSize(new Dimension(120, 20));
		boxMotExclure.add(textFieldMotExclure);
		
		Box boxBtnRechTexteMotCle = Box.createHorizontalBox();
		panelTexteMotCle.add(boxBtnRechTexteMotCle);
		
		JButton btnRechTexteMotCle = new JButton("Lancer la recherche");
		btnRechTexteMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String motCle = textFieldMotInclure.getText();
				TreeSet<EntreeRecherche> resRech = new TreeSet<>();
				if (chckbxMotsAExclure.isSelected()) {// recherche complexe moteurPrincipal 
					ControlLancerRecherche controlLancerRecherche = new ControlLancerRecherche();
					String motAExclure = textFieldMotExclure.getText();
					try {
						resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheTexteMotCleComplexe(motCle.split(" "), motAExclure.split(" "));
					} catch (FileNotFoundException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
				////////////// Recherche multiMoteur///////////////
				/*ControlLancerRecherche controlLancerRecherche = new ControlLancerRecherche();
				TreeSet<EntreeRecherche> resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheTexte(motCle);
				*/
				
				try {
					resRech = (TreeSet<EntreeRecherche>) controlRecherche.lancerRechercheTexteMotCle(motCle);
				} catch (FileNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				modeleResultatsRecherche.clear();
				for (EntreeRecherche entreeRecherche : resRech) {
					modeleResultatsRecherche.addElement(entreeRecherche);
				}
				repaint();
			}
		});
		boxBtnRechTexteMotCle.add(btnRechTexteMotCle);
		
		Component verticalStrut_1 = Box.createVerticalStrut(15);
		panelTexteMotCle.add(verticalStrut_1);
		
		JPanel panelTexteFichier = new JPanel();
		tabPanTexte.addTab("Recherche par fichier", null, panelTexteFichier, null);
		panelTexteFichier.setLayout(new BoxLayout(panelTexteFichier, BoxLayout.Y_AXIS));
		
		Box boxTexteChoixFichier = Box.createHorizontalBox();
		panelTexteFichier.add(boxTexteChoixFichier);
		
		JButton btnTexteChoisirLeFichier = new JButton("Choisir le fichier");
		btnTexteChoisirLeFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooserTexte.setFileFilter(xmlFilter);
				int choice = chooserTexte.showOpenDialog(chooserTexte);
				if (choice != JFileChooser.APPROVE_OPTION) 
					return;
				File chosenFile = chooserTexte.getSelectedFile();
				textFieldCheminTexte.setText(chosenFile.getName());
			}
		});
		boxTexteChoixFichier.add(btnTexteChoisirLeFichier);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		boxTexteChoixFichier.add(horizontalStrut_2);
		
		textFieldCheminTexte = new JTextField();
		textFieldCheminTexte.setMaximumSize(new Dimension(300, 20));
		textFieldCheminTexte.setEditable(false);
		boxTexteChoixFichier.add(textFieldCheminTexte);
		
		Box boxBtnRechTexteFic = Box.createHorizontalBox();
		panelTexteFichier.add(boxBtnRechTexteFic);
		
		JButton btnRechTexteFic = new JButton("Lancer la recherche");
		btnRechTexteFic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cheminTexte = textFieldCheminTexte.getText();
				if(cheminTexte.contains(".xml")) {	
					TreeSet<EntreeRecherche> listeRes = new TreeSet<>();
					try {
						listeRes = (TreeSet<EntreeRecherche>) controlRecherche.lancerRechercheTexteFichier(cheminTexte);
					} catch (FileNotFoundException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					modeleResultatsRecherche.clear();
					for (EntreeRecherche entreeRecherche : listeRes) {
						modeleResultatsRecherche.addElement(entreeRecherche);
					}
				}
			}
		});
		boxBtnRechTexteFic.add(btnRechTexteFic);
		
		Component verticalStrut_3 = Box.createVerticalStrut(15);
		panelTexteFichier.add(verticalStrut_3);
		
		modeleResultatsRecherche = new DefaultListModel<>();
		
		Component verticalStrut = Box.createVerticalStrut(20);
		boxRecherche.add(verticalStrut);
		listeResultats = new JList<>(modeleResultatsRecherche);
		listeResultats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					EntreeRecherche entreeChoisie = listeResultats.getSelectedValue();
					if(entreeChoisie.getCheminFichier().contains("IMG")) {
						try {
							Runtime.getRuntime().exec("eog " + entreeChoisie.getCheminFichier() + ".jpg");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else {
						if(entreeChoisie.getCheminFichier().contains("Textes")) {
							try {
								Runtime.getRuntime().exec("gedit " + entreeChoisie.getCheminFichier());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		JScrollPane scrollPaneResultats = new JScrollPane(listeResultats);
		boxRecherche.add(scrollPaneResultats);
		
		Component verticalGlue = Box.createVerticalGlue();
		boxRecherche.add(verticalGlue);
		boxRechercheTexte.setVisible(false);
		boxRechercheImage.setVisible(true);

	}

}
