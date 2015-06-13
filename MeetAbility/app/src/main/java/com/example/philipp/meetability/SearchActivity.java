package com.example.philipp.meetability;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SearchActivity extends Activity implements View.OnClickListener
{

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Datum
        etFromDate = (EditText) findViewById(R.id.etFromDate);
        etFromDate.setInputType(InputType.TYPE_NULL);
        etToDate = (EditText) findViewById(R.id.etToDate);
        etToDate.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);
        setDateField();
        //Datum ende

        //Uhrzeit
        etFromTime = (EditText) findViewById(R.id.etFromTime);
        etFromTime.setInputType(InputType.TYPE_NULL);
        etToTime = (EditText) findViewById(R.id.etToTime);
        etToTime.setInputType(InputType.TYPE_NULL);
        etFromTime.setOnClickListener(this);
        etToTime.setOnClickListener(this);
        setTimeField();
        //Uhrzeit ende
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
                newTime.set(hourOfDay, minute);
                etFromTime.setText(hourOfDay + ":" + minute);
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);

        toTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                Calendar newTime = Calendar.getInstance();
                newTime.set(hourOfDay, minute);
                etToTime.setText(hourOfDay + ":" + minute);
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);
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
    }
}
