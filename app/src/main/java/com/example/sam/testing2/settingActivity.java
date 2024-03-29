package com.example.sam.testing2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.Iterator;


public class settingActivity extends Fragment  {

    private FirebaseAuth firebaseAuth;
    private Button buttonLogOut;
    private TextView email;
    private TextView name;
    private String strName;
    private String strAddress;
    private String strCity;
    private String strState;
    private int strPoint;
    private TextView settings;

    private TextView address;
    private TextView state;
    private TextView point;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;
    FirebaseUser user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.activity_setting, container, false);
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
        user = firebaseAuth.getCurrentUser(); //current user's ID


        // final
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //the UID of the current user
        DatabaseReference ref = database.getReference(user.getUid());


        //prompt user for information
        buttonLogOut = (Button) getView().findViewById(R.id.logOut);
        email = (TextView) getView().findViewById(R.id.textView2);
        name = (TextView) getView().findViewById(R.id.textView3);
        address = (TextView) getView().findViewById(R.id.textView5);
        state = (TextView) getView().findViewById(R.id.textView6);
        point = (TextView) getView().findViewById(R.id.textView7);

        settings = (TextView) getView().findViewById(R.id.settings);
        settings.setTextColor(Color.parseColor("#D9251C"));



        //we will use this to construct a new user.
        email.setText("Account Summary:");

        firebase = new Firebase("https://krazysub-aac40.firebaseio.com/");

        firebase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot data) {

                if (data.hasChildren()) {
                    Iterator<DataSnapshot> it = data.getChildren().iterator();

                    while (it.hasNext()) {
                        DataSnapshot dataSnapshot = (DataSnapshot) it.next();


                        if(user.getEmail() != null) {
                            if (dataSnapshot.getValue(UserInfo.class).getEmail().equals(user.getEmail())) {

                                strName = dataSnapshot.getValue(UserInfo.class).getName();
                                strAddress = dataSnapshot.getValue(UserInfo.class).getAddress();
                                strCity = dataSnapshot.getValue(UserInfo.class).getCity();
                                strState = dataSnapshot.getValue(UserInfo.class).getState();
                                strPoint = dataSnapshot.getValue(UserInfo.class).getPoint();

                                break;
                            }
                        }
                    }
                }
                // Set text on the Screen
                name.setText("Full name: " + strName);
                address.setText("Address: " + strAddress);
                state.setText("City and State: " + strCity + " ," + strState);
                point.setText("Awarded points: " + strPoint);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();

                Intent intent = new Intent(getActivity(), SignIn.class);

                getActivity().startActivity(intent);
            }
        });
    }

    // Points were added
    public void display(){
       Toast.makeText(settingActivity.this.getActivity(),"New Reward Points were added...", Toast.LENGTH_SHORT).show();
    }
}
