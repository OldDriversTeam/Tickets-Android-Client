package com.example.olddrivers.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FrankLin on 2017/6/9.
 */

public class Showing implements Serializable {

    private String id;
    private String date;
    private String time;
    private String price;
    private Movie movie;
    private Cinema cinema;
    private Room room;
    private List<Cinema> cinemaList;

    public Showing(String id, String date, String time, String price, Movie movie, Cinema cinema, Room room) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movie = movie;
        this.cinema = cinema;
        this.room = room;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCinemaList(List<Cinema> cinemaList) {
        this.cinemaList = cinemaList;
    }


    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Room getRoom() {
        return room;
    }

    public List<Cinema> getCinemaList() {
        return cinemaList;
    }
}
