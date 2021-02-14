package com.aips.demofirebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password;
    private TextView textView;
    private Button regbutton;
    private FirebaseAuth fireb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fireb = FirebaseAuth.getInstance();

        initializemethod();

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToHomeActivity();
            }
        });

    }

    private void RegisterUser() {
        String emid = email.getText().toString();
        String pwd = password.getText().toString();
        if(emid.isEmpty())
        {
            email.setError("Enter Your email...");
            email.requestFocus();
        }
        else if(pwd.isEmpty())
        {
            Toast.makeText(this, "Please enter Pws...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            fireb.createUserWithEmailAndPassword(emid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!(task.isSuccessful()))
                    {
                        Toast.makeText(RegisterActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registered Sucessful..", Toast.LENGTH_SHORT).show();
                        sendUserToHomeActivity();
                    }
                }
            });
        }
    }

    private void sendUserToHomeActivity() {
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
        finish();
    }

    private void initializemethod() {
        email = findViewById(R.id.regemail);
        password= findViewById(R.id.regepass);
        textView = findViewById(R.id.TEXTVIEWREG);
        regbutton = findViewById(R.id.regbtn);
    }
}