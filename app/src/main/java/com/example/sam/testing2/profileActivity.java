package com.example.sam.testing2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    // firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button  buttonSave;
    private Button buttonMain;
    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextCite;
    private EditText editTextNameSate;


    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initialize firebase object
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextCite = (EditText) findViewById(R.id.editTextCity);
        editTextNameSate = (EditText) findViewById(R.id.editTextState);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail.setText("Welcome " + user.getEmail());
        buttonSave = (Button) findViewById(R.id.safeInfo);
        buttonMain = (Button) findViewById(R.id.mainMenu);


        buttonSave.setOnClickListener(this);
        buttonMain.setOnClickListener(this);


    }

    private void saveUserInfo(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String city = editTextCite.getText().toString().trim();
        String state = editTextNameSate.getText().toString().trim();
        int point = 0;

        UserInfo userInfo = new UserInfo(name, address,city, state);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInfo);

        Toast.makeText(this, "Information saved...", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onClick(View view) {

        if(view == buttonMain){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        if(view == buttonSave){
            saveUserInfo();
        }

    }
}
