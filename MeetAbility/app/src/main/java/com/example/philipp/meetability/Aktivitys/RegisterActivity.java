package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public static boolean deaktivate;


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

        if(deaktivate==true){
            etEmail.setHint("Password");
            etPassword.setHint("Neues Password");
            //etEmail.setVisibility(View.GONE);
            btRegistration.setText("Password ändern");
        }


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
                Storage.getStorageInstance().saveUser(new User(email, pw1, "", 0,"00-00-0000", ""));
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
        else if(btRegistration.getText().equals("Password ändern")){
            if(pw1.equals(pw2) && email.equals(LoginActivity.usercheckItem.getPassword())){
                LoginActivity.usercheckItem.setPassword(pw1);
                Storage.getStorageInstance().saveUser(LoginActivity.usercheckItem);
                Toast.makeText(getApplicationContext(), "Passwort erfolgreich geändert", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                this.finish();

            }
            else{
                etPassword.setText(null);
                etEmail.setText(null);
                etPassword2.setText(null);
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Falsches Passwort", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this, "Sie müssen eine E-Mail-Adresse angeben", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }
        if (id == R.id.action_userreport) {
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("report", "userreport");
            startActivity(intent);
        }
        if (id == R.id.action_bugreport) {
            Intent intent = new Intent(this, ReportActivity.class);
            intent.putExtra("report", "bugreport");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
