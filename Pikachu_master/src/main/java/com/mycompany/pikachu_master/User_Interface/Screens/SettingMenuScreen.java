/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pikachu_master.User_Interface.Screens;

import com.mycompany.pikachu_master.User_Interface.Components.BackgroundSettingStartScreen;
import com.mycompany.pikachu_master.Utils.Button_Icon;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import com.mycompany.pikachu_master.User_Interface.Components.BackgroundSettingMenuScreen;
import com.mycompany.pikachu_master.Utils.SoundLoad;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author laptop
 */
public class SettingMenuScreen extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SettingMenuScreen.class.getName());
    private SoundLoad audioManager = new SoundLoad();
    /**
     * Creates new form SettingMenuScreen
     */
    StartScreen start;

    public SettingMenuScreen(StartScreen start) {
        this.setUndecorated(true);
        setContentPane(new BackgroundSettingStartScreen());
        initComponents();
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
// 1. Rút nút Back ra khỏi lưới GridBagLayout
        this.getContentPane().remove(exitButton6);
        
        // 2. Ném nó lên tầng LayeredPane (tầng cao nhất, cho phép đặt vị trí tự do)
        this.getLayeredPane().add(exitButton6, javax.swing.JLayeredPane.PALETTE_LAYER);
        
        // 3. Đóng đinh tọa độ tuyệt đối: Cách mép trái 20px, mép trên 20px, kích thước 60x40
        exitButton6.setBounds(10, 10, 50, 30);
        
        ImageLoad.loadBg("PAUSE_BTN", 4, 280, 60, 10);
        ImageLoad.loadBg("MINI_BTN", 4, 50, 30, 10);
        setupAllButtonIcons();

        //this.setMinimumSize(new java.awt.Dimension(300, 400));
        this.start = start;
        
        this.darkOverlay = new javax.swing.JWindow(start);
        this.darkOverlay.setBounds(start.getBounds()); 
        this.darkOverlay.setBackground(new java.awt.Color(0, 0, 0, 180)); 
        this.darkOverlay.addMouseListener(new java.awt.event.MouseAdapter() {}); 
        this.darkOverlay.setVisible(true); 
        this.setAlwaysOnTop(true);
    }

    private void setupAllButtonIcons() {
        // ---> 1. THUỐC ĐẶC TRỊ TẬT KÉO DÃN LỆCH CHỮ CỦA NETBEANS <---
        java.awt.GridBagLayout layout = (java.awt.GridBagLayout) getContentPane().getLayout();
        javax.swing.AbstractButton[] btns = {soundButton, volumnButton, authorButton, exitButton6};

        for (javax.swing.AbstractButton btn : btns) {
            java.awt.GridBagConstraints gbc = layout.getConstraints(btn);
            gbc.fill = java.awt.GridBagConstraints.NONE; // Cấm tiệt việc tự kéo dãn nút
            gbc.ipadx = 0; // Xóa sạch cái lề ảo 150px mà NetBeans tự nhét vào
            layout.setConstraints(btn, gbc);
        }

        //Button_Icon.applyCachedIcons(exitButton6, "CHƠI TIẾP", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(soundButton, "", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(volumnButton, "", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(authorButton, "NHÀ SẢN SUẤT", "PAUSE_BTN");
        Button_Icon.applyCachedIcons(canncelButton, "XÓA DỮ LIỆU", "PAUSE_BTN");
        //Button_Icon.applyCachedIcons(exitButton6, "<", "MINI_BTN");
        
        soundButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        volumnButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        // --- BẮT ĐẦU: ĐỘ NÚT < THÀNH BO GÓC TRONG SUỐT ---
        exitButton6.setContentAreaFilled(false);
        exitButton6.setFocusPainted(false);
        exitButton6.setBorderPainted(false);
        exitButton6.setOpaque(false);
        exitButton6.setForeground(java.awt.Color.WHITE); // Chữ màu trắng
        exitButton6.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)); // Chữ to rõ

        // Ghi đè giao diện UI để tự vẽ hình bo góc
        exitButton6.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                // Bật khử răng cưa cho góc bo mượt mà
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                javax.swing.AbstractButton b = (javax.swing.AbstractButton) c;
                
                // Hiệu ứng đổi màu nền khi di chuột / bấm
                if (b.getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(255, 255, 255, 100)); // Trắng mờ khi bấm
                } else if (b.getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(255, 255, 255, 50)); // Trắng rất mờ khi chuột lướt qua
                } else {
                    g2.setColor(new java.awt.Color(0, 0, 0, 150)); // Nền đen trong suốt lúc bình thường
                }
                
                // Vẽ nền bo góc (Độ cong 20px)
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                
                // Vẽ thêm cái viền trắng mỏng cho sang trọng
                g2.setColor(java.awt.Color.WHITE);
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 20, 20);
                
                g2.dispose();
                super.paint(g, c); // Ra lệnh vẽ cái chữ "<" đè lên trên nền
            }
        });
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

        exitButton6 = new javax.swing.JButton();
        soundButton = new javax.swing.JToggleButton();
        volumnButton = new javax.swing.JToggleButton();
        authorButton = new javax.swing.JButton();
        canncelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        exitButton6.setText("<");
        exitButton6.setPreferredSize(new java.awt.Dimension(50, 30));
        exitButton6.addActionListener(this::exitButton6ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(exitButton6, gridBagConstraints);

        soundButton.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        soundButton.setText("🔊");
        soundButton.setPreferredSize(new java.awt.Dimension(250, 40));
        soundButton.addActionListener(this::soundButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 233;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.insets = new java.awt.Insets(275, 50, 0, 60);
        getContentPane().add(soundButton, gridBagConstraints);

        volumnButton.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        volumnButton.setText("🎧");
        volumnButton.setAutoscrolls(true);
        volumnButton.setPreferredSize(new java.awt.Dimension(250, 40));
        volumnButton.addActionListener(this::volumnButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 233;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.insets = new java.awt.Insets(6, 50, 0, 60);
        getContentPane().add(volumnButton, gridBagConstraints);

        authorButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        authorButton.setForeground(new java.awt.Color(0, 0, 255));
        authorButton.setText("ĐỘI NGŨ SẢN XUẤT");
        authorButton.setPreferredSize(new java.awt.Dimension(250, 40));
        authorButton.addActionListener(this::authorButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.insets = new java.awt.Insets(6, 50, 0, 60);
        getContentPane().add(authorButton, gridBagConstraints);

        canncelButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        canncelButton.setForeground(new java.awt.Color(51, 51, 255));
        canncelButton.setText("XÓA DỮ LIỆU");
        canncelButton.setPreferredSize(new java.awt.Dimension(250, 40));
        canncelButton.addActionListener(this::canncelButtonActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.insets = new java.awt.Insets(6, 50, 153, 60);
        getContentPane().add(canncelButton, gridBagConstraints);

        setSize(new java.awt.Dimension(464, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButton6ActionPerformed
        // TODO add your handling code here:
       audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
        
        start.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_exitButton6ActionPerformed
// này là tiếng thao tác nhé, như kiểu chọn hay ăn á
    private void soundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundButtonActionPerformed
        // TODO add your handling code here:
       if (soundButton.isSelected()) {
            soundButton.setText("🔇");
            SoundLoad.isSfxOn = false; // TẮT tiếng thao tác
        } else {
            soundButton.setText("🔊");
            SoundLoad.isSfxOn = true;  // BẬT tiếng thao tác
            audioManager.playSoundEffect("/sound/SoundTap/tap.wav"); // Phát thử 1 tiếng click cho người dùng biết
        }
    }//GEN-LAST:event_soundButtonActionPerformed

    private void volumnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumnButtonActionPerformed
        // TODO add your handling code here:
        if (volumnButton.isSelected()) {
            volumnButton.setText("🚫");
            SoundLoad.isBgmOn = false; // TẮT trạng thái nhạc nền
            audioManager.stopBGM();    // Dừng nhạc nền đang chạy ngay lập tức
        } else {
            volumnButton.setText("🎧");
            SoundLoad.isBgmOn = true;  // BẬT trạng thái nhạc nền
            audioManager.playBGM("/sound/SoundBackground/SoundStart.wav"); // Phát lại nhạc nền của StartScreen
        }
    }//GEN-LAST:event_volumnButtonActionPerformed

    private void authorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorButtonActionPerformed
        // TODO add your handling code here:
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
    }//GEN-LAST:event_authorButtonActionPerformed

    private void canncelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canncelButtonActionPerformed
        // TODO add your handling code here:
        audioManager.playSoundEffect("/sound/SoundTap/tap.wav");
    }//GEN-LAST:event_canncelButtonActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new SettingMenuScreen().setVisible(true));
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
    private javax.swing.JButton authorButton;
    private javax.swing.JButton canncelButton;
    private javax.swing.JButton exitButton6;
    private javax.swing.JToggleButton soundButton;
    private javax.swing.JToggleButton volumnButton;
    // End of variables declaration//GEN-END:variables
}
