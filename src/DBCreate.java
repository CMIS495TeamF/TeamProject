/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Aaron
 */
public class DBCreate {
    Connection con = null;
    Statement st = null;
    // String array declared at class level to be used by methods
    static String[] c;
    int p = 0;
    
    public int getP() {
        return p;
    }
    // data base connection strings
    private String dbURL = "jdbc:derby:myDB;create=true;user=me;password=mine";
    private String dbURL2 = "jdbc:derby:myDB;create=false;user=me;password=mine";
    public DBCreate(String[] c, String dir, CurrencyCalc cc){
        // set the passed string array to the local string array
        this.c = c;
        // this sets the derby database file location
        Properties p = System.getProperties();
        p.setProperty("derby.system.home", dir);
        // method to create new data store
        createDB();
        // create the tables in the data store
        createTables();
        new Thread(new XmlParser(c, true, cc)).start();
    }
    // method to create the data store
    private void createDB()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            con = DriverManager.getConnection(dbURL); 
            con.close();
        }
        catch (Exception ex)
        { JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
           System.exit(0);
        }
    }
    // method to create the tables
    private void createTables(){
        try{
        con = DriverManager.getConnection(dbURL2);
        st = con.createStatement();
        // for loop iterate over all elements in the array and create the tables
        for (String h : c){
        String insert = "CREATE TABLE \"ME\"."+ h + "(CountryCode VARCHAR(5) NOT NULL, CurrencyName VARCHAR(50), Rate DECIMAL(10,5),\n" +
"RetreiveDate TIMESTAMP, primary key (CountryCode))";
            
            st.execute(insert);
            p++;
        }
        st.close();
        con.close();
        }catch(SQLException e){}
    }
    
   
    
}
