package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SessionChoosingActivity extends AppCompatActivity {

    TextView cinema_name;
    TextView movie_name;
    ListView listview;

    Cinema cinema;
    Movie movie;
    String date;
    List<Map<String, Object>> session_data;
    List<String> detailStart;
    List<String> detailEnd;
    SimpleAdapter simpleAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_choosing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

    }

    void initialize() {
        Bundle bundle_in = getIntent().getExtras();
        cinema = (Cinema) bundle_in.getSerializable("cinema");
        movie = (Movie) bundle_in.getSerializable("movie");
        date = bundle_in.getString("date");

        cinema_name = (TextView) findViewById(R.id.ss_cinema_name);
        movie_name = (TextView) findViewById(R.id.ss_movie_name);
        listview = (ListView) findViewById(R.id.ss_listview);

        cinema_name.setText(cinema.getName());
        movie_name.setText(movie.getName());

        session_data = new ArrayList<>();
        detailStart = new ArrayList<>();
        detailEnd = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            detailStart.add(String.valueOf(i));
            detailEnd.add(String.valueOf(i+1));
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("start_time", detailStart.get(i));
            temp.put("end_time", detailEnd.get(i));
            session_data.add(temp);
        }
        simpleAdapter = new SimpleAdapter(this, session_data, R.layout.sesion_item,
                new String[] {"start_time", "end_time"}, new int[] {R.id.ss_start, R.id.ss_end});
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionChoosingActivity.this, SeatChoosingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("start_time", detailStart.get(position));
                bundle.putString("end_time", detailEnd.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
