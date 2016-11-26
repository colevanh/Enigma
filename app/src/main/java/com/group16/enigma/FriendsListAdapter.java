package com.group16.enigma;

/**
 * Created by Michaella on 11/25/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendsListAdapter extends BaseAdapter {
    Context context;
    List<User> friendsList;
    private static LayoutInflater inflater = null;
    public FriendsListAdapter(Context mainActivity, List<User> friendsList) {
        context = mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.friendsList = friendsList;
    }
    @Override
    public int getCount() {
        return friendsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item_friend, null);
        holder.tv=(TextView) rowView.findViewById(R.id.item_friend_name);
        holder.img=(ImageView) rowView.findViewById(R.id.item_friend_dp);
        holder.tv.setText(friendsList.get(position).email);
        setDPDrawable(friendsList.get(position).email, holder);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates new chat with friend
                Intent intent = new Intent(context, ChatActivity.class);
                //Fix below
                Conversation nConversation = new Conversation("testNewRefToConvo", "msheng@ucsd.edu");
                context.startActivity(intent);
            }
        });
        return rowView;
    }

    public static void setDPDrawable(String name, final Holder h){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(name.replace(".",""));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> objectMap = (HashMap<String, Object>)
                        dataSnapshot.getValue();
                if(objectMap != null){
                    h.img.setImageResource(getDPDrawable((String)objectMap.get("img")));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public static int getDPDrawable(String fileName){
        if(fileName == null){
            return R.drawable.bird;
        }
        switch (fileName){
            case "bat.png":
                return R.drawable.bat;
            case "bee.png":
                return R.drawable.bee;
            case "bird.png":
                return R.drawable.bird;
            case "butterfly.png":
                return R.drawable.butterfly;
            case "dog.png":
                return R.drawable.dog;
            case "dolphin.png":
                return R.drawable.dolphin;
            case "duck.png":
                return R.drawable.duck;
            case "fish.png":
                return R.drawable.fish;
            case "gorilla.png":
                return R.drawable.gorilla;
            case "kangaroo.png":
                return R.drawable.kangaroo;
            case "kiwi.png":
                return R.drawable.kiwi;
            case "rabbit.png":
                return R.drawable.rabbit;
            case "shark.png":
                return R.drawable.shark;
            case "snail.png":
                return R.drawable.snail;
            case "turtle.png":
                return R.drawable.turtle;
            default:
                return R.drawable.bird;

        }
    }

}

