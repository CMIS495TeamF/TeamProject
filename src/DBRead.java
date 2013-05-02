
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
    
    public static double getRate(String c1, String c2){
        Connection conn = DBase.dbConnection();
        PreparedStatement dbpst;
        double dr =0;
        try{
           String query = "Select Rate FROM "+c1+ " where CountryCode = ?";
           dbpst=conn.prepareStatement(query);
           dbpst.setString(1, c2);
          
            
            ResultSet rs = dbpst.executeQuery();
            
             while(rs.next()){
                 dr=rs.getDouble(1);
         
             } 
             dbpst.close();
             conn.close();
        }catch(SQLException e){System.err.println(e);}
        
        return dr;
    }
}
