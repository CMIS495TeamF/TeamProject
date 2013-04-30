
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
public class XmlParser implements Runnable{
Utilities u = new Utilities();
DBLoad dbl = new DBLoad();
String[] c;
Boolean isNew;
int p =0;
Boolean kill = false;

    public int getP() {
        return p;
    }
    public XmlParser(String[] c, Boolean isNew){
        // set local string array to the array data from the currencyCalc class
        this.c = c;
        this.isNew = isNew;
       
    }
    
    public void stop(){
      Thread.currentThread().interrupt();
      kill = true;
    }
    
@Override
    public void run(){
   // try {
       // netTest();
   // } catch (InterruptedException ex) {
   //     Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
   // }
           
       
        try {
            parseFiles();
        } catch (InterruptedException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void netTest() throws InterruptedException{
        URL netTest = null;
       try{        
           netTest = new URL("http://themoneyconverter.com");
       } catch(MalformedURLException e){
          
           System.out.println(e+"test");}
    try {
        InputStream xml = netTest.openStream();
    } catch (IOException ex) {
        Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        stop();
    }
    }
    
    // method that will parse the online xml files and read data on the page
    private void parseFiles() throws InterruptedException{
        // iterate through each country in a for loop 
       if (Thread.currentThread().isInterrupted()){
               return;
           }
        
        
       new Thread(new createFrame(c)).start();
       for (String h : c){
           if (Thread.currentThread().isInterrupted()){
               return;
           }
               // declare arraylist to store data from the xml file
               ArrayList<String[]> curData = new ArrayList<String[]>();
               String xmlUrlString = "http://themoneyconverter.com/rss-feed/"+h+"/rss.xml";
               URL xmlURL = null;
               
           try {
               xmlURL = new URL(xmlUrlString);
           } catch (MalformedURLException ex) {
               
              
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Malformed exception");
               
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
               stop();
               Thread.currentThread().interrupt();
               System.out.println("in loop error");
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
               
           }
           try {
               
               if(isNew){
                dbl.fillTables(h, curData);   
               }
               else if(!isNew){
               dbl.updateTables(h, curData);
           }
           } catch (SQLException ex) {
             System.err.println(ex);
           }
           p++;
       } 
    }
    
    public class createFrame implements Runnable{
        String[] c;
        public createFrame(String[] c){
            this.c = c;   
           }
        
       public void run(){ 
           
           
           int i=0;
           float dpercent = 0f;
           String cc = c[0];
           
        JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame fFrame = new JFrame("Please wait while updating.");
            
            fFrame.setLocationRelativeTo(null);
            JPanel panel = new JPanel();
             JPanel panel2 = new JPanel();
            fFrame.setSize(400,125);  
            //panel.setSize(300,300);
            fFrame.getContentPane().add(panel, BorderLayout.CENTER);
            JLabel percent = new JLabel();
            JLabel message = new JLabel();
            message.setText("Updating Currency Code " +  cc + ".");
            percent.setText(String.format("%,.0f%%", dpercent));
            panel.add(percent);
            fFrame.getContentPane().add(panel2, BorderLayout.SOUTH);
            panel2.add(message);
            JProgressBar jpb = new JProgressBar(0,90);
            panel.add(jpb);
            //fFrame.pack();
            fFrame.setVisible(true);
            while (p<=90){
               if (Thread.currentThread().isInterrupted()){
               fFrame.dispose();
               return;
           }
               if (kill){
                   Thread.currentThread().interrupt();
               }
               
               i=getP();
               jpb.setValue(i);
               dpercent = (i * 100.0f) / 90;
               cc = c[i];
               percent.setText(String.format("%,.0f%%", dpercent));
               message.setText("Updating Currency Code " +  cc + ".");
               jpb.repaint();
               //percent.setText("bad");
               try{Thread.sleep(50);} //Sleep 50 milliseconds  

              catch (InterruptedException err){}  

            }
            
          }
      
       
    }
    
}
