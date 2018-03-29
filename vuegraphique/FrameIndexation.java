package vuegraphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import control.ControlLancerIndexation;

import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameIndexation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5108070177044637166L;
	private JPanel contentPane;
	private ControlLancerIndexation controlLancerIndexation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameIndexation frame = new FrameIndexation();
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
	public FrameIndexation() {
		
		controlLancerIndexation = new ControlLancerIndexation();
		
		this.setSize(300,200);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Box boxTitre = Box.createVerticalBox();
		contentPane.add(boxTitre, BorderLayout.NORTH);
		
		JLabel lblSaissisezLeType = new JLabel("Saissisez le type de fichier \u00E0 indexer");
		lblSaissisezLeType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaissisezLeType.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxTitre.add(lblSaissisezLeType);
		
		Box boxBoutons = Box.createVerticalBox();
		contentPane.add(boxBoutons, BorderLayout.CENTER);
		
		Component verticalGlue = Box.createVerticalGlue();
		boxBoutons.add(verticalGlue);
		
		JButton btnImage = new JButton("Images");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlLancerIndexation.lancerIndexationImage();
			}
		});
		btnImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxBoutons.add(btnImage);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		boxBoutons.add(verticalGlue_1);
		
		JButton btnTexte = new JButton("Textes");
		btnTexte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlLancerIndexation.lancerIndexationTexte();
			}
		});
		btnTexte.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxBoutons.add(btnTexte);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		boxBoutons.add(verticalGlue_2);
		
		JButton btnSon = new JButton("Sons");
		btnSon.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxBoutons.add(btnSon);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		boxBoutons.add(verticalGlue_3);
		
		JButton btnTout = new JButton("Tout");
		btnTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlLancerIndexation.lancerIndexationImage();
				controlLancerIndexation.lancerIndexationTexte();
			}
		});
		btnTout.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxBoutons.add(btnTout);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		boxBoutons.add(verticalGlue_4);
	}

}