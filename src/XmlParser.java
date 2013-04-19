
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class XmlParser {
Utilities u = new Utilities();
DBLoad dbl = new DBLoad();
String[] c;
    public XmlParser(String[] c){
        // set local string array to the array data from the currencyCalc class
        this.c = c;
        parseFiles();
    }
    // method that will parse the online xml files and read data on the page
    private void parseFiles(){
        // iterate through each country in a for loop 
       for (String h : c){
               // declare arraylist to store data from the xml file
               ArrayList<String[]> curData = new ArrayList<String[]>();
               String xmlUrlString = "http://themoneyconverter.com/rss-feed/"+h+"/rss.xml";
               URL xmlURL = null;
               
           try {
               xmlURL = new URL(xmlUrlString);
           } catch (MalformedURLException ex) {
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               // set up the xml parsing commands
               InputStream xml = xmlURL.openStream();
               DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
               DocumentBuilder db = dbf.newDocumentBuilder();
               Document doc = db.parse(xml);
               xml.close();
               // create NodeList of each "item" element (root name for each country inside
               // of the xml file
               NodeList nodeList = doc.getElementsByTagName("item");
               // itereate through each node in the nodelist
               for (int n = 0; n < nodeList.getLength(); n++){
               String[] items = new String[4];
               Node topNode = nodeList.item(n);
               
               if (topNode.getNodeType() == Node.ELEMENT_NODE){
                   Element titleElement = (Element) topNode;
                   // extract data from the title element and format with utility class
                   NodeList titleNode = titleElement.getElementsByTagName("title");
                   String title = u.titleCut(((Node)titleNode.item(0)).getTextContent());
                   items[0]=title;
                   String descrip = null;
                   // extract data from the description element and format with utility class
                   NodeList descripNode = titleElement.getElementsByTagName("description");
                   descrip = ((Node)descripNode.item(0)).getTextContent();
                   String curName = u.currencyName(descrip);
                   items[1]=curName;
                   String rate  = u.getRate(descrip);
                   items[2]=rate;
                   // extract data from the pubDate element and format with utility class
                   NodeList dateNode = titleElement.getElementsByTagName("pubDate");
                   String pubDate = u.pubDate(((Node)dateNode.item(0)).getTextContent());
                   items[3]=pubDate;
                   //System.out.println("Title is "+ title + " Currency Name is " + curName +
                   //    " Rate is " + rate + " date is "+ pubDate);
                   
                   // Add the items to the arraylist of string arrays so that it can be passed
                   // to the dbload class
                   curData.add(items);
               }
               
               }
               
           } catch (Exception ex) {
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               dbl.fillTables(h, curData);
           } catch (SQLException ex) {
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
           }
       } 
    }
    
}
