package vuegraphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.ControlLancerRecherche;
import model.EntreeRecherche;

public class PanelRechercheInt extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209829585835617324L;

	/**
	 * Create the panel.
	 */
	
	private ControlLancerRecherche controlLancerRecherche;
  
	private Box boxRechercheImage;
	private Box boxRechercheTexte;
	private JTextField textFieldMotInclure;
	private JTextField textFieldMotExclure;
	private JTextField textFieldCheminTexte;
	private JList<EntreeRecherche> listeResultats;
	private DefaultListModel<EntreeRecherche> modeleResultatsRecherche;
	private JTextField textFieldCheminImage;
	private JSlider sliderR;
	private JSlider sliderG;
	private JSlider sliderB;
	private JLabel lblValeurRGB;
	private JLabel lblStatusRechImageCouleur;
	private JCheckBox chckBxMultiMoteur;

	
	private JFileChooser chooserTexte= new JFileChooser(System.getProperty("user.dir"));
	private FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Fichiers XML (*.xml)", "xml");
	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Fichiers image (*.jpg, *.bmp)", "jpg", "bmp");
	
	public PanelRechercheInt() {
		
		controlLancerRecherche = new ControlLancerRecherche();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ButtonGroup groupeChoixTypeRecherche = new ButtonGroup();
		
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
		
		chckBxMultiMoteur = new JCheckBox("Recherche multi-moteur");
		boxBoutonsChoixType.add(chckBxMultiMoteur);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		boxBoutonsChoixType.add(horizontalGlue_4);
		
		Box boxRecherche = Box.createVerticalBox();
		add(boxRecherche);
		
		boxRechercheImage = Box.createVerticalBox();
		boxRecherche.add(boxRechercheImage);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		boxRechercheImage.add(tabbedPane);
		
		JPanel panelImageCouleur = new JPanel();
		tabbedPane.addTab("Recherche par couleur", null, panelImageCouleur, null);
		panelImageCouleur.setLayout(new BoxLayout(panelImageCouleur, BoxLayout.Y_AXIS));
		

		Box boxChoixCouleurs = Box.createHorizontalBox();
		panelImageCouleur.add(boxChoixCouleurs);
		
		sliderR = new JSlider();
		sliderR.setPaintTicks(true);
		sliderG = new JSlider();
		sliderG.setPaintTicks(true);
		sliderB = new JSlider();
		sliderB.setPaintTicks(true);
		
		JPanel panelCouleur = new JPanel();
		panelCouleur.setBackground(Color.getHSBColor(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
		boxChoixCouleurs.add(panelCouleur);
		
		Box boxSliders = Box.createVerticalBox();
		boxChoixCouleurs.add(boxSliders);
		
		Box boxSliderR = Box.createHorizontalBox();
		boxSliders.add(boxSliderR);
		
		JLabel lblR = new JLabel("R :");
		boxSliderR.add(lblR);
		lblValeurRGB = new JLabel();
		lblValeurRGB.setText("R = " + sliderR.getValue() + " G = " + sliderG.getValue() + " B = " + sliderB.getValue());
		
		sliderR.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				panelCouleur.setBackground(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
				lblValeurRGB.setText("R = " + sliderR.getValue() + " G = " + sliderG.getValue() + " B = " + sliderB.getValue());
			}
		});
		sliderR.setMaximum(255);
		boxSliderR.add(sliderR);
		
		Box boxSliderG = Box.createHorizontalBox();
		boxSliders.add(boxSliderG);
		
		JLabel lblG = new JLabel("G :");
		boxSliderG.add(lblG);
		
		sliderG.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				panelCouleur.setBackground(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
				lblValeurRGB.setText("R = " + sliderR.getValue() + " G = " + sliderG.getValue() + " B = " + sliderB.getValue());
			}
		});
		sliderG.setMaximum(255);
		boxSliderG.add(sliderG);
		
		Box boxSliderB = Box.createHorizontalBox();
		boxSliders.add(boxSliderB);
		
		JLabel lblB = new JLabel("B :");
		boxSliderB.add(lblB);
		
		sliderB.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				panelCouleur.setBackground(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()));
				lblValeurRGB.setText("R = " + sliderR.getValue() + " G = " + sliderG.getValue() + " B = " + sliderB.getValue());
			}
		});
		sliderB.setMaximum(255);
		boxSliderB.add(sliderB);
		
		lblValeurRGB.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxSliders.add(lblValeurRGB);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		panelImageCouleur.add(verticalGlue_2);
		
		lblStatusRechImageCouleur = new JLabel("Erreur durant la recherche...");
		lblStatusRechImageCouleur.setVisible(false);
		lblStatusRechImageCouleur.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelImageCouleur.add(lblStatusRechImageCouleur);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		panelImageCouleur.add(verticalGlue_1);
		
		Box boxBtnRechImageCouleur = Box.createHorizontalBox();
		panelImageCouleur.add(boxBtnRechImageCouleur);
		boxBtnRechImageCouleur.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JButton btnRechImageCouleur = new JButton("Lancer la recherche");
		btnRechImageCouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblStatusRechImageCouleur.setVisible(false);
					modeleResultatsRecherche.clear();
					TreeSet<EntreeRecherche> resRech;
					if(!chckBxMultiMoteur.isSelected()) {
						resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheImageCouleur(sliderR.getValue(), sliderG.getValue(), sliderB.getValue());
					}
					else
						resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheImageCouleur(sliderR.getValue(), sliderG.getValue(), sliderB.getValue());
					if(!resRech.isEmpty()){
						modeleResultatsRecherche.clear();
						for (EntreeRecherche entreeRecherche : resRech.descendingSet()) {
							modeleResultatsRecherche.addElement(entreeRecherche);
						}
					}
					else {
						lblStatusRechImageCouleur.setText("Pas de resultats trouves...");
						lblStatusRechImageCouleur.setVisible(true);
					}
					repaint();
				}
				catch(Exception ex) {
					lblStatusRechImageCouleur.setText("Erreur durant la recherche...");
					lblStatusRechImageCouleur.setVisible(true);
				}
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

		Component verticalGlue_3 = Box.createVerticalGlue();
		panelImageFichier.add(verticalGlue_3);
		
		JLabel lblStatusImageFichier = new JLabel("Erreur durant la recherche...");
		lblStatusImageFichier.setVisible(false);
		lblStatusImageFichier.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelImageFichier.add(lblStatusImageFichier);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		panelImageFichier.add(verticalGlue_4);
		
		Box boxBtnRechImage = Box.createHorizontalBox();
		panelImageFichier.add(boxBtnRechImage);
		
		JButton btnRechImage = new JButton("Lancer la recherche");
		boxBtnRechImage.add(btnRechImage);
		
		Component verticalStrut_2 = Box.createVerticalStrut(15);
		panelImageFichier.add(verticalStrut_2);
		btnRechImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cheminImage = textFieldCheminImage.getText();
				modeleResultatsRecherche.clear();
				if(cheminImage.contains(".jpg") || cheminImage.contains(".bmp")) {
					lblStatusImageFichier.setVisible(false);
					cheminImage = cheminImage.substring(0, cheminImage.length()-4);
					cheminImage = cheminImage + ".txt";
					TreeSet<EntreeRecherche> resRech;
					try {
						if(!chckBxMultiMoteur.isSelected())
							resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheImage(cheminImage);
						else
							resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheImage(cheminImage);
						if(!resRech.isEmpty()) {
							modeleResultatsRecherche.clear();
							for (EntreeRecherche entreeRecherche : resRech.descendingSet()) {
								modeleResultatsRecherche.addElement(entreeRecherche);
							}
						}
						else {
							lblStatusImageFichier.setText("Pas de resultats trouve...");
							lblStatusImageFichier.setVisible(true);
						}
						repaint();
					}
					catch(Exception ex) {
						lblStatusImageFichier.setText("Erreur durant la recherche...");
						lblStatusImageFichier.setVisible(true);
					}
				}
				else {
					lblStatusImageFichier.setText("Format du fichier incorrect!");
					lblStatusImageFichier.setVisible(true);
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
		
		Component verticalGlue_6 = Box.createVerticalGlue();
		panelTexteMotCle.add(verticalGlue_6);
		
		JLabel lblStatusRechTexteMotCle = new JLabel("Erreur durant la recherche...");
		lblStatusRechTexteMotCle.setVisible(false);
		lblStatusRechTexteMotCle.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTexteMotCle.add(lblStatusRechTexteMotCle);
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		panelTexteMotCle.add(verticalGlue_5);

		Box boxBtnRechTexteMotCle = Box.createHorizontalBox();
		panelTexteMotCle.add(boxBtnRechTexteMotCle);
		
		JButton btnRechTexteMotCle = new JButton("Lancer la recherche");
		btnRechTexteMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblStatusRechTexteMotCle.setVisible(false);
					modeleResultatsRecherche.clear();
					TreeSet<EntreeRecherche> resRech;
					String motCle = textFieldMotInclure.getText();
					String motExclus = textFieldMotExclure.getText();
					if(!chckBxMultiMoteur.isSelected()) {
						resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheTexteMotCleComplexe(motCle.split(" "), motExclus.split(" "));
					}	
					else {
						resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheTexteMotCle(motCle);
						//if(!chckbxMotsAExclure.isSelected())
							//resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheTexteMotCle(motCle);
						//else
							//resRech = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheTexteMotCleComplexe(motCle.split(" "), motExclus.split(" "));
					}
					if(!resRech.isEmpty()) {
							modeleResultatsRecherche.clear();
							for (EntreeRecherche entreeRecherche : resRech.descendingSet()) {
								modeleResultatsRecherche.addElement(entreeRecherche);
							}
							repaint();
						}else {
							lblStatusRechTexteMotCle.setText("Pas de resultats trouves...");
							lblStatusRechTexteMotCle.setVisible(true);
						}
					}
					catch(Exception ex) {
						lblStatusRechTexteMotCle.setText("Erreur durant la recherche...");
						lblStatusRechTexteMotCle.setVisible(true);
					}
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
		
		Component verticalGlue_8 = Box.createVerticalGlue();
		panelTexteFichier.add(verticalGlue_8);
		
		JLabel lblStatusTexteFichier = new JLabel("Erreur durant la recherche...");
		lblStatusTexteFichier.setVisible(false);
		lblStatusTexteFichier.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTexteFichier.add(lblStatusTexteFichier);
		
		Component verticalGlue_7 = Box.createVerticalGlue();
		panelTexteFichier.add(verticalGlue_7);
		Box boxBtnRechTexteFic = Box.createHorizontalBox();
		panelTexteFichier.add(boxBtnRechTexteFic);
		
		JButton btnRechTexteFic = new JButton("Lancer la recherche");
		btnRechTexteFic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblStatusRechTexteMotCle.setVisible(false);
					modeleResultatsRecherche.clear();
					String cheminTexte = textFieldCheminTexte.getText();
					TreeSet<EntreeRecherche> listeRes;
					if(cheminTexte.contains(".xml")) {
						//if(!chckBxMultiMoteur.isSelected())
							listeRes = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerRechercheTexteFichier(cheminTexte);
						//else
							//listeRes = (TreeSet<EntreeRecherche>) controlLancerRecherche.lancerMultiRechercheTexteFichier(cheminTexte);
						if(!listeRes.isEmpty()) {
							modeleResultatsRecherche.clear();
							for (EntreeRecherche entreeRecherche : listeRes.descendingSet()) {
								modeleResultatsRecherche.addElement(entreeRecherche);
							}
							repaint();
						}else {
							lblStatusTexteFichier.setText("Pas de resultats trouves...");
							lblStatusTexteFichier.setVisible(true);
						}
					}
					else {
						lblStatusTexteFichier.setText("Format du fichier incorrect!");
						lblStatusTexteFichier.setVisible(true);
					}
				}
				catch(Exception ex) {
					lblStatusTexteFichier.setText("Erreur durant la recherche...");
					lblStatusTexteFichier.setVisible(true);
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
							if(entreeChoisie.getCheminFichier().contains("IMG_RGB"))
								Runtime.getRuntime().exec("eog " + entreeChoisie.getCheminFichier() + ".jpg");
							if(entreeChoisie.getCheminFichier().contains("IMG_NB"))
								Runtime.getRuntime().exec("eog " + entreeChoisie.getCheminFichier() + ".bmp");
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
