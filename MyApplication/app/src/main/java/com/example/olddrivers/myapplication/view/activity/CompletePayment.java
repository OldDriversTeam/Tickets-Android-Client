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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        setListener();

    }

    void initialize() {
        btn = (Button) findViewById(R.id.btn_returnMainPage);
    }

    void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String test = "{\"id\":\"id1\",\"name\":\"name1\",\"address\":\"address1\",\"phone\":\"phone1\",\"cityName\":\"cityName1\"}";
                String test = "{\"size\":2," +
                        "\"showinglist\":[" +
                        "{\"date\":\"date1\"," + "\"cinemaIdList\":[\"cinema1\"," + "\"cinema2\"]}" +
                        "]}";
                //Toast.makeText(CompletePayment.this, test, Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject jsonObject = new JSONObject(test);
//                    Log.i("size", test);
//                    ParseJSON parseJSON = new ParseJSON(jsonObject);
//                    List<Showing> showings = parseJSON.getShowingsFromMovieId();
//                    //Toast.makeText(CompletePayment.this, showings.get(0).getDate() + "\n" + showings.get(1).getCinemaId(), Toast.LENGTH_SHORT).show();
//                    Log.i("sizeOfShowings", String.valueOf(showings.size()));
//                    Toast.makeText(CompletePayment.this, String.valueOf(showings.size()), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });
    }

}
