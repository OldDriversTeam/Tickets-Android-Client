package com.example.olddrivers.myapplication.util;

import android.text.TextUtils;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

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

    public static int PhoneNumberCheck(String phoneNumber) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);

        if (TextUtils.isEmpty(phoneNumber)) return INFO_EMPTY;
        if (phoneNumber.length() == 11 && m.matches()) return SUCCESS;

        return INFO_FORMAT_ERR;
    }

    public static int UserNameCheck(String userName) {
        if (TextUtils.isEmpty(userName)) return INFO_EMPTY;
        if (userName.length() < 3) return INFO_TOO_SHORT;

        return SUCCESS;
    }
}
