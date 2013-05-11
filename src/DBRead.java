
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

CurrencyCalc app;   
DBRead dbr;

    DBRead(CurrencyCalc app){
        this.app = app;
        
    }

    
    // static method to get the date of the timestamp from tables
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
    
    // static method to get the rate of the selected countries
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
    
    public static String currencyName1(String ccCode){
        Connection conn = DBase.dbConnection();
        PreparedStatement dbpst;
        String curName ="";
        try{
           String query = "Select CurrencyName FROM "+ccCode+ " where CountryCode = ?";
           dbpst=conn.prepareStatement(query);
           dbpst.setString(1, ccCode);
          
            
            ResultSet rs = dbpst.executeQuery();
            
             while(rs.next()){
                 curName=rs.getString(1);
         
             } 
             dbpst.close();
             conn.close();
        }catch(SQLException e){System.err.println(e);}
        
        return curName;
        
    }
    
   public static String currencyName2(String c1, String c2){
        Connection conn = DBase.dbConnection();
        PreparedStatement dbpst;
        String curName2 ="";
        try{
           String query = "Select CurrencyName FROM "+c1+ " where CountryCode = ?";
           dbpst=conn.prepareStatement(query);
           dbpst.setString(1, c2);
          
            
            ResultSet rs = dbpst.executeQuery();
            
             while(rs.next()){
                 curName2=rs.getString(1);
         
             } 
             dbpst.close();
             conn.close();
        }catch(SQLException e){System.err.println(e);}
        
        return curName2;
    }
    
    // static method to determine if data exists in the tables
    public static boolean isData(String[] ccCode){
        String[] tCodes = ccCode;
        int tblestart = 0;
        int tbleend = 0;
        Boolean b=false;
        Connection con = DBase.dbConnection();
        PreparedStatement pst;
        try{
            String query = "Select Count(CountryCode) FROM AED";
            pst = con.prepareStatement(query);
     
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                tblestart =0;
            }
            else do{              
            tblestart = rs.getInt(1);
            System.out.println(tblestart);
            }while(rs.next());
            
            rs.close();
            
            pst.close();
            
            String query2 = "Select Count(CountryCode) From ZAR";
            pst = con.prepareStatement(query2);
            ResultSet rs1 = pst.executeQuery();
            if(!rs1.next()){
                tbleend =0;
            }
            else  do{              
            tbleend = rs1.getInt(1);
            System.out.println(tbleend);
            }while(rs1.next());
            rs1.close();
            pst.close();
            con.close();
        }catch(SQLException e){System.err.println(e + "?");}
        
               
        if (tbleend ==0){
            b=true;
        }
        else if (tbleend ==90){
            b = false;
        }
        
        return b;
    }
    
    public static Boolean DBIntegrityCheck(String[] tCodes){
        Boolean destroyed=false;
        Connection con = DBase.dbConnection();
        PreparedStatement pst;
        
        iCheck:  for (String cc : tCodes){
            String query = "Select Count(CountryCode) FROM " + cc;
            ResultSet frs;
            
            try{
            pst = con.prepareStatement(query);
            frs = pst.executeQuery();
            while (frs.next()){
               int records = frs.getInt(1);
                if (records != 90){
                  destroyed = true;
                    break iCheck;
                
                }else if (records == 90) destroyed = false;
                
            }
            pst.close();
            con.close();
            }catch(SQLException e){};
        }
        return destroyed;
    }
    
    public static void DBDelete(String[] tCodes){
        Connection con = DBase.dbConnection();
        PreparedStatement pst =null;
        try{
        for (String tableCodes : tCodes){
            String  d = "delete from \"ME\"." + tableCodes;
            
            pst = con.prepareStatement(d);
            pst.execute();
            con.commit();
        }
        
             pst.close();
             
            }catch(SQLException e){System.out.println(e);};
      
            try{     
           
         
           for (String tableCodes : tCodes){
            String  d = "Select Count(CountryCode) from \"ME\"." + tableCodes;
            pst = con.prepareStatement(d);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()){
            int i =0;  
            i = rs.getInt(1);
                System.out.println(i +" " + tableCodes);
            }
            rs.close();
       }
          pst.close();
          con.close();
    }catch(SQLException e){System.out.println(e);};
}
}
