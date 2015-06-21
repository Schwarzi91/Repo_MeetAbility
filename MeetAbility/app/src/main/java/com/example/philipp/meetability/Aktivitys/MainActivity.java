package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.philipp.meetability.Database.Report;
import com.example.philipp.meetability.R;

import com.example.philipp.meetability.viewpager.PageViewActivity;




public class MainActivity extends Activity implements View.OnClickListener
{
    private ImageButton btCreateActivity;
    private ImageButton btSearchActivity;
    private ImageButton btShowProfile;
    private ImageButton btShowHistory;
    private TextView tvUsername;
    private String strUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        btCreateActivity = (ImageButton) findViewById(R.id.ibCreateActivity);
        btSearchActivity = (ImageButton) findViewById(R.id.ibSearchActivity);
        btShowProfile = (ImageButton) findViewById(R.id.ibShowProfile);
        btShowHistory = (ImageButton) findViewById(R.id.ibShowHistory);

        btCreateActivity.setOnClickListener(this);
        btSearchActivity.setOnClickListener(this);
        btShowProfile.setOnClickListener(this);
        btShowHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)

    {
        if(v == btCreateActivity)
        {
            Intent intent = new Intent(this, CreateActivity.class);
            startActivity(intent);
        }

        else if(v == btSearchActivity)
        {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        else if(v == btShowProfile)
        {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
        }
        else if(v == btShowHistory)
        {
            Intent viewPagerIntent = new Intent(this, PageViewActivity.class);
            startActivity(viewPagerIntent);
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
