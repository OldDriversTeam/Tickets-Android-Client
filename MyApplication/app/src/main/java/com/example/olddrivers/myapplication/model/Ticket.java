package com.example.olddrivers.myapplication.model;

import java.util.List;

/**
 * Created by FrankLin on 2017/6/8.
 */

public class Ticket {

    private String showingId;
    private String userId;
    private int count;
    private Seat seat;

    private String cinema;
    private String movie;
    private String session;
    private String time;
    private String price;

    public Ticket(String showingId, String userId, Seat seat) {
        this.showingId = showingId;
        this.userId = userId;
        this.seat = seat;
    }

    public String getShowingId() {
        return showingId;
    }

    public void setShowingId(String showingId) {
        this.showingId = showingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
