/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


// class to create a connection to the data store
public class DBase {
    
    public static Connection dbConnection(){
       Properties p = System.getProperties();
       p.setProperty("derby.system.home", "\\db\\");
       Connection con = null;
       String dbURL = "jdbc:derby:myDB;create=false;user=me;password=mine";
       try{
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           con = DriverManager.getConnection(dbURL); 
           
       }catch (Exception e){System.err.println(e + " DBase Class Error");}
       return con;
    } 
            
}
