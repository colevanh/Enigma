package com.group16.enigma;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.group16.enigma.MainActivity.mUsername;

public class ChatActivity extends AppCompatActivity {

    //Firebase object
    private DatabaseReference mFirebaseDatabaseReference;
    //*The message child in the console
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMessageRecyclerView;
    private EditText mMessageEditText;
    private Button mSendButton;
    private Aes.SecretKeys keys;
    private Aes.CipherTextIvMac cipherTextIvMac;
    private String ciphertextString;
    private boolean isDecrypting = false;
    private String userKey;
    private boolean verified = true;

    //*General string used for writing all messages
    public static String MESSAGES_CHILD = "messages";
    public String MESSAGES_HASH;
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 200;

    /*Reformat database structure to:
        AllMessages
            Messages1
                Key
            Messages2
            Messages3
            Messages4
        User1 (accessible only to User1 on login, created on sign up)
            Friend1 - Reference to Messages1 (Unique history of msgs between User1 and Friend1)
            Friend2 - Reference to Messages2
        User2
            Friend1 - Reference to Messages3
            Friend3 - Reference to Messages4
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        getSupportActionBar().setTitle(name);

        //What does this do?
        //MESSAGES_CHILD = name.replace(".", "");
        MESSAGES_CHILD = "messages";
        MESSAGES_HASH = "423472384"

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageRecyclerView = (RecyclerView) findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);

        //Only need to call reference once?
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child("chat").child(MESSAGES_HASH);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.item_message,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child(MESSAGES_CHILD)){

            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message friendlyMessage, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.messageTextView.setText(friendlyMessage.getText());
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

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);

        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mSendButton = (Button) findViewById(R.id.sendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    cipherTextIvMac = Aes.encrypt(mMessageEditText.getText().toString(), keys);
                    ciphertextString = cipherTextIvMac.toString();

                }catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                Message message = new Message(ciphertextString, mUsername,
                        null);
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(message);
                mMessageEditText.setText("");
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        //mFirebaseDatabaseReference.child("chat").child("somekey").child("key").push().setValue("Hello");

//        Map<String, String> chattest = new HashMap<String, String>();
//        chattest.put("key", "apple");
//        chattest.put("salt", "somelongsalt");
//
//        mFirebaseDatabaseReference.child("chat").child("423472384").child("key").setValue(chattest);
//        mFirebaseDatabaseReference.child("chat").child("423472384").child("messages").setValue(new Message("cipher string","h@d.com", null));

        String salt = null;
        try{
            salt = Aes.saltString(Aes.generateSalt());

        }catch(GeneralSecurityException e){
            e.printStackTrace();
        }

        String hardcodeSalt = "VMq4oX7rxhRS8r0vwWEc2uspgsu/rpF7G+mw3vgtUzrAcsNGHQJb+DKXDrolxUTBGpq8XAkKRUWN5ZeSRUi7dSYavjO/gwErrDv2sDzoEaTvQCXLNpAhm6cwxLidAw3nTOY/wITpY4DiZzbV8bMatUjhCRPsujBHZY8CqD0oTbU=";
        try{
            keys = Aes.generateKeyFromPassword("TEST", hardcodeSalt);
        }catch(GeneralSecurityException e){
            e.printStackTrace();
        }

        promptPassword();

    }

    private void promptPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the key");
        View viewInflated = getLayoutInflater().inflate(R.layout.dialog_key, null);

        final EditText input = (EditText) viewInflated.findViewById(R.id.user_key);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                userKey = input.getText().toString();
            }
        });

        builder.show();
    }


    public int hash(String v1, String v2) {
        return v1.hashCode() ^ v2.hashCode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_decode_message:
//                Toast.makeText(this, "TODO: Decode most recent", Toast.LENGTH_SHORT)
//                        .show();
                if(verified)
                    refreshFirebaseAdapter(true);


                break;

            case R.id.action_decode_message_all:
//                Toast.makeText(this, "TODO: Decode all", Toast.LENGTH_SHORT)
//                        .show();
                if(verified)
                    refreshFirebaseAdapter(false);
                break;
            default:
                break;
        }

        return true;
    }
    private void refreshFirebaseAdapter(boolean isAll){

        FirebaseRecyclerAdapter<Message, MessageViewHolder> tempAdapter;
        if(isDecrypting){
            return;
        }
        isDecrypting = true;

        if(isAll) {
            tempAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                    Message.class,
                    R.layout.item_message,
                    MessageViewHolder.class,
                    mFirebaseDatabaseReference.child(MESSAGES_CHILD)) {

                @Override
                protected void populateViewHolder(MessageViewHolder viewHolder, Message friendlyMessage, int position) {
                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    if (this.getItemCount() - 1 == position)

                        viewHolder.messageTextView.setText(decypherText(friendlyMessage.getText()));
                    else
                        viewHolder.messageTextView.setText(friendlyMessage.getText());
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
        } else{
            tempAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                    Message.class,
                    R.layout.item_message,
                    MessageViewHolder.class,
                    mFirebaseDatabaseReference.child(MESSAGES_CHILD)) {

                @Override
                protected void populateViewHolder(MessageViewHolder viewHolder, Message friendlyMessage, int position) {
                    mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    viewHolder.messageTextView.setText(decypherText(friendlyMessage.getText()));
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
        mMessageRecyclerView.swapAdapter(tempAdapter, true);
        isDecrypting = false;
    }

    private String decypherText(String message){
        String decryptedMessage = "";
        try {
            decryptedMessage = Aes.decryptString(new Aes.CipherTextIvMac(message), keys);

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(GeneralSecurityException e){
            e.printStackTrace();
        }
        return decryptedMessage;
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public CircleImageView messengerImageView;

        public MessageViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
            messengerImageView = (CircleImageView) itemView.findViewById(R.id.messengerImageView);
        }
    }
}
