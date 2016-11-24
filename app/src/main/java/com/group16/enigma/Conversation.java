package com.group16.enigma;

/**
 * Created by Hayde on 22-Nov-16.
 */

public class Conversation {
    String reference;
    String friend;
    public Conversation(String ref, String f){
        reference = ref;
        friend = f;
    }

    public Conversation(){}

    public void setReference(String ref){
        reference = ref;
    }

    public void setFriend(String fri){
        friend = fri;
    }
}
