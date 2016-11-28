package com.example.sam.testing2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class settingActivity extends Fragment  {

    private FirebaseAuth firebaseAuth;
    private Button buttonLogOut;
    private TextView email;
    private TextView name;

    private TextView address;
    private TextView state;
    private TextView point;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.activity_setting, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Firebase.setAndroidContext(getActivity());
        userInfo = new UserInfo();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(user.getUid());

        buttonLogOut = (Button) getView().findViewById(R.id.logOut);
        email = (TextView) getView().findViewById(R.id.textView2);
        name = (TextView) getView().findViewById(R.id.textView3);
        address = (TextView) getView().findViewById(R.id.textView5);
        state = (TextView) getView().findViewById(R.id.textView6);
        point = (TextView) getView().findViewById(R.id.textView7);

        email.setText("Welcome " + user.getEmail() + " " + "Account Summary:");
        name.setText("Full name: " + userInfo.getName());
        address.setText("Address: " + userInfo.getAddress());
        state.setText("City and State: " + userInfo.getCity() + " " + userInfo.getState());
        point.setText("Awarded points: " + userInfo.getPoint());


        DatabaseReference upvotesRef = ref.child("point");
        upvotesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer currentValue = mutableData.getValue(Integer.class);
                if (currentValue == null) {
                    mutableData.setValue(1);
                } else {
                    mutableData.setValue(currentValue + 8);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, com.google.firebase.database.DataSnapshot dataSnapshot) {
                display();
            }

        });


        email.setText("Welcome " + user.getEmail() + " " + "Account Summary:");
        firebase = new Firebase("https://krazysub-aac40.firebaseio.com/");

        firebase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    userInfo = new UserInfo();

                    String strName = data.getValue(UserInfo.class).getName();
                    String strAddress = data.getValue(UserInfo.class).getAddress();
                    String strCity = data.getValue(UserInfo.class).getCity();
                    String strState = data.getValue(UserInfo.class).getState();
                    int strPoint = data.getValue(UserInfo.class).getPoint();

                    name.setText("Full name: " + strName);
                    address.setText("Address: " + strAddress);
                    state.setText("City and State: " + strCity + " ," + strState);
                    point.setText("Awarded points: " + strPoint );
                }
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
