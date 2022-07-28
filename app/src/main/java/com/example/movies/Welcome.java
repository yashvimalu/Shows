package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.Data.Databasehandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Welcome extends AppCompatActivity {

    Button logIn, sign_up, fp;
    TextInputLayout uname, password;
    TextInputEditText uname2, password2;
    TextView error;
    String username, pwd, user_name, pass_word, table_name = "apple";
    ProgressDialog TempDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Databasehandler db = new Databasehandler(this);

        uname = findViewById(R.id.uname);
        uname2 = findViewById(R.id.uname2);
        password = findViewById(R.id.pwd);
        password2 = findViewById(R.id.pwd2);
        logIn = findViewById(R.id.login);
        sign_up = findViewById(R.id.signup);
        error = findViewById(R.id.error);
        fp = findViewById(R.id.fp);

        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = uname2.getEditableText().toString();
                System.out.println("Welcome"+username);
                if (username.equals("")) {
                    Toast.makeText(Welcome.this, "Enter username", Toast.LENGTH_LONG).show();
                    uname.requestFocus();
                } else {
                    if (db.userExists(username)) {
                        Intent intent = new Intent(Welcome.this, ForgetPassword.class);
                        intent.putExtra("user_name", username);
                        startActivity(intent);
                    } else {
                        //TempDialog.dismiss();
                        Toast.makeText(Welcome.this, "User Does not Exist ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Welcome.this, SignUp.class);
                        startActivity(intent);
                    }
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = uname2.getText().toString();
                pwd = password2.getText().toString();

                TempDialog = new ProgressDialog(Welcome.this);
                TempDialog.setMessage("Please Wait");
                TempDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                TempDialog.setCancelable(false);
                TempDialog.show();

                if (db.searchStudent(username,pwd)) {
                    TempDialog.dismiss();
                    Toast.makeText(Welcome.this, "Login Successful: " + username, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Welcome.this, Dashboard.class);
                    intent.putExtra("user_name", username);
                    startActivity(intent);
                    finish();
                } else {
                    TempDialog.dismiss();
                    Toast.makeText(Welcome.this, "User Does not Exist ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Welcome.this, SignUp.class);
                    startActivity(intent);
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, SignUp.class);
                startActivity(intent);
            }
        });
        db.close();
    }
}