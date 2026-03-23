/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu_master.Controller;

import com.mycompany.pikachu_master.Algorithm.ClassicAlgorithm;
import com.mycompany.pikachu_master.Model.Board;
import com.mycompany.pikachu_master.Model.Cell;
import com.mycompany.pikachu_master.User_Interface.Components.ButtonMain;
import com.mycompany.pikachu_master.User_Interface.Screens.LevelScreen;
import com.mycompany.pikachu_master.Utils.ImageLoad;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class PlayScreen extends JPanel implements ActionListener {
    private Board board;
    private ClassicAlgorithm algorithm;
    private ButtonMain[][] btnMatrix;
    private Cell firstClick = null;
    private GameConfig config;
    

    public PlayScreen(GameConfig config ) {
        
        int rows = config.GetRows();
        int cols = config.GetCols();
        int timeLimit = config.GetTimeLimit();
        int tileSize = 55;
        
        setLayout(new GridLayout(rows, cols, 0, 0)); 
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.setMaximumSize(new Dimension(cols * tileSize, rows * tileSize));
        this.setMinimumSize(new Dimension(cols * tileSize, rows * tileSize));
        
        this.setOpaque(false); // Dòng này làm cho Panel không còn màu nền xám nữa
        this.setBackground(new Color(0, 0, 0, 0)); // Đảm bảo màu nền hoàn toàn trong suốt
        algorithm = new ClassicAlgorithm();
        board = new Board(config.GetRows(), config.GetCols()); 
        board.initBoard(algorithm); // Tạo số ngẫu nhiên từ thuật toán chính
//        setLayout(new GridLayout(config.GetRows(), config.GetCols(), 0, 0)); // Ma trận 6 dòng, 5 cột
        btnMatrix = new ButtonMain[config.GetRows() + 2][config.GetCols() + 2]; // Bao gồm cả viền trống nếu cần


        for (int i = 1; i <= config.GetRows(); i++) {
            for (int j = 1; j <=  config.GetCols(); j++) {
                int id = board.getCell(i, j).getId();
//                Broad
                // Hiển thị con số ngẫu nhiên từ Board
//                btnMatrix[i][j] = new ButtonMain(i, j, String.valueOf(cell.getId()));
                btnMatrix[i][j] = new ButtonMain(i, j, null);
                ImageIcon icon = ImageLoad.getImage(id);
                if(icon != null){
                    btnMatrix[i][j].setIcon(icon);
                }
                btnMatrix[i][j].addActionListener(this);
                add(btnMatrix[i][j]);
            }
        }
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        ButtonMain clickedBtn = (ButtonMain) e.getSource();
//        int r = clickedBtn.getxCoord();
//        int c = clickedBtn.getyCoord();
//        Cell currentCell = board.getCell(r, c);
//
//        if (!currentCell.isStatus()) return; // Ô đã biến mất (>0 là có, 0 là trống)
//
//        if (firstClick == null) {
//            firstClick = currentCell;
//            clickedBtn.setBackground(Color.YELLOW); // Đánh dấu ô thứ nhất
//        } else {
//            // Gửi tọa độ về thuật toán xử lý
//            if (algorithm.checkPath(board, firstClick, currentCell)) {
//                // Xử lý logic khi ăn được
//                algorithm.removePair(firstClick, currentCell, board);
//                
//                // Cập nhật giao diện (ẩn các ô = 0)
//                btnMatrix[firstClick.getX()][firstClick.getY()].setVisible(false);
//                btnMatrix[currentCell.getX()][currentCell.getY()].setVisible(false);
//                
//                System.out.println("delete: [" + firstClick.getX() + " " + firstClick.getY() + "] ["+ currentCell.getX() + " " + currentCell.getY() +"]");
//            } else {
//                // Không ăn được thì bỏ chọn
//                btnMatrix[firstClick.getX()][firstClick.getY()].setBackground(null);
//            }
//            firstClick = null;
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonMain clickedBtn = (ButtonMain) e.getSource();
        int r = clickedBtn.getxCoord();
        int c = clickedBtn.getyCoord();
        Cell currentCell = board.getCell(r, c);

        if (!currentCell.isStatus()) return; 

        if (firstClick == null) {
            firstClick = currentCell;

            // --- HIỆU ỨNG VIỀN KHI CHỌN ---
            // Tạo viền màu vàng, độ dày 3 pixel
            clickedBtn.setBorder(javax.swing.BorderFactory.createLineBorder(Color.YELLOW, 3));
            clickedBtn.setBorderPainted(true); // Bật hiển thị viền
        } else {
            // Lấy nút đầu tiên để xử lý viền
            ButtonMain firstBtn = btnMatrix[firstClick.getX()][firstClick.getY()];

            if (algorithm.checkPath(board, firstClick, currentCell)) {
                // Ăn được: Ẩn cả 2 nút
                algorithm.removePair(firstClick, currentCell, board);
                firstBtn.setVisible(false);
                clickedBtn.setVisible(false);
            } else {
                // Không ăn được: Tắt viền của nút thứ nhất (trở về trạng thái cũ)
                firstBtn.setBorderPainted(false); 
                // Nếu em muốn báo hiệu chọn sai, có thể nháy đỏ ở đây
            }
            firstClick = null;
    }
}
}
