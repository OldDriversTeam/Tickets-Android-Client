package com.example.olddrivers.myapplication.util;

import android.util.Log;

import com.example.olddrivers.myapplication.model.Cinema;
import com.example.olddrivers.myapplication.model.Movie;
import com.example.olddrivers.myapplication.model.Room;
import com.example.olddrivers.myapplication.model.Seat;
import com.example.olddrivers.myapplication.model.Showing;
import com.example.olddrivers.myapplication.model.Ticket;
import com.example.olddrivers.myapplication.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankLin on 2017/6/9.
 */

public class ParseJSON {
    private JSONObject toParse;

    public ParseJSON(String toParse) {
        try {
            JSONObject jsonObject = new JSONObject(toParse);
            this.toParse = jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToParse(JSONObject toParse) {
        this.toParse = toParse;
    }

    public JSONObject getToParse() {
        return toParse;
    }

    public Cinema getCinemaFromId() {
        Cinema cinema = null;
        try {
            String id = toParse.getString("id");
            String name = toParse.getString("name");
            String address = toParse.getString("address");
            String phone = toParse.getString("phone");
            String cityName = toParse.getString("cityName");
            cinema = new Cinema(id, name, address, phone, cityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cinema;
    }

    public Movie getMovieFromId() {
        Movie movie = null;
        try {
            String id = toParse.getString("id");
            String name = toParse.getString("name");
            String releaseDate = toParse.getString("releaseDate");
            String storyLine = toParse.getString("storyLine");
            String detail = toParse.getString("detail");
            String poster = toParse.getString("poster");
            String avgScore = toParse.getString("avgScore");
            Boolean isShow = toParse.getBoolean("isShow");
            String movieType = toParse.getString("movieType");
            movie = new Movie(id, name, Float.valueOf(avgScore), poster);
            movie.setReleaseDate(releaseDate);
            movie.setStoryLine(storyLine);
            movie.setDetai(detail);
            movie.setMovieType(movieType);
            movie.setShow(isShow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    public List<Movie> getOnshowMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("movieList");
            for (int i = 0; i < size; i++) {
                JSONObject showingListObject = jsonArray.getJSONObject(i);
                String id = toParse.getString("id");
                String name = toParse.getString("name");
                String poster = toParse.getString("poster");
                String avgScore = toParse.getString("avgScore");
                Movie movie = new Movie(id, name, Float.valueOf(avgScore), poster);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Showing getShowingFromId() {
        Showing showing = null;
        try {
            String id = toParse.getString("id");
            String date = toParse.getString("date");
            String time = toParse.getString("time");
            String price = toParse.getString("price");
            String movieId = toParse.getString("movieId");
            String cinemaId = toParse.getString("cinemaId");
            String roomId = toParse.getString("roomId");
            showing = new Showing(id, date, time, price, movieId, cinemaId, roomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showing;
    }

    public List<Showing> getShowingsFromMovieId() {
        List<Showing> showings = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("showinglist");
            Log.i("size", String.valueOf(size));
            for (int i = 0; i < size; i++) {
                JSONObject showingListObject = jsonArray.getJSONObject(i);
                String date = showingListObject.getString("date");
                JSONArray cinemaIdList = showingListObject.getJSONArray("cinemaIdList");
                for (int j = 0; j < cinemaIdList.length(); j++) {
                    String cinemaId = cinemaIdList.getString(j);
                    Showing showing = new Showing(null, date, null, null, null, cinemaId, null);
                    showings.add(showing);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showings;
    }

    public Room getRoomFromId() {
        Room room = null;
        try {
            String id = toParse.getString("id");
            String name = toParse.getString("name");
            String col = toParse.getString("time");
            String row = toParse.getString("price");
            String cinemaId = toParse.getString("cinemaId");
            room = new Room(id, name, col, row, cinemaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    public List<String> getShowingIdList() {
        List<String> showingList = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("showingIdList");
            for (int i = 0; i < size; i++) {
                showingList.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showingList;
    }

    public List<String> getTicketIdsAfterBuying() {
        List<String> ticketIds = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("ticketIds");
            for (int i = 0; i < size; i++) {
                ticketIds.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketIds;
    }

    public List<Seat> getSoldSeatsFromShowingId() {
        List<Seat> seats = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("seatsSoldList");
            for (int i = 0; i < size; i++) {
                JSONArray seatArray = jsonArray.getJSONArray(i);
                Seat seat = new Seat(seatArray.getInt(0), seatArray.getInt(1));
                seats.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seats;
    }

    public List<String> getTicketIdsFromUserId() {
        List<String> ticketIds = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("ticketIdsList");
            for (int i = 0; i < size; i++) {
                ticketIds.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketIds;
    }

    public Ticket getTicketFromTicketId() {
        Ticket ticket = null;
        try {
            String showingId = toParse.getString("showingId");
            JSONArray jsonArray = toParse.getJSONArray("seat");
            Seat seat = new Seat(jsonArray.getInt(0), jsonArray.getInt(1));
            ticket = new Ticket(showingId, null, seat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public User getUserFromId() {
        User user = null;
        try {
            String id = toParse.getString("id");
            String name = toParse.getString("name");
            String password = toParse.getString("password");
            String gender = toParse.getString("gender");
            String age = toParse.getString("age");
            String phone = toParse.getString("phone");
            String email = toParse.getString("email");
            String avatar = toParse.getString("avatar");
            user = new User(id, name, password);
            user.setGender(gender);
            user.setAge(age);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAvatar(avatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Object> getIdAfterRegister() {
        List<Object> res = new ArrayList<>();
        try {
            int state = toParse.getInt("state");
            JSONObject jsonObject = toParse.getJSONObject("user");
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String password = jsonObject.getString("password");
            String gender = jsonObject.getString("gender");
            String age = jsonObject.getString("age");
            String phone = jsonObject.getString("phone");
            String email = jsonObject.getString("email");
            String avatar = jsonObject.getString("avatar");
            User user = new User(id, name, password);
            user.setGender(gender);
            user.setAge(age);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAvatar(avatar);
            res.add(state);
            res.add(user);
            User test = (User) res.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Object> getIdAfterLogin() {
        List<Object> res = new ArrayList<>();
        try {
            int state = toParse.getInt("state");
            JSONObject jsonObject = toParse.getJSONObject("user");
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String password = jsonObject.getString("password");
            String gender = jsonObject.getString("gender");
            String age = jsonObject.getString("age");
            String phone = jsonObject.getString("phone");
            String email = jsonObject.getString("email");
            String avatar = jsonObject.getString("avatar");
            User user = new User(id, name, password);
            user.setGender(gender);
            user.setAge(age);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAvatar(avatar);
            res.add(state);
            res.add(user);
            User test = (User) res.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean getCheckPhone() {
        Boolean exits = false;
        try {
            exits = toParse.getBoolean("exits");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exits;
    }

    public Boolean getUpdateResult() {
        Boolean result = false;
        try {
            result = toParse.getBoolean("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}