package com.example.muaaz.kitchen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText mEmailET;
    TextView mNoAccountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent getEmail = getIntent();
        String userEmail = getEmail.getStringExtra("userEmail");

        mEmailET = (EditText) findViewById(R.id.login_email_et);
        mNoAccountTv = (TextView) findViewById(R.id.login_no_account_tv);

        mEmailET.setText(userEmail);

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("2", 2);
        editor.commit();*/
        //String sharedTest = String.valueOf(sharedPref.getInt("2", 3));
        //mEmailET.setText(sharedTest);

        mNoAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
