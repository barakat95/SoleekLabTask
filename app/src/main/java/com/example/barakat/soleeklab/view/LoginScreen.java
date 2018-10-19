package com.example.barakat.soleeklab.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barakat.soleeklab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    EditText edtTxtEmail;
    EditText edtTxtPassword;
    Button btnLogin;
    Button btnRegister;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

//        Get Instance of Firebase Authuntecation
        firebaseAuth = FirebaseAuth.getInstance();
//        Check if the user already logged in start the Main Activity
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
//        Find Login Activity Views By Ids
        edtTxtEmail = findViewById(R.id.login_email);
        edtTxtPassword = findViewById(R.id.login_password);

        btnLogin = findViewById(R.id.btn_login_login_screen);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btn_register_login_screen);
        btnRegister.setOnClickListener(this);
    }

    //    Login Process
    private void userLogin() {
//        getting text from edit text fields
        String email = edtTxtEmail.getText().toString().trim();
        String password = edtTxtPassword.getText().toString().trim();
//        check if the edit text is empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter your email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        //        in case connection is succeded
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                If the connection succeded Start The Main Activity else you will get Toast
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(LoginScreen.this, "user loged in successfully", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(LoginScreen.this, "Can't login, check entered data and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    //                OnClick function
    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            userLogin();
        } else if (v == btnRegister) {
//            Open sign up activity
            finish();
            startActivity(new Intent(this, RegistrationScreen.class));
        }
    }
}
