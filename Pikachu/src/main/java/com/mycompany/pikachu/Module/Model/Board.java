/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu.Module.Model;

import com.mycompany.pikachu.Module.Algorithm.ClassicAlgorithm;
import com.mycompany.pikachu.Module.Algorithm.IArgorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Board {
    private int rows, cols, totalCells;
    private Cell[][] matrix;
    private Map<Integer, List<Cell>> map;

    public Board() {
        do {            
            Random rand = new Random();
            rows = rand.nextInt(5, 10);
            cols = rand.nextInt(5, 10);
        } while (rows * cols % 2 != 0);
        this.matrix = new Cell[rows+ 2][cols + 2];
        this.totalCells = rows * cols;
        map = new HashMap<>();
    }
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new Cell[rows+ 2][cols + 2];
        this.totalCells = rows * cols;    
        map = new HashMap<>();
    }
    
    public void initBoard(int rows, int cols) {
        Random rand = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        int halfElems = totalCells / 2;
        for (int i = 0; i < halfElems; i++) {
            int randomNumber = rand.nextInt(1, 20);
            list.add(randomNumber);
            list.add(randomNumber);
        }
        
        IArgorithm a = new ClassicAlgorithm();
        do {
            Collections.shuffle(list);
            int index = 0;
            for (int i = 0; i <= rows + 1; i++) {
                for (int j = 0; j <= cols + 1; j++) {
                    if (i == 0 || j == 0 || i == rows + 1 || j == cols + 1) {
                        matrix[i][j] = new Cell(i, j, 0, false);
                    } else {
                        matrix[i][j] = new Cell(i, j, list.get(index++), true);
                    }
                }
            }
            
            map.clear();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (map.containsKey(matrix[i][j].getId()) == false) {
                        map.put(matrix[i][j].getId(), new ArrayList<>());
                    }
                    map.get(matrix[i][j].getId()).add(matrix[i][j]);
                }
            }

        } while(a.hasAnyMatch(this) == false);
        

    }
    
    public Cell getCell(int r, int c) {
        return matrix[r][c];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public int getTotalCells() {
        return totalCells;
    }

    public Map<Integer, List<Cell>> getMap() {
        return map;
    }
    
    public void shuffle() {
        ArrayList<Cell> allCells = new ArrayList<>();
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= cols; j++) {
                allCells.add(matrix[i][j]);
            }
        }
        
        IArgorithm a = new ClassicAlgorithm();
        do {            
            Collections.shuffle(allCells);
            int index = 0;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    matrix[i][j] = allCells.get(index++);
                    matrix[i][j].setX(i);
                    matrix[i][j].setY(j);
                }
            }
            
            map.clear();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (map.containsKey(matrix[i][j].getId()) == false) {
                        map.put(matrix[i][j].getId(), new ArrayList<>());
                    }
                    map.get(matrix[i][j].getId()).add(matrix[i][j]);
                }
            }
            
        } while (a.hasAnyMatch(this) == false);
    }
    
    public void removePair(Cell c1, Cell c2) {
        c1.setStatus(false);
        c2.setStatus(false);
        totalCells -= 2;
    }
}
