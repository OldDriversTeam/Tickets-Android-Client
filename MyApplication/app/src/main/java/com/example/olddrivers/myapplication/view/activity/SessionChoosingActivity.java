package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.util.ParseJSON;

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
    List<Showing> showingList;
    List<Map<String, Object>> session_data;
    List<String> room;
    List<String> time;
    List<String> price;
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
        session_data = new ArrayList<>();
        room = new ArrayList<>();
        time = new ArrayList<>();
        price = new ArrayList<>();
        cinema = (Cinema) bundle_in.getSerializable("cinema");
        movie = (Movie) bundle_in.getSerializable("movie");
        date = bundle_in.getString("date");

        cinema_name = (TextView) findViewById(R.id.ss_cinema_name);
        movie_name = (TextView) findViewById(R.id.ss_movie_name);
        listview = (ListView) findViewById(R.id.ss_listview);

        cinema_name.setText(cinema.getName());
        movie_name.setText(movie.getName());
        String url = AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.GET_SHOWS_BY_DATA_CINEMA
                + cinema.getId() + "/date/" + date + "/movie/" + movie.getId();
        AsynNetUtils.get(url, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ParseJSON parseJSON = new ParseJSON(response);
                showingList = parseJSON.getShowingListFromCDM();
                for (int i = 0; i < showingList.size(); i++) {
                    showingList.get(i).setDate(date);
                    time.add(showingList.get(i).getTime());
                    room.add(showingList.get(i).getRoom().getName());
                    price.add(showingList.get(i).getPrice());
                    Map<String, Object> temp = new LinkedHashMap<>();
                    temp.put("time", time.get(i));
                    temp.put("room", room.get(i));
                    temp.put("price", price.get(i));
                    session_data.add(temp);
                }
                simpleAdapter = new SimpleAdapter(SessionChoosingActivity.this, session_data, R.layout.sesion_item,
                        new String[] {"time", "room", "price"}, new int[] {R.id.ss_time, R.id.ss_room, R.id.ss_price});
                listview.setAdapter(simpleAdapter);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionChoosingActivity.this, SeatChoosingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                bundle.putSerializable("cinema", cinema);
                bundle.putSerializable("showing", showingList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
