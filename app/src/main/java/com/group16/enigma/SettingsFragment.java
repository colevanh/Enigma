package com.group16.enigma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Hayde on 22-Oct-16.
 */

public class SettingsFragment extends Fragment {

    private Button logOutButton;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();

        logOutButton =(Button) v.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        return v;
    }
}
