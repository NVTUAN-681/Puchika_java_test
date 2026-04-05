/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu_master;

import com.mycompany.pikachu_master.User_Interface.Screens.StartScreen;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
public static void main(String args[]) {
    // 1. Cài đặt Look and Feel (Nimbus)
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(StartScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    // 2. Chạy ứng dụng
    java.awt.EventQueue.invokeLater(() -> {
        // Gọi Constructor không tham số vừa tạo ở trên
        StartScreen mainFrame = new StartScreen();
        mainFrame.setVisible(true);
    });
}
}
