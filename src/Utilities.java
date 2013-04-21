/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
// class that provides helper functions to format data strings
public class Utilities {
    
    
    public Utilities(){};
    // method to convert the title string parsed into a single country code
    public String titleCut(String t){
        if (t == null)return null;
        int pos = t.lastIndexOf("/");
        t = t.substring(0, pos);
        return t;
    }
    // method to extract the date out of the parsed data
    public String pubDate(String pb){
        if (pb == null) return null;
        int pos = pb.lastIndexOf("GMT");
        pb = pb.substring(0, pos -1);
        return pb;
    }
    // method to extract the currency name from the parsed data
    public String currencyName(String cn){
        if (cn == null)return null;
        int pos = cn.lastIndexOf(".");
        cn = cn.substring(pos + 7);
        return cn;
    }
    // method to extract the converstion rate from the parsed data
    public String getRate(String r){
        if (r == null)return null;
        int pos = r.lastIndexOf("=");
        r = r.substring(pos + 1);
        
        pos = r.lastIndexOf(".");
        r = r.substring(0, pos + 6);
        if (r.contains(",")){
            pos = r.lastIndexOf(",");
        r = (r.substring(0, pos) + r.substring(pos+1));
        System.out.println(r);
        }
        return r;
    }
    
}
