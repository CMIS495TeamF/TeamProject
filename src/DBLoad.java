
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
        String insert = "Insert into \"ME\"."+ t +" VALUES(?,?,?,?)";
        //con.setAutoCommit(false);
        pst = con.prepareStatement(insert);
        for (String[] items : c){
           pst.setString(1, items[0]);
           pst.setString(2, items[1]);
           pst.setDouble(3, Double.valueOf(items[2]));
           pst.setDate(4, null);
           pst.addBatch();
        }
        pst.executeBatch();
        con.commit();
        pst.close();
        con.close();
    }
    
}
