package com.example.olddrivers.myapplication.util;

import android.text.TextUtils;

/**
 * Created by bin on 2017/6/7.
 */

public class InfoChecker {

    public static final int INFO_EMPTY = 0;
    public static final int INFO_TOO_SHORT = 1;
    public static final int INFO_FORMAT_ERR = 2;
    public static final int SUCCESS = 10;

    public static int PasswordCheck(String password) {
        if (TextUtils.isEmpty(password)) return INFO_EMPTY;
        if (password.length() < 6) return INFO_TOO_SHORT;

        return SUCCESS;
    }
}
