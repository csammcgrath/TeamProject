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
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonLogOut = (Button) findViewById(R.id.logOut);
        textView = (TextView) findViewById(R.id.textView2);

        textView.setText("Welcome " + user.getEmail());
        buttonLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == buttonLogOut){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Singin.class));
        }
    }
}
