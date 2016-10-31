package com.example.sam.testing2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.sam.testing2.R.styleable.View;

public class Singin extends AppCompatActivity implements android.view.View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private  EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();

            // Stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            // password id empty
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            // Stopping the function execution further
            return;
        }

        // if validation  are ok
        // we wil register
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            // user is successfully registered
                            // we will start the profile activity
                            // here
                           finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Singin.this,"Could not register", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view){
        if(view == buttonRegister) {
            registerUser();
        }

         if(view == textViewSignin){
             // will open login activity here
             startActivity(new Intent(this, MainActivity.class));
         }
    }
}
