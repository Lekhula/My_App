package com.pabi.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Admin on 1/18/2018.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnSign;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!= null){
            startActivity(new Intent(SignUpActivity.this, BottomNav_Activity.class));
            finish();
        }
        setContentView(R.layout.signup_activity);


        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPassword);

        btnSign = (Button)findViewById(R.id.btnSignUp);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        String inputEmail = email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();

        if(TextUtils.isEmpty(inputEmail)){
            Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(inputEmail)){
            Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(inputPassword.length() < 8){
            Toast.makeText(getApplicationContext(), "Password too short, please enter a minimum of 8 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        if(!task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Authentication failed!" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();

                        }
                    }
                });

                //Intent sign = new Intent(SignUpActivity.this, BottomNav_Activity.class);
                //startActivity(sign);
            }
        });
    }
}
