package com.example.philipp.meetability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import Database.Aktivity;
import Database.Storage;

/**
 * Created by Edik on 14.06.2015.
 */
public class InitializeAktivity extends Activity implements View.OnClickListener {


    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        //Initializing Database
       // Storage.getStorageInstance().createTestDataIfNeccessary();

        //Timeout and Aktivity Switch
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(InitializeAktivity.this, LoginActivity.class);
                InitializeAktivity.this.startActivity(intent);
                InitializeAktivity.this.finish();
            }
        }, 3000);



    }
}


