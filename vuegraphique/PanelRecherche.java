package vuegraphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PanelRecherche extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2956031824911671259L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_motRecherches;
	private JTextField textField_motAexclure;
	

	JFileChooser chooser= new JFileChooser();
	
	public PanelRecherche()
	{
		
	JPanel panelFinalInteg = new JPanel();
	this.setBounds(0, 0, 635, 400);
	this.setLayout(null);
	
	JPanel Choix = new JPanel();
	Choix.setBounds(24, 0, 587, 56);
	panelFinalInteg.add(Choix);
	Choix.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type de recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	Choix.setLayout(null);
	
	Box horizontalBox = Box.createHorizontalBox();
	horizontalBox.setBounds(6, 16, 575, 33);
	Choix.add(horizontalBox);
	
	Component horizontalGlue_3 = Box.createHorizontalGlue();
	horizontalBox.add(horizontalGlue_3);
	
	JRadioButtonMenuItem rdbtnmntmTexte_1 = new JRadioButtonMenuItem("Texte");
	rdbtnmntmTexte_1.setSelected(true);
	
	horizontalBox.add(rdbtnmntmTexte_1);
	rdbtnmntmTexte_1.setBackground(UIManager.getColor("Button.background"));
	buttonGroup.add(rdbtnmntmTexte_1);
	
	Component horizontalGlue_1 = Box.createHorizontalGlue();
	horizontalBox.add(horizontalGlue_1);
	
	JRadioButtonMenuItem rdbtnmntmImage = new JRadioButtonMenuItem("Image");
	
	horizontalBox.add(rdbtnmntmImage);
	buttonGroup.add(rdbtnmntmImage);
	
	Component horizontalGlue = Box.createHorizontalGlue();
	horizontalBox.add(horizontalGlue);
	
	JRadioButtonMenuItem rdbtnmntmSon = new JRadioButtonMenuItem("Son");
	
	horizontalBox.add(rdbtnmntmSon);
	buttonGroup.add(rdbtnmntmSon);
	
	Component horizontalGlue_2 = Box.createHorizontalGlue();
	horizontalBox.add(horizontalGlue_2);
	
	JPanel panelTexte = new JPanel();
	panelTexte.setBounds(33, 70, 569, 301);
	panelFinalInteg.add(panelTexte);
	panelTexte.setLayout(null);
	
	Box horizontalBox_2 = Box.createHorizontalBox();
	horizontalBox_2.setBounds(34, 0, 500, 22);
	panelTexte.add(horizontalBox_2);
	
	JTextPane txtpnMotsChercher = new JTextPane();
	horizontalBox_2.add(txtpnMotsChercher);
	txtpnMotsChercher.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtpnMotsChercher.setBackground(SystemColor.menu);
	txtpnMotsChercher.setEditable(false);
	txtpnMotsChercher.setText("Mots \u00E0 chercher");
	
	Component horizontalGlue_6 = Box.createHorizontalGlue();
	horizontalBox_2.add(horizontalGlue_6);
	
	Component horizontalStrut_1 = Box.createHorizontalStrut(70);
	horizontalBox_2.add(horizontalStrut_1);
	
	Component horizontalGlue_7 = Box.createHorizontalGlue();
	horizontalBox_2.add(horizontalGlue_7);
	
	textField_motRecherches = new JTextField();
	textField_motRecherches.setToolTipText("S\u00E9parer les mots par un espace.");
	horizontalBox_2.add(textField_motRecherches);
	textField_motRecherches.setColumns(10);
	
	Box horizontalBox_1 = Box.createHorizontalBox();
	horizontalBox_1.setBounds(34, 40, 500, 22);
	panelTexte.add(horizontalBox_1);
	
	Component horizontalGlue_5 = Box.createHorizontalGlue();
	horizontalBox_1.add(horizontalGlue_5);
	
	JCheckBox chckbxMotsExclure = new JCheckBox("Mots \u00E0 exclure");
	chckbxMotsExclure.setSelected(true);
	chckbxMotsExclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
	chckbxMotsExclure.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(chckbxMotsExclure.isSelected())
				textField_motAexclure.setVisible(true);
			else
				textField_motAexclure.setVisible(false);
		}
	});
	chckbxMotsExclure.setToolTipText("Cocher si vous souhaitez que les r\u00E9sultats ne comportent pas certains mots.");
	horizontalBox_1.add(chckbxMotsExclure);
	
	Component horizontalStrut = Box.createHorizontalStrut(165);
	horizontalBox_1.add(horizontalStrut);
	
	Component horizontalGlue_4 = Box.createHorizontalGlue();
	horizontalBox_1.add(horizontalGlue_4);
	
	textField_motAexclure = new JTextField();
	textField_motAexclure.setToolTipText("S\u00E9parer les mots par un espace.");
	horizontalBox_1.add(textField_motAexclure);
	textField_motAexclure.setColumns(10);
	
	
	Box horizontalBox_3 = Box.createHorizontalBox();
	horizontalBox_3.setBounds(221, 80, 160, 23);
	panelTexte.add(horizontalBox_3);
	
	JButton btnLancerLaRecherche = new JButton("Lancer la recherche");
	horizontalBox_3.add(btnLancerLaRecherche);
	btnLancerLaRecherche.setHorizontalAlignment(SwingConstants.LEFT);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(34, 130, 500, 150);
	panelTexte.add(scrollPane);
	
	JTextPane txtResRecTextes = new JTextPane();
	txtResRecTextes.setEditable(false);
	scrollPane.setViewportView(txtResRecTextes);
	
	
	btnLancerLaRecherche.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			txtResRecTextes.setText("Test Resultat recherche\nFiller text\n1\2\n3\n4\5\n6\n7\n8\n8bis\n9\n10\nToutes ces lignes servent à voir si le scroll fonctionne.\n");
		}
	});
	
	Panel panelImage = new Panel();
	panelImage.setBounds(33, 70, 569, 301);
	panelFinalInteg.add(panelImage);
	panelImage.setLayout(null);
	panelImage.setBackground(SystemColor.menu);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(0, 0, 569, 301);
	panelImage.add(tabbedPane);
	
	JPanel panelimageFile = new JPanel();
	panelimageFile.setToolTipText("Si vous voulez chercher une image que vous poss\u00E9dez d\u00E9j\u00E0.");
	tabbedPane.addTab("Recherche Fichier", null, panelimageFile, null);
	panelimageFile.setLayout(null);
	
	Box horizontalBox_4 = Box.createHorizontalBox();
	horizontalBox_4.setBounds(0, 0, 569, 20);
	panelimageFile.add(horizontalBox_4);
	
	JButton btnFichier_a_chercher = new JButton("Fichier \u00E0 rechercher");
	horizontalBox_4.add(btnFichier_a_chercher);
	
	Component horizontalStrut_2 = Box.createHorizontalStrut(130);
	horizontalBox_4.add(horizontalStrut_2);
	
	JTextPane textPaneImageFileSelect = new JTextPane();
	textPaneImageFileSelect.setEditable(false);
	horizontalBox_4.add(textPaneImageFileSelect);
	
	JButton btnLancerRechercheImageFile = new JButton("Lancer la recherche");
	
	btnLancerRechercheImageFile.setBounds(30, 70, 160, 30);
	panelimageFile.add(btnLancerRechercheImageFile);
	
	JPanel ResultatRechercheImageFile = new JPanel();
	ResultatRechercheImageFile.setBackground(SystemColor.menu);
	ResultatRechercheImageFile.setBounds(12, 105, 540, 160);
	panelimageFile.add(ResultatRechercheImageFile);
	ResultatRechercheImageFile.setLayout(null);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(0, 0, 270, 160);
	ResultatRechercheImageFile.add(scrollPane_1);
	
	JTextPane textPaneResRechercheFile = new JTextPane();
	scrollPane_1.setViewportView(textPaneResRechercheFile);
	textPaneResRechercheFile.setEditable(false);
	textPaneResRechercheFile.setText("Test Resultat recherche\r\nFiller text\r\n1\r\n2\r\n3\r\n4\r\n6\r\n7\r\n8\r\n8bis\r\n9\r\n10\r\nToutes ces lignes servent \u00E0 voir si le scroll fonctionne.");
	
	JLabel lblNewLabelAffImage = new JLabel("");
	lblNewLabelAffImage.setBounds(310, 0, 200, 160);
	ResultatRechercheImageFile.add(lblNewLabelAffImage);
	
	lblNewLabelAffImage.setForeground(Color.DARK_GRAY);
	lblNewLabelAffImage.setBackground(Color.DARK_GRAY);
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File("questions.jpg"));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	Image dimg = img.getScaledInstance(lblNewLabelAffImage.getWidth(), lblNewLabelAffImage.getHeight(),
	        Image.SCALE_SMOOTH);
	ImageIcon imageIcon = new ImageIcon(dimg);
	
	lblNewLabelAffImage.setIcon(imageIcon);
	
	JPanel panelImageCouleurs = new JPanel();
	panelImageCouleurs.setToolTipText("Si vous cherchez une image bas\u00E9e sur sa couleur dominante.");
	tabbedPane.addTab("Recherche par couleurs", null, panelImageCouleurs, null);
	panelImageCouleurs.setLayout(null);
	
	Box horizontalBox_5 = Box.createHorizontalBox();
	horizontalBox_5.setBounds(32, 0, 500, 22);
	panelImageCouleurs.add(horizontalBox_5);
	
	JTextPane txtpnCouleursChercher = new JTextPane();
	txtpnCouleursChercher.setAlignmentX(Component.LEFT_ALIGNMENT);
	txtpnCouleursChercher.setText("Couleurs \u00E0 chercher");
	txtpnCouleursChercher.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtpnCouleursChercher.setEditable(false);
	txtpnCouleursChercher.setBackground(SystemColor.menu);
	horizontalBox_5.add(txtpnCouleursChercher);
	
	Component horizontalStrut_4 = Box.createHorizontalStrut(60);
	horizontalBox_5.add(horizontalStrut_4);
	
	JButton btnCouleurRecherchees = new JButton("Choix...");
	
	horizontalBox_5.add(btnCouleurRecherchees);
	
	Component horizontalStrut_5 = Box.createHorizontalStrut(210);
	horizontalBox_5.add(horizontalStrut_5);
	
	Box horizontalBox_6 = Box.createHorizontalBox();
	horizontalBox_6.setBounds(32, 40, 500, 22);
	panelImageCouleurs.add(horizontalBox_6);
	
	JCheckBox chckbxCouleursExclure = new JCheckBox("Couleurs \u00E0 exclure");
	
	chckbxCouleursExclure.setToolTipText("Cocher si vous souhaitez que les r\u00E9sultats ne comportent pas certaines couleurs.");
	chckbxCouleursExclure.setSelected(true);
	chckbxCouleursExclure.setFont(new Font("Tahoma", Font.PLAIN, 14));
	horizontalBox_6.add(chckbxCouleursExclure);
	
	Component horizontalStrut_3 = Box.createHorizontalStrut(80);
	horizontalBox_6.add(horizontalStrut_3);
	
	JButton btnCouleursInterdites = new JButton("Choix...");
	
	horizontalBox_6.add(btnCouleursInterdites);
	
	Box horizontalBox_7 = Box.createHorizontalBox();
	horizontalBox_7.setBounds(34, 70, 160, 23);
	panelImageCouleurs.add(horizontalBox_7);
	
	JButton button = new JButton("Lancer la recherche");
	
	button.setHorizontalAlignment(SwingConstants.LEFT);
	horizontalBox_7.add(button);
	
	JPanel panelResRecImageCoul = new JPanel();
	panelResRecImageCoul.setVisible(false);
	panelResRecImageCoul.setBounds(35, 100, 500, 150);
	panelImageCouleurs.add(panelResRecImageCoul);
	panelResRecImageCoul.setLayout(null);
	
	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(0, 0, 250, 150);
	panelResRecImageCoul.add(scrollPane_2);
	
	JTextPane textPaneResRecImageCoul = new JTextPane();
	textPaneResRecImageCoul.setEditable(false);
	scrollPane_2.setViewportView(textPaneResRecImageCoul);
	
	JLabel lblAffImageCouleur = new JLabel("");
	lblAffImageCouleur.setBounds(260, 0, 240, 150);
	
	BufferedImage img2 = null;
	try {
	    img2 = ImageIO.read(new File("questions.jpg"));
	} catch (IOException e2) {
	    e2.printStackTrace();
	}
	Image dimg2 = img2.getScaledInstance(lblAffImageCouleur.getWidth(), lblAffImageCouleur.getHeight(),
	        Image.SCALE_SMOOTH);
	ImageIcon imageIcon2 = new ImageIcon(dimg2);
	
	lblAffImageCouleur.setIcon(imageIcon2);
	panelResRecImageCoul.add(lblAffImageCouleur);
	
	JPanel panelSon = new JPanel();
	panelSon.setBounds(33, 70, 569, 301);
	panelFinalInteg.add(panelSon);
	panelSon.setLayout(null);
	
	Box horizontalBox_8 = Box.createHorizontalBox();
	horizontalBox_8.setBounds(84, 130, 400, 40);
	panelSon.add(horizontalBox_8);
	
	JTextPane textPaneplaceholder = new JTextPane();
	horizontalBox_8.add(textPaneplaceholder);
	textPaneplaceholder.setText("PLACEHOLDER");
	textPaneplaceholder.setFont(new Font("Tahoma", Font.PLAIN, 18));
	textPaneplaceholder.setBackground(SystemColor.textHighlight);
	textPaneplaceholder.setEditable(false);
	
	JButton btnNewButton = new JButton("New button");
	horizontalBox_8.add(btnNewButton);
	
	ResultatRechercheImageFile.setVisible(false);
	btnFichier_a_chercher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			

			int choice = chooser.showOpenDialog(chooser);

			if (choice != JFileChooser.APPROVE_OPTION) return;

			File chosenFile = chooser.getSelectedFile();
			
			textPaneImageFileSelect.setText(chosenFile.getName());

		}
	});
	
	btnLancerRechercheImageFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			ResultatRechercheImageFile.setVisible(true);
		}
	});
	
	chckbxCouleursExclure.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chckbxCouleursExclure.isSelected())
				btnCouleursInterdites.setVisible(true);
			else
				btnCouleursInterdites.setVisible(false);
				
		}
	});
	
	btnCouleursInterdites.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ColorChooserDemo test = new ColorChooserDemo();
			if(!test.isShowing())
				test.createAndShowGUI();
		}
	});
	
	btnCouleurRecherchees.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {	
			ColorChooserDemo test2 = new ColorChooserDemo();
			if(!test2.isShowing())
				test2.createAndShowGUI();
			
		}
	});
	
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panelResRecImageCoul.setVisible(true);
			textPaneResRecImageCoul.setText("Test Resultat recherche\nFiller text\n1\2\n3\n4\5\n6\n7\n8\n8bis\n9\n10\nToutes ces lignes servent à voir si le scroll fonctionne.\n");
		}
	});
	
	rdbtnmntmTexte_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			panelImage.setVisible(false);
			panelSon.setVisible(false);
			panelTexte.setVisible(true);
		}
	});
	
	rdbtnmntmImage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panelTexte.setVisible(false);
			panelSon.setVisible(false);
			panelImage.setVisible(true);
			
		}
	});
	
	rdbtnmntmSon.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panelTexte.setVisible(false);
			panelImage.setVisible(false);
			panelSon.setVisible(true);
		}
	});
	
	if(!panelimageFile.isShowing())
		ResultatRechercheImageFile.setVisible(false);
	
	this.add(Choix);
	this.add(panelTexte);
	this.add(panelImage);
	this.add(panelSon);
	}
}
