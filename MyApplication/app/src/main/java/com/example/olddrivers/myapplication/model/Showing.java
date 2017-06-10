package com.example.olddrivers.myapplication.model;

/**
 * Created by FrankLin on 2017/6/9.
 */

public class Showing {

    private String id;
    private String date;
    private String time;
    private String price;
    private String movieId;
    private String cinemaId;
    private String roomId;

    public Showing(String id, String date, String time, String price, String movieId, String cinemaId, String roomId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.price = price;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.roomId = roomId;
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

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public String getMovieId() {
        return movieId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public String getRoomId() {
        return roomId;
    }

}