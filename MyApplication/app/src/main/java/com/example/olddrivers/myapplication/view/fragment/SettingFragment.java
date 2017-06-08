package com.example.olddrivers.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.view.activity.MainActivity;
import com.example.olddrivers.myapplication.view.activity.MyTicketsActivity;
import com.google.android.gms.plus.PlusOneButton;

public class SettingFragment extends Fragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        InitButton();

        return view;

    }

    private void InitButton() {
        Button setting = (Button)view.findViewById(R.id.setting_button);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/6/8
            }
        });

        Button ticket = (Button)view.findViewById(R.id.myticket_button_setting);

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyTicketsActivity.class);
                startActivity(intent);
            }
        });
    }


}
