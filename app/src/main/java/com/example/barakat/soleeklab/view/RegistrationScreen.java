package com.example.barakat.soleeklab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;

public class RegistrationScreen extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    EditText edtTxtEmail;
    EditText edtTxtPassword;
    EditText edtTxtPasswordConfirmation;
    Button btnRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

//        Get Instance of Firebase Auth and get the Current user and check if the user already logged in
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

//        Find Regester Screen views by Ids
        edtTxtEmail = findViewById(R.id.register_email);
        edtTxtPassword = findViewById(R.id.register_password);
        edtTxtPasswordConfirmation = findViewById(R.id.register_confirm_password);

        btnRegister = findViewById(R.id.btn_register_register_screen);
        btnRegister.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login_register_screen);
        btnLogin.setOnClickListener(this);
    }

    //    Sign up Process
    private void userSignUp() {
//        getting text from edit text fields
        String email = edtTxtEmail.getText().toString().trim();
        String password = edtTxtPassword.getText().toString().trim();
        String passwordConfirmation = edtTxtPasswordConfirmation.getText().toString().trim();

        if (password.length() < 6) {
            Toast.makeText(this, "Your password must have at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!passwordConfirmation.equals(password)) {
            Toast.makeText(this, "Both password fields must be identic", Toast.LENGTH_SHORT).show();
            return;
        }
//        check if the edit text is empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
//        in case connection is succeded
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                If the connection succeded Start The Main Activity else you will get Toast
                if (task.isSuccessful()) {
                    Toast.makeText(RegistrationScreen.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationScreen.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegistrationScreen.this, "Error, Invalid Email or Password please try again", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    //                OnClick function
    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
            userSignUp();
        } else if (v == btnLogin) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }
    }
}
