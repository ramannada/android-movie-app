package com.blogspot.ramannada.movieapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        final TextView tvRegister = findViewById(R.id.tv_register);

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

//        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (!b) {
//                    if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
//                        etEmail.setError("please check your email address");
//                    }
//                }
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (etEmail.getText().toString().isEmpty()) {
                etEmail.setError("email can't be blank");
                etEmail.requestFocus();
            } else {
                if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    etEmail.setError("please check your email address");
                    etEmail.requestFocus();

                } else {

                    if (!etPassword.getText().toString().isEmpty()) {

                        if (User.processUserLogin(etEmail.getText().toString(),
                                etPassword.getText().toString())) {

                            User user = User.selectUserByEmail(etEmail.getText().toString());

                            SharedData.getSharedData().saveUserStorage(user);
                            SharedData.getSharedData().saveLoginStatus(true);

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            Toast.makeText(LoginActivity.this, "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        etPassword.setError("password can't be blank");
                    }
                }
            }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
