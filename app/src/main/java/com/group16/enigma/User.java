package com.group16.enigma;

import java.util.List;

/**
 * Created by Michaella on 11/13/2016.
 */

public class User {
    public String email;
    private String password;
    List<Friend> friends;

    public User() {
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
