package com.example.olddrivers.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.User;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.InfoChecker;
import com.example.olddrivers.myapplication.util.ParseJSON;
import com.example.olddrivers.myapplication.view.activity.LoginActivity;
import com.example.olddrivers.myapplication.view.activity.MainActivity;
import com.example.olddrivers.myapplication.view.activity.MyTicketsActivity;
import com.example.olddrivers.myapplication.view.activity.RegisterActivity;
import com.google.android.gms.plus.PlusOneButton;

import org.json.JSONObject;

import java.util.List;

public class SettingFragment extends Fragment {

    private View view;

    private AutoCompleteTextView userNameView;
    private EditText ageView, phoneView, emailView;

    private SharedPreferences sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        sp = getContext().getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        InitUserNameView();

        InitAgeView();

        InitPhoneView();

        InitEmailView();



        InitSettingButton();

        InitTicketButton();

        InitExitButton();

        return view;

    }

    private void InitUserNameView() {
        userNameView = (AutoCompleteTextView) view.findViewById(R.id.user_name_setting);
        userNameView.setText(sp.getString(LocalServer.USER_NAME, null));

    }

    private void InitAgeView() {
        ageView = (EditText)view.findViewById(R.id.user_age_setting);
        ageView.setText(sp.getString(LocalServer.USER_AGE, null));

    }

    private void InitPhoneView() {
        phoneView = (EditText)view.findViewById(R.id.user_phone_setting);
        phoneView.setText(sp.getString(LocalServer.USER_PHONE_NUMBER, null));
        phoneView.setFocusableInTouchMode(false);

    }

    private void InitEmailView() {
        emailView = (EditText)view.findViewById(R.id.user_email_setting);
        emailView.setText(sp.getString(LocalServer.USER_EMAIL, null));

    }

    private void InitSettingButton() {
        Button setting = (Button)view.findViewById(R.id.setting_button);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSetting();
            }
        });

    }

    private void InitTicketButton() {
        Button ticket = (Button)view.findViewById(R.id.myticket_button_setting);

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyTicketsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitExitButton() {
        Button exit = (Button) view.findViewById(R.id.exit_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().clear().commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void attemptSetting() {
        userNameView.setError(null);
        ageView.setError(null);
        phoneView.setError(null);
        emailView.setError(null);

        // Store values at the time of the login attempt.
        String sUserName = userNameView.getText().toString();
        String sPhoneNumber = phoneView.getText().toString();
        String sAge = ageView.getText().toString();
        String sEmail = emailView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        switch (InfoChecker.AgeCheck(sAge)) {
            case InfoChecker.INFO_EMPTY:
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                ageView.setError(getString(R.string.error_invalid_age));
                focusView = ageView;
                cancel = true;
                break;

        }

        switch (InfoChecker.PhoneNumberCheck(sPhoneNumber)) {
            case InfoChecker.INFO_EMPTY:
                phoneView.setError(getString(R.string.error_empty_phone));
                focusView = phoneView;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                phoneView.setError(getString(R.string.error_invalid_phone));
                focusView = phoneView;
                cancel = true;
                break;

        }

        switch (InfoChecker.UserNameCheck(sUserName)) {
            case InfoChecker.INFO_EMPTY:
                userNameView.setError(getString(R.string.error_empty_user_name));
                focusView = userNameView;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                userNameView.setError(getString(R.string.error_invalid_user_name));
                focusView = userNameView;
                cancel = true;
                break;

        }

        // TODO: 2017/6/12 email check

        if (cancel) {
            focusView.requestFocus();
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                Log.i("id", sp.getString(LocalServer.USER_ID, null));
                jsonObject.put("id", sp.getString(LocalServer.USER_ID, null));
                jsonObject.put("name", sUserName);
                jsonObject.put("password", sp.getString(LocalServer.USER_PASSWORD, null));
                jsonObject.put("gender", sp.getString(LocalServer.USER_GENDER, null));
                jsonObject.put("age", sAge);
                jsonObject.put("phone", sp.getString(LocalServer.USER_PHONE_NUMBER, null));
                jsonObject.put("email", sEmail);
                jsonObject.put("avatar", sp.getString(LocalServer.USER_EMAIL, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_UPDATE_USER_INFO, jsonObject.toString(), new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    ParseJSON parseJSON = new ParseJSON(response);
                    if (parseJSON.getUpdateResult() == AsynNetUtils.SUCCESSD) {
                        Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

}
