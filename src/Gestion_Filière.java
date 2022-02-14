import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;


public class Gestion_Filière extends JFrame {

	private JFrame frame;
	
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;
	private JTable table;
	private JTextField Nom_Filière;
	@SuppressWarnings("rawtypes")
	private JComboBox comboType;
	
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
					Gestion_Filière window = new Gestion_Filière();
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
	public Gestion_Filière() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
		JButton btnConnect = new JButton("ADD");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnect.setBackground(Color.GREEN);
		btnConnect.setBounds(106, 397, 72, 34);
		frame.getContentPane().add(btnConnect);
		
		comboType = new JComboBox();
		comboType.setModel(new DefaultComboBoxModel(new String[] {"Selectionnez", "Programmation mobile","Programmation web","Programmation " }));
		comboType.setBounds(228, 230, 243, 34);
		frame.getContentPane().add(comboType);
		
		btnConnect.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {	
				
				String nomfil = Nom_Filière.getText().toString();
				String type = comboType.getSelectedItem().toString();

				
				
				String sql = "insert into filière (libelle,type) values (?,?)";
				
			    try {
			    	
			    	if(Nom_Filière.getText().equals("") || comboType.getSelectedItem().equals("Selectionnez") ) {
			    		
			    		if(Nom_Filière.getText().equals("") && !comboType.getSelectedItem().equals("Selectionnez"))  {
			    			
			    			JOptionPane.showMessageDialog(null, "Tapez le nom de la filière");
			    		}
			    		
			    		if(!Nom_Filière.getText().equals("") && comboType.getSelectedItem().equals("Selectionnez"))  {
			    			
			    			JOptionPane.showMessageDialog(null, "Selectionnez le type de la filière");
			    		}
			    					    	
			    	} else {
			    		
			    		
			    		prepared = cnx.prepareStatement(sql);
				    	prepared.setString(1, nomfil);
				    	prepared.setString(2, type);
				    	prepared.execute();
				    	
				    	Nom_Filière.setText("");
				    	comboType.setSelectedItem("Selectionnez");
				    	

				    	JOptionPane.showMessageDialog(null,"Filère  ajoutée avec succés ");
				    	UpdateTable();
			    		
			    	}
			    	
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
		lblNewLabel_5.setIcon(new ImageIcon(Gestion_Filière.class.getResource("/images/icons8_Back_To_25px.png")));
		lblNewLabel_5.setBounds(47, 5, 43, 43);
		panel.add(lblNewLabel_5);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 51, 100, 402);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Gestion_Filière.class.getResource("/images/icons8_User_Shield_100px.png")));
		panel_1.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion_Filière");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.BOLD, 22));
		lblNewLabel_4.setBounds(239, 58, 243, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if(ligne == -1) {
					
			    	JOptionPane.showMessageDialog(null,"Selectionnez une filière");

				} else {
					
					int a = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cette filière ?", "Supprimer filière", JOptionPane.YES_NO_OPTION);
					
					String id = table.getModel().getValueAt(ligne, 0).toString();					
					
					String sql = "DELETE from filière where id_filière = '"+id+"'";
						
				    try {
						    	
							    prepared = cnx.prepareStatement(sql);
							    
							    if(a==0) {
							    	 prepared.execute();
								     JOptionPane.showMessageDialog(null,"Filière supprimée avec succés ");
							    }
							   
						    	UpdateTable();
						    	
						    }catch (SQLException s) {
						    	
						    	s.printStackTrace();
						    }
					}		
				
				}
					
				}
							
		);
		
		
		  JButton btnNewButton = new JButton("Update");
		  btnNewButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  
				int ligne = table.getSelectedRow();
				
				if (ligne == -1 ) {
			    	JOptionPane.showMessageDialog(null,"Selectionnez une filière");
					
				} else {
					String id = table.getModel().getValueAt(ligne, 0).toString();					
					String sql = "UPDATE filière set libelle = ? , type = ? where id_filière = '"+id+ "'";
					  
					try {
							  
							    prepared = cnx.prepareStatement(sql);
						    	prepared.setString(1, Nom_Filière.getText().toString());
						    	prepared.setString(2, comboType.getSelectedItem().toString());
						    	prepared.execute();
						    	JOptionPane.showMessageDialog(null,"Filière modifié avec succés ");
						    	UpdateTable();
						    	
						    	Nom_Filière.setText("");
						    	comboType.setSelectedItem("Sélectionnez");

						  }catch (SQLException s) {
						  
						  s.printStackTrace(); }

				}
				
					  
		  } });
		  
		  btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnNewButton.setBackground(new Color(30, 144, 255));
		  btnNewButton.setBounds(212, 397, 92, 34);
		  frame.getContentPane().add(btnNewButton);
		  
		 		
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(342, 397, 92, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(481, 104, 490, 338);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
		    	
				String fil = table.getModel().getValueAt(ligne, 1).toString();
				String typ = table.getModel().getValueAt(ligne, 2).toString();

				Nom_Filière.setText(fil);
		    	comboType.setSelectedItem(typ);
				

				
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
		
		Nom_Filière = new JTextField();
		Nom_Filière.setBounds(228, 139, 243, 34);
		frame.getContentPane().add(Nom_Filière);
		Nom_Filière.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Nom Filière :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(106, 143, 112, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(146, 228, 72, 34);
		frame.getContentPane().add(lblNewLabel_1);
	}
		
		public void UpdateTable() {
			
			String sql = "select * from filière";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();
		    	table.setModel(DbUtils.resultSetToTableModel(resultat));
		    	
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}
}