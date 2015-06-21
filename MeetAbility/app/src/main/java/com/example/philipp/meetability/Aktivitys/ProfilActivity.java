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
import android.widget.TextView;
import android.widget.Toast;

import com.example.philipp.meetability.Database.DatabaseHelper;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.Database.User;
import com.example.philipp.meetability.Helper.ProfilHelper;
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
    //profilbild
    private ImageView  ivUser;
    //Buttons
    private Button btChangeUserInfo;
    private Button btChangePW;
    private Button btLogOut;
    private Button btDeaktivate;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //Spinner
        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> activityTypeAdapter = ArrayAdapter.createFromResource(this, R.array.genderProfil, R.layout.spinner_style);
        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(activityTypeAdapter);
        spGender.setEnabled(false);
        spGender.setSelection(LoginActivity.usercheckItem.getSex());
        //Datum
        etAge = (EditText) findViewById(R.id.etAge);
        etAge.setInputType(InputType.TYPE_NULL);
        etAge.setEnabled(false);
        etAge.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);

        //view setzen
        tvEmail = (TextView) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserName.setEnabled(false);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etDescription.setEnabled(false);

        btChangeUserInfo = (Button) findViewById(R.id.btChangeUserInfo);
        btChangePW = (Button) findViewById(R.id.btChangePW);
        btLogOut = (Button)findViewById(R.id.btLogOut);
        btDeaktivate = (Button)findViewById(R.id.btDeaktivate);

        //listener
        btChangeUserInfo.setOnClickListener(this);
        btChangePW.setOnClickListener(this);
        btLogOut.setOnClickListener(this);
        btDeaktivate.setOnClickListener(this);

        tvEmail.setText(LoginActivity.usercheckItem.getEmail());
        setDateField();

        if(LoginActivity.usercheckItem.getUsername().equals(""))
        {
            setEditable(true);
        }
        else
        {
            setUserinformation();
        }
    }

    private void setUserinformation()
    {
        etUserName.setText(LoginActivity.usercheckItem.getUsername());
        etDescription.setText(LoginActivity.usercheckItem.getDescription());
        //spGender.setSelection(LoginActivity.usercheckItem.getSex());
    }
    

    private void setEditable(boolean i)
    {
        /*
        spGender.setFocusable(i);
        etAge.setFocusable(i);
        tvEmail.setFocusable(i);
        etUserName.setFocusable(i);
        etDescription.setFocusable(i);*/
        if (i == false)
        {
            i = true;
            etDescription.setEnabled(false);
            etAge.setEnabled(false);
            etUserName.setEnabled(false);
            spGender.setEnabled(false);
            btChangeUserInfo.setText("Profil bearbeiten");
        }
        else
        {

            etDescription.setEnabled(true);
            etAge.setEnabled(true);
            etUserName.setEnabled(true);
            spGender.setEnabled(true);
            i = false;
            btChangeUserInfo.setText("Speichern");
        }
        /*btChangePW.setFocusable(i);
        btLogOut.setFocusable(i);
        btDeaktivate.setFocusable(i);*/

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
    public void onClick(View view)
    {
        if(view == etAge)
        {
            datePicker.show();
        }
        else if(view == btChangeUserInfo)
        {
            if(etUserName.getText().toString().trim().equals(""))
            {
                Toast.makeText(this, "Bitte geben Sie einen Nutzernamen ein!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (btChangeUserInfo.getText() == "Speichern")
                {
                    setEditable(false);
                    LoginActivity.usercheckItem.setUsername(etUserName.getText().toString());
                    LoginActivity.usercheckItem.setSex(spGender.getSelectedItemPosition());
                    LoginActivity.usercheckItem.setDescription(etDescription.getText().toString());
                    Storage.getStorageInstance().saveUser(LoginActivity.usercheckItem);
                    //etAge.setFocusable(false);
                } else {
                    setEditable(true);
                }
            }
        }
        else if(view == btLogOut)
        {
            DatabaseHelper dbh = new DatabaseHelper(this);
            //dbh.close(LoginActivity.usercheckItem);
        }
    }
}