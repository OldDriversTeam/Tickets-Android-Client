package com.example.olddrivers.myapplication.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Ticket;

import java.util.List;

/**
 * Created by bin on 2017/6/8.
 */

public class MyTicketsListAdapter extends RecyclerView.Adapter<MyTicketsListAdapter.ViewHolder> {

    private final List<Ticket> list;
    private final OnMyTicketListItemClickListener mListener;

    public MyTicketsListAdapter(List<Ticket> items, OnMyTicketListItemClickListener listener) {
        list = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_my_tickets_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = list.get(position);
        holder.imageView.setImageResource(R.mipmap.movie);
        holder.cinema.setText(holder.item.getShowing().getCinema().getName());
        holder.movie.setText(holder.item.getShowing().getMovie().getName());
        holder.session.setText(holder.item.getShowing().getRoom().getName() + "  " + holder.item.getSeat().toString());
        holder.time.setText(holder.item.getShowing().getTime());
        holder.price.setText(holder.item.getShowing().getPrice());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemClick(holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageView;
        public final TextView cinema;
        public final TextView movie;
        public final TextView session;
        public final TextView time;
        public final TextView price;
        public Ticket item;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageView = (ImageView) view.findViewById(R.id.image_ticket_item);
            cinema = (TextView) view.findViewById(R.id.cinema_ticket_item);
            movie = (TextView) view.findViewById(R.id.movie_name_ticket_item);
            session = (TextView) view.findViewById(R.id.session_ticket_item);
            time = (TextView) view.findViewById(R.id.time_ticket_item);
            price = (TextView) view.findViewById(R.id.price_ticket_item);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + item.getShowing().getMovie().getName() + "'";
        }
    }

    public interface OnMyTicketListItemClickListener {
        void onItemClick(View view);
    }
}