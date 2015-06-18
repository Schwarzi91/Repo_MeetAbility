package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.philipp.meetability.Database.DatabaseHelper;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.Database.User;
import com.example.philipp.meetability.R;


public class RegisterActivity extends Activity implements View.OnClickListener
{
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPassword2;
    private Button btRegistration;
    private String email;
    private String pw1;
    private String pw2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        btRegistration = (Button) findViewById(R.id.btRegister);

        btRegistration.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        email = etEmail.getText().toString();
        pw1 = etPassword.getText().toString();
        pw2 = etPassword2.getText().toString();

        if(etEmail.getText().toString().contains("@"))
        {
            if (Storage.getStorageInstance().getUserByEmail(email) == null && pw1.equals(pw2))
            {
                Storage.getStorageInstance().saveUser(new User(email, pw1, "", "", 0, ""));
                LoginActivity.usercheckItem = Storage.getStorageInstance().getUserByEmail(email);
                Intent intent = new Intent(this, ProfilActivity.class);
                this.startActivity(intent);
                this.finish();
            }
            else
            {
                //etEmail.setText(null); usability -> man sollte nicht immer wieder seine e-mail eingeben müssen
                etPassword.setText(null);
                etPassword2.setText(null);
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Falsches Passwort", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this, "Sie müssen eine E-Mail-Adresse angeben", Toast.LENGTH_LONG).show();
    }
}
