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
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.view.adapter.SeatGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class TicketConfirm extends AppCompatActivity {

    TextView cinemaTextView;

    GridView gridView;
    SeatGridAdapter gridAdapter;

    Bundle bundle_in;
    List<Seat> selected_seats;

    int seats_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_confirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

    }

    void initialize() {
        selected_seats = new ArrayList<>();
        bundle_in = getIntent().getExtras();
        seats_count = bundle_in.getInt("count");
        for (int i = 0; i < seats_count; i++) {
            selected_seats.add((Seat) bundle_in.getSerializable("seat" + String.valueOf(i)));
        }

        gridView = (GridView) findViewById(R.id.tc_seats);
        gridAdapter = new SeatGridAdapter(this, selected_seats, 1);
        gridView.setAdapter(gridAdapter);
    }

}
