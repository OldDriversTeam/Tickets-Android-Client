package com.example.olddrivers.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.DummyContent;
import com.example.olddrivers.myapplication.model.DummyContent.DummyItem;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.util.ParseJSON;
import com.example.olddrivers.myapplication.view.activity.CinemaChoosingActivity;
import com.example.olddrivers.myapplication.view.activity.MainActivity;
import com.example.olddrivers.myapplication.view.activity.MovieDetailActivity;
import com.example.olddrivers.myapplication.view.adapter.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment implements MovieListAdapter.OnMovieListItemClickListener {

    private View view;
    private List<Movie> list = new ArrayList<>();
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        setSearch();

        setList();

        return view;

    }

    private void setSearch() {
        //搜索框
        SearchView searchView = (SearchView)view.findViewById(R.id.movie_list_searchview);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setList() {
        //列表
        final RecyclerView listView = (RecyclerView) view.findViewById(R.id.movie_list);
        AsynNetUtils.get(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.GET_ONSHOW_MOVIES, new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ParseJSON parseJSON = new ParseJSON(response);
                list = parseJSON.getOnshowMovies();
                MovieListAdapter ma = new MovieListAdapter(list, MovieListFragment.this);
                listView.setAdapter(ma);
            }
        });

    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);

        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
