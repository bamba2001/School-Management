import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.GridLayout;


public class Gestion_Etudiant extends JFrame {

	private static JFrame frame;
	private JTextField name;
	private JTextField surname;
	
	
	Connection cnx = null ;
	PreparedStatement prepared = null;
	ResultSet resultat = null ;
	private JTable table;
	private JTextField adresse;
	private JTextField matricule;
	private JTextField telephone;
	private String savePhoto ; 
	@SuppressWarnings("rawtypes")
	JComboBox comboBox ;
	
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
					Gestion_Etudiant window = new Gestion_Etudiant();
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
	public Gestion_Etudiant() {
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
		frame.setBounds(100, 100, 1200, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		cnx = Connexion.connect();		
		
		
		name = new JTextField();
		name.setBounds(199, 110, 137, 32);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(199, 153, 137, 32);
		frame.getContentPane().add(surname);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(106, 113, 108, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(106, 155, 92, 25);
		frame.getContentPane().add(lblNewLabel_1);
		

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(342, 110, 122, 119);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 1));
		
		JLabel labeIcon = new JLabel("");
		panel_2.add(labeIcon);
		
		JButton btnConnect = new JButton("ADD");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnect.setBackground(Color.GREEN);
		btnConnect.setBounds(106, 397, 72, 34);
		frame.getContentPane().add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {	

				String sql = "insert into student (nom,prenom,adresse,matricule,tel,filière,image) values (?,?,?,?,?,?,?)";
				
			    try {
			    	
			    	InputStream photo = new FileInputStream(new File(savePhoto));
			    			
			    	if(!name.equals("") && !surname.equals("") && !adresse.equals("") && !matricule.equals("") && !telephone.equals("")  ) {
			    		
			    		prepared = cnx.prepareStatement(sql);
				    	prepared.setString(1, name.getText().toString());
				    	prepared.setString(2, surname.getText().toString());
				    	prepared.setString(3, adresse.getText().toString());
				    	prepared.setString(4, matricule.getText().toString());
				    	prepared.setString(5, telephone.getText().toString());
				    	prepared.setString(6, comboBox.getSelectedItem().toString());
				    	prepared.setBlob(7,photo );


				    	prepared.execute();
				    	
				    	name.setText("");
				    	surname.setText("");
				    	adresse.setText("");
				    	matricule.setText("");
				    	telephone.setText("");
				    	comboBox.setSelectedItem("Selectionnez");
				    	
				    	JOptionPane.showMessageDialog(null,"utilisateur ajoutée avec succés ");
				    	UpdateTable();
			    	}
			    
	
			    }catch (SQLException | FileNotFoundException s) {
			    	
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
		lblNewLabel_5.setIcon(new ImageIcon(Gestion_Etudiant.class.getResource("/images/icons8_Back_To_25px.png")));
		lblNewLabel_5.setBounds(47, 5, 43, 43);
		panel.add(lblNewLabel_5);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 51, 100, 402);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Gestion_Etudiant.class.getResource("/images/icons8_User_Shield_100px.png")));
		panel_1.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("REGISTER_STUDENT");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.BOLD, 22));
		lblNewLabel_4.setBounds(239, 58, 243, 32);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				
				if (ligne == -1)  {
					
					JOptionPane.showMessageDialog(null, "Sélectionner un étudiant ! ");
				}
				
				else {
					int a = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cet étudiant ?", "Supprimer étudiant", JOptionPane.YES_NO_OPTION);
					
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "DELETE from student where id_etud = '"+id+"'";
					
					   try {
					    	
						    prepared = cnx.prepareStatement(sql);
						    
						    if(a==0) {
						    	
						    	prepared.execute();
						    	JOptionPane.showMessageDialog(null,"utilisateur supprimé avec succés ");

						    }
						    					    	UpdateTable();
					    	
					    }catch (SQLException s) {
					    	
					    	s.printStackTrace();
					    }
					
				}		
						
			}
		});
		
		
		  JButton btnNewButton = new JButton("Update");
		  btnNewButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  
				int ligne = table.getSelectedRow();
				
				if (ligne == -1)  {
					
					JOptionPane.showMessageDialog(null, "Sélectionner un étudiant ! ");
				}
				else {
					
					  String id = table.getModel().getValueAt(ligne, 0).toString();
						
					  String sql = "UPDATE student set nom = ? , prenom = ?, adresse = ?,matricule = ?, tel= ?, filière = ?, image = ? where id_etud = '"+id+"'  ";
					  
					  try {
						  
						  	
					    	InputStream _image = new FileInputStream(new File(savePhoto));
						    prepared = cnx.prepareStatement(sql);
					    	prepared.setString(1, name.getText().toString());
					    	prepared.setString(2, surname.getText().toString());
					    	prepared.setString(3, adresse.getText().toString());
					    	prepared.setString(4, matricule.getText().toString());
					    	prepared.setString(5, telephone.getText().toString());
					    	prepared.setString(6, comboBox.getSelectedItem().toString());
					    	prepared.setBlob(7, _image);
					    	prepared.execute();
					    	JOptionPane.showMessageDialog(null,"utilisateur modifié avec succés ");
					    	UpdateTable();
					    	
					    	name.setText("");
							surname.setText("");
							adresse.setText("");
							matricule.setText("");
							telephone.setText("");
							comboBox.setSelectedItem("Selectionnez");

					  }catch (SQLException | FileNotFoundException s) {
					  
					  s.printStackTrace(); }
	
				}
				
					  
		  } });
		  
		  btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		  btnNewButton.setBackground(new Color(30, 144, 255));
		  btnNewButton.setBounds(184, 397, 92, 34);
		  frame.getContentPane().add(btnNewButton);
		  
		 		
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(286, 397, 92, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(481, 104, 693, 338);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql ="select * from student where id_etud = '"+id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					if(resultat.next()) {
						
						String nom = table.getModel().getValueAt(ligne, 1).toString();
						String prenom = table.getModel().getValueAt(ligne, 2).toString();
						String adress = table.getModel().getValueAt(ligne, 3).toString();
						String matric = table.getModel().getValueAt(ligne, 4).toString();
						String tel = table.getModel().getValueAt(ligne, 5).toString();
						String fil = table.getModel().getValueAt(ligne, 6).toString();
						
						name.setText(nom);
						surname.setText(prenom);
						adresse.setText(adress);
						matricule.setText(matric);
						telephone.setText(tel);
						comboBox.setSelectedItem(fil);
						
						byte [] img = resultat.getBytes("image");
						ImageIcon image = new ImageIcon(img);
						java.awt.Image _img = image.getImage();
						java.awt.Image myImg = _img.getScaledInstance(labeIcon.getHeight(), labeIcon.getWidth(), java.awt.Image.SCALE_SMOOTH);
						
					    ImageIcon imgg = new ImageIcon(myImg);
						labeIcon.setIcon(imgg);

					}

				}
				catch(SQLException s)
				
				{
					s.printStackTrace();
					
				}
		
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
		
		adresse = new JTextField();
		adresse.setBounds(199, 196, 137, 32);
		frame.getContentPane().add(adresse);
		
		matricule = new JTextField();
		matricule.setBounds(199, 239, 137, 32);
		frame.getContentPane().add(matricule);
		
		telephone = new JTextField();
		telephone.setBounds(199, 282, 137, 32);
		frame.getContentPane().add(telephone);
		
		JLabel lblNewLabel_1_1 = new JLabel("Adresse :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(106, 198, 92, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Matricule");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(106, 241, 92, 25);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Téléphone :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(106, 284, 92, 25);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_6 = new JLabel("Filière :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(106, 320, 92, 23);
		frame.getContentPane().add(lblNewLabel_6);
		
		JButton btnUpload = new JButton("Print");
		btnUpload.setBackground(new Color(255, 255, 0));
		btnUpload.setFont(new Font("Verdana", Font.BOLD, 15));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Document doc = new Document();
				
				String sql = "Select * from student";
				
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\supdeco.pdf"));
					doc.open();
					
					
					Image img = Image.getInstance("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\supdeco.png");
					
					img.scaleAbsoluteWidth(400);
					img.scaleAbsoluteHeight(200);
					img.setAlignment(Image.ALIGN_CENTER);
					doc.add(img);
					
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" ----------------------------------------------------Liste des Etudiants --------------------------------------------------- "));
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(6);
					table.setWidthPercentage(100);
					
					PdfPCell cell ;
					
					///////////////////////////////////////////////////////////////////////////////
					
					cell = new PdfPCell(new Phrase ("Name", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase ("Surname", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase ("Adresse", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase ("Matricule", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase ("Téléphone", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase ("Filière", FontFactory.getFont("Comic Sans MS", 11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					
					////////////////////////////////////////////////////////////////////////////////////
					
					while (resultat.next()) {
						
						cell = new PdfPCell(new Phrase (resultat.getString("nom").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase (resultat.getString("prenom").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase (resultat.getString("adresse").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase (resultat.getString("matricule").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase (resultat.getString("tel").toString(), FontFactory.getFont("Arial", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase (resultat.getString("filière").toString(), FontFactory.getFont("Comic Sans MS", 11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
					
					}

					///////////////////////////////////////////////////////////////////////////////////
					
					doc.add(table);
					
					doc.close();
					Desktop.getDesktop().open(new File("C:\\Users\\LENOVO\\eclipse-workspace\\School_Management\\src\\images\\supdeco.pdf"));	
					
				} catch (FileNotFoundException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				} catch (SQLException e2) {
					
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		btnUpload.setBounds(388, 397, 79, 34);
		frame.getContentPane().add(btnUpload);
		
        comboBox = new JComboBox();
        comboBox.addMouseListener(new MouseAdapter()  {
        
        	public void mouseClicked(MouseEvent arg0) {
        		
        		
        	}
        });  
        
		comboBox.setBounds(199, 322, 257, 32);
		frame.getContentPane().add(comboBox);
		
		
		Button button = new Button("Upload Image");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\Users\\LENOVO\\OneDrive - Groupe SupdeCo Dakar\\Desktop"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					java.awt.Image img = myImage.getImage();
					java.awt.Image newImage = img.getScaledInstance(labeIcon.getHeight(), labeIcon.getWidth(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalImg = new ImageIcon(newImage);
					labeIcon.setIcon(finalImg);
					savePhoto = path;
	
				}else {
					
					if(result == JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Vous n'avez rien choisi");
				}
				
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 13));
		button.setBackground(new Color(211, 211, 211));
		button.setBounds(352, 235, 108, 32);
		frame.getContentPane().add(button);
		
		remplirComboBox();
	}
		
		public void UpdateTable() {
			
			String sql = "select * from student";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();
		    	table.setModel(DbUtils.resultSetToTableModel(resultat));
		    	
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}
		
		@SuppressWarnings("unchecked")
		
		public void remplirComboBox() {

			String sql = "select * from filière";
			
		    try {
		    	
		    	prepared = cnx.prepareStatement(sql);
		    	resultat = prepared.executeQuery();		
		    	
		    	while (resultat.next()) {
		    		
		    		String fil = resultat.getString("type").toString();
		    		comboBox.addItem(fil);
		    		
		    	}
		    	
		    	
		    }catch (SQLException s) {
		    	
		    	s.printStackTrace();
		    }
		
		}	
}
