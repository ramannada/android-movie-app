package com.blogspot.ramannada.movieapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DatabaseHandler db = new DatabaseHandler(this);

        final EditText etUsername = findViewById(R.id.et_username);
        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);
        RadioButton rMale = findViewById(R.id.radio_gender_male);
        RadioButton rFemale = findViewById(R.id.radio_gender_female);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLogin = findViewById(R.id.tv_login);

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                        etEmail.setError("check your email address");
                    }
                }
            }
        });

        rMale.setSelected(true);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("password can't be empty");
                    etPassword.requestFocus();
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    etEmail.setError("check your email address");
                    etEmail.requestFocus();
                }

                if (etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("email can't be empty");
                    etEmail.requestFocus();
                }

                if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("username can't be empty");
                    etUsername.requestFocus();
                }

               if (!etUsername.getText().toString().isEmpty() &&
                       !etPassword.getText().toString().isEmpty() &&
                       !etEmail.getText().toString().isEmpty()) {

                   User user = new User(
                           etUsername.getText().toString(),
                           etEmail.getText().toString(),
                           etPassword.getText().toString(),
                           onRadioGenderClicked(view)
                   );

                   if (db.getUserByEmail(user.getEmail()) == null) {
                       db.addUser(user);
                       SharedData.getSharedData().saveLoginStatus(true);


                       Toast.makeText(RegisterActivity.this, "Welcome " +
                               db.getUserByEmail(user.getEmail()).getUsername(),
                               Toast.LENGTH_SHORT).show();

                       Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(i);
                       finish();
                   } else {
                       Toast.makeText(RegisterActivity.this, "Email has been used. Please use another", Toast.LENGTH_LONG).show();
                   }


               }
            }


        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
    }

    private String onRadioGenderClicked (View v) {
        String gender = "Male";

        switch (v.getId()) {
            case R.id.radio_gender_male:
                if (((RadioButton) v).isChecked())
                gender = "Male";
            break;
            case R.id.radio_gender_female:
                if (((RadioButton) v).isChecked())
                gender = "Female";
            break;
        }

        return gender;
    }
}
