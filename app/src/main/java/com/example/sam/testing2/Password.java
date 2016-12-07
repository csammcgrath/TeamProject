package com.example.sam.testing2;

/**
 * Created by Max&Lena on 12/7/2016.
 */

public class Password {
    private String password;

    public Password(){
        password = "unknown";
    }
    public Password(String password){
        this.password = password;
    }

    public String getPassword()              {return password;         }
    public void setPassword(String password) {this.password = password;}
}
