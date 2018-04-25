package vuegraphique;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import control.ControlSeConnecter;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanSaisieMdp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665819871378370090L;
	
	private ControlSeConnecter controlSeConnecter;
	private JLabel statusMdp;
	private Box boxSaisieMdp;
	private JFrame frame;
	private JPasswordField texteSaisieMdp;

	/**
	 * Create the panel.
	 */
	public PanSaisieMdp(JFrame frame, ControlSeConnecter controlSeConnecter) {	
		
		this.frame = frame;
		this.controlSeConnecter = controlSeConnecter;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
		
		texteSaisieMdp = new JPasswordField();
		texteSaisieMdp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					saisieMdp();
			}
		});
		texteSaisieMdp.setMaximumSize(new Dimension(80, 25));
		boxSaisieMdp.add(texteSaisieMdp);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		boxMiseEnPage.add(verticalStrut);
		
		statusMdp = new JLabel("\"Mot de passe incorrect!\"");
		statusMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(statusMdp);
		statusMdp.setVisible(false);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		boxMiseEnPage.add(verticalGlue_2);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxMiseEnPage.add(btnValider);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		boxMiseEnPage.add(verticalGlue_3);
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
		String mdp = new String(texteSaisieMdp.getPassword());
		boolean mdpOK = controlSeConnecter.seConnecter(mdp);
		if(!mdpOK) {
			statusMdp.setVisible(true);
		}
		else
			this.frame.setVisible(false);
	}
	
}
