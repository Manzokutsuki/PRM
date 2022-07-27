package com.example.authenticationform;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvNotAccountYet;
    private Button btnSignIn;
    private final String REQUIRE="Require";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Reference from Layout
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tvNotAccountYet = findViewById(R.id.tvAlreadyAccount);
        btnSignIn = findViewById(R.id.btnSignUp);
        tvNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private boolean checkInput(){
        if(TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError(REQUIRE);
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void signIn(){
        if(!checkInput()){
            return;
        }
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpForm(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                signIn();
                break;
            case R.id.tvAlreadyAccount:
                signUpForm();
                break;
        }
    }
}
