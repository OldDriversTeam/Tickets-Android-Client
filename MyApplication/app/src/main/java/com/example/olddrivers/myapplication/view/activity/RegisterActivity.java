package com.example.olddrivers.myapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.User;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.InfoChecker;
import com.example.olddrivers.myapplication.util.ParseJSON;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView userName;
    private AutoCompleteTextView phone;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitUserNameText();

        InitUserPhoneText();

        InitPasswordText();

        InitButton();
    }

    private void InitUserNameText() {
        userName = (AutoCompleteTextView)findViewById(R.id.user_name_register);
// TODO: 2017/6/12 自动补全

    }

    private void InitUserPhoneText() {
        phone = (AutoCompleteTextView)findViewById(R.id.user_phone_register);

    }

    private void InitPasswordText() {
        password = (EditText)findViewById(R.id.user_password_register);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptRegister();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void InitButton() {
        Button registerButton = (Button)findViewById(R.id.register_button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptRegister();

            }
        });
    }

    private void attemptRegister() {
        userName.setError(null);
        phone.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String sUserName = userName.getText().toString();
        String sPhoneNumber = phone.getText().toString();
        String sPassword = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        switch (InfoChecker.PasswordCheck(sPassword)) {
            case InfoChecker.INFO_EMPTY:
                password.setError(getString(R.string.error_empty_password));
                focusView = password;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                password.setError(getString(R.string.error_incorrect_password));
                focusView = password;
                cancel = true;
                break;

        }

        switch (InfoChecker.PhoneNumberCheck(sPhoneNumber)) {
            case InfoChecker.INFO_EMPTY:
                phone.setError(getString(R.string.error_empty_phone));
                focusView = phone;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                phone.setError(getString(R.string.error_invalid_phone));
                focusView = phone;
                cancel = true;
                break;

        }

        switch (InfoChecker.UserNameCheck(sUserName)) {
            case InfoChecker.INFO_EMPTY:
                userName.setError(getString(R.string.error_empty_user_name));
                focusView = userName;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                userName.setError(getString(R.string.error_invalid_user_name));
                focusView = userName;
                cancel = true;
                break;

        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("phone", sPhoneNumber);
                jsonObject.put("name", sUserName);
                jsonObject.put("password", sPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_REGISTER, jsonObject.toString(), new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    Log.i("response", response);
                    ParseJSON parseJSON = new ParseJSON(response);
                    List<Object> list = parseJSON.getUserAfterRegister();
                    if (list.size() != 0 && (int)list.get(0) == AsynNetUtils.SUCCESSD) {
                        User user = (User)list.get(1);
                        SharedPreferences sp = getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                        sp.edit().putString(LocalServer.USER_ID, user.getId())
                                .putString(LocalServer.USER_NAME, user.getName())
                                .putString(LocalServer.USER_PASSWORD, user.getPassword())
                                .putString(LocalServer.USER_GENDER, user.getGender())
                                .putString(LocalServer.USER_AGE, user.getAge())
                                .putString(LocalServer.USER_PHONE_NUMBER, user.getPhone())
                                .putString(LocalServer.USER_EMAIL, user.getEmail())
                                .putString(LocalServer.USER_AVATAR, user.getAvatar()).commit();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败, 手机号已存在", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

}
