package com.example.olddrivers.myapplication.server;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

/**
 * Created by bin on 2017/6/10.
 */

public class AsynNetUtils {

    //public static String SERVER_ADDRESS = "http://172.18.71.226:8080";
    public static String SERVER_ADDRESS = "http://211.159.183.245:8080/tickets/";

    public static String GET_CINEMA_BY_ID = "/api/cinemas/";
    public static String GET_MOVIE_BY_ID = "/api/movies/";
    public static String GET_ONSHOW_MOVIES = "/api/movies/onshow";
    public static String GET_ROOM_BY_ID = "/api/rooms/";
    public static String GET_SHOW_BY_ID = "/api/showings/";
    public static String GET_SHOWS_BY_MOVIE_ID = "/api/showings/movie/";
    public static String GET_SHOWS_BY_DATA_CINEMA = "/api/showings/cinema/"; //GET /api/showings/cinema/{cinemaId}/date/{date}movie/{movieId}
    public static String POST_BUY_TICKETS = "/api/tickets/order";
    public static String GET_SOLD_SEAT_BY_SHOW = "/api/tickets/showing/";
    public static String GET_TICKETS_BY_USER_ID = "/api/tickets/user/";
    public static String GET_TICKET_INFO_BY_ID = "/api/tickets/";
    public static String GET_USER_INFO_BY_ID = "/api/users/";
    public static String POST_REGISTER = "/api/users/register";
    public static String POST_LOGIN = "/api/users/login";
    public static String GET_CHECK_PHONE = "/api/users/";
    public static String POST_UPDATE_USER_INFO = "/api/users/update";

    public static int SUCCESSD = 200;
    public static int FAILED = 400;
    public static int PHONE_EXISTED = 401;
    public static int USER_NOT_EXIST = 402;
    public static int PASSWORD_ERR = 403;


    public interface Callback{
        void onResponse(String response);
    }

    public interface BitmapCallback{
        void onResponse(Bitmap response);
    }

    public static void get(final String url, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    public static void post(final String url, final String content, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.post(url,content);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

    public static void getBitmap(final String url, final BitmapCallback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap response = NetUtils.getBitmapFromURL(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }
}
