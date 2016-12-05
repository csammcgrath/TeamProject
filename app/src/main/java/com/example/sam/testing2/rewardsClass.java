package com.example.sam.testing2;

import android.graphics.Color;
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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

/**
 * Created by Sam on 11/30/2016.
 */

public class rewardsClass extends Fragment {

    private Handler mHandler = new Handler();
    private int leftOverPoints = 50;
    private TextView messageText;
    private TextView rewardTitle;
    private TextView instructText;
    private Button claimButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;
    private int progress = 0;
    private  int userPoints;

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

        rewardTitle = (TextView) getView().findViewById(R.id.textView4);
        rewardTitle.setTextColor(Color.parseColor("#D9251C"));
        messageText = (TextView) getView().findViewById(R.id.textView11);
        claimButton = (Button) getView().findViewById(R.id.button2);
        instructText = (TextView) getView().findViewById(R.id.textView10);
        instructText.setTextColor(Color.parseColor("#D9251C"));
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar2);


        // Point Calculation

        //each uid has children that make up user info such as email, or numPoints
        DatabaseReference upvotesRef = ref.child("point");
        //instantiate a new transaction
        //transactions are used to change user information
        upvotesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                //This is used to increment user points after a transaction
                Integer currentValue = mutableData.getValue(Integer.class);
                if (currentValue == null) {
                    mutableData.setValue(1);
                } else {
                    //increment value by 8
                    mutableData.setValue(currentValue + 2);
                }
                //assume transaction worked, and return new value
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, com.google.firebase.database.DataSnapshot dataSnapshot) {
                display();
            }

        });


        firebase = new Firebase("https://krazysub-aac40.firebaseio.com/");
        firebase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    userInfo = new UserInfo();
                    userPoints = data.getValue(UserInfo.class).getPoint();


                    leftOverPoints = 50 - userPoints;

                    setProgressBar(userPoints);

                    mHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(getProgressBar() * 2);
                        }
                    });

                    messageText.setText("You have " + userPoints + " points! You are " + leftOverPoints + " away from a FREE sub!");

                    if (userPoints >= 50) {
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

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    // Points were added
    public void display(){
        Toast.makeText(rewardsClass.this.getActivity(),"New Reward Points were added...", Toast.LENGTH_SHORT).show();
    }
}