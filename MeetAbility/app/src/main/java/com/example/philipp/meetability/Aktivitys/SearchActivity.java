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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SearchActivity extends Activity implements View.OnClickListener
{
    private Button btSearch;
    private Spinner spActivityType;
    private Spinner spGender;

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

        btSearch = (Button) findViewById(R.id.btSearch);
        btSearch.setOnClickListener(this);

        spActivityType = (Spinner) findViewById(R.id.spActivityType);
        spGender = (Spinner) findViewById(R.id.spGender);

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
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                etFromTime.setText(hourOfDay + ":" + minute);
            }
        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);

        toTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
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
        else if(view == btSearch)
        {
            if(!etToTime.getText().toString().isEmpty() && !etFromTime.getText().toString().isEmpty() && !etToDate.getText().toString().isEmpty() && !etFromDate.getText().toString().isEmpty())
            {
                Storage.getStorageInstance().getFilteredAktivity(spActivityType.getSelectedItem().toString(), spGender.getSelectedItemPosition(), etFromDate.getText().toString() + " " + etFromTime.getText().toString(), etToDate.getText().toString() + " " + etToTime.getText().toString());
            }
            else
            {
                Toast.makeText(this, "Bitte füllen Sie alle Felder aus!", Toast.LENGTH_LONG).show();
            }
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
