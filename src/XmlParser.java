
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
import javax.swing.JOptionPane;
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

// this class implement runnable an starts a new thread so that the main gui can be displayed while the
// tables in the background are updaing
public class XmlParser implements Runnable{
// intialized other classes for use in this class    
Utilities u = new Utilities();
DBLoad dbl = new DBLoad();

// declare variables 
String[] c;
Boolean isNew;
int p =0;
Boolean kill = false;

    public int getP() {
        return p;
    }
    // class constructor
    public XmlParser(String[] c, Boolean isNew){
        // set local string array to the array data from the currencyCalc class
        this.c = c;
        this.isNew = isNew;
       
    }
    
    // stop method that the thread can be safely stoped if there is an error or task completed
    public void stop(){
       // option pane to show message if app can not connect to the web stite 
       JOptionPane.showMessageDialog(null, "Connection to the server could not be established1.\n"
                   + "Check your internet connection and try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
      Thread.currentThread().interrupt();
      // boolean value used to triger the thread stopping of the progress bar
      kill = true;
      
      
              
               
    }
    
@Override
    public void run(){
    // try netTest method to see if app can connect to the internet.
    try {
        netTest();
    } catch (InterruptedException ex) {
       Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
    }
           
       // if there is internet access run the parseFiles method
        try {
            parseFiles();
        } catch (InterruptedException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // method to check to see there is an internet connection to the web site
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
       // is new method checks to see if there is a db, but has no data 
       // ie... databases were created but never filled on start
       // this will check to see if data exits, so that that data can be inserted
       // rather than using the update method.
       isNew = DBRead.isData();
       // start thread for progress bar
       new Thread(new createFrame(c)).start();
       // loop through cc codes to parse the xml data
       for (String h : c){
           if (Thread.currentThread().isInterrupted()){
               
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
               // if connection is stopped while loop is running catch exception and stop thread and display message in 
               // stop method
               stop();
               Thread.currentThread().interrupt();
              // System.out.println("in loop error");
               Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
               
           }
           try {
               // if new or needs filled uses the insert method
               if(isNew){
                  dbl.fillTables(h, curData);
               }
               // data alllready exists, use udpate method
               else if(!isNew){
               dbl.updateTables(h, curData);                
               }
           } catch (SQLException ex) {
             System.err.println(ex);
           }
           // increment counter for progress bar 
           p++;
       } 
    }
    
    
    
    // inner class to create new thread and progress bar
    public class createFrame implements Runnable{
        String[] c;
        public createFrame(String[] c){
            this.c = c;   
           }
        
        @Override
       public void run(){ 
            try {
                go();     
            } catch (InterruptedException ex) {
                Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("thread kill");
            }
          
          }
        // method that creates new frame and calculates percentage done and udpates bar
      private void go()throws InterruptedException{ 
           int i=1;
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
            // string format for percentage
            percent.setText(String.format("%,.0f%%", dpercent));
            panel.add(percent);
            fFrame.getContentPane().add(panel2, BorderLayout.SOUTH);
            panel2.add(message);
            JProgressBar jpb = new JProgressBar(0,90);
            panel.add(jpb);
            //fFrame.pack();
            fFrame.setVisible(true);
            if (p<90){
            while (p<90){
               if (Thread.currentThread().isInterrupted()){
               fFrame.dispose();
               return;
           }
               if (kill){
                  Thread.currentThread().interrupt();
                 fFrame.dispose();
                  return;
              }
               
               i=getP();
               jpb.setValue(i);
               dpercent = ((i+1) * 100.0f) / 90;
               //System.out.println(i);
               cc = c[i];
               percent.setText(String.format("%,.0f%%", dpercent));
               message.setText("Updating Currency Code " +  cc + ".");
               jpb.repaint();
               try{Thread.sleep(450);} //Sleep 50 milliseconds  

              catch (InterruptedException err){}  

            }
            }
          
                fFrame.dispose();
            
            
      }
    
    }
    
}
