package com.group16.enigma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class FriendsListFragment extends Fragment {

    private DatabaseReference mDatabase;
    public static String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_friendslist, container, false);
        final ArrayList<String> list = new ArrayList<String>();
        final ListView lv = (ListView) v.findViewById(R.id.userList);

        //Get reference to database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Listen only for user changes
        Query mQuery = mDatabase.orderByKey().equalTo("user");
        // Attach a listener to read the data reference
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    username = user.getKey();
                    list.add(username);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, list);
                lv.setAdapter(arrayAdapter);
            }
            @Override
            //TODO:FIX
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    username = user.getKey();
                    list.remove(username);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, list);
                lv.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The child read failed: " + databaseError.getCode());
            }
        });
        return v;
    }

}
