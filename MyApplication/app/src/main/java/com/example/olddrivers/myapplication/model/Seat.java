package com.example.olddrivers.myapplication.model;

/**
 * Created by FrankLin on 2017/6/7.
 */

public class Seat {

    private int row;
    private int col;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.row = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
