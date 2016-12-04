package com.example.sam.testing2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

/**
 * Created by Sam on 11/21/2016.
 */

public class homeScreen extends Fragment {

    private FirebaseAuth firebaseAuth;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;

    private TextView email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.homescreen, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //sets context to the current fragment
        Firebase.setAndroidContext(getActivity());
        //create new instance of userInfo class
        userInfo = new UserInfo();

        //this points to the database's URL
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser(); //current user's ID

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //the UID of the current user
        DatabaseReference ref = database.getReference(user.getUid());

        //connects the email variable to the respective textview (13)
        email = (TextView) getView().findViewById(R.id.textView13);
        //displays a welcome with the current user's ID
        email.setText("Welcome " + user.getEmail() + "!");



    }
}
