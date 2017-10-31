package com.blogspot.ramannada.movieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    String email, password;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText etEmail = rootView.findViewById(R.id.et_email);
        final EditText etPassword = rootView.findViewById(R.id.et_password);
        final Button btnLogin = rootView.findViewById(R.id.btn_login);
        final TextView tvRegister = rootView.findViewById(R.id.tv_register);

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("email can't be blank");
                    etEmail.requestFocus();
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                        etEmail.setError("please check your email address");
                        etEmail.requestFocus();
                    } else {
                        if (!etPassword.getText().toString().isEmpty()) {
                            if (User.processUserLogin(etEmail.getText().toString(),
                                    etPassword.getText().toString())) {

                                User user = User.selectUserByEmail(etEmail.getText().toString());

                                SharedData.getSharedData().saveUserStorage(user);
                                SharedData.getSharedData().saveLoginStatus(true);

                                MainActivity.hideKeyboard(getActivity());

                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.content, new MainFragment())
                                        .commit();


                                Toast.makeText(getActivity(), "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
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
                MainActivity.hideKeyboard(getActivity());


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }


}
