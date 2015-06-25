package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ProfilActivity extends Activity implements View.OnClickListener
{
    //Geschlecht
    private Spinner spGender;
    //Datum
    private EditText etAge;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePicker;
    //textfelder
    private TextView tvEmail;
    private EditText etUserName;
    private EditText etDescription;
    private EditText etEmail;
    //profilbild
    private ImageView  ivUser;
    //Buttons
    private ImageButton ibChangePw;
    private ImageButton ibSetting;
    private Button btDeaktivate;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //Geschlecht
        spGender = (Spinner)findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> activityTypeAdapter = ArrayAdapter.createFromResource(this, R.array.genderProfil, R.layout.spinner_style);
        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(activityTypeAdapter);
        spGender.setEnabled(false);
        spGender.setSelection(LoginActivity.usercheckItem.getSex());
        //Geburtsdatum
        etAge = (EditText) findViewById(R.id.etAge);
        etAge.setInputType(InputType.TYPE_NULL);
        etAge.setEnabled(false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        //view setzen
        tvEmail = (TextView) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserName.setEnabled(false);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etDescription.setEnabled(false);
        //Buttons
        ibChangePw= (ImageButton) findViewById(R.id.ibChangePw);
        ibSetting =(ImageButton)findViewById(R.id.ibSettings);
        btDeaktivate = (Button)findViewById(R.id.btDeaktivate);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.setInputType(InputType.TYPE_NULL

        );

        //listener
        btDeaktivate.setOnClickListener(this);
        ibSetting.setOnClickListener(this);
        ibChangePw.setOnClickListener(this);
        etAge.setOnClickListener(this);

        //Disable Editing E-Mail
        tvEmail.setEnabled(false);
        //Setting Spinner
        setDateField();

        //Schauen ob User neu angelegt wurde
        if(LoginActivity.usercheckItem.getUsername().equals(""))
        {
            btDeaktivate.setText("Änderungen Speichern");
            tvEmail.setText(LoginActivity.usercheckItem.getEmail());
            setEditable(true);
        }
        else
        {
            etUserName.setText(LoginActivity.usercheckItem.getUsername());
            etDescription.setText(LoginActivity.usercheckItem.getDescription());
            tvEmail.setText(LoginActivity.usercheckItem.getEmail());
            etAge.setText(LoginActivity.usercheckItem.getAge());
        }
    }


    //Felder Aktivieren/Deaktivieren
    private void setEditable(boolean i)
    {
        if (i == false){
            etDescription.setEnabled(false);
            etAge.setEnabled(false);
            etUserName.setEnabled(false);
            spGender.setEnabled(false);
        }
        else {
            etDescription.setEnabled(true);
            etAge.setEnabled(true);
            etUserName.setEnabled(true);
            spGender.setEnabled(true);
        }
    }

    protected void setDateField()
    {
        Calendar newCalendar = Calendar.getInstance();
        int year;
        datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etAge.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View v)
    {
        if(v == etAge){datePicker.show();}

        else if(v == ibChangePw) {
            RegisterActivity.deaktivate=true;
            Intent intent = new Intent(this,RegisterActivity.class);
            this.startActivity(intent);
            this.finish();
        }
        else if(v == ibSetting && btDeaktivate.getText().equals("Account Deaktivieren")){
            setEditable(true);
            etUserName.requestFocus();
            btDeaktivate.setText("Änderungen Speichern");
        }
        else if(v==btDeaktivate && btDeaktivate.getText().equals("Änderungen Speichern")){

            setEditable(false);
            LoginActivity.usercheckItem.setUsername(etUserName.getText().toString());
            LoginActivity.usercheckItem.setSex(spGender.getSelectedItemPosition());
            LoginActivity.usercheckItem.setDescription(etDescription.getText().toString());
            LoginActivity.usercheckItem.setAge(etAge.getText().toString());
            Storage.getStorageInstance().saveUser(LoginActivity.usercheckItem);


            btDeaktivate.setText("Account Deaktivieren");
            tvEmail.requestFocus();
            Toast.makeText(getApplicationContext(), "Änderungen gespeichert", Toast.LENGTH_LONG).show();
            }
        else if (btDeaktivate.getText().equals("Account Deaktivieren")){
            Toast.makeText(getApplicationContext(), "Account wurde deaktiviert", Toast.LENGTH_LONG).show();
            LoginActivity.usercheckItem=null;
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            LoginActivity.usercheckItem = null;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
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