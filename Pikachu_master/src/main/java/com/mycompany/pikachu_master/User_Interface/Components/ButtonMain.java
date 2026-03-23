    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu_master.User_Interface.Components;
import java.awt.Dimension;
import javax.swing.JButton;

public class ButtonMain extends JButton {
    private int xCoord;
    private int yCoord;

    public ButtonMain(int x, int y, String value) {
        super(value);
        this.xCoord = x;
        this.yCoord = y;
        
        this.setContentAreaFilled(false); // Quan trọng nhất: Làm trong suốt nền nút
        this.setFocusPainted(false);       // Làm mất viền mờ khi nút được chọn

    // Mặc định tắt viền đi
        this.setBorderPainted(false); 
    
    // Đảm bảo kích thước cố định
        this.setPreferredSize(new Dimension(55, 55));
        this.setMargin(new java.awt.Insets(0, 0, 0, 0)); // Xóa bỏ lề trong nút
        this.setBorder(null); // Tạm thời xóa border mặc định để các nút khít nhau
    }

    public int getxCoord() { return xCoord; }
    public int getyCoord() { return yCoord; }
}
