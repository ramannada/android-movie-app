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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

/*
**Initial view object
*/
        final EditText etUsername = findViewById(R.id.et_username);
        final EditText etEmail =  findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);
        RadioButton rMale = findViewById(R.id.radio_gender_male);
        RadioButton rFemale = findViewById(R.id.radio_gender_female);
        final RadioGroup rgGender = findViewById(R.id.radio_group_gender);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLogin = findViewById(R.id.tv_login);

//        Email validation check on unfocus email textview
//        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b) {
//                    if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
//                        etEmail.setError("check your email address");
//                    }
//                }
//            }
//        });

        rMale.setSelected(true);


//      button register listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//      check is username edittext is empty
            if (!etUsername.getText().toString().isEmpty()) {
                //      check is email edittext is empty
                if (!etEmail.getText().toString().isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {

                        if (!etPassword.getText().toString().isEmpty()) {

                            if (rgGender.getCheckedRadioButtonId() != -1) {

//                            instansiate new user from edittext
                                User user = new User(
                                        etUsername.getText().toString(),
                                        etEmail.getText().toString(),
                                        etPassword.getText().toString(),
                                        onRadioGenderClicked(view)
                                );

//                            check is email already used
                                if (User.selectUserByEmail(user.getEmail()) == null) {

//                                save new user to db with active android
                                    user.save();

//                                save user to sharedpreferences
                                    SharedData.getSharedData().saveUserStorage(user);

//                                save login status to shared preferences
                                    SharedData.getSharedData().saveLoginStatus(true);

//                                create toast
                                    Toast.makeText(RegisterActivity.this, "Welcome " +
                                                    user.getUsername(),
                                            Toast.LENGTH_SHORT).show();

//                                initiate new intent and close the old one
                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Email has been used. Please use another", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Please choose a gender", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            etPassword.setError("password can't be empty");
                            etPassword.requestFocus();
                        }

                    } else {
                        etEmail.setError("check your email address");
                        etEmail.requestFocus();
                    }

                } else {
                    etEmail.setError("email can't be empty");
                    etEmail.requestFocus();
                }

            } else {
                etUsername.setError("username can't be empty");
                etUsername.requestFocus();
            }

            }


        });

//        button login listener
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

//    radiobutton listener
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
