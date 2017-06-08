package com.example.olddrivers.myapplication.model;

import java.util.List;

/**
 * Created by FrankLin on 2017/6/8.
 */

public class Ticket {

    private String showingId;
    private String userId;
    private int count;
    private List<Seat> seats;

    public Ticket(String showingId, String userId, int count, List<Seat> seats) {
        this.showingId = showingId;
        this.userId = userId;
        this.count = count;
        this.seats = seats;
    }

    void setShowingId(String showingId) {
        this.showingId = showingId;
    }

    void setUserI(String userId) {
        this.userId = userId;
    }

    void setCount(int count) {
        this.count = count;
    }

    void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    String getShowingId() {
        return showingId;
    }

    String getUserId() {
        return userId;
    }

    int getCount() {
        return count;
    }

    List<Seat> getSeats() {
        return seats;
    }

}
