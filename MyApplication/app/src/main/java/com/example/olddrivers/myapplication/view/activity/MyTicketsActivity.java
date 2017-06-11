package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Ticket;
import com.example.olddrivers.myapplication.view.adapter.MovieListAdapter;
import com.example.olddrivers.myapplication.view.adapter.MyTicketsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsActivity extends AppCompatActivity implements MyTicketsListAdapter.OnMyTicketListItemClickListener{

    private List<Ticket> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
        list.add(new Ticket("1", "2", new Seat(1, 2)));

        setList();

    }

    private void setList() {
        //列表
        RecyclerView listView = (RecyclerView) findViewById(R.id.ticket_list_mytickets);
        MyTicketsListAdapter ma = new MyTicketsListAdapter(list, this);
        listView.setAdapter(ma);
    }

    @Override
    public void onItemClick(View view) {
        // TODO: 2017/6/8  
    }
    
}
