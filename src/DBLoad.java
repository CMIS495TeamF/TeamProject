
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class DBLoad {
    Connection con;
    PreparedStatement pst;
    public DBLoad(){
      
    }
    // method to take parsed information and store int he tables
    public void fillTables(String t, ArrayList<String[]> c) throws SQLException{
        con = DBase.dbConnection();
        String insert = "INSERT INTO \"ME\"."+ t +" VALUES(?,?,?,?)";
               
        //con.setAutoCommit(false);
        pst = con.prepareStatement(insert);
        for (String[] items : c){
           pst.setString(1, items[0]);
           pst.setString(2, items[1]);
           pst.setDouble(3, Double.valueOf(items[2]));
           pst.setDate(4, Utilities.getSQLDate(items[3]));
           pst.addBatch();
        }
        pst.executeBatch();
        con.commit();
        pst.close();
        con.close();
    }
    
    public void updateTables(String t, ArrayList<String[]> c) throws SQLException{
        con = DBase.dbConnection();
        //String insert = "INSERT INTO \"ME\"."+ t +" VALUES(?,?,?,?)";
        String update = "UPDATE \"ME\"." + t +" SET CurrencyName=?, Rate=?, RetreiveDate=? WHERE "
                + "CountryCode=?";       
        //con.setAutoCommit(false);
        pst = con.prepareStatement(update);
        for (String[] items : c){
           pst.setString(1, items[1]);
           pst.setDouble(2, Double.valueOf(items[2]));
            pst.setDate(3, Utilities.getSQLDate(items[3]));
           pst.setString(4, items[0]);
           
          
           pst.addBatch();
        }
        pst.executeBatch();
        con.commit();
        pst.close();
        con.close();
    }
    
}
