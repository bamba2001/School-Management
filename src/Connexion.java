
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class Connexion {
    private static Connection DBConnection;
    public static Connection connect(){
    
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Success");            
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Driver not found" + cnfe);
        }        
        String url="jdbc:mysql://localhost:3306/gestion_etud";        
        try{
          DBConnection=DriverManager.getConnection(url,"root","");
          System.out.println("Database Connected");
        }
        catch(SQLException se){
          System.out.println("Database Not Found");  
        }
        return DBConnection;
        
 
        
        
    }
    
}
