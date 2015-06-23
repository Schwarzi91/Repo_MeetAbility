package com.example.philipp.meetability.Aktivitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.philipp.meetability.R;

public class CurrentActivity extends Fragment
{
    private ListView lvDetails;
    private Button btTeilnehmen;

    private TextView tvActivityType;
    private TextView tvGender;
    private TextView tvLocation;
    private TextView tvParticipants;
    private TextView tvFromDate;
    private TextView tvToDate;
    private TextView tvFromTime;
    private TextView tvToTime;
    private TextView tvDescription;

    public static final CurrentActivity newInstance(String activityName, int gender, String location, int participants,
                                                   String startTime, String endTime, String description, int position)
    {
        CurrentActivity f = new CurrentActivity();
        Bundle bdl = new Bundle(1);
        bdl.putString("activityName", activityName);
        bdl.putInt("gender", gender);
        bdl.putString("location", location);
        bdl.putInt("teilnehmer", participants);
        bdl.putString("startTime", startTime);
        bdl.putString("endTime", endTime);
        bdl.putString("description", description);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_current_activities, container, false);

        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        tvActivityType = (TextView) v.findViewById(R.id.tvActivityType);
        tvGender = (TextView) v.findViewById(R.id.tvGender);
        tvLocation = (TextView) v.findViewById(R.id.tvLocation);
        tvParticipants = (TextView) v.findViewById(R.id.tvParticipants);
        tvFromDate = (TextView) v.findViewById(R.id.tvFromDate);
        tvToDate = (TextView) v.findViewById(R.id.tvToDate);
        tvFromTime = (TextView) v.findViewById(R.id.tvFromTime);
        tvToTime = (TextView) v.findViewById(R.id.tvToTime);


        tvActivityType.setText(getArguments().getString("activityName"));
        tvLocation.setText(getArguments().getString("location"));
        tvFromDate.setText(getArguments().getString("startTime"));
        tvToDate.setText(getArguments().getString("endTime"));
        tvParticipants.setText(getArguments().getInt("teilnehmer")+"");
        tvDescription.setText(getArguments().getString("description"));

        if(getArguments().getInt("gender") == 0)
            tvGender.setText("egal");
        else if(getArguments().getInt("gender") == 1)
            tvGender.setText("männlich");
        else if(getArguments().getInt("gender") == 2)
            tvGender.setText("weiblich");

        return v;
    }
}

