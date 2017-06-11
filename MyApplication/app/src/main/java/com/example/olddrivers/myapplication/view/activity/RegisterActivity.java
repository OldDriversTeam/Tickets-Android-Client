package com.example.olddrivers.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.User;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.ParseJSON;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitButton();
    }

    private void InitButton() {
        Button registerButton = (Button)findViewById(R.id.register_button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteTextView userName = (AutoCompleteTextView)findViewById(R.id.user_name_register);
                AutoCompleteTextView phone = (AutoCompleteTextView)findViewById(R.id.user_phone_register);
                EditText password = (EditText)findViewById(R.id.user_password_register);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone", phone.getText());
                    jsonObject.put("name", userName.getText());
                    jsonObject.put("password", password.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_REGISTER, jsonObject.toString(), new AsynNetUtils.Callback() {
                    @Override
                    public void onResponse(String response) {
                        ParseJSON parseJSON = new ParseJSON(response);
                        List<Object> list = parseJSON.getUserAfterLogin();
                        if ((int)list.get(0) == AsynNetUtils.SUCCESSD) {
                            User user = (User)list.get(1);
                            SharedPreferences sp = getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                            Log.i("name", user.getName());
                            sp.edit().putString(LocalServer.USER_NAME, user.getName())
                                    .putString(LocalServer.USER_PHONE_NUMBER, user.getPhone())
                                    .putString(LocalServer.USER_PASSWORD, user.getPassword()).commit();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            RegisterActivity.this.startActivity(intent);

                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG);
                        }

                    }
                });

            }
        });
    }

}
