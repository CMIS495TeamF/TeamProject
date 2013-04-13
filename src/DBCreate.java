/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Aaron
 */
public class DBCreate {
    Connection con = null;
    Statement st = null;
    String[] c;
    private String dbURL = "jdbc:derby:myDB;create=true;user=me;password=mine";
    private String dbURL2 = "jdbc:derby:myDB;create=false;user=me;password=mine";
    public DBCreate(String[] c){
        this.c = c;
        Properties p = System.getProperties();
        p.setProperty("derby.system.home", "\\db\\");
        createDB();
        createTables();
    }
    
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
        {System.err.println(ex);
        }
    }

    private void createTables(){
        try{
        con = DriverManager.getConnection(dbURL2);
        st = con.createStatement();
        //st.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(12))");
        //st.execute("INSERT  INTO TEST VALUES(1,'TEN'),(2,'TWO')");
        for (String h : c){
        String insert = "CREATE TABLE \"ME\"."+ h + "(CountryCode VARCHAR(5) NOT NULL, CurrencyName VARCHAR(50), Rate DECIMAL(10,5),\n" +
"RetreiveDate DATE, primary key (CountryCode))";
            st.execute(insert);
        }
        st.close();
        con.close();
        }catch(SQLException e){}
    }
}
