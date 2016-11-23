package com.group16.enigma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaella on 11/13/2016.
 */

public class User {
    private int ID;
    public String email;
    public String username;
    private String password;

    //List of friends, which is a list of conversations
    ArrayList<ArrayList<Message>> friends;

    public User() {
    }
    //TODO: Define constructor that populates an existing friends list
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFriend() {

    }
    public void removeFriend() {

    }
}
