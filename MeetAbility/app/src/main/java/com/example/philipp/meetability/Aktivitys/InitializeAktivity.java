package com.example.philipp.meetability.Aktivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

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

        //Initializing com.example.philipp.meetability.Database

        Storage.initialize(this);
        Storage.getStorageInstance().createTestDataIfNeccessary();

        //Timeout and Aktivity Switch
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(InitializeAktivity.this, LoginActivity.class);
                InitializeAktivity.this.startActivity(intent);
                //InitializeAktivity.this.finish();
            }
        }, 5000);



    }
}


