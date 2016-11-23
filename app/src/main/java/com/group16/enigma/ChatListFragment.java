package com.group16.enigma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class ChatListFragment extends Fragment {
    private List<Conversation> conversationList;

    private DatabaseReference mFirebaseDatabaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_chatlist, container, false);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(MainActivity.mUsername.replace(".","")).child("conversations");

        // Attach a listener to read the data at our posts reference
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> objectMap = (HashMap<String, Object>)
                        dataSnapshot.getValue();
                conversationList= new ArrayList<>();
                if(objectMap != null){
                    for (Object obj : objectMap.values()) {
                        if (obj instanceof Map) {
                            Map<String, Object> mapObj = (Map<String, Object>) obj;
                            Conversation convo = new Conversation();
                            convo.setReference((String) mapObj.get("reference"));
                            Log.v("////", (String) mapObj.get("reference"));
                            Log.v("////", (String) mapObj.get("friend"));
                            convo.setFriend((String) mapObj.get("friend"));
                            conversationList.add(convo);
                        }
                    }
                }
                ListView listView = (ListView) v.findViewById(R.id.chatList);
                listView.setAdapter(new ChatListAdapter(getContext(),conversationList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // Get ListView object from xml


        // Defined Array values to show in ListView
        String[] values = new String[] {
                "a@b.com",
                "arminv94@gmail.com",
                "colevanh@gmail.com",
                "h@d.com",
                "hello@hello.com",
                "mich@test.com",
                "michaella.sheng@gmail.com",
                "rudr.tandon@gnail.com",
                "tmelano82@gmail.com",
                //"Bill Gates",
                //"Steve Jobs",
                //"Elon Musk",
                //"Spongebob Squarepants"
        };

        //TODO:Once user is added, add an icon for them so we don't have to manually add in code
        int[] tempDp = new int[]{
                R.drawable.bat,
                R.drawable.bird,
                R.drawable.fish,
                R.drawable.kangaroo,
                R.drawable.shark,
                R.drawable.butterfly,
                R.drawable.bee,
                R.drawable.gorilla,
                R.drawable.dog
        };


        return v;
    }
}
