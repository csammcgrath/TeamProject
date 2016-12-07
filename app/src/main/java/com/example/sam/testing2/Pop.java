package com.example.sam.testing2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

/**
 * Created by Max&Lena on 12/6/2016.
 */

public class Pop extends AppCompatActivity  implements android.view.View.OnClickListener {
    private Button button;
    private EditText editText;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private  DatabaseReference ref;
    private Firebase firebase;
    private FirebaseUser user;

    public static String code = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser(); //current user's ID

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference(user.getUid());
        button = (Button) findViewById(R.id.verify);
        editText = (EditText) findViewById(R.id.editText);
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.7),(int)(height* .5));

        button.setOnClickListener(this);
        editText.setOnClickListener(this);
    }

    public void checkPassword(){
        String pass = editText.getText().toString().trim();

        if(pass.equals("1234")){
            code = pass;


            //each uid has children that make up user info such as email, or numPoints
            DatabaseReference upvotesRef = ref.child("point");
            upvotesRef.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    //This is used to increment user points after a transaction
                    Integer currentValue = mutableData.getValue(Integer.class);
                        //increment value by 2
                        mutableData.setValue(currentValue - 50);

                    //assume transaction worked, and return new value
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b, com.google.firebase.database.DataSnapshot dataSnapshot) {
                    // display();
                }

            });
            Toast.makeText(this,"Congratulation! 50 points were subtracted.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Password Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view){
        if(view == button) {
            // Check if account exists
            checkPassword();
        }

    }
}