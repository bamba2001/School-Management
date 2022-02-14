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
import com.toedter.calendar.JDateChooser;


public class Gestion_Absence extends JFrame {

	private JFrame frame;
	
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboType;
	private JComboBox comboBoxRaison;
	private JDateChooser dateChooser;
	
	
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
					Gestion_Absence window = new Gestion_Absence();
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
	public Gestion_Absence() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
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
		
		JButton btnConnect = new JButton("ADD");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnect.setBackground(Color.GREEN);
		btnConnect.setBounds(106, 397, 72, 34);
		frame.getContentPane().add(btnConnect);
		
		comboType = new JComboBox();
		comboType.setBounds(228, 139, 243, 34);
		frame.getContentPane().add(comboType);
		remplirComboBox(); 
		
		btnConnect.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {	
				
				String nometud = comboType.getSelectedItem().toString();
				String dater = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison = comboBoxRaison.getSelectedItem().toString();

				
				
				String sql = "insert into absence (nom_etud,date,raison) values (?,?,?)";
				
			   try {
			    	
			    	if(comboType.getSelectedItem().equals("Selectionnez") || dateChooser.equals("") || comboBoxRaison.getSelectedItem().equals("Selectionnez") ) {
			    					    			
			    			JOptionPane.showMessageDialog(null, "Remplissez tous les champs");  		
			    					    	
			    	} else {
			    		
			    		
			    		prepared = cnx.prepareStatement(sql);
				    	prepared.setString(1, nometud);
				    	prepared.setString(2, dater);
				    	prepared.setString(3, raison);

				    	prepared.execute();
				    	
				    	comboType.setSelectedItem("Selectionnez");
				    	comboBoxRaison.setSelectedItem("Selectionnez");
				    	dateChooser.setDateFormatString("");
				    	

				    	JOptionPane.showMessageDialog(null,"Absence ajoutée avec succés ");
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
		lblNewLabel_5.setIcon(new ImageIcon(Gestion_Absence.class.getResource("/images/icons8_Back_To_25px.png")));
		lblNewLabel_5.setBounds(47, 5, 43, 43);
		panel.add(lblNewLabel_5);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 51, 100, 402);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Gestion_Absence.class.getResource("/images/icons8_User_Shield_100px.png")));
		panel_1.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion_Absence");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.BOLD, 22));
		lblNewLabel_4.setBounds(239, 58, 243, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if(ligne == -1) {
					
			    	JOptionPane.showMessageDialog(null,"Selectionnez une absence");

				} else {
					
					int a = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cette absence ?", "Supprimer absence", JOptionPane.YES_NO_OPTION);
					
					String id = table.getModel().getValueAt(ligne, 0).toString();					
					
					String sql = "DELETE from absence where id_absence = '"+id+"'";
						
				    try {	
							    prepared = cnx.prepareStatement(sql);  
							  	prepared.execute();
								JOptionPane.showMessageDialog(null,"Absence supprimée avec succés ");
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
				String nometud = comboType.getSelectedItem().toString();
				String dater = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String raison = comboBoxRaison.getSelectedItem().toString();
				
				if (ligne == -1 ) {
			    	JOptionPane.showMessageDialog(null,"Selectionnez une absence");
					
				} else {
					
					String id = table.getModel().getValueAt(ligne, 0).toString();					
					String sql = "UPDATE absence set nom_etud = ? , date = ? , raison = ? where id_absence = '"+id+ "'";
					  
					try {
							  
								prepared = cnx.prepareStatement(sql);
						    	prepared.setString(1, nometud);
						    	prepared.setString(2, dater);
						    	prepared.setString(3, raison);
		
						    	prepared.execute();
						    	
						    	comboType.setSelectedItem("Selectionnez");
						    	comboBoxRaison.setSelectedItem("Selectionnez");
						    	dateChooser.setDateFormatString("");
						    	
						    	JOptionPane.showMessageDialog(null,"Absence modifié avec succés ");
						    	UpdateTable();

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
				String id = table.getModel().getValueAt(ligne, 0).toString();

				String sql ="select * from absence where id_absence = '"+id+"'";

		    	
				String fil = table.getModel().getValueAt(ligne, 1).toString();
				String typ = table.getModel().getValueAt(ligne, 2).toString();
				String raison = table.getModel().getValueAt(ligne, 3).toString();

		    	comboType.setSelectedItem(fil);
		    	dateChooser.setDateFormatString(typ);
		    	comboBoxRaison.setSelectedItem(raison);
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
		
		
		
		JLabel lblNewLabel = new JLabel("Nom Etudiant :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(106, 143, 112, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date Absence :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(106, 203, 123, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Raison :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(106, 277, 92, 37);
		frame.getContentPane().add(lblNewLabel_6);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-mm-dd");
		dateChooser.setBounds(228, 203, 243, 34);
		frame.getContentPane().add(dateChooser);
		
		comboBoxRaison = new JComboBox();
		comboBoxRaison.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBoxRaison.setModel(new DefaultComboBoxModel(new String[] {"Malade\t", "Retard", "Paiement "}));
		comboBoxRaison.setBounds(228, 271, 243, 37);
		frame.getContentPane().add(comboBoxRaison);
	}
		
		public void UpdateTable() {
			
			String sql = "select * from absence";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();
		    	table.setModel(DbUtils.resultSetToTableModel(resultat));
		    	
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}
		
		public void remplirComboBox() {

			String sql = "select * from student";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();		
		    	
		    	while (resultat.next()) {
		    		
		    		String name = resultat.getString("nom").toString();
		    		String surname = resultat.getString("prenom").toString();
		    		

		    		comboType.addItem(surname+" "+name);
		    		
		    	}
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}	
}