package com.group16.enigma;

import java.util.Date;

/**
 * Created by Michaella on 11/3/2016.
 */

public class Message {
    private String text;
    private String sender;
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
