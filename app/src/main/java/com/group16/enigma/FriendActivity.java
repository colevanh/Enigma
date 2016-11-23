package com.group16.enigma;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Michaella on 11/19/2016.
 */

//This activity is for the logged in user's list of active friends.
/*public class FriendActivity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    public static String USERS_CHILD = "users";
    private FirebaseRecyclerAdapter<User, FriendActivity.FriendViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mFriendRecyclerView;

    //TODO: Define the adaptors and layouts for friends class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<User, ChatActivity.UserViewHolder>(
                User.class,
                R.layout.item_user,
                ChatActivity.UserViewHolder.class,
                mFirebaseDatabaseReference.child(USERS_CHILD)) {

            @Override
            protected void populateViewHolder(ChatActivity.UserViewHolder viewHolder, User activeUser, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.FriendView.setText(friendlyMessage.getText());
                viewHolder.messengerTextView.setText(friendlyMessage.getName());
                if (friendlyMessage.getPhotoUrl() == null) {
                    viewHolder.messengerImageView.setImageDrawable(ContextCompat.getDrawable(ChatActivity.this,
                            R.drawable.bird));
                } else {
                    Glide.with(ChatActivity.this)
                            .load(friendlyMessage.getPhotoUrl())
                            .into(viewHolder.messengerImageView);
                }
            }
        };
    }
    //TODO:Define this to hold all friends for the active user
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public CircleImageView messengerImageView;

        public UserViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
            messengerImageView = (CircleImageView) itemView.findViewById(R.id.messengerImageView);
        }
    }
}*/
