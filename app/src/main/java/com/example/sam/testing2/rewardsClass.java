package com.example.sam.testing2;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sam on 11/30/2016.
 */

public class rewardsClass extends Fragment {

    private Handler mHandler = new Handler();
    private int points = 0;
    private int leftOverPoints = 50;
    private TextView messageText;
    private TextView instructText;
    private Button claimButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;
    private int progress = 0;

    private synchronized int getProgressBar() {
        return this.progress;
    }

    private synchronized void setProgressBar(int progress) {
        this.progress = Math.min(50, progress);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.activity_rewards, container, false);

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

        messageText = (TextView)getView().findViewById(R.id.textView11);
        claimButton = (Button)getView().findViewById(R.id.button2);
        instructText = (TextView)getView().findViewById(R.id.textView10);
        progressBar = (ProgressBar)getView().findViewById(R.id.progressBar2);

        //points = userInfo.getPoint();
        points = 50;
        leftOverPoints = 50 - points;

        setProgressBar(points);

        mHandler.post(new Runnable() {
            public void run() {
                progressBar.setProgress(getProgressBar() * 2);
            }
        });

        messageText.setText("You have " + points + " points! You are " + leftOverPoints + " away from the FREE sub!");

        if (points >= 50) {
            messageText.setVisibility(View.INVISIBLE);
            instructText.setVisibility(View.VISIBLE);
            claimButton.setVisibility(View.VISIBLE);
        } else {
            messageText.setVisibility(View.VISIBLE);
            instructText.setVisibility(View.INVISIBLE);
            claimButton.setVisibility(View.INVISIBLE);
        }
    }


}