package vuegraphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControlChangerMdpAdmin;

public class FrameChangerMotDePasse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5067339035528824695L;

	private ControlChangerMdpAdmin controlChangerMdpAdmin;
	
	private JPanel contentPane;
	private JTextField txtMdpActuel;
	private JTextField txtNouveauMdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameChangerMotDePasse frame = new FrameChangerMotDePasse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameChangerMotDePasse() {
		
		controlChangerMdpAdmin = new ControlChangerMdpAdmin();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Changer le mot de passe");
		this.setSize(new Dimension(300, 150));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		Box boxMiseEnPage = Box.createVerticalBox();
		boxMiseEnPage.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(boxMiseEnPage);
		
		Box boxMotDePasseActuel = Box.createHorizontalBox();
		boxMiseEnPage.add(boxMotDePasseActuel);
		
		JLabel lblMdpActuel = new JLabel("Saissisez le mot de passe actuel :");
		lblMdpActuel.setFont(new Font("Calibri", Font.PLAIN, 14));
		boxMotDePasseActuel.add(lblMdpActuel);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		boxMotDePasseActuel.add(horizontalStrut);
		
		txtMdpActuel = new JTextField();
		boxMotDePasseActuel.add(txtMdpActuel);
		txtMdpActuel.setMaximumSize(new Dimension(120,20));
		
		Box boxNouveauMdp = Box.createHorizontalBox();
		boxMiseEnPage.add(boxNouveauMdp);
		
		JLabel lblSaissisezLeNouveau = new JLabel("Saissisez le nouveau mot de passe :");
		lblSaissisezLeNouveau.setFont(new Font("Calibri", Font.PLAIN, 14));
		boxNouveauMdp.add(lblSaissisezLeNouveau);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(17);
		boxNouveauMdp.add(horizontalStrut_1);
		
		txtNouveauMdp = new JTextField();
		txtNouveauMdp.setMaximumSize(new Dimension(120, 20));
		boxNouveauMdp.add(txtNouveauMdp);
		
		Box boxStatus = Box.createHorizontalBox();
		boxMiseEnPage.add(boxStatus);
		
		JLabel lblStatus = new JLabel("Mot de passe incorrect!");
		lblStatus.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblStatus.setVisible(false);
		boxStatus.add(lblStatus);
		
		Box boxValider = Box.createHorizontalBox();
		boxMiseEnPage.add(boxValider);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mdpActuel = txtMdpActuel.getText();
				String nouvMdp = txtNouveauMdp.getText();
				boolean mdpOK = controlChangerMdpAdmin.verifierMdp(mdpActuel);
				if(mdpOK) {
					controlChangerMdpAdmin.changerMdp(nouvMdp);
				}
				else {
					lblStatus.setVisible(true);
				}
			}
		});
		boxValider.add(btnValider);
	}
	
	public void saisieMdp() {
		txtMdpActuel.setText("");
		txtNouveauMdp.setText("");
		this.setVisible(true);
	}

}
