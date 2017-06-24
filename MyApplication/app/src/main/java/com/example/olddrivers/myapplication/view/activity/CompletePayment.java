package com.example.olddrivers.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.ParseJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompletePayment extends AppCompatActivity {

    Button btn;
    Bundle bundle_in;

    SharedPreferences sp;
    String showingId;
    String userId;
    int count;
    List<Seat> seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        setListener();
        postTicket();

    }

    void initialize() {
        sp = getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        bundle_in = getIntent().getExtras();
        btn = (Button) findViewById(R.id.btn_returnMainPage);
        showingId = ( (Showing) bundle_in.getSerializable("showing")).getId();
        userId = sp.getString(LocalServer.USER_ID, "");
        count = bundle_in.getInt("count");
        seats = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            seats.add((Seat) bundle_in.getSerializable("seat" + String.valueOf(i)));
        }
    }

    void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletePayment.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void postTicket() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("showingId", showingId);
            jsonObject.put("userId", userId);
            jsonObject.put("count", count);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < count; i++) {
                int[] seat = new int[2];
                seat[0] = seats.get(i).getCol();
                seat[1] = seats.get(i).getRow();
                JSONArray seatArray = new JSONArray(seat);
                jsonArray.put(seatArray);
            }
            jsonObject.put("seats", jsonArray);
            Log.i("ticket object", jsonObject.toString());
            AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_BUY_TICKETS, jsonObject.toString(), new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    Log.i("buy response", response);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
