package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
          usercheckItem=Storage.getStorageInstance().getUserByEmail(etEmail.getText().toString());
            if(usercheckItem.getEmail().equals(etEmail.getText().toString())){

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("E-Mail", etEmail.getText().toString());
            startActivity(intent);
        }}
        else if(v == tvNotRegistered)
        {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
