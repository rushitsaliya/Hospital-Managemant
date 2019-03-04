package Backend;

import java.sql.*;
import javax.swing.JOptionPane;
public class Javaconnect {

    Connection conn=null;

    public static Connection ConnectDb(){
        try{
         Class.forName("org.sqlite.JDBC");
         Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rushi\\IdeaProjects\\Hospital-Managemant-in-JAVA\\HospitalManagemant.sqlite");
                 return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
