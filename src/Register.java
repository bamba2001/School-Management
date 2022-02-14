import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Register extends JFrame  {

	JFrame frame;
	private JTextField username;
	private JPasswordField password;
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;
	
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
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(253, 245, 230));
		frame.setBackground(new Color(0, 191, 255));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/images/supdeco.png")));
		frame.setBounds(100, 100, 997, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cnx = Connexion.connect();
		
		//Initialize the contents of the panel Register
		
		username = new JTextField();
		username.setBounds(633, 209, 181, 32);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(499, 212, 108, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(499, 263, 92, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnConnect = new JButton("Se Connecter");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnect.setBackground(new Color(128, 128, 128));
		btnConnect.setBounds(637, 323, 177, 32);
		frame.getContentPane().add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String user = username.getText().toString(); 
				String pass = password.getText().toString(); 
				
	
				String sql = "select username, password from user";
				
			    try {
			    	
			    	prepared = cnx.prepareStatement(sql);
			    	resultat = prepared.executeQuery();
			    	
			    	if (user.equals("") || pass.equals("")) {
			    		
				    	JOptionPane.showMessageDialog(null," SVP, remplissez les informations");

			    	}
			    	else {
			    		
				    	while(resultat.next()) {
				    		
				    		String username1 = resultat.getString("username");
				    		String password1 = resultat.getString("password");
				    		
				    		
				    		if(username1.equals(user) && !password1.equals(pass)) {
				    			
						    	JOptionPane.showMessageDialog(null,"Mot de passe incorrect ! ");
						    	
				    		}
				    		
				    		if(!username1.equals(user) && password1.equals(pass)) {
				    			
						    	JOptionPane.showMessageDialog(null,"Login incorrect ! ");
						    	
				    		}
				    		
				    		
				    		
				    		if(username1.equals(user) && password1.equals(pass)) {
				    			
						    	JOptionPane.showMessageDialog(null,"Connexion réussie");
						    	
						    	Administrateur admin = new Administrateur();
						    	admin.setVisible(true);
						    	admin.setLocationRelativeTo(null);
						    	admin.setBounds(100, 100, 997, 492);
						    	fermer();
						    	
				    		}
				    		
				    	}
			    	}

			    }catch (SQLException s) {
			    	
			    	s.printStackTrace();
			    }
	
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1000, 53);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	
		JLabel lblNewLabel_2 = new JLabel("GESTION DES ETUDIANTS");
		lblNewLabel_2.setBounds(315, 5, 369, 34);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 51, 385, 402);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 385, 402);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Register.class.getResource("/images/icons8_Male_User_100px.png")));
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("LOGIN");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_4.setBounds(694, 64, 101, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
		password = new JPasswordField();
		password.setBounds(633, 261, 181, 32);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel_5 = new JLabel("Mot de passe oublié ?");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				IdentificationPass pass = new IdentificationPass();
				pass.setVisible(true);
				pass.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_5.setBounds(684, 298, 139, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Register.class.getResource("/images/icons8_Male_User_100px.png")));
		lblNewLabel_6.setBounds(684, 107, 92, 91);
		frame.getContentPane().add(lblNewLabel_6);
		
		
	}
}
