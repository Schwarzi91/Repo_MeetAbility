package com.example.philipp.meetability;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class CreateActivity extends Activity implements View.OnClickListener
{
    private Spinner spActivityTypes;
    private Spinner spGender;
    private EditText etFromDate;
    private EditText etToDate;
    private DatePickerDialog fromDatePicker;
    private DatePickerDialog toDatePicker;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //Spinner
        spActivityTypes = (Spinner) findViewById(R.id.spActivityType);

        ArrayAdapter<CharSequence> activityTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.activityTypes, android.R.layout.simple_spinner_item);

        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spActivityTypes.setAdapter(activityTypeAdapter);

        spGender = (Spinner) findViewById(R.id.spGender);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_expandable_list_item_1);

        spGender.setAdapter(genderAdapter);
        //Spinner ende

        //Datum
        etFromDate = (EditText) findViewById(R.id.etFromDate);
        etFromDate.setInputType(InputType.TYPE_NULL);
        etToDate = (EditText) findViewById(R.id.etToDate);
        etToDate.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);
        setDateTimeField();
        //Datum ende
    }

    private void setDateTimeField()
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

    @Override
    public void onClick(View view)
    {
        if(view == etFromDate) {
            fromDatePicker.show();
        } else if(view == etToDate) {
            toDatePicker.show();
        }
    }
}
