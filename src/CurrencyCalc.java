import java.io.File;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class CurrencyCalc extends javax.swing.JFrame {
// String array that contains all of the countries codes. This is what names each table
// and is also used to open each rss feed, as the url contains these codes
String[] countries = new String[]{"AED","ARS","AUD","AWG","BAM","BBD","BDT","BGN",
"BHD","BMD","BOB","BRL","BSD","CAD","CHF","CLP","CNY","COP","CZK","DKK","EGP","EUR",
"FJD","GBP","GHS","GMD","GTQ","HKD","HRK","HUF","IDR","ILS","INR","ISK","JMD","JOD",
"JPY","KES","KHR","KRW","KWD","LAK","LBP","LKR","LTL","LVL","MAD","MDL","MGA","MKD",
"MUR","MVR","MXN","MYR","NAD","NGN","NOK","NPR","NZD","OMR", "PAB","PEN","PHP","PKR",
"PLN","PYG","QAR","RON","RSD","RUB","SAR","SCR","SEK","SGD","SYP","THB","TND","TRY",
"TWD","UAH","UGX","USD","UYU","VEF","VND","XAF","XCD","XOF","XPF","ZAR"};

String[] cNames = new String[]{"United Arab Emirates","Argentina","Australia","Aruba",
"Bosnia and Herzegovina","Barbados","Bangladesh","Bulgaria","Bahrain","Bermuda","Bolivia",
"Brazil","Bahamas","Canada","Switzerland","Chile","China","Colombia","Czech Republic","Denmark",
"Egypt","Euro","Fiji","United Kingdom","Ghana","Gambia","Guatemala","Hong Kong","Croatia",
"Hungary","Indonesia","Israel","India","Iceland","Jamaica","Jordan","Japan","Kenya","Cambodia",
"South Korea","Kuwait","Laos","Lebanon","Sri Lanka","Lithuania","Latvia","Morocco","Moldova",
"Madagascar","Macedonia","Mauritius","Maldives","Mexico","Malaysia","Namibia","Nigeria",
"Norway","Nepal","New Zealand","Oman","Panama","Peru","Philippines","Pakistan","Poland",
"Paraguay","Qatar","Romania","Serbia","Russia","Saudi Arabia","Seychelles","Sweden","Singapore",
"Syria","Thailand","Tunisia","Turkey","Taiwan","Ukraine","Uganda","United States","Uruguay",
"Venezuela","Vietnam","Central African Franc","East Caribbean Dollar","West African Franc","CFP Franc",
"South Africa"};
    /**
     * Creates new form CurrencyCalc
     */
// static string to set the db to the executing directory, instead of the root
// set static so any class has any access to it
public static String dir;
    public CurrencyCalc() {
        initComponents();
        // method to see if a data store allready exists
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Currency Converter");

        jLabel2.setText("Currency you have:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Currency to exchange to:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel5.setText("jLabel5");

        jLabel6.setText("Amount:");

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

        jLabel7.setText("Result:");

        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jLabel1.setText(countries[jComboBox1.getSelectedIndex()]);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
       jLabel5.setText(countries[jComboBox2.getSelectedIndex()]);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tableName = countries[jComboBox1.getSelectedIndex()];
        String ccCode = countries[jComboBox2.getSelectedIndex()];
        if (isNumeric(jTextField3.getText())){
        Double amount = Double.valueOf(jTextField3.getText());
        Double result;
        Double rate = DBRead.getRate(tableName, ccCode);
        result = amount * rate;
        jTextField4.setText(result.toString());
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField3.requestFocusInWindow();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        // TODO add your handling code here:
        jTextField3.setText("");
            jTextField4.setText("");
    }//GEN-LAST:event_jTextField3FocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CurrencyCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CurrencyCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CurrencyCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CurrencyCalc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            CurrencyCalc app =  new CurrencyCalc();
                app.setLocationRelativeTo(null);
                app.setVisible(true);
                app.load();
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    // method to determine if a data store is allready present
private void dbCheck(String dir){   //checks to see if the db has been created
   File isdb = new File(dir +"\\myDB"); 
   if (isdb.exists()){
       System.out.println("does exist");
       Boolean refresh = dbDateCheck();
       int c=0;
       if (!refresh){
       c = JOptionPane.showConfirmDialog(null, "The currecncy information is less than 24 hours old.\n"
                   + "Do you want to update anyway?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       if (c==0){
           new Thread(new XmlParser(countries, false)).start();
       }
       }
       else if (refresh){     
       new Thread(new XmlParser(countries, false)).start();
       }
   }
   else{// if it does not exist run the DB create class to create DB
      
     JOptionPane.showMessageDialog(null,"A Database does not exist, please wait for the database to be created",
        "Warning",JOptionPane.WARNING_MESSAGE);
       System.out.println("does not exist");
       new DBCreate(countries, dir);
   }
}

private void load(){
   DefaultComboBoxModel dcbm = new DefaultComboBoxModel(cNames);
   DefaultComboBoxModel dcbm2 = new DefaultComboBoxModel(cNames);
   jComboBox1.setModel(dcbm);
   jComboBox1.setSelectedIndex(81);
   jComboBox2.setModel(dcbm2);
   jLabel1.setText(countries[jComboBox1.getSelectedIndex()]);
   jLabel5.setText(countries[jComboBox2.getSelectedIndex()]);
   dir = System.getProperty("user.dir");
   
   dbCheck(dir);
}

private Boolean dbDateCheck(){
    Boolean b;
    Calendar now = Calendar.getInstance();
    long current = now.getTimeInMillis();
   // Calendar c;
    long rtime = DBRead.getLastUpdate();
    long hours =  TimeUnit.MILLISECONDS.toHours(current-rtime);
    // System.out.println(current);    
    // System.out.println(rtime);  
    //System.out.println(hours);
    if (hours < 24){
        b =false;
    }
    else b = true;
    
    return b;
}

 public static boolean isNumeric(String s){
        return s.matches("[-+]?\\d*\\.?\\d+");
          
    }

}
