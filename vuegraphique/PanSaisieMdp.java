package vuegraphique;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

import control.ControlSeConnecter;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class PanSaisieMdp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665819871378370090L;
	
	private JTextField texteSaisieMdp;
	private ControlSeConnecter controlSeConnecter;
	private JLabel statusMdp;
	private Box boxSaisieMdp;
	private JFrame frame;

	/**
	 * Create the panel.
	 */
	public PanSaisieMdp(JFrame frame, ControlSeConnecter controlSeConnecter) {	
		
		this.frame = frame;
		this.controlSeConnecter = controlSeConnecter;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Box boxMiseEnPage = Box.createVerticalBox();
		boxMiseEnPage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(boxMiseEnPage);
		JLabel titre = new JLabel("Connexion administrateur");
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(titre);
		titre.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		Component verticalGlue = Box.createVerticalGlue();
		boxMiseEnPage.add(verticalGlue);
		
		boxSaisieMdp = Box.createHorizontalBox();
		boxSaisieMdp.setAlignmentY(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(boxSaisieMdp);
		
		JLabel labelSaisieMdp = new JLabel("Saisissez le mot de passe");
		boxSaisieMdp.add(labelSaisieMdp);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		boxSaisieMdp.add(horizontalStrut);
		
		texteSaisieMdp = new JTextField();
		texteSaisieMdp.setMaximumSize(new Dimension(80, 25));
		boxSaisieMdp.add(texteSaisieMdp);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		boxMiseEnPage.add(verticalGlue_1);
		
		statusMdp = new JLabel("\"Mot de passe incorrect!\"");
		statusMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(statusMdp);
		statusMdp.setVisible(false);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		boxMiseEnPage.add(verticalGlue_2);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(btnValider);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		boxMiseEnPage.add(verticalStrut_3);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saisieMdp();
			}
		});
		
		this.ouvertureFenetre();

	}

	public void ouvertureFenetre() {
		texteSaisieMdp.setText("");
		statusMdp.setVisible(false);
		this.frame.setVisible(true);
	}
	
	private void saisieMdp() {
		String mdp = texteSaisieMdp.getText();
		boolean mdpOK = controlSeConnecter.seConnecter(mdp);
		if(!mdpOK) {
			statusMdp.setVisible(true);
		}
		else
			this.frame.setVisible(false);
	}
	
}
