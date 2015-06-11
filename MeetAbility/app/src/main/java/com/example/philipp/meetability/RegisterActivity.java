package com.example.philipp.meetability;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity implements View.OnClickListener
{
    EditText etEmail;
    EditText etPassword;
    Button btRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btRegistration = (Button) findViewById(R.id.btRegister);
    }

    @Override
    public void onClick(View v) {

    }
}
