package com.example.olddrivers.myapplication.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Room;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.view.adapter.SeatGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class newTicketConfirm extends AppCompatActivity {

    TextView cinemaTextView;
    TextView dateTextView;
    TextView roomTextView;
    TextView timeTextView;
    GridView gridView;
    SeatGridAdapter gridAdapter;

    Bundle bundle_in;
    Movie movie;
    Cinema cinema;
    Showing showing;
    Room room;
    List<Seat> selected_seats;
    int seats_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ticket_confirm);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.ntoolbar);
        setSupportActionBar(toolbar);*/

        initialize();

    }

    void initialize() {
        bundle_in = getIntent().getExtras();

        movie = (Movie) bundle_in.getSerializable("movie");
        cinema = (Cinema) bundle_in.getSerializable("cinema");
        showing = (Showing) bundle_in.getSerializable("showing");
        room = (Room) bundle_in.getSerializable("room");

        cinemaTextView = (TextView) findViewById(R.id.ntc_cinema);
        dateTextView = (TextView) findViewById(R.id.ntc_date);
        roomTextView = (TextView) findViewById(R.id.ntc_room);
        timeTextView = (TextView) findViewById(R.id.ntc_time);

        cinemaTextView.setText(cinema.getName());
        dateTextView.setText(showing.getDate());
        roomTextView.setText(room.getName());
        timeTextView.setText(showing.getTime());


        selected_seats = new ArrayList<>();
        seats_count = bundle_in.getInt("count");

        for (int i = 0; i < seats_count; i++) {
            selected_seats.add((Seat) bundle_in.getSerializable("seat" + String.valueOf(i)));
        }

        gridView = (GridView) findViewById(R.id.ntc_seats);
        gridAdapter = new SeatGridAdapter(this, selected_seats, 1);
        gridView.setAdapter(gridAdapter);
    }

}
