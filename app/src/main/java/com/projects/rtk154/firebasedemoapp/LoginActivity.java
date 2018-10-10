package com.projects.rtk154.firebasedemoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseSignIn;
    ProgressBar  mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseSignIn=FirebaseAuth.getInstance();
        if(mFirebaseSignIn.getCurrentUser()!=null) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
        final EditText emailLogin=(EditText)findViewById(R.id.LoginEmailET);
        final EditText passwordLogin=(EditText)findViewById(R.id.LoginPasswordET);
        final Button signIn=(Button)findViewById(R.id.signInButton);
        TextView registerAgain=(TextView)findViewById(R.id.registerTV);
        TextView forgotPassword=(TextView)findViewById(R.id.forgotPassTV);
        mProgressBar =(ProgressBar)findViewById(R.id.progressBarView2);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(emailLogin,passwordLogin);
            }
        });
        registerAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to signIn
                finish();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open Forget Password Activity
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });
    }

    private void logIn(EditText emailLogin, EditText passwordLogin) {
        String email=emailLogin.getText().toString().trim();
        String password=passwordLogin.getText().toString().trim();
        if(email.isEmpty()||password.isEmpty()) {
            Toast.makeText(this, "Email or Password not entered", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            mProgressBar.setVisibility(View.VISIBLE);
            mFirebaseSignIn.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()) {
                                //message showing registeration complete
                                Toast.makeText(LoginActivity.this,"Signed in Successfully",Toast.LENGTH_SHORT).show();

                                //starting profile activity

                                finish();
                                Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                                startActivity(intent);
                            }
                            else {
                                //properly shows error message about password length ,already registered user and other
                                Toast.makeText(LoginActivity.this, "Failed to create user:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
