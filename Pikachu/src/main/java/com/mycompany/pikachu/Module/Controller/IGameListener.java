/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IGameListener {
    public void onPairsMatch(List<Point> path); // Để View vẽ đường nối
    public void onGameWin();                    // Để View hiện thông báo thắng
    public void onGameOver();                   // Để View hiện thông báo thua
    public void onScoreChanged(int newScore);   // Để View cập nhật điểm số
}
