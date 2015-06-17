package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.widget.Toast;

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
    private EditText etEmail;
    private EditText etUser;
    private EditText etDescription;
    //profilbild
    private ImageView  ivUser;
    //Buttons
    private Button btChangeUserInfo;
    private Button btChangePW;
    private Button btLogOut;
    private Button btDeaktivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //Spinner
        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> activityTypeAdapter = ArrayAdapter.createFromResource(this, R.array.genderProfil, android.R.layout.simple_spinner_item);
        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(activityTypeAdapter);
        //Datum
        etAge = (EditText) findViewById(R.id.etAge);
        etAge.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        etAge.setOnClickListener(this);

        //view setzen
        etEmail=(EditText) findViewById(R.id.etEmail);
        etUser=(EditText) findViewById(R.id.etUser);
        etDescription=(EditText)findViewById(R.id.etDescription);
        btChangeUserInfo= (Button) findViewById(R.id.btChangeUserInfo);
        btChangePW=(Button) findViewById(R.id.btChangePW);
        btLogOut=(Button)findViewById(R.id.btLogOut);
        btDeaktivate=(Button)findViewById(R.id.btDeaktivate);

        //listener
        btChangeUserInfo.setOnClickListener(this);
        btChangePW.setOnClickListener(this);
        btLogOut.setOnClickListener(this);
        btDeaktivate.setOnClickListener(this);

        setDateField();
        setChange(false);
    }

    private void setChange(boolean i) {
        spGender.setFocusable(i);
        etAge.setFocusable(i);
        etEmail.setFocusable(i);
        etUser.setFocusable(i);
        etDescription.setFocusable(i);
        if (i==false) {
            i = true;
            btChangeUserInfo.setText("Profil bearbeiten");
        }
        else {
            i=false;
            btChangeUserInfo.setText("Speichern");
        }
        btChangePW.setFocusable(i);
        btLogOut.setFocusable(i);
        btDeaktivate.setFocusable(i);

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
    public void onClick(View view) {
        if(view==etAge)
            datePicker.show();
        if(view==btChangeUserInfo) {
            if(btChangeUserInfo.getText()=="Speichern"){
                setChange(false);
                etAge.setFocusable(false);}
            else
                setChange(true);



        }
    }
}