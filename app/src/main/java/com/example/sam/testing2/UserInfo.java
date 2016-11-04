package com.example.sam.testing2;

/**
 * Created by Max&Lena on 11/2/2016.
 */

// Class User Info
public class UserInfo {

    public String name;
    public String address;
    public String city;
    public String state;
    public int point;

    public UserInfo(){
        this.address = "";
        this.name = "";
        this.city = "";
        this.state = "";
        this.point = 0;

    }

    public UserInfo( String name, String address,String city, String state){
        this.address = address;
        this.name = name;
        this.city = city;
        this.state = state;
        this.point = 0;
    }

    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public int getPoint(){
        return point;
    }
}
