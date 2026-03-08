/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pikachu;

import com.mycompany.pikachu.Module.Model.Board;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Pikachu {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Board b = new Board(6, 5);        
        b.initBoard(6, 5);
        System.out.println("Hello World!");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(b.getCell(i, j).getId() + " ");
            }
            System.out.print("\n");
        }
        
        
        Scanner sc = new Scanner(System.in);
        int temp = sc.nextInt();
        if(temp == 1)
            b.shuffle();
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(b.getCell(i, j).getId() + " ");
            }
            System.out.print("\n");
        }    
    }
}
