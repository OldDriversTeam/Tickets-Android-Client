package com.example.olddrivers.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Ticket;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.ParseJSON;
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

        setList();

    }

    private void setList() {
        //列表
        SharedPreferences sp = getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final RecyclerView listView = (RecyclerView) findViewById(R.id.ticket_list_mytickets);

        AsynNetUtils.get(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.GET_TICKETS_BY_USER_ID + sp.getString(LocalServer.USER_ID, null),
                new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.i("response", response);
                ParseJSON parseJSON = new ParseJSON(response);
                list =  parseJSON.getTicketsFromUserId();
                MyTicketsListAdapter ma = new MyTicketsListAdapter(list, MyTicketsActivity.this);
                listView.setAdapter(ma);
            }
        });



    }

    @Override
    public void onItemClick(View view) {
        // TODO: 2017/6/8  
    }
    
}
