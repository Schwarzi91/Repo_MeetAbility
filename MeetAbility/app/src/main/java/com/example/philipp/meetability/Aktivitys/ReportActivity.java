package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.philipp.meetability.R;

public class ReportActivity extends Activity implements View.OnClickListener{

    private Spinner spinnerReport;
    private String reportType;
    private Button btReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        reportType = getIntent().getExtras().getString("report");
        spinnerReport = (Spinner) findViewById(R.id.spActivityType);

        if(reportType.equals("userreport")) {
            ArrayAdapter<CharSequence> activityReasonUserreportAdapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons_userreport, R.layout.spinner_style);
            activityReasonUserreportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReport.setAdapter(activityReasonUserreportAdapter);
        }
        else if(reportType.equals("bugreport")) {
            ArrayAdapter<CharSequence> activityReasonBugreportAdapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons_bugreport, R.layout.spinner_style);
            activityReasonBugreportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReport.setAdapter(activityReasonBugreportAdapter);
        }
        btReport = (Button) findViewById(R.id.btReport);
        btReport.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Report gemeldet", Toast.LENGTH_SHORT).show();
    }
}
