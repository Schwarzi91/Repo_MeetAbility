package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.Database.User;
import com.example.philipp.meetability.R;


public class LoginActivity extends Activity implements View.OnClickListener
{
    private Button btLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvNotRegistered;
    public static User usercheckItem;
    private String userpw;
    private String usermail;


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
    }

    @Override
    public void onClick(View v)
    {

        if(v == btLogin)
        {
            //Initialisieren von variablen
            usermail = etEmail.getText().toString();
            userpw = etPassword.getText().toString();

            //Initializieren vom User Dao
            usercheckItem = Storage.getStorageInstance().getUserByEmail(usermail);

            //Abfrage nach Passwort
            if(usercheckItem != null && usercheckItem.getEmail().equals(usermail) && usercheckItem.getPassword().equals(userpw) )
            {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("E-Mail", etEmail.getText().toString());
                this.startActivity(intent);
                this.finish();
            }

        else
        {
            //etEmail.setText(null); usability
            etPassword.setText(null);
            etEmail.requestFocus();
            Toast.makeText(getApplicationContext(), "E-Mail oder Passwort falsch", Toast.LENGTH_LONG).show();
        }

        }
        else if(v == tvNotRegistered)
        {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
