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

import java.awt.Component;
import Model.*;
import Controller.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class MainFrame extends javax.swing.JFrame {

    /*the singleton instance of MainFrame*/
    private static MainFrame instanceOfMainFrame = null;

    /*the singleton instance of Scheduler*/
    private Scheduler sched = null;

    /*the context of thread Producer*/
    ThreadContext producer = null;

    /*reference to the singleton buffer*/
    Buffer buff = null;

    /*the context of thread Consumer*/
    ThreadContext consumer = null;

    /*the string printed in jTextArea, telling the steps the program is taking*/
    private String verboseEnglish;
    private String verbosePortuguese;

    private int defaultHeight;

    /**
     * Creates new form MainFrame
     */
    private MainFrame() {
        initComponents();
        sched = Scheduler.getInstance();
        defaultHeight = this.getHeight();
        producer = ProducerThread.getInstance().getCurrentContext();
        consumer = ConsumerThread.getInstance().getCurrentContext();
        buff = Buffer.getInstance();
        verboseEnglish = "";
        verbosePortuguese = "";
    }

    /**
     * Using the Singleton design patter, gets the singleton instance of MainFrame
     * @return the singleton instance of MainFrame
     */
    public static MainFrame getInstanceOfMainFrame() {
        if (instanceOfMainFrame == null) {
            instanceOfMainFrame = new MainFrame();
        }
        return instanceOfMainFrame;
    }

    /**
     * Update the view od ProducerThread
     */
    public void updateProducer() {
        updateViewPaneProducer();
        updateViewPaneBuffer();
        updateViewCodeProducer();
    }

    /**
     * Update the viw of ConsumerThread
     */
    public void updateConsumer() {
        updateViewPaneConsumer();
        updateViewPaneBuffer();
        updateViewCodeConsumer();
    }

    /**
     * Translates all the interaction to English Language
     */
    public void viewEnglishLanguage() {
        jLabel10.setText("Code of Producer");
        jLabel11.setText("Code of Consumer");
        TitledBorder tb1 = (TitledBorder) jPanel4.getBorder();
        tb1.setTitle("Producer");
        TitledBorder tb2 = (TitledBorder) jPanel3.getBorder();
        tb2.setTitle("Consumer");
        TitledBorder tb3 = (TitledBorder) jPanel7.getBorder();
        tb3.setTitle("Options:");
        jLabel13.setText("PRODUCER");
        jLabel14.setText("BACK");
        jLabel12.setText("CONSUMER");
        jCheckBox1.setText("Enable verbose");
        jLabel15.setText("Idiom:");
        jMenu1.setText("Informations");
        jMenuItem1.setText("The Producer/Consumer Problem");
        jMenuItem2.setText("License");
        jMenuItem3.setText("How to use this program");
    }

    /**
     * Translates all the interaction to Portuguese language
     */
    public void viewPortugueseLanguage() {
        jLabel10.setText("Código do Produtor");
        jLabel11.setText("Código do Consumidor");
        TitledBorder tb = (TitledBorder) jPanel4.getBorder();
        tb.setTitle("Produtor:");
        TitledBorder tb2 = (TitledBorder) jPanel3.getBorder();
        tb2.setTitle("Consumidor:");
        TitledBorder tb3 = (TitledBorder) jPanel7.getBorder();
        tb3.setTitle("Opções:");
        jLabel13.setText("PRODUTOR");
        jLabel14.setText("RETORNAR");
        jLabel12.setText("CONSUMIDOR");
        jCheckBox1.setText("Ativar verbose");
        jLabel15.setText("Idioma:");
        jMenu1.setText("Informações");
        jMenuItem1.setText("O problema do Produtor/Consumidor");
        jMenuItem2.setText("Licença");
        jMenuItem3.setText("Como usar este programa");
    }

    /**
     * Update the C language code of thread Producer at MainFrame
     */
    void updateViewCodeProducer() {
        int currentStackPointer = producer.getStackPointer();
        String imageName = "";

        switch (currentStackPointer) {
            case -2:
                imageName = "./imgs/p1.png";
                break;
            case -1:
                imageName = "./imgs/p2.png";
                break;
            case 0:
                imageName = "./imgs/p3.png";
                break;
            case 1:
                imageName = "./imgs/p4.png";
                break;
            case 2:
                imageName = "./imgs/p5.png";
                break;
            case 3:
                imageName = "./imgs/p6.png";
                break;
            case 4:
                imageName = "./imgs/p7.png";
                break;
            case 5:
                imageName = "./imgs/p8.png";
                break;
            case 6:
                imageName = "./imgs/p9.png";
                break;
            case 7:
                imageName = "./imgs/p10.png";
                break;
            default:
                imageName = "./imgs/c0.png";
                break;
        }
        try {
            jLabel1.setIcon(new ImageIcon(ImageIO.read( new File(imageName) ) ));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (currentStackPointer == 6) {
            updateViewPaneCounter();
        }
    }

    /**
     * Update the C language code of thread Consumer at MainFrame
     */
    void updateViewCodeConsumer() {
        int currentLineCode = consumer.getStackPointer();
        String imageName = "";

        switch (currentLineCode) {
            case -2:
                imageName = "./imgs/c1.png";
                break;
            case -1:
                imageName = "./imgs/c2.png";
                break;
            case 0:
                imageName = "./imgs/c3.png";
                break;
            case 1:
                imageName = "./imgs/c4.png";
                break;
            case 2:
                imageName = "./imgs/c5.png";
                break;
            case 3:
                imageName = "./imgs/c6.png";
                break;
            case 4:
                imageName = "./imgs/c7.png";
                break;
            case 5:
                imageName = "./imgs/c8.png";
                break;
            case 6:
                imageName = "./imgs/c9.png";
                break;
            case 7:
                imageName = "./imgs/c10.png";
                break;
            default:
                imageName = "./imgs/c0.png";
                break;
        }

        try {
            jLabel3.setIcon(new ImageIcon(ImageIO.read( new File(imageName) ) ));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (currentLineCode == 5) {
            updateViewPaneCounter();
        }
    }

    /**
     * Update all the grouped informations about the context of thread Producer
     * in its JPane.
     */
    void updateViewPaneProducer() {
        int currentProducerStatus;
        producer = ProducerThread.getInstance().getCurrentContext();
        jTextField1.setText(getImageLineProducer());
        jTextField2.setText(String.valueOf(producer.getProducedItem()));
        currentProducerStatus = producer.getStatus();
        setIdiomStatusProducer();
    }

    /**
     * Update all the grouped informations about the context of thread Consumer
     * in its JPane.
     */
    void updateViewPaneConsumer() {
        int currentConsumerStatus;
        consumer = ConsumerThread.getInstance().getCurrentContext();
        jTextField4.setText(getImageLineConsumer());
        jTextField5.setText(String.valueOf(consumer.getProducedItem()));
        setIdiomStatusConsumer();
    }

    /**
     * Update the display label of variable Count.<p>
     * (Displays the new size of buffer to the user)
     */
    void updateViewPaneCounter() {
        jTextField7.setText(String.valueOf(buff.getBufferSize()));
    }

    /**
     * Sets the translated informations according to the chosen language for
     * the thread Producer.<p>
     */
    public void setIdiomStatusProducer() {
        int currentProducerStatus = producer.getStatus();
        if (getLanguage() == 0) {
            switch (currentProducerStatus) {
            case Consts.STATUS_THREAD_EXECUTING:
                jTextField3.setText("Executando");
                break;
            case Consts.STATUS_THREAD_READY_TO_EXEC:
                jTextField3.setText("Pronto para executar");
                break;
            case Consts.STATUS_THREAD_BLOCKED:
                jTextField3.setText("Bloqueado");
                break;
            case Consts.STATUS_THREAD_GOING_BLOCK:
                jTextField3.setText("Executando");
                break;
            }
        } else {
            switch (currentProducerStatus) {
            case Consts.STATUS_THREAD_EXECUTING:
                jTextField3.setText("Running");
                break;
            case Consts.STATUS_THREAD_READY_TO_EXEC:
                jTextField3.setText("Ready");
                break;
            case Consts.STATUS_THREAD_BLOCKED:
                jTextField3.setText("Blocked");
                break;
            case Consts.STATUS_THREAD_GOING_BLOCK:
                jTextField3.setText("Running");
                break;
            }
        }
    }
    
    /**
     * Sets the translated informations according to the chosen language for
     * the thread Consumer.<p>
     */
    public void setIdiomStatusConsumer() {
        int currentConsumerStatus = consumer.getStatus();
        if (getLanguage() == 0) {
            switch (currentConsumerStatus) {
                case Consts.STATUS_THREAD_EXECUTING:
                    jTextField6.setText("Executando");
                    break;
                case Consts.STATUS_THREAD_READY_TO_EXEC:
                    jTextField6.setText("Pronto");
                    break;
                case Consts.STATUS_THREAD_BLOCKED:
                    jTextField6.setText("Bloqueado");
                    break;
                case Consts.STATUS_THREAD_GOING_BLOCK:
                    jTextField6.setText("Executando");
                    break;
            }
        } else {
            switch (currentConsumerStatus) {
                case Consts.STATUS_THREAD_EXECUTING:
                    jTextField6.setText("Running");
                    break;
                case Consts.STATUS_THREAD_READY_TO_EXEC:
                    jTextField6.setText("Ready");
                    break;
                case Consts.STATUS_THREAD_BLOCKED:
                    jTextField6.setText("Blocked");
                    break;
                case Consts.STATUS_THREAD_GOING_BLOCK:
                    jTextField6.setText("Running");
                    break;
            }
        }
    }
    
    /**
     * Updates the view for slots value of buffer.<p>
     */
    void updateViewPaneBuffer() {
        int i = 0;
        JTextField slots[] = new JTextField[5];
        slots[0] = jTextField8;
        slots[1] = jTextField9;
        slots[2] = jTextField10;
        slots[3] = jTextField11;
        slots[4] = jTextField12;

        for (i = 0; i < Consts.MAX_SIZE_BUFFER; i++) {
            if (i < buff.getBufferSize()) {
                slots[i].setText(String.valueOf(buff.get(i)));
            } else {
                slots[i].setText(" ");
            }

        }
    }

    /**
     * Gets the selected line of code from Consumer source code.
     * @return the string contaning the suffix for the image file of the current selected line.
     */
    String getImageLineConsumer() {
        int currentLine = consumer.getStackPointer();
        String retval = "0";

        switch (currentLine) {
            case -2:
                retval = "1";
                break;
            case -1:
                retval = "2";
                break;
            case 0:
                retval = "4";
                break;
            case 1:
                retval = "6";
                break;
            case 2:
                retval = "7";
                break;
            case 3:
                retval = "9";
                break;
            case 4:
                retval = "10";
                break;
            case 5:
                retval = "11";
                break;
            case 6:
                retval = "12";
                break;
            case 7:
                retval = "14";
                break;
        }
        return retval;
    }

    /**
     * Gets the selected line of code from Producer source code.
     * @return the string contaning the suffix for the image file of the current selected line.
     */
    String getImageLineProducer() {
        int currentLine = producer.getStackPointer();
        String retval = "0";

        switch (currentLine) {
            case -2:
                retval = "1";
                break;
            case -1:
                retval = "2";
                break;
            case 0:
                retval = "4";
                break;
            case 1:
                retval = "6";
                break;
            case 2:
                retval = "7";
                break;
            case 3:
                retval = "8";
                break;
            case 4:
                retval = "10";
                break;
            case 5:
                retval = "11";
                break;
            case 6:
                retval = "12";
                break;
            case 7:
                retval = "13";
                break;
        }
        return retval;
    }

    /**
     * Let user schedule Consumer (press the button next for Consumer)
     */
    public static void enableSchedulingConsumer() {
        MainFrame.getInstanceOfMainFrame().jButton3.setEnabled(true);
    }

    /**
     * Prohibits user schedule Consumer (press the button next for Consumer)
     */
    public static void unableSchedulingConsumer() {
        MainFrame.getInstanceOfMainFrame().jButton3.setEnabled(false);
    }

    /**
     * Let user schedule Producer (press the button next for Producer)
     */
    public static void enableSchedulingProducer() {
        MainFrame.getInstanceOfMainFrame().jButton4.setEnabled(true);
    }

    /**
     * Prohibits user schedule Producer (press the button next for Producer)
     */
    public static void unableSchedulingProducer() {
        MainFrame.getInstanceOfMainFrame().jButton4.setEnabled(false);
    }

    /**
     * Get the select idiom by the user. <p>
     * @return 
     */
    public int getLanguage() {
        return jComboBox1.getSelectedIndex();
    }

    /**
     * Appends information to the console about sleeping thread.
     * @param thread which the information come from.
     */
    public void warnSleepingThread(int thread) {
        if (thread == Consts.THREAD_PRODUCER) {
            verboseEnglish += "> Thread Producer is sleeping and cannot be executed.\n";
            verbosePortuguese += "> Thread Produtor está dormindo e não pode ser executada.\n";
        } else if (thread == Consts.THREAD_CONSUMER) {
            verboseEnglish += "> Thread Consumer is sleeping and cannot be executed.\n";
            verbosePortuguese += "> Thread Consumidor está dormindo e não pode ser executada.\n";
        }
    }

    /**
     * Appends information to the console about scheduled thread.
     * @param thread which the information come from.
     */
    public void warnScheduledThread(int thread) {
        if (thread == Consts.THREAD_PRODUCER) {
            verboseEnglish += "> Scheduling Thread Producer.\n";
            verbosePortuguese += "> Escalonando Thread Produtor\n";
        } else if (thread == Consts.THREAD_CONSUMER) {
            verboseEnglish += "> Scheduling Thread Consumer.\n";
            verbosePortuguese += "> Escalonando Thread Consumidor\n";
        }
    }
    
    /**
     * Appends information to the console about almost sleeping thread.
     * @param thread which the information come from.
     */
    public void warnAlmostSleeping(int thread) {
        if (thread == Consts.THREAD_PRODUCER) {
            verboseEnglish += "> The sleeping condition of the Thread Producer has been satisfied "
                    + "and the thread will sleep when the next line is "
                    + "executed\n";
            verbosePortuguese += "> A condição de dormir da Thread Produtor foi satisfeita e "
                    + "a thread irá dormir quando a próxima linha for executada\n";
        } else if (thread == Consts.THREAD_CONSUMER) {
            verboseEnglish += "> The sleeping condition of the Thread Consumer has been satisfied "
                    + "and the thread will sleep when the next line is "
                    + "executed\n";
            verbosePortuguese += "> A condição de dormir da Thread Consumidor foi satisfeita e "
                    + "a thread irá dormir quando a próxima linha for executada\n";
            
        }
    }
    
    /**
     * Appends information to the console about deadlock situation.<p>
     * This function shall be called only once when the threads cannot wake up each ohter.
     */
    public void warnDeadlock() {
        verboseEnglish += "Both threads Producer and Consumer will sleep forever because one can not "
                       + "wake up the other\n";
        verbosePortuguese += "As threads Produtor e Consumidor irão dormir para sempre porque uma"
                       + "não pode acordar a outra";
        this.setEnabled(false);
        if (this.getLanguage() == 0) {
            MessageDeadlock.showPortugueseInstance();
        } else if (this.getLanguage() == 1) {
            MessageDeadlock.showEnglishInstance();
        }
    }
    
    /**
     * Updates the language and appends new information to the console.
     */
    public void updateConsoleText() {
        jTextArea1.setText("");
        if (jComboBox1.getSelectedIndex() == 0) {
            jTextArea1.setText(verbosePortuguese);
        } else {
            jTextArea1.setText(verboseEnglish);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Produtor/Consumidor - Sleep/Wake_Up");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/consumer0.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/producer0.png"))); // NOI18N

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumidor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Program Counter");

        jTextField4.setEditable(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Item");

        jTextField5.setEditable(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Status");

        jTextField6.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Program Counter");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Item");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Status");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel10.setText("Código Produtor");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Código Consumidor");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1.png"))); // NOI18N
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1rollover_selected.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1_rollover.png"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1rollover_selected.png"))); // NOI18N
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1_selected.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1.png"))); // NOI18N
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1rollover_selected.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1_rollover.png"))); // NOI18N
        jButton4.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1rollover_selected.png"))); // NOI18N
        jButton4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/next_icon1_selected.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("CONSUMIDOR");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("PRODUTOR");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back_icon.png"))); // NOI18N
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back_iconrollover_selected.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back_icon_rollover.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back_icon_selected.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back_icon_selected.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("RETORNAR");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Count", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buffer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Ativar Verbose");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Portugês", "English" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Idioma");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setText("Informações");

        jMenuItem1.setText("O Problema do Produtor/Consumidor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Como usar este programa");
        jMenu1.add(jMenuItem3);

        jMenuItem2.setText("Licença");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jLabel3.getAccessibleContext().setAccessibleName("labelSourceProducer");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    /**
     * Slect actions when the Console ins enabled/unabled by the user.
     * @param evt the click of the mouse.
     */
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.getSelectedObjects() == null) {
            jTextArea1.setVisible(false);
            this.setSize(this.getWidth(), this.defaultHeight - 120);
        } else {
            jTextArea1.setVisible(true);
            this.setSize(this.getWidth(), this.defaultHeight);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * Actions to be taken when user change the idiom. <p>
     * @param evt 
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        if (jComboBox1.getSelectedIndex() == 0) {
            viewPortugueseLanguage();
            jTextArea1.setText(verbosePortuguese);
        } else {
            viewEnglishLanguage();
            jTextArea1.setText(verboseEnglish);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed

    }//GEN-LAST:event_jTextField10ActionPerformed
    /**
     * Actions to be takne when user try to shcedule thread Producer.
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (producer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            /*echos a warning of sleeping thread*/
            warnSleepingThread(Consts.THREAD_PRODUCER);
        } else {
            /*echos a warning of scheduled thread*/
            warnScheduledThread(Consts.THREAD_PRODUCER);
            /*do the context switching*/
            sched.doContextSwitch(Consts.EXECUTE_NEXT_PRODUCER);
            /*update the form*/
            updateProducer();
            updateConsumer();
            /*checks whehther the thread was blocked - went sleep*/
            if (producer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
                warnSleepingThread(Consts.THREAD_PRODUCER);
                unableSchedulingProducer();
                if (sched.checkDeadlockStatus()) {
                    warnDeadlock();
                }
                
            } else if (producer.getStatus() == Consts.STATUS_THREAD_GOING_BLOCK) {
                /*warning for almost sleeping thread*/
                warnAlmostSleeping(Consts.THREAD_PRODUCER);
                this.setEnabled(false);
                if (jComboBox1.getSelectedIndex() == 0) {
                    MessageBufferFull.showPortugueseInstance();
                } else {
                    MessageBufferFull.showEnglishInstance();
                }
            }
        }
        updateConsoleText();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Actions to be takne when user try to shcedule thread Producer.
     * @param evt 
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (consumer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            /*echos a warning of sleeping thread*/
            warnSleepingThread(Consts.THREAD_CONSUMER);
        } else {
            /*echos a warning of scheduled thread*/
            warnScheduledThread(Consts.THREAD_CONSUMER);
            /*do the context switching*/
            sched.doContextSwitch(Consts.EXECUTE_NEXT_CONSUMER);
            /*update the view*/
            updateConsumer();
            updateProducer();
            /*checks whether the thread was blocked - went sleep*/
            if (consumer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
                warnSleepingThread(Consts.THREAD_CONSUMER);
                unableSchedulingConsumer();
                if (sched.checkDeadlockStatus()) {
                    warnDeadlock();
                }
            } else if (consumer.getStatus() == Consts.STATUS_THREAD_GOING_BLOCK) {
                /*warning for almost sleeping thread*/
                this.setEnabled(false);
                warnAlmostSleeping(Consts.THREAD_CONSUMER);
                if (jComboBox1.getSelectedIndex() == 0) {
                    MessageBufferEmpty.showPortugueseInstance();
                } else {
                    MessageBufferEmpty.showEnglishInstance();
                }
            }
        }
        updateConsoleText();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Take back one step the last action (scheduled thread) of the user.
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sched.doContextSwitch(Consts.EXECUTE_BACK);
        updateProducer();
        updateConsumer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
