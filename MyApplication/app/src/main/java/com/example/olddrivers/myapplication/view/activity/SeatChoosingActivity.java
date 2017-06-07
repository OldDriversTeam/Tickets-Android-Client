package com.example.olddrivers.myapplication.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.view.adapter.SeatGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeatChoosingActivity extends AppCompatActivity {

    SeatTable seatTableView;

    LinearLayout selected_layout;
    TextView changing;
    List<Seat> selected_seats;
    GridView gridView;
    SeatGridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_choosing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialze();

    }

    void initialze() {
        /*Bundle bundle_in = getIntent().getExtras();
        Toast.makeText(this, bundle_in.getString("start_time"), Toast.LENGTH_SHORT).show();*/

        //selected_layout = (LinearLayout) findViewById(R.id.selected_layout);

        selected_seats = new ArrayList<>();
        /*selected_seats.add(new Seat(0,0));
        selected_seats.add(new Seat(1,1));
        selected_seats.add(new Seat(2,2));
        selected_seats.add(new Seat(3,3));*/

        gridView = (GridView) findViewById(R.id.seat_gridview);
        gridAdapter = new SeatGridAdapter(this, selected_seats);
        gridView.setAdapter(gridAdapter);

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(20);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                /*int temp = selected_seats.size();
                selected_seats.add(new Seat(row, column));
                switch (temp) {
                    case 0: changing = (TextView) findViewById(R.id.selected1); break;
                    case 1: changing = (TextView) findViewById(R.id.selected2); break;
                    case 2: changing = (TextView) findViewById(R.id.selected3); break;
                }
                changing.setText(String.valueOf(row) + "行" + String.valueOf(column) + "列");
                changing.setVisibility(View.VISIBLE);*/

                selected_seats.add(new Seat(row + 1, column + 1));
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void unCheck(int row, int column) {
                /*int uncheck_index = -1;
                for (int i = 0; i < selected_seats.size(); i++) {
                    if (row == selected_seats.get(i).getRow() && column == selected_seats.get(i).getCol()) {
                        uncheck_index = i;
                        break;
                    }
                }

                switch (uncheck_index) {
                    case 0: changing = (TextView) findViewById(R.id.selected1); break;
                    case 1: changing = (TextView) findViewById(R.id.selected2); break;
                    case 2: changing = (TextView) findViewById(R.id.selected3); break;
                }

                if (uncheck_index == (selected_seats.size() - 1)) {
                    changing.setVisibility(View.INVISIBLE);
                }*/

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
        seatTableView.setData(10,12);

    }

}
