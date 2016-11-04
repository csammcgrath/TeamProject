package com.example.sam.testing2;

/**
 * Created by Max&Lena on 11/2/2016.
 */


public class UserInfo {
    private String name;
    private String address;
    private String city;
    private String state;
    private int point;

    public  UserInfo(String name, String address, String city, String state, int point){

    }

    public UserInfo(String name, String address,String city, String state){
        this.address = address;
        this.name = name;
        this.city = city;
        this.state = state;
        this.point = 0;
    }
}
