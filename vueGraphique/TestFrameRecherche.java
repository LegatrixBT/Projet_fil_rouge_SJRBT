package vueGraphique;

import javax.swing.JFrame;

public class TestFrameRecherche extends JFrame {

	PanelRecherche pan = new PanelRecherche();
	public TestFrameRecherche()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		this.setSize(641, 429);
		setTitle("Recherche");
		getContentPane().setLayout(null);
		setContentPane(pan);
		setVisible(true);
	}
}
