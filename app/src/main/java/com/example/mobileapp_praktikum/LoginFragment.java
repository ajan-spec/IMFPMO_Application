package com.example.mobileapp_praktikum;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private static boolean passwordLength(CharSequence password) {
        return password.length() > 5;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Anmelden");
        Button registerButton = view.findViewById(R.id.login_register_button);
        Button forgotPasswordButton = view.findViewById(R.id.login_forgot_password_button);
        Button loginButton = view.findViewById(R.id.login_login_button);

        final EditText mailfield = view.findViewById(R.id.login_mail_textfield);

        final EditText passwordfield = view.findViewById(R.id.login_password_textfield);

        ((DrawerLocker) getActivity()).setDrawerLocked(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.changeFragment(1);
            }
        });
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.changeFragment(2);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isValidEmail(mailfield.getText().toString()) && passwordLength(passwordfield.getText().toString())) {
                    if(Usermanagement.getInstance().login(mailfield.getText().toString(),passwordfield.getText().toString(),getContext())) {
                        mListener.changeFragment(3);
                    }
                    else {
                        Toast.makeText(getContext(),"E-Mail oder Passwort sind falsch", Toast.LENGTH_LONG).show();
                        mailfield.setText("");
                        passwordfield.setText("");
                    }
                }
                if (!isValidEmail(mailfield.getText().toString())) {
                    mailfield.setError("Geben Sie eine valide E-Mail-Adresse an");
                }
                if (!passwordLength(passwordfield.getText().toString()))
                    passwordfield.setError("Das Passwort muss mindestens 6 Zeichen lang sein");
            }
        });
        mListener = new OnFragmentInteractionListener() {
            @Override
            public void changeFragment(int id) {
                ((MainActivity) Objects.requireNonNull(getActivity())).changeFragment(id);
            }
        };
        // Inflate the layout for this fragment
        return view;
    }
}
