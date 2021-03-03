package com.example.instaparse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button bttnLogIn;
    private Button bttnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bttnLogIn = findViewById(R.id.bttnLogIn);
        bttnSignup = findViewById(R.id.bttnSignup);

        bttnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick button login");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(username, password);
            }
        });

        bttnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                            loginUser(username, password);
                        } else {
                            Log.e(TAG, "Sign up error.", e);
                            Toast.makeText(LoginActivity.this, "Sign up error.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void loginUser(String username, String password){

        Log.i(TAG, "Attempting to login user: " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with login");
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    return;
                }

                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity(){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

}
