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

public class MainActivity extends AppCompatActivity {
    private EditText email , pass;
    private Button loginbutton;
    private TextView t1;
    private FirebaseAuth fireb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMethod();
        fireb = FirebaseAuth.getInstance();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

    }

    private void LoginUser() {
        String emid = email.getText().toString();
        String pwd = pass.getText().toString();
        if(emid.isEmpty())
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(pwd.isEmpty())
        {
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            fireb.signInWithEmailAndPassword(emid, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Sign In Succesful", Toast.LENGTH_SHORT).show();
                        sendUserToHomeActivity();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Sign in Unsuccesful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToHomeActivity() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    private void sendUserToRegisterActivity() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private void initializeMethod() {
        email = findViewById(R.id.logemail);
        pass = findViewById(R.id.logpass);
        loginbutton = findViewById(R.id.logbtn);
        t1 = findViewById(R.id.textviewlog);
    }
}