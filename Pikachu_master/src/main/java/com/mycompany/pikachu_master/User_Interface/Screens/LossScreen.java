/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pikachu_master.User_Interface.Screens;

import com.mycompany.pikachu_master.Controller.GameConfig;
import com.mycompany.pikachu_master.Controller.PlayScreen;
import com.mycompany.pikachu_master.Model.LevelType;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundLossScreen;
import com.mycompany.pikachu_master.Utils.Button_Icon;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import com.mycompany.pikachu_master.Utils.SoundLoad;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundLossScreen;
import com.mycompany.pikachu_master.Model.LevelType;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundLossScreen;
import com.mycompany.pikachu_master.Utils.Button_Icon;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import com.mycompany.pikachu_master.Utils.SoundLoad;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundLossScreen;
import com.mycompany.pikachu_master.Model.LevelType;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author laptop
 */
public class LossScreen extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LossScreen.class.getName());

    /**
     * Creates new form LossScreen
     */
    
    MainScreen main;
    GameConfig config;
    LevelType level;
    PlayScreen play;
    
    
    
    private SoundLoad audioManager = new SoundLoad();    
    private int displayedScore = 0;
    private int currentTotalScore = 0;
    
    public LossScreen(MainScreen main, GameConfig config , LevelType level, PlayScreen play) {
        this.setUndecorated(true);
        setContentPane(new BackgroundLossScreen());
        initComponents();
        
                 // ---> THÊM ĐOẠN CODE NÀY ĐỂ BO GÓC JFRAME <---
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
                 public void componentResized(java.awt.event.ComponentEvent evt) {
            // Cắt JFrame thành hình chữ nhật bo góc
            // Tham số 40, 40 là độ cong của góc (bạn có thể tăng giảm tùy ý)
            setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
        }
        });
                javax.swing.JPanel contentPane = (javax.swing.JPanel) this.getContentPane();
        contentPane.setBorder(new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                // Bật khử răng cưa cho viền mượt mà
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Chọn màu viền (Ví dụ: Màu Vàng Gold giống TopBar của bạn)
                g2.setColor(new java.awt.Color(255, 215, 0));
                // Chỉnh độ dày của đường viền (4f là 4 pixel)
                g2.setStroke(new java.awt.BasicStroke(4f)); 
                
                // Vẽ viền bo góc 40px (Khớp với thông số 40 của lệnh setShape ở trên)
                // Cộng trừ vài pixel (x+2, y+2, width-4, height-4) để viền không bị lẹm ra ngoài khung
                g2.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 40, 40);
                g2.dispose();
            }
        });
     
        setupAllButtonIcons();
        
        this.setMinimumSize(new java.awt.Dimension(300, 400));
        this.main = main;
        this.config = config;
        this.level = level;
        this.play = play;
        
        // ---> TẠO LỚP PHỦ ĐEN MỜ KHÓA MÀN HÌNH CHÍNH TẠI ĐÂY <---
        this.darkOverlay = new javax.swing.JWindow(main);
        this.darkOverlay.setBounds(main.getBounds()); 
        this.darkOverlay.setBackground(new java.awt.Color(0, 0, 0, 180)); 
        this.darkOverlay.addMouseListener(new java.awt.event.MouseAdapter() {}); 
        this.darkOverlay.setVisible(true); 
        this.setAlwaysOnTop(true);
        
        //this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        
        this.main.stopMusic();
        audioManager.playBGM("/sound/SoundEndMain/Loss.wav");
        this.updateScore(play.get_TotalScore());
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                main.setEnabled(true);
                main.dispose(); 
                StartScreen pika = new StartScreen(config, level, play);
                pika.setLevel(config.GetLevel());
                pika.setVisible(true); // Báo màn hình chính đếm thời gian tiếp
            }
        });
        
    }
    
    public void updateScore(int newScore) {
    // 1. Tính tổng điểm cuối cùng
        this.currentTotalScore = newScore;
        this.displayedScore = 0;

        // 2. Thiết lập cấu hình thời gian
        final int TotalAnimationTime = 2000;
        final int TimeDelay = 20;            

        // 3. Tính toán số bước nhảy và giá trị mỗi bước
        int totalSteps = TotalAnimationTime / TimeDelay; // Tổng số lần Timer sẽ chạy
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
    
    private void setupAllButtonIcons() {
        java.awt.GridBagLayout layout = (java.awt.GridBagLayout) getContentPane().getLayout();
        javax.swing.AbstractButton[] btns = {exitButton, retryButton};
        
        for (javax.swing.AbstractButton btn : btns) {
            java.awt.GridBagConstraints gbc = layout.getConstraints(btn);
            gbc.fill = java.awt.GridBagConstraints.NONE; 
            gbc.ipadx = 0; 
            gbc.ipady = 0; 
            layout.setConstraints(btn, gbc);
            
            java.awt.Dimension fixedSize = new java.awt.Dimension(300, 60);
            btn.setPreferredSize(fixedSize);
            btn.setMinimumSize(fixedSize); 
            btn.setMaximumSize(fixedSize); 
            
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(false);
            
            btn.setForeground(new java.awt.Color(255, 255, 255)); 

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn.setForeground(new java.awt.Color(40, 40, 40)); 
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn.setForeground(new java.awt.Color(255, 255, 255)); 
                }
            });

            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    javax.swing.AbstractButton b = (javax.swing.AbstractButton) c;

                    if (b.getModel().isPressed()) {
                        g2.setColor(new java.awt.Color(200, 200, 200, 255)); 
                    } else if (b.getModel().isRollover()) {
                        g2.setColor(new java.awt.Color(255, 255, 255, 230)); 
                    } else {
                        g2.setColor(new java.awt.Color(0, 0, 0, 150)); 
                    }

                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                    g2.setColor(java.awt.Color.WHITE);
                    g2.setStroke(new java.awt.BasicStroke(1.5f));
                    g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 20, 20);

                    g2.dispose();
                    super.paint(g, c); 
                }
            });
        }
          
        exitButton.setText("THOÁT");
        retryButton.setText("VÁN MỚI");
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

        retryButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        retryButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        retryButton.setText("VÁN MỚI");
        retryButton.setPreferredSize(new java.awt.Dimension(150, 30));
        retryButton.addActionListener(this::retryButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 220;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.insets = new java.awt.Insets(130, 30, 0, 30);
        getContentPane().add(retryButton, gridBagConstraints);

        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitButton.setText("THOÁT");
        exitButton.setPreferredSize(new java.awt.Dimension(150, 30));
        exitButton.addActionListener(this::exitButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 288;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 116, 30);
        getContentPane().add(exitButton, gridBagConstraints);

        scoreLabel.setFont(new java.awt.Font("Segoe UI", 3, 38)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(255, 255, 0));
        scoreLabel.setText("9999");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(138, 110, 0, 0);
        getContentPane().add(scoreLabel, gridBagConstraints);

        setSize(new java.awt.Dimension(464, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void retryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retryButtonActionPerformed
        // TODO add your handling code here:
      audioManager.playSoundEffect("/sound/SoundTap/tap.wav"); // Tiếng click
       audioManager.stopBGM();
       main.setEnabled(true);
       main.resertGame(config, level);
       this.dispose();
    }//GEN-LAST:event_retryButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav"); // Tiếng click
        audioManager.stopBGM();
        
        StartScreen pika = new StartScreen(config, level, play);
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
        //java.awt.EventQueue.invokeLater(() -> new LossScreen().setVisible(true));
    }
    
    private javax.swing.JWindow darkOverlay;

    @Override
    public void dispose() {
        if (darkOverlay != null) {
            darkOverlay.dispose(); 
        }
        super.dispose();
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && darkOverlay != null) {
            darkOverlay.setVisible(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton retryButton;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
