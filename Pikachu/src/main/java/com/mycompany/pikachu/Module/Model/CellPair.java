/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pikachu.Module.Model;

/**
 *
 * @author Admin
 */
public class CellPair {
    public Cell cell1;
    public Cell cell2;

    public CellPair() {
    }

    public CellPair(Cell c1, Cell c2) {
        this.cell1 = c1;
        this.cell2 = c2;
    }

    public Cell getCell1() {
        return cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    public void setCell1(Cell cell1) {
        this.cell1 = cell1;
    }

    public void setCell2(Cell cell2) {
        this.cell2 = cell2;
    }

    @Override
    public String toString() {
        return "CellPair{" + "cell1=" + cell1.toString() + ", cell2=" + cell2.toString() + '}';
    }
    
}
