package com.projects.rtk154.firebasedemoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseRegister;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFirebaseRegister=FirebaseAuth.getInstance();
        if(mFirebaseRegister.getCurrentUser()!=null) {
            finish();
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        final EditText name=(EditText)findViewById(R.id.nameET);
        final EditText email=(EditText)findViewById(R.id.emailET);
        final EditText password=(EditText)findViewById(R.id.passwordET);
        final Button register=(Button)findViewById(R.id.registerButton);
        TextView signIn=(TextView)findViewById(R.id.signInTV);
        mProgressBar =(ProgressBar)findViewById(R.id.progressBarView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(email,password,name);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to signIn
                finish();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void register(EditText email,EditText password,EditText name){
        String emailEntered=email.getText().toString().trim();
        final String passwordEnterd=password.getText().toString().trim();
        final String nameEntered=name.getText().toString().trim();
        if(emailEntered.isEmpty()||passwordEnterd.isEmpty()) {
            Toast.makeText(this, "Email or Password not entered", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            mProgressBar.setVisibility(View.VISIBLE);
            mFirebaseRegister.createUserWithEmailAndPassword(emailEntered,passwordEnterd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                //adding user name

                                FirebaseUser user = mFirebaseRegister.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nameEntered).build();

                                user.updateProfile(profileUpdates);

                                //message showing registeration complete
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();

                                //starting profile activity

                                finish();
                                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                                startActivity(intent);
                            }
                            else {
//                                if(passwordEnterd.length()<6) {
//                                    Toast.makeText(MainActivity.this, "Password is too Short...Try Again..", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
                                //properly shows error message about password length ,already registered user and other
                                Toast.makeText(MainActivity.this, "Failed to create user:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //Log.i("Response","Failed to create user:"+task.getException().getMessage());
                            }
                        }
                    });
        }


    }
}
