
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
String[] c;
    public XmlParser(String[] c){
        this.c = c;
        parseFiles();
    }
    
    private void parseFiles(){
       for (String h : c){
           
               String xmlUrlString = "http://themoneyconverter.com/rss-feed/"+h+"/rss.xml";
               URL xmlURL = null;
               
           try {
               xmlURL = new URL(xmlUrlString);
           } catch (MalformedURLException ex) {
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               InputStream xml = xmlURL.openStream();
               DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
               DocumentBuilder db = dbf.newDocumentBuilder();
               Document doc = db.parse(xml);
               xml.close();
               NodeList nodeList = doc.getElementsByTagName("item");
               
               for (int n = 0; n < nodeList.getLength(); n++){
               
               Node topNode = nodeList.item(n);
               
               if (topNode.getNodeType() == Node.ELEMENT_NODE){
                   Element titleElement = (Element) topNode;
                   NodeList titleNode = titleElement.getElementsByTagName("title");
                   String title = u.titleCut(((Node)titleNode.item(0)).getTextContent());
                   String descrip = null;
                   NodeList descripNode = titleElement.getElementsByTagName("description");
                   descrip = ((Node)descripNode.item(0)).getTextContent();
                   String curName = u.currencyName(descrip);
                   String rate  = u.getRate(descrip);
                   NodeList dateNode = titleElement.getElementsByTagName("pubDate");
                   String pubDate = u.pubDate(((Node)dateNode.item(0)).getTextContent());
                   
                   System.out.println("Title is "+ title + " Currency Name is " + curName +
                       " Rate is " + rate + " date is "+ pubDate);
               }
               
               }
               
           } catch (Exception ex) {
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
           }
       } 
    }
    
}
