package com.group16.enigma;


/**
 * Created by Michaella on 11/13/2016.
 */

public class User {
    public String email;
    public String username;

    public User() {}
    public User(String email) {
        this.email = email;
        int i = 0;
        while(email.charAt(i) != '@') {
            username += email.charAt(i);
            ++i;
        }
    }
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
    public String getUsername() { return username; }
    public void setUsername(String newUsername) {
        username = newUsername;
        return ;
    }
    public String getEmail() {
        return email;
    }
    public void addFriend() {}
    public void removeFriend() {}
}
