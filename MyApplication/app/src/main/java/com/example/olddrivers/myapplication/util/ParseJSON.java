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
                String id = showingListObject.getString("id");
                String name = showingListObject.getString("name");
                String avgScore = showingListObject.getString("avgScore");
                String poster = showingListObject.getString("poster");
                String storyLine = showingListObject.getString("storyLine");
                Movie movie = new Movie(id, name, Float.valueOf(avgScore), poster);
                movie.setStoryLine(storyLine);
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
            Movie movie = new Movie(movieId, null, 0, null);
            Cinema cinema = new Cinema(movieId, null, null, null, null);
            Room room = new Room(roomId, null, null, null, null);
            showing = new Showing(id, date, time, price, movie, cinema, room);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showing;
    }

    public List<Showing> getShowingsFromMovieId() {
        List<Showing> showings = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("showingList");
            for (int i = 0; i < size; i++) {
                JSONObject showingListObject = jsonArray.getJSONObject(i);
                String date = showingListObject.getString("date");
                JSONArray cinemaList = showingListObject.getJSONArray("cinemaList");
                Showing showing = new Showing(null, date, null, null, null, null, null);
                List<Cinema> cinemas = new ArrayList<>();
                for (int j = 0; j < cinemaList.length(); j++) {
                    JSONObject cinemaObject = cinemaList.getJSONObject(j);
                    String name = cinemaObject.getString("name");
                    String id = cinemaObject.getString("id");
                    Cinema cinema = new Cinema(id, name, null, null, null);
                    showing.setCinema(cinema);
                    cinemas.add(cinema);
                }
                showing.setCinemaList(cinemas);
                showings.add(showing);
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
            String col = toParse.getString("col");
            String row = toParse.getString("row");
            String cinemaId = toParse.getString("cinemaId");
            room = new Room(id, name, col, row, cinemaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    public List<Showing> getShowingListFromCDM() {
        List<Showing> showingList = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("showingList");
            for (int i = 0; i < size; i++) {
                JSONObject showingObject = jsonArray.getJSONObject(i);
                String id = showingObject.getString("showingId");
                String time = showingObject.getString("time");
                String price = showingObject.getString("price");
                String roomId = showingObject.getString("roomId");
                String roomName = showingObject.getString("roomName");
                Room room = new Room(roomId, roomName, null, null, null);
                Showing showing = new Showing(id, null, time, price, null, null, room);
                showingList.add(showing);
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
            JSONArray jsonArray = toParse.getJSONArray("seatSoldList");
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

    public List<Ticket> getTicketsFromUserId() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            int size = toParse.getInt("size");
            JSONArray jsonArray = toParse.getJSONArray("ticketList");
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String showingId = jsonObject.getString("showingId");
                String movieName = jsonObject.getString("movieName");
                String movieType = jsonObject.getString("movieType");
                String moviePoster = jsonObject.getString("moviePoster");
                String cinemaName = jsonObject.getString("cinemaName");
                String roomName = jsonObject.getString("roomName");
                String date = jsonObject.getString("date");
                String time = jsonObject.getString("time");
                int seatColNum = jsonObject.getInt("seatColNum");
                int seatRowNum = jsonObject.getInt("seatRowNum");
                Movie movie = new Movie(null, movieName, 0, moviePoster);
                movie.setMovieType(movieType);
                Cinema cinema = new Cinema(null, cinemaName, null, null, null);
                Room room = new Room(null, roomName, String.valueOf(seatColNum), String.valueOf(seatRowNum), null);
                Showing showing = new Showing(showingId, date, time, null, movie, cinema, room);
                Seat seat = new Seat(seatRowNum, seatColNum);
                Ticket ticket = new Ticket(showing, null, seat);
                tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public Ticket getTicketFromTicketId() {
        Ticket ticket = null;
        try {
            String showingId = toParse.getString("showingId");
            JSONArray jsonArray = toParse.getJSONArray("seat");
            Seat seat = new Seat(jsonArray.getInt(0), jsonArray.getInt(1));
            Showing showing = new Showing(showingId, null, null, null, null, null, null);
            ticket = new Ticket(showing, null, seat);
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

    public List<Object> getUserAfterRegister() {
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
            User user = new User(phone, name, password);
            user.setId(id);
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

    public List<Object> getUserAfterLogin() {
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
            User user = new User(phone, name, password);
            user.setId(id);
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

    public int getUpdateResult() {
        int result = 0;
        try {
            result = toParse.getInt("state");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}