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

    public static String code = "1234";

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

        getWindow().setLayout((int) (width*.7),(int)(height* .5));

        button.setOnClickListener(this);
        editText.setOnClickListener(this);
    }

    public void checkPassword(){
        String pass = editText.getText().toString().trim();

        if(pass.equals("1234")){

            code = pass;
            Log.v("Code :  ", code);
            Toast.makeText(this,"Congratulation!", Toast.LENGTH_SHORT).show();
        }
        else{
            code = pass;
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