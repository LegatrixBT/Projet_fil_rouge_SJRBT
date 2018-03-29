package vuegraphique;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlSeConnecter;
import java.awt.BorderLayout;

public class FrameConnexionAdmin extends JFrame {

	
	private static final long serialVersionUID = -189353213730751669L;
	private JPanel contentPane;
	private PanSaisieMdp panSaisieMdp;

	/**
	 * Create the frame.
	 */
	public FrameConnexionAdmin(ControlSeConnecter controlSeConnecter) {
		this.setTitle("Connexion administrateur");
		this.setSize(300, 200);
		panSaisieMdp = new PanSaisieMdp(this, controlSeConnecter);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panSaisieMdp);
	}
	
	public void ouvertureFenetre() {
		panSaisieMdp.ouvertureFenetre();
	}

}
