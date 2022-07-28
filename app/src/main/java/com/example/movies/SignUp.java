package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movies.Data.Databasehandler;
import com.example.movies.model.Info;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    Button signUp;
    TextInputLayout uname,emailid,number,password,conf_password;
    TextInputEditText uname2,emailid2,number2,password2,conf_password2;
    float v=0;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uname = findViewById(R.id.name);
        emailid = findViewById(R.id.email);
        number = findViewById(R.id.mobileNo);
        password = findViewById(R.id.password);
        conf_password = findViewById(R.id.confirm_password);
        signUp = findViewById(R.id.register);

        uname2 = findViewById(R.id.name2);
        emailid2 = findViewById(R.id.email2);
        number2 = findViewById(R.id.mobileNo2);
        password2 = findViewById(R.id.password2);
        conf_password2 = findViewById(R.id.confirm_password2);

        uname.setAlpha(v);
        emailid.setAlpha(v);
        number.setAlpha(v);
        password.setAlpha(v);
        conf_password.setAlpha(v);

        uname.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(200).start();
        emailid.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        number.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();
        conf_password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();


        uname2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    emailid.setError(null);
                    number.setError(null);
                    password.setError(null);
                    conf_password.setError(null);
                }
            }
        });

        emailid2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    uname.setError(null);
                    number.setError(null);
                    password.setError(null);
                    conf_password.setError(null);
                }
            }
        });

        number2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    emailid.setError(null);
                    uname.setError(null);
                    password.setError(null);
                    conf_password.setError(null);
                }
            }
        });

        password2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    emailid.setError(null);
                    number.setError(null);
                    uname.setError(null);
                    conf_password.setError(null);
                }
            }
        });

        conf_password2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    emailid.setError(null);
                    number.setError(null);
                    password.setError(null);
                    uname.setError(null);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailid.clearFocus();
                number.clearFocus();
                password.clearFocus();
                conf_password.clearFocus();
                uname.clearFocus();

                final String fullname = uname.getEditText().getText().toString();
                final String email = emailid.getEditText().getText().toString();
                final String mob_no= number.getEditText().getText().toString();
                final String pwd= password.getEditText().getText().toString();
                final String conf_pwd = conf_password.getEditText().getText().toString();

                if(fullname.equals("")){
                    uname.requestFocus();
                    uname.setError("Enter fullname");

                    emailid.setError(null);
                    number.setError(null);
                    password.setError(null);
                    conf_password.setError(null);
                }
                else if(email.equals("")){
                    uname.setError(null);
                    number.setError(null);
                    password.setError(null);
                    conf_password.setError(null);

                    emailid.requestFocus();
                    emailid.setError("Enter email");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    uname.clearFocus();
                    number.clearFocus();
                    password.clearFocus();
                    conf_password.clearFocus();

                    emailid.setError("Please enter a valid email address");
                    emailid.requestFocus();
                }
                else if(mob_no.equals("")){
                    uname.setError(null);
                    emailid.setError(null);
                    password.setError(null);
                    conf_password.setError(null);

                    number.requestFocus();
                    number.setError("Enter 10 digit mobile number");
                }
                else if(mob_no.length()<1 || mob_no.length()<10 || mob_no.length()>11){
                    uname.setError(null);
                    emailid.setError(null);
                    password.setError(null);
                    conf_password.setError(null);

                    number.requestFocus();
                    number.setError("Enter 10 digit mobile number");
                }
                else if(pwd.equals("")){
                    uname.setError(null);
                    number.setError(null);
                    emailid.setError(null);
                    conf_password.setError(null);

                    password.requestFocus();
                    password.setError("Enter password");
                }
                else if (!PASSWORD_PATTERN.matcher(pwd).matches()) {
                    uname.setError(null);
                    number.setError(null);
                    emailid.setError(null);
                    conf_password.setError(null);

                    password.setError("Password is too weak");
                    password.requestFocus();
                }
                else if(!(pwd.equals(conf_pwd)) ){
                    uname.setError(null);
                    number.setError(null);
                    password.setError(null);
                    emailid.setError(null);

                    conf_password.setError("Passwords do not match");
                    conf_password.requestFocus();
                }
                else{

                    Databasehandler db=new Databasehandler(SignUp.this);

                    Log.d("Insert..", "Inserting stduent data");
                    db.addStudent(new Info(fullname,email,pwd,mob_no));

                    Toast.makeText(SignUp.this,"Registered Successfully!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Welcome.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}