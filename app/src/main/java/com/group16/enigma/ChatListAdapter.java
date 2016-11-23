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

import java.util.List;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class ChatListAdapter extends BaseAdapter {
    Context context;
    List<Conversation> conversationList;
    private static LayoutInflater inflater=null;
    public ChatListAdapter(Context mainActivity, List<Conversation> conversationList) {
        // TODO Auto-generated constructor stub
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.conversationList = conversationList;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return conversationList.size();
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
        holder.tv.setText(conversationList.get(position).friend);
        holder.img.setImageResource(R.drawable.bee);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigate to chat page
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", conversationList.get(position).friend);
                intent.putExtra("reference", conversationList.get(position).reference);
                context.startActivity(intent);
            }
        });
        return rowView;
    }

}
