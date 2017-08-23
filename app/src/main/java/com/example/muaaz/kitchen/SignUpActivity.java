package com.example.muaaz.kitchen;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.muaaz.kitchen.classes.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText mEmailEditText;
    EditText mNameEditText;
    EditText mSurnameEditText;
    EditText mPhoneNoEditText;
    EditText mPasswordEditText;
    Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");


        mEmailEditText = (EditText) findViewById(R.id.signup_email_et);
        mNameEditText = (EditText) findViewById(R.id.signup_name_et);
        mSurnameEditText = (EditText) findViewById(R.id.signup_surname_et);
        mPhoneNoEditText = (EditText) findViewById(R.id.signup_phone_et);
        mPasswordEditText = (EditText) findViewById(R.id.signup_password_et);
        mSignUpButton = (Button) findViewById(R.id.signup_signup_button);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean allchecked = true;

                String email = null;
                String name = null;
                String surname = null;
                String phoneNo = null;
                String password = null;


                if(mEmailEditText.getText().toString().trim().length() > 0 ){
                    email = mEmailEditText.getText().toString();
                }else {
                    allchecked = false;
                }

                if(mNameEditText.getText().toString().trim().length() > 0 ){
                    name = mNameEditText.getText().toString();
                }else {
                    allchecked = false;
                }

                if(mSurnameEditText.getText().toString().trim().length() > 0 ){
                    surname = mSurnameEditText.getText().toString();
                }else {
                    allchecked = false;
                }

                if(mPhoneNoEditText.getText().toString().trim().length() == 10){
                    phoneNo = mPhoneNoEditText.getText().toString();
                }else {
                    allchecked = false;
                }

                if(mPasswordEditText.getText().toString().trim().length() > 0 ){
                    password = mPasswordEditText.getText().toString();
                }else {
                    allchecked = false;
                }
//mk
                if(allchecked){

                    DatabaseReference ref = database.getReference("Users").child(email);

                    User user = new User(email, name, surname, phoneNo, password);
                    ref.setValue(user);

                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("One or more fields are empty or have incorrect inputs");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ODismiss",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }
}


