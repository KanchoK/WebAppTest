package com.web;

/**
 * Created by R500 on 10.7.2014 г..
 */
public class User {
    private int userID;
    private String username;
    private String password;

    public int getUserID(){
        return userID;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUserID(int ID){
        userID = ID;
    }

    public void setUsername(String name){
        username = name;
    }

    public void setPassword(String pass){
        password = pass;
    }
}
