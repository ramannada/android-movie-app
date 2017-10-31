package com.blogspot.ramannada.movieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText etUsername = rootView.findViewById(R.id.et_username);
        final EditText etEmail =  rootView.findViewById(R.id.et_email);
        final EditText etPassword = rootView.findViewById(R.id.et_password);
        final RadioGroup rgGender = rootView.findViewById(R.id.radio_group_gender);
        Button btnRegister = rootView.findViewById(R.id.btn_register);
        TextView tvLogin = rootView.findViewById(R.id.tv_login);

//        rMale.setSelected(true);


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

                                        MainActivity.hideKeyboard(getActivity());

                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.content, new MainFragment())
                                                .commit();
//                                create toast
                                        Toast.makeText(getActivity(), "Welcome " +
                                                        user.getUsername(),
                                                Toast.LENGTH_SHORT).show();

//                                initiate new intent and close the old one

                                    } else {
                                        Toast.makeText(getActivity(), "Email has been used. Please use another", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Please choose a gender", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "login", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
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
