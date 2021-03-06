package com.example.olddrivers.myapplication.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.olddrivers.myapplication.R;
import com.example.olddrivers.myapplication.model.User;
import com.example.olddrivers.myapplication.server.AsynNetUtils;
import com.example.olddrivers.myapplication.server.LocalServer;
import com.example.olddrivers.myapplication.util.InfoChecker;
import com.example.olddrivers.myapplication.util.ParseJSON;

import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via phone/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mPhoneView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getSharedPreferences(LocalServer.USER_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (sp.getString(LocalServer.USER_ID, null) != null) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("phone", sp.getString(LocalServer.USER_PHONE_NUMBER, null));
                jsonObject.put("password", sp.getString(LocalServer.USER_PASSWORD, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_LOGIN, jsonObject.toString(), new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    ParseJSON parseJSON = new ParseJSON(response);
                    List<Object> list = parseJSON.getUserAfterLogin();
                    if (list.size() != 0 && (int)list.get(0) == AsynNetUtils.SUCCESSD) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "密码已经更改，请重新登录", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        InitLogText();

        InitPasswordText();

        InitButton();

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void InitLogText() {
        mPhoneView = (AutoCompleteTextView) findViewById(R.id.user_phone_login);

        //prepare autoComplete, 异步加载数据
//        getLoaderManager().initLoader(0, null, this);
        // TODO: 2017/6/7
    }

    private void InitPasswordText() {
        mPasswordView = (EditText) findViewById(R.id.user_password_login);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void InitButton() {
        Button loginButton = (Button) findViewById(R.id.login_button_login);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button registerButton = (Button)findViewById(R.id.register_button_login);

        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // TODO: 2017/6/11 防止多次登录

        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String phoneNumber = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        switch (InfoChecker.PasswordCheck(password)) {
            case InfoChecker.INFO_EMPTY:
                mPasswordView.setError(getString(R.string.error_empty_password));
                focusView = mPasswordView;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                focusView = mPasswordView;
                cancel = true;
                break;
        }

        switch (InfoChecker.PhoneNumberCheck(phoneNumber)) {
            case InfoChecker.INFO_EMPTY:
                mPhoneView.setError(getString(R.string.error_empty_phone));
                focusView = mPhoneView;
                cancel = true;
                break;
            case InfoChecker.INFO_TOO_SHORT:
            case InfoChecker.INFO_FORMAT_ERR:
                mPhoneView.setError(getString(R.string.error_invalid_phone));
                focusView = mPhoneView;
                cancel = true;
                break;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

//            showProgress(true);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("phone", phoneNumber);
                jsonObject.put("password", password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AsynNetUtils.post(AsynNetUtils.SERVER_ADDRESS + AsynNetUtils.POST_LOGIN, jsonObject.toString(), new AsynNetUtils.Callback() {
                @Override
                public void onResponse(String response) {
                    ParseJSON parseJSON = new ParseJSON(response);
                    List<Object> list = parseJSON.getUserAfterLogin();
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
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "账号密码错误，登录失败", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mPhoneView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


}

