package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.philipp.meetability.R;

public class ReportActivity extends Activity implements View.OnClickListener {

    private Spinner spinnerReport;
    private String reportType;
    private Button btReport;
    private EditText etBugDescription;
    private String strReportType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        reportType = getIntent().getExtras().getString("report");
        spinnerReport = (Spinner) findViewById(R.id.spReportReason);
        etBugDescription = (EditText) findViewById(R.id.etBugDescription);

        if (reportType.equals("userreport")) {
            ArrayAdapter<CharSequence> activityReasonUserreportAdapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons_userreport, R.layout.spinner_style);
            activityReasonUserreportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReport.setAdapter(activityReasonUserreportAdapter);
            strReportType = "Userreport: ";
        } else if (reportType.equals("bugreport")) {
            ArrayAdapter<CharSequence> activityReasonBugreportAdapter = ArrayAdapter.createFromResource(this,
                    R.array.reasons_bugreport, R.layout.spinner_style);
            activityReasonBugreportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReport.setAdapter(activityReasonBugreportAdapter);
            strReportType = "Bugreport: ";
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
        sendEmail();
    }

    private void sendEmail() {
        String[] recipients = {"admin@meetability.de"};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // prompts email clients only
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, strReportType + spinnerReport.getSelectedItem().toString());
        email.putExtra(Intent.EXTRA_TEXT, etBugDescription.getText().toString());

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email,"E-Mail Client wählen" ));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }

    }
}
