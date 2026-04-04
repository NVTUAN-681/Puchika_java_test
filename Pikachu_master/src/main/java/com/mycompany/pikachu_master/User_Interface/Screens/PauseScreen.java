/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pikachu_master.User_Interface.Screens;

import com.mycompany.pikachu_master.Controller.GameConfig;
import com.mycompany.pikachu_master.Controller.PlayScreen;
import com.mycompany.pikachu_master.Model.LevelType;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundPause;
import com.mycompany.pikachu_master.User_Interface.Screens.MainScreen;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import com.mycompany.pikachu_master.Utils.Button_Icon;
import com.mycompany.pikachu_master.Utils.SoundLoad;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JWindow;

/**
 *
 * @author laptop
 */
public class PauseScreen extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PauseScreen.class.getName());

    /**
     * Creates new form setting
     */
    MainScreen main;
    GameConfig config;
    LevelType level;
    PlayScreen play;
    private final JWindow darkOverlay;
    private SoundLoad audioManager = new SoundLoad();

    public PauseScreen(MainScreen main, GameConfig config, LevelType level, PlayScreen play) {
        this.setUndecorated(true);
        setContentPane(new BackgroundPause());
        initComponents();
        // 1. Đồng bộ nút Nhạc nền
        if (SoundLoad.isBgmOn) {
            volumnButton.setSelected(false);
            volumnButton.setText("🎧");
        } else {
            volumnButton.setSelected(true);
            volumnButton.setText("🚫");
        }

        // 2. Đồng bộ nút Âm thanh thao tác
        if (SoundLoad.isSfxOn) {
            soundButton.setSelected(false);
            soundButton.setText("🔊");
        } else {
            soundButton.setSelected(true);
            soundButton.setText("🔇");
        }
        // ---> THÊM ĐOẠN CODE NÀY ĐỂ BO GÓC JFRAME <---
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Cắt JFrame thành hình chữ nhật bo góc
                // Tham số 40, 40 là độ cong của góc (bạn có thể tăng giảm tùy ý)
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
            }
        });

        // ---> BẮT ĐẦU THÊM MỚI TỪ ĐÂY: VẼ ĐƯỜNG VIỀN MÀU (BORDER) BO TRÒN THEO KHUNG <---
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
        //this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        main.setEnabled(true);

        // Gọi hàm loadBg mới, truyền số 2 để lôi ảnh BạkgroundButtonMainGame.jpg ra xài
        ImageLoad.loadBg("PAUSE_BTN", 4, 250, 60, 10);
        ImageLoad.loadBg("HALF_BTN", 4, 122, 40, 10);
       
        setupAllButtonIcons();
        // ---> ÉP CỨNG FONT EMOJI SAU KHI GẮN PHÔI <---
        java.awt.Font emojiFont = new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 22);
        volumnButton.setFont(emojiFont);
        soundButton.setFont(emojiFont);

        // 1. Đồng bộ và GHI LẠI Emoji cho nút Nhạc nền
        if (SoundLoad.isBgmOn) {
            volumnButton.setSelected(false);
            volumnButton.setText("🎧"); // Ghi đè lại Emoji
        } else {
            volumnButton.setSelected(true);
            volumnButton.setText("🚫"); 
        }

        // 2. Đồng bộ và GHI LẠI Emoji cho nút Âm thanh
        if (SoundLoad.isSfxOn) {
            soundButton.setSelected(false);
            soundButton.setText("🔊"); // Ghi đè lại Emoji
        } else {
            soundButton.setSelected(true);
            soundButton.setText("🔇");
        }
        
        this.level = level;
        this.config = config;
        this.main = main;
        this.play = play;

        // ---> TẠO LỚP PHỦ ĐEN MỜ KHÓA MÀN HÌNH CHÍNH TẠI ĐÂY <---
        this.darkOverlay = new javax.swing.JWindow(main);
        this.darkOverlay.setBounds(main.getBounds()); // Phủ kín toàn bộ MainScreen
        this.darkOverlay.setBackground(new java.awt.Color(0, 0, 0, 180)); // Màu đen, độ mờ 180/255
        this.darkOverlay.addMouseListener(new java.awt.event.MouseAdapter() {
        }); // Hút hết click chuột (không cho bấm xuyên qua)
        this.darkOverlay.setVisible(true); // Bật lớp kính lên
        this.setAlwaysOnTop(true); // Đảm bảo Menu Pause luôn nổi lên trên cùng
    }

    private void setupAllButtonIcons() {
        // ---> 1. THUỐC ĐẶC TRỊ TẬT KÉO DÃN LỆCH CHỮ CỦA NETBEANS <---
        java.awt.GridBagLayout layout = (java.awt.GridBagLayout) getContentPane().getLayout();
        javax.swing.AbstractButton[] btns = {Choi_tiep, Van_moi, instructionButton1, exitmenuButton, soundButton, volumnButton};

        for (javax.swing.AbstractButton btn : btns) {
            java.awt.GridBagConstraints gbc = layout.getConstraints(btn);
            gbc.fill = java.awt.GridBagConstraints.NONE; // Cấm tiệt việc tự kéo dãn nút
            gbc.ipadx = 0; // Xóa sạch cái lề ảo 150px mà NetBeans tự nhét vào
            layout.setConstraints(btn, gbc);
        }
        // --------------------------------------------------------------

        // ---> 2. GẮN BACKGROUND VÀ CHỮ (Tao đã đổi nút cuối thành THOÁT) <---
        Button_Icon.applyCachedIcons(Choi_tiep, "CHƠI TIẾP", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(Van_moi, "VÁN MỚI", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(instructionButton1, "HƯỚNG DẪN", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(exitmenuButton, "THOÁT", "PAUSE_BTN");

        Button_Icon.applyCachedIcons(soundButton, "", "HALF_BTN");
        Button_Icon.applyCachedIcons(volumnButton, "", "HALF_BTN");

        soundButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volumnButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        // ---> 3. ĐỔI MÀU CHỮ SANG TRẮNG <---
        for (javax.swing.AbstractButton btn : btns) {
            btn.setForeground(java.awt.Color.WHITE);
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
        java.awt.GridBagConstraints gridBagConstraints;

        Choi_tiep = new javax.swing.JButton();
        Van_moi = new javax.swing.JButton();
        instructionButton1 = new javax.swing.JButton();
        exitmenuButton = new javax.swing.JButton();
        volumnButton = new javax.swing.JToggleButton();
        soundButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        Choi_tiep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Choi_tiep.setText("CHƠI TIẾP");
        Choi_tiep.setPreferredSize(new java.awt.Dimension(250, 40));
        Choi_tiep.addActionListener(this::Choi_tiepActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(150, 26, 0, 24);
        getContentPane().add(Choi_tiep, gridBagConstraints);

        Van_moi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Van_moi.setText("VÁN MỚI");
        Van_moi.setPreferredSize(new java.awt.Dimension(250, 40));
        Van_moi.addActionListener(this::Van_moiActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(0, 26, 0, 24);
        getContentPane().add(Van_moi, gridBagConstraints);

        instructionButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        instructionButton1.setText("HƯỚNG DẪN");
        instructionButton1.setPreferredSize(new java.awt.Dimension(250, 40));
        instructionButton1.addActionListener(this::instructionButton1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 129;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(0, 26, 0, 24);
        getContentPane().add(instructionButton1, gridBagConstraints);

        exitmenuButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitmenuButton.setText("MÀN HÌNH CHÍNH");
        exitmenuButton.setPreferredSize(new java.awt.Dimension(250, 40));
        exitmenuButton.addActionListener(this::exitmenuButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 91;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(0, 26, 0, 24);
        getContentPane().add(exitmenuButton, gridBagConstraints);

        volumnButton.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        volumnButton.setText("🎧");
        volumnButton.setPreferredSize(new java.awt.Dimension(122, 40));
        volumnButton.setRolloverEnabled(false);
        volumnButton.addActionListener(this::volumnButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 91;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 94, 24);
        getContentPane().add(volumnButton, gridBagConstraints);

        soundButton.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        soundButton.setText("🔊");
        soundButton.setMaximumSize(new java.awt.Dimension(125, 40));
        soundButton.setPreferredSize(new java.awt.Dimension(122, 40));
        soundButton.setRolloverEnabled(false);
        soundButton.addActionListener(this::soundButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 91;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(6, 26, 94, 3);
        getContentPane().add(soundButton, gridBagConstraints);

        setSize(new java.awt.Dimension(464, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void volumnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumnButtonActionPerformed
        // TODO add your handling code here:
        if (volumnButton.isSelected()) {
            volumnButton.setText("🚫"); // Đổi Emoji
            SoundLoad.isBgmOn = false; 
            audioManager.stopBGM();    
        } else {
            volumnButton.setText("🎧"); // Đổi Emoji
            SoundLoad.isBgmOn = true;  
            main.playMusicByLevel();   
        }
    }//GEN-LAST:event_volumnButtonActionPerformed

    private void Choi_tiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Choi_tiepActionPerformed
        // TODO add your handling code here:  
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
        play.resumeTimer();
        //this.setVisible(false);
        this.dispose();
        play.countdownTimer.start();
    }//GEN-LAST:event_Choi_tiepActionPerformed

    private void Van_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Van_moiActionPerformed
        // TODO add your handling code here:
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
        main.resertGame(config, level);
        this.dispose();
    }//GEN-LAST:event_Van_moiActionPerformed

    private void exitmenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitmenuButtonActionPerformed
        // TODO add your handling code here:
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
        this.main.stopMusic();
        StartScreen pika = new StartScreen(config, level, play);
        pika.setLevel(config.GetLevel());
        pika.setVisible(true);
        main.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_exitmenuButtonActionPerformed

    private void instructionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionButton1ActionPerformed
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
        HelpMainScreen help = new HelpMainScreen(this);
        help.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_instructionButton1ActionPerformed

    private void soundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundButtonActionPerformed
        // TODO add your handling code here:                                          
       if (soundButton.isSelected()) {
            soundButton.setText("🔇"); // Đổi Emoji
            SoundLoad.isSfxOn = false; 
        } else {
            soundButton.setText("🔊"); // Đổi Emoji
            SoundLoad.isSfxOn = true;  
            audioManager.playSoundEffect("/sound/SoundTap/tap.wav"); 
        }
    }//GEN-LAST:event_soundButtonActionPerformed

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
    }
    
    @Override
    public void dispose() {
        if (darkOverlay != null) {
            darkOverlay.dispose(); 
        }
        super.dispose();
    }
    
    // Ghi đè hàm setVisible: Nếu ẩn menu đi thì cũng phải ẩn kính mờ
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && darkOverlay != null) {
            darkOverlay.setVisible(false);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Choi_tiep;
    private javax.swing.JButton Van_moi;
    private javax.swing.JButton exitmenuButton;
    private javax.swing.JButton instructionButton1;
    private javax.swing.JToggleButton soundButton;
    private javax.swing.JToggleButton volumnButton;
    // End of variables declaration//GEN-END:variables
}
