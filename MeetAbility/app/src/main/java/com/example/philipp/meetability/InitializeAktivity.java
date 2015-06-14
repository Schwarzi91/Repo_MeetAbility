package com.example.philipp.meetability;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Database.Aktivity;
import Database.Storage;

/**
 * Created by Edik on 14.06.2015.
 */
public class InitializeAktivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View v) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);
        Storage.initialize(this);


    }
}


