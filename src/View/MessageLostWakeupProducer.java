/*
 *   Author: Alex Frederico Ramos Barboza <alex.barboza@usp.br>
 *   Author: Bruno Stefano <bruno.stefano@usp.br>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 */
package View;

public class MessageLostWakeupProducer extends javax.swing.JFrame {

    private static MessageLostWakeupProducer instance;

    private static String portugueseTitleMessageBox;
    private static String portugueseStringMessage;
    private static String englishTitleMessageBox;
    private static String englishStringMessage;
    private static boolean canReturn;

    /**
     * Creates new form MessageSleep
     */
    private MessageLostWakeupProducer() {
        initComponents();
        portugueseTitleMessageBox = "Sinal de wakeup perdido!";
        portugueseStringMessage = "<html>Você não pode acordar uma thread que já está acordada!<br>"
                                    + ">>>O sinal de Wakeup foi perdido!<<<</hmtl>";
        englishTitleMessageBox = "Lost wakeup!";
        englishStringMessage = "<html>You can not wake up a thread that is already awake!<br>"
                                    + ">>>The signal of wake up was lost!<<<</html>";
        this.setResizable(false);
    }

    private static MessageLostWakeupProducer getInstance() {
        if (instance == null) {
            instance = new MessageLostWakeupProducer();
        }
        return instance;
    }

    public static void showEnglishInstance() {
        MessageLostWakeupProducer.canReturn = false;
        MessageLostWakeupProducer.getInstance().jLabel1.setText(englishStringMessage);
        MessageLostWakeupProducer.getInstance().jLabel3.setText(englishTitleMessageBox);
        MessageLostWakeupProducer.getInstance().setEnabled(true);
        MessageLostWakeupProducer.getInstance().setVisible(true);
    }

    public static void showPortugueseInstance() {
        MessageLostWakeupProducer.getInstance().jLabel1.setText(portugueseStringMessage);
        MessageLostWakeupProducer.getInstance().jLabel3.setText(portugueseTitleMessageBox);
        MessageLostWakeupProducer.getInstance().setEnabled(true);
        MessageLostWakeupProducer.getInstance().setVisible(true);
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

        jLabel2.setIcon(new javax.swing.ImageIcon("/home/alex/so1/REA/projeto/REA/REA/src/View/imgs/lost_wakeup_resized.png")); // NOI18N

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
                .addContainerGap(105, Short.MAX_VALUE))
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
        MessageLostWakeupProducer.getInstance().setVisible(false);
        MessageLostWakeupProducer.getInstance().setEnabled(false);
        MainFrame.getInstanceOfMainFrame().setVisible(true);
        MainFrame.getInstanceOfMainFrame().setEnabled(true);
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
            java.util.logging.Logger.getLogger(MessageLostWakeupProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageLostWakeupProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageLostWakeupProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageLostWakeupProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageLostWakeupProducer().setVisible(true);
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
