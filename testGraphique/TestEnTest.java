package testGraphique;

public class TestEnTest {

	
	
	
	// ajout benoit

	panelOngletRechercheAvancee = new PanelOngletRechercheAvancee();
	panelOngletRechercheAvancee.setVisible(false);
	
	this.add(panelOngletRechercheAvancee, BorderLayout.EAST);
	this.setVisible(true);
	Component horizontalGlue = Box.createHorizontalGlue();
	menuBar.add(horizontalGlue);

	JMenu mnRechercheAvancee = new JMenu("Recherche Avancee");
	menuBar.add(mnRechercheAvancee);
	
	JMenuItem mntmRechercheavancee = new JMenuItem("RechercheAvancee");
	mnRechercheAvancee.add(mntmRechercheavancee);
	
		mntmRechercheavancee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelOngletRechercheAvancee.setVisible(true);
				afficherOngletRechercheAvancee();
			}
		});
		
		mntmRechercheavancee.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmRechercheavancee.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		
		public void afficherOngletRechercheAvancee() {
			panelOngletRechercheAvancee.setVisible(true);
		}
		
		public void cacherOngletRechercheAvancee() {
			panelOngletRechercheAvancee.setVisible(false);
		}

}
