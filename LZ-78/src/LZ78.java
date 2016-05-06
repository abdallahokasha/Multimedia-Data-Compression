
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdallah
 */
public class LZ78 extends javax.swing.JFrame {

    /**
     * Creates new form LZ78
     */
    String data = "";
    Hashtable <String, Integer>dictionary = new Hashtable<String, Integer>();
    Hashtable <Integer, String> inverseDictionary = new Hashtable<Integer, String>();
    ArrayList <Integer> numOfString = new ArrayList <Integer> ();
    ArrayList <Character> nextChar = new ArrayList <Character> ();
    
    public LZ78() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("Compress");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("De-Compress");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("LZ-78");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(jRadioButton2)))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(21, 21, 21)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jRadioButton2.setSelected(true);
        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jRadioButton1.isSelected()){
         File file = new File("D:\\FCI\\3th-Level\\Java\\LZ-78\\input.txt");
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    //System.out.println(line);
                    data += line;
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            textArea1.append("input data: ");
            textArea1.append(data);
            textArea1.append("\n");
            textArea1.append("          << Compression >> \n");
            int count = 0;
            String str = "";
            for (int i = 0; i < data.length(); i++){
                str += data.charAt(i);
                if (dictionary.containsKey(str) == false){
                    dictionary.put(str, new Integer(++count));
                    inverseDictionary.put(new Integer(count), str);
                    String find = str.subSequence(0, str.length()-1) + "";
                    Integer x =  dictionary.get(find);
                    if (x == null) x = 0;
                    numOfString.add(x);
                    nextChar.add(str.charAt(str.length()-1));
                    str = "";
                }
            }
            if (dictionary.containsKey(str) == false && str.length() != 0){
                    dictionary.put(str, new Integer(++count));
                    inverseDictionary.put(new Integer(count), str);
                    String find = str.subSequence(0, str.length()-1) + "";
                    Integer x =  dictionary.get(find);
                    if (x == null) x = 0;
                    numOfString.add(x);
                    nextChar.add(str.charAt(str.length()-1));
                    str = "";
                }
            // if string already exists !  
            else if (dictionary.containsKey(str) == true && str.length() != 0)
            {
                    Integer x =  dictionary.get(str);
                    numOfString.add(x);
                    nextChar.add('-');
            }
            textArea1.append("tags...\n");
            for (int i = 0; i < nextChar.size(); ++i)
                textArea1.append("<" + numOfString.get(i) + ", " + nextChar.get(i)+">\n");
        }
        else if (jRadioButton2.isSelected()){
             textArea1.append(" << Decompression >> \n");
             String dec = "";
             for (int i = 0; i < nextChar.size(); i++){
                 String str = inverseDictionary.get(numOfString.get(i));
                if (numOfString.get(i) != 0) dec += str;
                 if (nextChar.get(i) != '-')dec += nextChar.get(i);
             }
             textArea1.append(dec);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LZ78.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LZ78.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LZ78.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LZ78.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LZ78().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
// abaababaa
//abaababaabbbb
//abaababaabbbbbbbbbbbba