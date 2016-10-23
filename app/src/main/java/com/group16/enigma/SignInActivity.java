package com.group16.enigma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Hayde on 23-Oct-16.
 */

public class SignInActivity  extends AppCompatActivity {
    private Button createAccountBtn;
    private EditText textEmailAddress;
    private EditText textPassword;
    private EditText textFname;
    private EditText textLname;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        createAccountBtn = (Button) findViewById(R.id.createAccountButton);
        textEmailAddress = (EditText) findViewById(R.id.email);
        textPassword = (EditText) findViewById(R.id.password);
        textFname = (EditText) findViewById(R.id.firstName);
        textLname = (EditText) findViewById(R.id.lastName);

        mAuth = FirebaseAuth.getInstance();


//        createAccountBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createAccount(textEmailAddress.getText().toString(), textPassword.getText().toString());
//            }
//        });


//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d("///////////////", "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d("///////////////////", "onAuthStateChanged:signed_out");
//                }
//            }
//        };

    }

    private void createAccount(String firstName, String lastName){
            mAuth.createUserWithEmailAndPassword(firstName, lastName)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("/////////", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Failed Auth :'(",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

}