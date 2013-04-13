/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class Utilities {
    
    
    public Utilities(){};
    
    public String titleCut(String t){
        if (t == null)return null;
        int pos = t.lastIndexOf("/");
        t = t.substring(0, pos);
        return t;
    }
    
    public String pubDate(String pb){
        if (pb == null) return null;
        int pos = pb.lastIndexOf("GMT");
        pb = pb.substring(0, pos -1);
        return pb;
    }
    
    public String currencyName(String cn){
        if (cn == null)return null;
        int pos = cn.lastIndexOf(".");
        cn = cn.substring(pos + 7);
        return cn;
    }
    
    public String getRate(String r){
        if (r == null)return null;
        int pos = r.lastIndexOf("=");
        r = r.substring(pos + 1);
        pos = r.lastIndexOf(".");
        r = r.substring(0, pos + 6);
        return r;
    }
    
}
