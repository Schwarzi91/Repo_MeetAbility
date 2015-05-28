package com.example.philipp.meetability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;


public class MainActivity extends Activity implements View.OnClickListener
{
    private Button btCreateActivity;
    private Button btSearchActivity;
    private Button btShowProfile;
    private Button btShowHistory;
    private TextView tvUsername;
    private String strUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreateActivity = (Button) findViewById(R.id.btCreateActivity);
        btSearchActivity = (Button) findViewById(R.id.btSearchActivity);
        btShowProfile = (Button) findViewById(R.id.btShowProfile);
        btShowHistory = (Button) findViewById(R.id.btShowHistory);
        tvUsername = (TextView) findViewById(R.id.tvUsername);

        btCreateActivity.setOnClickListener(this);
        btSearchActivity.setOnClickListener(this);
        btShowProfile.setOnClickListener(this);
        btShowHistory.setOnClickListener(this);

        Intent intent = getIntent();
        strUsername = intent.getStringExtra("E-Mail");
        tvUsername.setText(strUsername);
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
            Toast.makeText(this, "Historie anzeigen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

}
