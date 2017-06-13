package com.example.olddrivers.myapplication.model;

import java.util.List;

/**
 * Created by FrankLin on 2017/6/8.
 */

public class Ticket {

    private Showing showing;
    private String userId;
    private Seat seat;

    public Ticket(Showing showing, String userId, Seat seat) {
        this.showing = showing;
        this.userId = userId;
        this.seat = seat;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
