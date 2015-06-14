package com.example.philipp.meetability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import Database.Aktivity;


public class LoginActivity extends Activity implements View.OnClickListener
{
    private Button btLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvNotRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        tvNotRegistered = (TextView) findViewById(R.id.tvNotRegistered);
        tvNotRegistered.setOnClickListener(this);

        //tvNotRegistered = (TextView) findViewById(R.id.tvNotRegistered);
        //tvNotRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == btLogin)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("E-Mail", etEmail.getText().toString());
            startActivity(intent);
        }
        else if(v == tvNotRegistered)
        {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
