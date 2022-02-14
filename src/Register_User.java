import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register_User extends JFrame {

	private static JFrame frame;
	private JTextField username;
	private JTextField password;
	
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;
	private JTable table;
	
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
					Register_User window = new Register_User();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register_User() {
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
		frame.setVisible(true);
		
		cnx = Connexion.connect();
		
		
		
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sql = "SELECT password from user where username = ?";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, username.getText().toString());
					resultat = prepared.executeQuery();
					
					if(resultat.next()) {
						
						String pass = resultat.getString("password");
						password.setText(pass);
						
					}
					
				}
				catch(SQLException s) {
					
					s.printStackTrace();

				}
			}
		});
		username.setBounds(308, 158, 181, 32);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(308, 244, 181, 32);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(208, 161, 108, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(208, 246, 92, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnConnect = new JButton("ADD");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnect.setBackground(Color.GREEN);
		btnConnect.setBounds(208, 329, 89, 34);
		frame.getContentPane().add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			

			
			
			public void actionPerformed(ActionEvent e) {		
	
				String sql = "insert into user (username, password) values (?, ?)";
				
			    try {
			    	
			    	prepared = cnx.prepareStatement(sql);
			    	prepared.setString(1, username.getText().toString());
			    	prepared.setString(2, password.getText().toString());
			    	prepared.execute();
			    	
			    	JOptionPane.showMessageDialog(null,"utilisateur ajoutée avec succés ");
			    	UpdateTable();
			    	
			    }catch (SQLException s) {
			    	
			    	s.printStackTrace();
			    }
	
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 1000, 53);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	
		JLabel lblNewLabel_2 = new JLabel("GESTION DES ETUDIANTS");
		lblNewLabel_2.setBounds(315, 5, 369, 34);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Administrateur ad = new Administrateur();
				ad.setVisible(true);
	
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon(Register_User.class.getResource("/images/icons8_Back_To_25px.png")));
		lblNewLabel_5.setBounds(47, 5, 43, 43);
		panel.add(lblNewLabel_5);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 51, 200, 402);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Register_User.class.getResource("/images/icons8_User_Shield_100px.png")));
		panel_1.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("REGISTER_USER");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel_4.setBounds(241, 74, 243, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
				
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "DELETE from user where username = ? and password = ?";
				
				   try {
				    	
				    	prepared = cnx.prepareStatement(sql);
				    	prepared.setString(1, username.getText().toString());
				    	prepared.setString(2, password.getText().toString());
				    	prepared.execute();
				    	
				    	JOptionPane.showMessageDialog(null,"utilisateur supprimé avec succés ");
				    	username.setText("");
				    	password.setText("");
				    	UpdateTable();
				    	
				    }catch (SQLException s) {
				    	
				    	s.printStackTrace();
				    }
				
				
			}
		});
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();	
				String user_old = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "UPDATE user set username = ? , password = ?    where username = '"+user_old+"' ";
				
				   try {
				    	
				    	prepared = cnx.prepareStatement(sql);
				    	prepared.setString(1, username.getText().toString());
				    	prepared.setString(2, password.getText().toString());
				    	prepared.execute();
				    	
				    	JOptionPane.showMessageDialog(null,"utilisateur modifié avec succés ");
				    	username.setText("");
				    	password.setText("");
				    	UpdateTable();
				    	
				    }catch (SQLException s) {
				    	
				    	s.printStackTrace();
				    }

			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBounds(308, 329, 89, 34);
		frame.getContentPane().add(btnNewButton);

		
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(407, 329, 92, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 104, 452, 338);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
		    	
				String user = table.getModel().getValueAt(ligne, 0).toString();
				String pass = table.getModel().getValueAt(ligne, 1).toString();
				
				username.setText(user);
				password.setText(pass);

				
			}
		});
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Actualiser");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateTable();
			}
		});
		btnNewButton_2.setBackground(new Color(224, 255, 255));
		btnNewButton_2.setBounds(549, 70, 116, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
		
		public void UpdateTable() {
			
			String sql = "select username,password from user";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();
		    	table.setModel(DbUtils.resultSetToTableModel(resultat));
		    	
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}
}