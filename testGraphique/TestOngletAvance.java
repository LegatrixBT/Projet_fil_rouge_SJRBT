package testGraphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControlMoteurActifs;
import vuegraphique.PanelOngletRechercheAvancee;

public class TestOngletAvance extends JFrame {

	private JPanel contentPane;
	ControlMoteurActifs controlMoteurActifs = new ControlMoteurActifs();
	PanelOngletRechercheAvancee panelOngletRechercheAvancee = new PanelOngletRechercheAvancee(); 

	/**
	 * Create the frame.
	 */
	public TestOngletAvance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
        this.add(panelOngletRechercheAvancee);
        this.setVisible(true);
		
		
	}
	
    public static void main(String args[]) {
   
        new TestOngletAvance();
    }

}
