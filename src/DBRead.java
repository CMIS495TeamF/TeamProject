
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class DBRead {
    
    Connection conn = DBase.dbConnection();
    PreparedStatement dbpst;
    
    public static long getLastUpdate(){
        Connection con = DBase.dbConnection();
        PreparedStatement pst;
        long t=0;
        Calendar c = Calendar.getInstance();
        try{
          String query = "Select Distinct RetreiveDate FROM \"ME\".USD";
          pst = con.prepareStatement(query);
         
          ResultSet rs = pst.executeQuery();
          
              while(rs.next()){
         
          c.setTime(rs.getTimestamp(1));
          
          
          t = c.getTimeInMillis();
              }
          
          pst.close();
          con.close();
        }catch(SQLException e){System.err.println(e);}
        
        return t;
    }
}
