package com.example.muaaz.kitchen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.muaaz.kitchen.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText mEmailET;
    EditText mPasswordET;
    Button mLoginButton;
    TextView mNoAccountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        mEmailET = (EditText) findViewById(R.id.login_email_et);
        mPasswordET = (EditText) findViewById(R.id.login_password_et);
        mLoginButton = (Button) findViewById(R.id.login_login_button);
        mNoAccountTv = (TextView) findViewById(R.id.login_no_account_tv);

        Intent getEmail = getIntent();
        String userEmail = getEmail.getStringExtra("userEmail");
        mEmailET.setText(userEmail);

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("2", 2);
        editor.commit();*/
        //String sharedTest = String.valueOf(sharedPref.getInt("2", 3));
        //mEmailET.setText(sharedTest);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String thisEmail = mEmailET.getText().toString();
                final String thisPassword = mPasswordET.getText().toString();

                final String mail = thisEmail.replaceAll("[.]", "%");

                DatabaseReference ref = database.getReference("Users/" + mail);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User attemptedLoginUser = dataSnapshot.getValue(User.class);
                        String attempteduserActualPassword = attemptedLoginUser.password;
                        if(thisPassword.equals(attempteduserActualPassword)){
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        mNoAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
