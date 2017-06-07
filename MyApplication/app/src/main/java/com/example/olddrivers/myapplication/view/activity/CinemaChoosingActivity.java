package com.example.olddrivers.myapplication.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.FilmItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CinemaChoosingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    FilmItem filmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_choosing);

        initialize();

    }

    void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        filmItem = (FilmItem) getIntent().getSerializableExtra("FilmItem");
        Toast.makeText(this, filmItem.getMovie_name(), Toast.LENGTH_SHORT).show();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        List<Map<String, Object>> data;
        SimpleAdapter simpleAdapter;
        int month;
        int date;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cinema_choosing, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.cinema_listview);
            data = new ArrayList<>();

            final List<String> cinemaNames = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                Map<String, Object> temp = new LinkedHashMap<>();
                cinemaNames.add("金逸（大学城）" + String.valueOf(i));
                temp.put("name", cinemaNames.get(i));
                temp.put("price", i);
                data.add(temp);
            }
            simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.cinema_item,
                    new String[] {"name", "price"}, new int[] {R.id.cinema_name, R.id.cinema_price});
            listView.setAdapter(simpleAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), SessionChoosingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("cinema_name", cinemaNames.get(position));

                    int index = getArguments().getInt(ARG_SECTION_NUMBER);
                    Calendar calendar = Calendar.getInstance();
                    bundle.putInt("month", calendar.get(Calendar.MONTH) + 1);
                    bundle.putInt("date", calendar.get(Calendar.DAY_OF_MONTH) + index);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            switch (position) {
                case 0:
                    return String.valueOf(month) + "月" + String.valueOf(day + 0) + "日";
                case 1:
                    return String.valueOf(month) + "月" + String.valueOf(day + 1) + "日";
                case 2:
                    return String.valueOf(month) + "月" + String.valueOf(day + 2) + "日";
                case 3:
                    return String.valueOf(month) + "月" + String.valueOf(day + 3) + "日";
                case 4:
                    return String.valueOf(month) + "月" + String.valueOf(day + 4) + "日";
            }
            return null;
        }
    }

}
