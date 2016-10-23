package com.group16.enigma;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class ChatListFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        // Get ListView object from xml
//        ListView listView = (ListView) getView().findViewById(R.id.list);
//
//        // Defined Array values to show in ListView
//        String[] values = new String[] {
//                "Bill Gates",
//                "Steve Jobs",
//                "Elon Musk",
//                "Larry Page",
//                "Spongebob Squarepants",
//        };
//
//        int[] tempDp = new int[]{
//                R.drawable.bat,
//                R.drawable.bird,
//                R.drawable.fish,
//                R.drawable.kangaroo,
//                R.drawable.shark
//
//        };
//
//        listView.setAdapter(new ChatListAdapter(getContext(), values, tempDp));


        return inflater.inflate(R.layout.fragment_chatlist, container, false);
    }
}


