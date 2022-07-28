package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movies.Data.Databasehandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {

    TextInputLayout uname, password;
    TextInputEditText uname2, password2;
    Button update;
    String user_name,pwd;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Intent i = getIntent();
        user_name = i.getStringExtra("user_name");

        uname = findViewById(R.id.p1);
        uname2 = findViewById(R.id.p11);
        password = findViewById((R.id.p2));
        password2 = findViewById((R.id.p22));
        update = findViewById(R.id.update);
        pwd = uname2.getEditableText().toString();

        System.out.println("New pwd is"+pwd);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Databasehandler db = new Databasehandler(ForgetPassword.this);
                    System.out.println("New pwd is"+pwd);
                    if (db.updatePassword(user_name, pwd)) {
                        Toast.makeText(ForgetPassword.this, "Password update Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ForgetPassword.this, Welcome.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(ForgetPassword.this, "Password not updated", Toast.LENGTH_LONG).show();
                    }
                }

        });
    }
}