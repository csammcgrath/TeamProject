package com.example.sam.testing2;

/**
 * Created by Max&Lena on 11/2/2016.
 */

// Class User Info
public class UserInfo {

    private String name;
    private String address;
    private String city;
    private String state;
    private int point;
    private String email;

    public UserInfo(){

        this.address = getAddress();
        this.name = getName();
        this.city = getCity();
        this.state = getState();
        this.point = 0;
    }

    public UserInfo(int point)
    {
        this.point = point;
        getName();
        getAddress();
        getState();
        getCity();

    }
    public UserInfo( String name, String address,String city, String state, String email){
        this.address = address;
        this.name = name;
        this.city = city;
        this.state = state;
        this.email = email;
        this.point = 0;
    }

    public String getName()  {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getEmail() { return email; }
    public int getPoint(){
        return point;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public void setPoint(int point){
        this.point = point;
    }
}

