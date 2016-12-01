package com.group16.enigma;

/**
 * Created by Hayde on 22-Nov-16.
 */

public class Conversation {
    public String reference;
    public String friend;
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

    public String getReference() {
        return reference;
    }

    public String getFriend() {
        return friend;
    }
}
