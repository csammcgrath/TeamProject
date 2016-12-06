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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

class SignIn extends AppCompatActivity implements android.view.View.OnClickListener {

    private Button signIn;
    private EditText editTextEmail;
    private  EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        firebaseAuth = FirebaseAuth.getInstance();

        // If a user logged in go string to the Application
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        signIn = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        signIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    // Does an account exist?
    private void isLoggedIn(){

        // Get email and user form from User
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate should not be empty
        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(SignIn.this,"Please enter email", Toast.LENGTH_SHORT).show();

            // Stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            // password is empty
            Toast.makeText(SignIn.this,"Please enter password", Toast.LENGTH_SHORT).show();
            // Stopping the function execution further
            return;
        }

        // While waiting enjoy the massage
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            // user is successfully registered
                            // we will start the main activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(SignIn.this,"Your credentials could not be verified", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    // Register A new user
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(SignIn.this,"Please enter email", Toast.LENGTH_SHORT).show();
            // Stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            // password is empty
            Toast.makeText(SignIn.this,"Please enter password", Toast.LENGTH_SHORT).show();
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
                            startActivity(new Intent(getApplicationContext(), profileActivity.class));
                        }else{
                            Toast.makeText(SignIn.this,"Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view){
        if(view == signIn) {
            // Check if account exists
            isLoggedIn();
        }

        if(view == textViewSignUp) {
            // Register a new user
            registerUser();
        }
    }
}
