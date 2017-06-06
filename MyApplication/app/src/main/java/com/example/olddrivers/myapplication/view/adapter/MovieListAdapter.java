package com.example.olddrivers.myapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final List<Movie> list;
    private final OnMovieListItemClickListener mListener;

    public MovieListAdapter(List<Movie> items, OnMovieListItemClickListener listener) {
        list = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = list.get(position);
        holder.imageView.setImageResource(R.mipmap.movie);
        holder.name.setText(holder.item.getName());
        holder.score.setText(String.valueOf(holder.item.getAvgScore()));
        holder.message.setText(holder.item.getDetai());

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
        public final TextView name;
        public final TextView score;
        public final TextView message;
        public Movie item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = (ImageView) view.findViewById(R.id.movie_list_item_image);
            name = (TextView) view.findViewById(R.id.movie_list_item_name);
            score = (TextView) view.findViewById(R.id.movie_list_item_score);
            message = (TextView) view.findViewById(R.id.movie_list_item_message);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + item.getName() + "'";
        }
    }

    public interface OnMovieListItemClickListener {
        void onItemClick(View view);
    }
}
