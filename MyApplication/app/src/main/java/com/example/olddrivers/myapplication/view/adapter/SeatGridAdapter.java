package com.example.olddrivers.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Seat;

import org.w3c.dom.Text;

/**
 * Created by FrankLin on 2017/6/8.
 */

public class SeatGridAdapter extends BaseAdapter {
    private Context context;
    private List<Seat> seat_loc = new ArrayList<>();
    private LayoutInflater mInflater;

    public SeatGridAdapter(Context context, List<Seat> seat_loc) {
        this.context = context;
        this.seat_loc = seat_loc;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return seat_loc.size();
    }

    @Override
    public Object getItem(int position) {
        return seat_loc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.seat_gridview_item, null);
            holder = new ViewHolder();
            holder.f_loc = (TextView) convertView.findViewById(R.id.seat_gridview_item);
            convertView.setTag(holder);

        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.f_loc.setText(String.valueOf(seat_loc.get(position).getRow()) + "排" + String.valueOf(seat_loc.get(position).getCol()) + "座");

        return convertView;
    }

    private class ViewHolder {
        TextView f_loc;
    }

}
