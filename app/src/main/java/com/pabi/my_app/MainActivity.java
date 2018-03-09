package com.pabi.my_app;

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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private FirebaseAuth auth;
    private LoginButton loginButton;
    private Button buttonLog;
    private TextView signup;
    private TextView reset;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        auth = FirebaseAuth.getInstance();

        FacebookSdk.sdkInitialize(getApplicationContext());
        initializeControls();
        loginWithFB();



        if(auth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(this,BottomNav_Activity.class));

        }


        email = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        reset = (TextView)findViewById(R.id.txtReset);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fb = new Intent(MainActivity.this, Lineup_Fragment.class);
                startActivity(fb);
            }
        });

        signup = (TextView)findViewById(R.id.signUpAcc);
        buttonLog = (Button)findViewById(R.id.btnLogInGoogle);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(reset);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });


        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = email.getText().toString().trim();
                final  String inputPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(inputEmail)){
                    Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(inputPassword)){
                    Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Intent log = new Intent(MainActivity.this, BottomNav_Activity.class);
                //startActivity(log);

                auth.signInWithEmailAndPassword(inputEmail,inputPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {

                                    if (inputPassword.length() < 8) {
                                        password.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, BottomNav_Activity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }


                        });

            }
        });

    }
    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();

    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
