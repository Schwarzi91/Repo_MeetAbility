package com.example.philipp.meetability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import viewpager.PageViewActivity;


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
            Toast.makeText(this, "Activity anlegen", Toast.LENGTH_SHORT).show();

        else if(v == btSearchActivity)
            Toast.makeText(this, "Activity suchen", Toast.LENGTH_SHORT).show();

        else if(v == btShowProfile)
            Toast.makeText(this, "Profil anzeigen", Toast.LENGTH_SHORT).show();

        else if(v == btShowHistory)
        {
            Intent viewPagerIntent = new Intent(this, PageViewActivity.class);
            startActivity(viewPagerIntent);
        }
    }

}
