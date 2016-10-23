package com.group16.enigma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class ChatListAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public ChatListAdapter(MainActivity mainActivity, String[] nameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=nameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item_chat, null);
        holder.tv=(TextView) rowView.findViewById(R.id.item_chat_friend_name);
        holder.img=(ImageView) rowView.findViewById(R.id.item_chat_friend_dp);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigate to chat page
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", result[position]);
                context.startActivity(intent);
            }
        });
        return rowView;
    }

}
