import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class IdentificationPass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationPass frame = new IdentificationPass();
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
	public IdentificationPass() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = Connexion.connect();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String username = textField.getText().toString();
				String sql = "select password from user where username = ?";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, username);
					resultat = prepared.executeQuery();
					
					if(resultat.next()) {
						
					
					String pass = resultat.getString("password");
					String pass1 = pass.substring(0,2);
					
					textField_1.setText("Les 2 premiers lettres du password sont: "+pass1+"*****");
				}
					
				}
				catch (SQLException s)  {
					
					s.printStackTrace();
					
					
				}
			}
		
			
		});
		textField.setBounds(294, 61, 228, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		
		textField_1.setBounds(294, 141, 324, 82);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(107, 61, 89, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(107, 164, 137, 41);
		contentPane.add(lblNewLabel_1);
	}

}
