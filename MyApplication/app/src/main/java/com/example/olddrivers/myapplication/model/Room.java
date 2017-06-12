package com.example.olddrivers.myapplication.model;

import java.io.Serializable;

/**
 * Created by FrankLin on 2017/6/10.
 */

public class Room implements Serializable {

    private String id;
    private String name;
    private String col;
    private String row;
    private String cinemaId;

    public Room(String id, String name, String col, String row, String cinemaId) {
        this.id = id;
        this.name = name;
        this.col = col;
        this.row = row;
        this.cinemaId = cinemaId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCol() {
        return col;
    }

    public String getRow() {
        return row;
    }

    public String getCinemaId() {
        return cinemaId;
    }

}
