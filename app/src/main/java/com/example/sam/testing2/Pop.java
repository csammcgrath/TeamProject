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

/**
 * Created by Max&Lena on 12/6/2016.
 */

public class Pop extends AppCompatActivity  implements android.view.View.OnClickListener {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        button = (Button) findViewById(R.id.verify);
        editText = (EditText) findViewById(R.id.editText);
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int)(height* .6));

        button.setOnClickListener(this);
        editText.setOnClickListener(this);
    }

    public void checkPassword(){
        String password = editText.getText().toString().trim();
        Log.v("My Password : ", password);
        if(password.equals("1234")){
            Toast.makeText(this,"Congratulation!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Password Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view){
        if(view == button) {
            // Check if account exists
            Log.v("My Password : ", "I am here");
            checkPassword();
        }

    }
}