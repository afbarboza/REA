/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author alex
 */
public class MessageBufferFull extends javax.swing.JFrame {

    private static MessageBufferFull instance;

    private static String portugueseTitleMessageBox;
    private static String portugueseStringMessage;
    private static String englishTitleMessageBox;
    private static String englishStringMessage;
    private static boolean canReturn;

    /**
     * Creates new form MessageSleep
     */
    private MessageBufferFull() {
        initComponents();
        portugueseTitleMessageBox = "Buffer Cheio!";
        portugueseStringMessage = "<html>O buffer está cheio. A thread Produtor irá dormir"
                + " pois itens não podem ser colocados em um <br> buffer cheio.\n</hmtl>";
        englishTitleMessageBox = "Empty Buffer!";
        englishStringMessage = "<html>The buffer is full. The thread Producer is going to sleep"
                + " because items can not be put <br> in a full buffer.\n</html>";
        this.setResizable(false);
    }

    private static MessageBufferFull getInstance() {
        if (instance == null) {
            instance = new MessageBufferFull();
        }
        return instance;
    }

    public static void showEnglishInstance() {
        MessageBufferFull.canReturn = false;
        MessageBufferFull.getInstance().jLabel1.setText(englishStringMessage);
        MessageBufferFull.getInstance().jLabel3.setText(englishTitleMessageBox);
        MessageBufferFull.getInstance().setEnabled(true);
        MessageBufferFull.getInstance().setVisible(true);
    }

    public static void showPortugueseInstance() {
        MessageBufferFull.getInstance().jLabel1.setText(portugueseStringMessage);
        MessageBufferFull.getInstance().jLabel3.setText(portugueseTitleMessageBox);
        MessageBufferFull.getInstance().setEnabled(true);
        MessageBufferFull.getInstance().setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jLabel2.setIcon(new javax.swing.ImageIcon("/home/alex/so1/REA/projeto/REA/REA/src/View/imgs/lazy_smile_resized.png")); // NOI18N

        jLabel1.setFont(new java.awt.Font("FreeMono", 0, 16)); // NOI18N
        jLabel1.setText("O buffer está vazio. Está thread irá dormir");

        jLabel3.setFont(new java.awt.Font("FreeMono", 1, 24)); // NOI18N
        jLabel3.setText("Buffer Vazio!");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleName("lblMessageSleep");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MessageBufferFull.getInstance().setVisible(false);
        MessageBufferFull.getInstance().setEnabled(false);
        MainFrame.getInstanceOfMainFrame().setVisible(true);
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
            java.util.logging.Logger.getLogger(MessageBufferFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageBufferFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageBufferFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageBufferFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageBufferFull().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
