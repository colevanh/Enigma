package com.group16.enigma;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
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

        Button github = (Button) v.findViewById(R.id.githubButton);
        github.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/colevanh/Enigma"));
                startActivity(browserIntent);
            }
        });

        return v;

    }
}
