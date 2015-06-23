package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.Database.Participant;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.nio.charset.Charset;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class CreateActivity extends Activity implements View.OnClickListener
{
    private Spinner spActivityTypes;
    private Spinner spGender;
    private Spinner spMaxParticipants;

    private String fromDate;
    private String toDate;
    private String unconvertDate;

    private Participant participant;
    //Datum
    private EditText etFromDate;
    private EditText etToDate;
    private DatePickerDialog fromDatePicker;
    private DatePickerDialog toDatePicker;
    private SimpleDateFormat dateFormatter;

    //Uhrzeit
    private EditText etFromTime;
    private EditText etToTime;
    private TimePickerDialog fromTimePicker;
    private TimePickerDialog toTimePicker;
    //Button
    private Button btCreate;
    //editText
    private EditText etLocation;
    private EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Spinner
        spActivityTypes = (Spinner) findViewById(R.id.spActivityType);
        ArrayAdapter<CharSequence> activityTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.activityTypes, R.layout.spinner_style);
        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spActivityTypes.setAdapter(activityTypeAdapter);

        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, R.layout.spinner_style);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);

        spMaxParticipants = (Spinner) findViewById(R.id.spMaxParticipants);
        ArrayAdapter<CharSequence> maxParticipantsAdapter = ArrayAdapter.createFromResource(this,
                R.array.maxParticipants, R.layout.spinner_style);
        maxParticipantsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaxParticipants.setAdapter(maxParticipantsAdapter);
        //Spinner ende

        //Datum
        etFromDate = (EditText) findViewById(R.id.etFromDate);
        etFromDate.setInputType(InputType.TYPE_NULL);
        etFromDate.setFocusable(false);
        etToDate = (EditText) findViewById(R.id.etToDate);
        etToDate.setInputType(InputType.TYPE_NULL);
        etToDate.setFocusable(false);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);
        setDateField();
        //Datum ende

        //Uhrzeit
        etFromTime = (EditText) findViewById(R.id.etFromTime);
        etFromTime.setFocusable(false);
        etFromTime.setInputType(InputType.TYPE_NULL);
        etToTime = (EditText) findViewById(R.id.etToTime);
        etToTime.setFocusable(false);
        etToTime.setInputType(InputType.TYPE_NULL);
        etFromTime.setOnClickListener(this);
        etToTime.setOnClickListener(this);
        setTimeField();
        //Uhrzeit ende

        //Button
        btCreate= (Button) findViewById(R.id.btCreate);
        btCreate.setOnClickListener(this);
        //EditText
        etLocation=(EditText) findViewById(R.id.etLocation);
        etDescription=(EditText) findViewById(R.id.etDescription);


    }

    private void setDateField()
    {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etFromDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etToDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setTimeField()
    {
        Calendar newCalendar = Calendar.getInstance();
        fromTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                etFromTime.setText(hourOfDay + ":" + pad(minute));
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);

        toTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                etToTime.setText(hourOfDay + ":" + pad(minute));
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
    }

    public String pad(int input)
    {

        String str = "";

        if (input > 10) {

            str = Integer.toString(input);
        } else {
            str = "0" + Integer.toString(input);

        }
        return str;
    }

    @Override
    public void onClick(View view)
    {
        if(view == etFromDate)
        {
            fromDatePicker.show();
        }
        else if(view == etToDate)
        {
            toDatePicker.show();
        }
        else if(view == etFromTime)
        {
            fromTimePicker.show();
        }
        else if(view == etToTime)
        {
            toTimePicker.show();
        }
        if(view == btCreate)
        {
            if(etLocation.length()>0){
                if(etDescription.length()>0) {
                    fromDate=etFromDate.getText().toString()+ " "+ etFromTime.getText().toString();
                    toDate=etToDate.getText().toString()+ " "+ etToTime.getText().toString();

                    Toast.makeText(this, "Aktivität angelegt", Toast.LENGTH_SHORT).show();
                    Storage.getStorageInstance().saveActivity(new Aktivity(LoginActivity.usercheckItem, spActivityTypes.getSelectedItem().toString(), spGender.getSelectedItemPosition(),etLocation.getText().toString(), fromDate, toDate, etDescription.getText().toString(), spMaxParticipants.getSelectedItemPosition(), false));
                    //participant= new Participant(LoginActivity.usercheckItem.getUser_id(), Storage.getStorageInstance().getAktivityList().get(Storage.getStorageInstance().getAktivityList().size()-1), true);
                    Storage.getStorageInstance().saveParticipant(participant);

                    etLocation.setText("");
                    etDescription.setText("");
                    etFromDate.setText("");
                    etToDate.setText("");
                    etFromTime.setText("");
                    etToTime.setText("");
                }else
                    Toast.makeText(getApplicationContext(), "Beschreibung ist nicht ausgefüllt", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Location nicht gew‰hlt", Toast.LENGTH_LONG).show();

        }
    }

}
