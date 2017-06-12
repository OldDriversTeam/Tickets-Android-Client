package com.example.olddrivers.myapplication.model;

import java.io.Serializable;

/**
 * Created by FrankLin on 2017/6/9.
 */

public class Cinema implements Serializable {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String cityName;

    public Cinema(String id, String name, String address, String phone, String cityName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cityName = cityName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCityName() {
        return cityName;
    }

}
