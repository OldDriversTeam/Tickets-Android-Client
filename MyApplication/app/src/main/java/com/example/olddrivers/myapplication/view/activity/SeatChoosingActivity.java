package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Room;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.util.ParseJSON;
import com.example.olddrivers.myapplication.view.adapter.SeatGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeatChoosingActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView cinemaTextView;
    TextView dateTextView;
    TextView timeTextView;
    SeatTable seatTableView;
    Button btn;
    GridView gridView;
    SeatGridAdapter gridAdapter;

    Cinema cinema;
    Movie movie;
    Showing showing;
    Room room;
    List<Seat> sold_seats;
    List<Seat> selected_seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_choosing);

        initialze();
        setListener();

    }

    void initialze() {
        Bundle bundle_in = getIntent().getExtras();
        selected_seats = new ArrayList<>();
        movie = (Movie) bundle_in.getSerializable("movie");
        cinema = (Cinema) bundle_in.getSerializable("cinema");
        showing = (Showing) bundle_in.getSerializable("showing");
        toolbar = (Toolbar) findViewById(R.id.seat_toolbar);
        toolbar.setTitle(movie.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(movie.getName());

        cinemaTextView = (TextView) findViewById(R.id.sc_cinema);
        dateTextView = (TextView) findViewById(R.id.sc_date);
        timeTextView = (TextView) findViewById(R.id.sc_time);
        btn = (Button) findViewById(R.id.btn_toConfirm);

        cinemaTextView.setText(cinema.getName());
        dateTextView.setText(showing.getDate());
        timeTextView.setText(showing.getTime().substring(0, 5).replace("-", ":"));

        AsynNetUtils.get(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.GET_ROOM_BY_ID + showing.getRoom().getId(), new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ParseJSON parseJSON = new ParseJSON(response);
                Log.i("response", response);
                room = parseJSON.getRoomFromId();
                Log.i("room", room.getRow() + "行" + room.getCol() + "列");
                seatTableView.setData(Integer.valueOf(room.getRow()),Integer.valueOf(room.getCol()));
                }
        });

        AsynNetUtils.get(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.GET_SOLD_SEAT_BY_SHOW + showing.getId(), new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ParseJSON parseJSON = new ParseJSON(response);
                Log.i("solded response", response);
                sold_seats = parseJSON.getSoldSeatsFromShowingId();
                seatTableView.solded_seats = sold_seats;
                Log.i("solded response", String.valueOf(seatTableView.solded_seats.size()));
                seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

                    @Override
                    public boolean isValidSeat(int row, int column) {
                        return true;
                    }

                    @Override
                    public boolean isSold(int row, int column) {
                        for (int i = 0; i < seatTableView.solded_seats.size(); i++) {
                            if (row == seatTableView.solded_seats.get(i).getRow() - 1
                                    && column == seatTableView.solded_seats.get(i).getCol() - 1) {
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public void checked(int row, int column) {
                        selected_seats.add(new Seat(row + 1, column + 1));
                        gridAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void unCheck(int row, int column) {

                        int uncheck_index = -1;
                        for (int i = 0; i < selected_seats.size(); i++) {
                            if (row + 1 == selected_seats.get(i).getRow() && column + 1 == selected_seats.get(i).getCol()) {
                                uncheck_index = i;
                                break;
                            }
                        }
                        selected_seats.remove(uncheck_index);
                        gridAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public String[] checkedSeatTxt(int row, int column) {
                        return null;
                    }

                });

            }
        });

        //selected_layout = (LinearLayout) findViewById(R.id.selected_layout);
        /*selected_seats.add(new Seat(0,0));
        selected_seats.add(new Seat(1,1));
        selected_seats.add(new Seat(2,2));
        selected_seats.add(new Seat(3,3));*/

        gridView = (GridView) findViewById(R.id.seat_gridview);
        gridAdapter = new SeatGridAdapter(this, selected_seats, 0);
        gridView.setAdapter(gridAdapter);

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(6);//设置最多选中

    }

    void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_seats.size() == 0) {
                    Toast.makeText(SeatChoosingActivity.this, "仍未选择座位", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SeatChoosingActivity.this, newTicketConfirm.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                bundle.putSerializable("cinema", cinema);
                bundle.putSerializable("showing", showing);
                bundle.putSerializable("room", room);
                bundle.putInt("count", selected_seats.size());
                for (int i = 0; i < selected_seats.size(); i++) {
                    bundle.putSerializable("seat" + String.valueOf(i), selected_seats.get(i));
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
