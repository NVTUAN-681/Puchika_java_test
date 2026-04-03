/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pikachu_master.User_Interface.Screens;

import com.mycompany.pikachu_master.Controller.GameConfig;
import com.mycompany.pikachu_master.Controller.PlayScreen;
import com.mycompany.pikachu_master.Model.LevelType;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundHonorScreen;
import com.mycompany.pikachu_master.Utils.Button_Icon;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import com.mycompany.pikachu_master.Utils.SoundLoad;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundHonorScreen;
import com.mycompany.pikachu_master.Model.LevelType;



/**
 *
 * @author laptop
 */
public class HonorScreen extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HonorScreen.class.getName());

    /**
     * Creates new form HonorScreen
     */
    MainScreen main;
    GameConfig config;
    LevelType level;
    PlayScreen play;
    
    private int displayedScore = 0;
    private int currentTotalScore = 0;
    
    private SoundLoad audioManager = new SoundLoad();

    public HonorScreen(MainScreen main, GameConfig config, LevelType level, PlayScreen play) {
        this.setUndecorated(true);
        setContentPane(new BackgroundHonorScreen());
        initComponents();
          
        ImageLoad.loadBg("PAUSE_BTN", 2, 250, 40, 10);
        setupAllButtonIcons();
        
        this.setMinimumSize(new java.awt.Dimension(300, 400));
        this.main = main;
        this.config = config;
        this.level = level;
        this.play = play;
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setEnabled(true);
        
        this.updateScore(play.get_TotalScore());
        this.main.stopMusic();
        audioManager.playBGM("/Sound/Winner.wav");
        // Bắt sự kiện bấm nút X

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                main.setEnabled(true);
                main.dispose(); 
                StartScreen pika = new StartScreen(config, level);
                pika.setLevel(config.GetLevel());
                pika.setVisible(true); // Báo màn hình chính đếm thời gian tiếp
            }
        });
    }
    
     private void setupAllButtonIcons() {
        // ---> 1. THUỐC ĐẶC TRỊ TẬT KÉO DÃN LỆCH CHỮ CỦA NETBEANS <---
        java.awt.GridBagLayout layout = (java.awt.GridBagLayout) getContentPane().getLayout();
        javax.swing.AbstractButton[] btns = {exitButton, newButton};
        
        for (javax.swing.AbstractButton btn : btns) {
            java.awt.GridBagConstraints gbc = layout.getConstraints(btn);
            gbc.fill = java.awt.GridBagConstraints.NONE; // Cấm tiệt việc tự kéo dãn nút
            gbc.ipadx = 0; // Xóa sạch cái lề ảo 150px mà NetBeans tự nhét vào
            layout.setConstraints(btn, gbc);
        }
        
        
       Button_Icon.applyCachedIcons(exitButton, "THOÁT", "PAUSE_BTN");
       Button_Icon.applyCachedIcons( newButton, "VÁN MỚI", "PAUSE_BTN");
       
       exitButton.setForeground(java.awt.Color.WHITE);
        newButton.setForeground(java.awt.Color.WHITE);
        
     }
     
public void updateScore(int newScore) {
    // 1. Tính tổng điểm cuối cùng
    this.currentTotalScore = newScore + (play.get_timeRemain() * 10);
    this.displayedScore = 0;

    // 2. Thiết lập cấu hình thời gian
    final int TotalAnimationTime = 2000;
    final int TimeDelay = 20;            
    
    // 3. Tính toán số bước nhảy và giá trị mỗi bước
    int totalSteps = TotalAnimationTime / TimeDelay; // Tổng số lần Timer sẽ chạy
    // Mỗi bước cộng bao nhiêu điểm (dùng số thực để tránh mất mát dữ liệu khi chia)
    final double incrementPerStep = (double) currentTotalScore / totalSteps;

    javax.swing.Timer scoreTimer = new javax.swing.Timer(TimeDelay, null);
    scoreTimer.addActionListener(new java.awt.event.ActionListener() {
        private int currentStep = 0;

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            currentStep++;
            
            if (currentStep <= totalSteps) {
                displayedScore = (int) (currentStep * incrementPerStep);
                if (displayedScore > currentTotalScore) 
                    displayedScore = currentTotalScore;
                
                scoreLabel.setText(String.valueOf(displayedScore));
            } else {
                // Bước cuối cùng: Chốt hạ con số chính xác nhất
                scoreLabel.setText(String.valueOf(currentTotalScore));
                ((javax.swing.Timer) e.getSource()).stop();
            }
        }
    });
    
    scoreTimer.start();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        exitButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        exitButton.setText("THOÁT");
        exitButton.setPreferredSize(new java.awt.Dimension(150, 30));
        exitButton.addActionListener(this::exitButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 78;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.insets = new java.awt.Insets(6, 74, 58, 76);
        getContentPane().add(exitButton, gridBagConstraints);

        newButton.setText("VÁN MỚI");
        newButton.setPreferredSize(new java.awt.Dimension(150, 30));
        newButton.addActionListener(this::newButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.insets = new java.awt.Insets(70, 74, 0, 76);
        getContentPane().add(newButton, gridBagConstraints);

        scoreLabel.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(255, 255, 0));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 118;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.insets = new java.awt.Insets(121, 74, 0, 76);
        getContentPane().add(scoreLabel, gridBagConstraints);

        setSize(new java.awt.Dimension(314, 408));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
       //this.main.playBGM();
       //audioManager.playBGM("/images/Sound/Winner.wav");
       audioManager.stopBGM();
        String levelName = config.GetLevel(); 
        
        if (levelName.equalsIgnoreCase("AFRICA")) {
            audioManager.playBGM("/Sound/SoundAfrica_Europe.wav");
        } else if (levelName.equalsIgnoreCase("ASIAN")) {
            audioManager.playBGM("/Sound/SoundAsian.wav");
        } else if (levelName.equalsIgnoreCase("EUROPE")) {
            audioManager.playBGM("/Sound/SoundAfrica_Europe.wav");
        } else {
            // Nhạc mặc định nếu người chơi không chọn màn mà bấm Play ngay từ đầu
            audioManager.playBGM("/Sound/SoundAfrica_Europe.wav");
        }

        main.setEnabled(true);
        main.resertGame(config, level);
        this.dispose();
    }//GEN-LAST:event_newButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
          audioManager.stopBGM();
        StartScreen pika = new StartScreen(config, level);
        pika.setLevel(config.GetLevel());
        pika.setVisible(true);
        main.dispose();
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new HonorScreen().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
