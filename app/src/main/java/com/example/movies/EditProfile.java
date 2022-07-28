package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditProfile extends AppCompatActivity {

    String username;
    String table_name = "apple";
    TextInputEditText name2, email2, mobile2, pwd2, confpwd2;
    TextInputLayout name,email,mobile,pwd,confpwd;
    String _NAME,_EMAIL,_MOBILE,_PWD;
    ProgressDialog TempDialog;
    AppCompatButton update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name2 = findViewById(R.id.name2);
        email2 = findViewById(R.id.email2);
        mobile2 = findViewById(R.id.mobileNo2);
        pwd2 = findViewById(R.id.password2);
        confpwd2 = findViewById(R.id.confirm_password2);
        update = findViewById(R.id.update);

        name = findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobileNo);
        pwd=findViewById(R.id.password);
        confpwd=findViewById(R.id.confirm_password);

    }
}