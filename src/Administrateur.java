import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Administrateur extends JFrame  {

	private JFrame frame;
	
	
	void fermer() {
		
		dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrateur window = new Administrateur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Administrateur() {
		getContentPane().setBackground(new Color(253, 245, 230));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrateur.class.getResource("/images/supdeco.png")));
		getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(250, 250, 210));
		panel.setBounds(0, 0, 1000, 50);
		getContentPane().add(panel);
		
		Label label = new Label("Bienvenu(e) cher(ère) Admin");
		label.setBounds(0, 56, 267, 30);
		getContentPane().add(label);
		label.setBackground(SystemColor.window);
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		initialize();
		panel.setLayout(null);
		
		JLabel label1 = new JLabel("GESTION DES ETUDIANTS");
		label1.setBounds(315, 5, 369, 34);
		panel.add(label1);
		label1.setForeground(new Color(0, 0, 0));
		label1.setFont(new Font("Tahoma", Font.BOLD, 28));
		label1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_6 = new JLabel("Se déconnecter");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Register log = new Register();
		    	log.setVisible(true);
		    	log.setLocationRelativeTo(null);
		    	fermer();
			}
		});
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setIcon(new ImageIcon(Administrateur.class.getResource("/images/icons8_Back_To_25px.png")));
		lblNewLabel_6.setBounds(10, 5, 154, 39);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register_User use = new Register_User();
				use.setVisible(true);
				use.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton.setBackground(new Color(253, 245, 230));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\user(1).jpg"));
		btnNewButton.setBounds(57, 112, 155, 93);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Gestion_Etudiant use = new Gestion_Etudiant();
				use.setVisible(true);
				use.setLocationRelativeTo(null);
				fermer();
			}
		});
		
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\etud(1).png"));
		btnNewButton_1.setBounds(731, 297, 155, 93);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Gestion_Filière use = new Gestion_Filière();
				use.setVisible(true);
				use.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\filière.png"));
		btnNewButton_2.setBounds(731, 112, 155, 93);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Gestion_Absence use = new Gestion_Absence();
				use.setVisible(true);
				use.setLocationRelativeTo(null);
				fermer();
			
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\retard.png"));
		btnNewButton_3.setBounds(57, 297, 155, 93);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Gestion des utilisateurs");
		lblNewLabel.setBounds(87, 218, 139, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des étudiants");
		lblNewLabel_1.setBounds(752, 401, 127, 30);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des absences");
		lblNewLabel_2.setBounds(87, 392, 111, 24);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gestion des filières");
		lblNewLabel_3.setBounds(752, 222, 127, 19);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Administrateur.class.getResource("/images/supdeco.png")));
		lblNewLabel_4.setBounds(365, 146, 242, 199);
		getContentPane().add(lblNewLabel_4);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 947, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
