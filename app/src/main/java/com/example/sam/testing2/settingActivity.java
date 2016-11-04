package com.example.sam.testing2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class settingActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button buttonLogOut;
    private TextView email;
    private TextView name;

    private TextView address;
    private TextView state;
    private TextView point;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        userInfo = new UserInfo();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        buttonLogOut = (Button) findViewById(R.id.logOut);
        email = (TextView) findViewById(R.id.textView2);
        name = (TextView) findViewById(R.id.textView3);
        address = (TextView) findViewById(R.id.textView5);
        state = (TextView) findViewById(R.id.textView6);
        point = (TextView) findViewById(R.id.textView7);

        email.setText("Welcome " + user.getEmail() + " " + "Account Summary:");
        name.setText("Full name: " + userInfo.getName());
        address.setText("Address: " + userInfo.getAddress());
        state.setText("City and State: " + userInfo.getCity() + " " + userInfo.getState());
        point.setText("Awarded points: " + userInfo.getPoint());

        buttonLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == buttonLogOut){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, SignIn.class));
        }
    }
}
