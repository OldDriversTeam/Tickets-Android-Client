package com.example.olddrivers.myapplication.view.activity;

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
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.util.ParseJSON;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompletePayment extends AppCompatActivity {

    Button btn;
    Bundle bundle_in;

    String showingId;
    String userId;
    int count;



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

        bundle_in = getIntent().getExtras();
        btn = (Button) findViewById(R.id.btn_returnMainPage);
        showingId = ( (Showing) bundle_in.getSerializable("showing")).getId();

    }

    void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void postTicket() {

    }

}
