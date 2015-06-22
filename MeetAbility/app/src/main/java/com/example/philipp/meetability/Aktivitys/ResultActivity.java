package com.example.philipp.meetability.Aktivitys;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends Fragment{
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
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

    public static final ResultActivity newInstance(String activityName, int gender, /*String location,*/ int participants,
                                                   String startTime, String endTime, String description)
    {
        ResultActivity f = new ResultActivity();
        Bundle bdl = new Bundle(1);
        bdl.putString("activityName", activityName);
        bdl.putInt("gender", gender);
        //bdl.putString("location", location);
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

        //String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.activity_searchresult, container, false);
        //TextView messageTextView = (TextView)v.findViewById(R.id.tvActivityType);
        //messageTextView.setText(message);

        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        tvActivityType = (TextView) v.findViewById(R.id.tvActivityType);
        tvGender = (TextView) v.findViewById(R.id.tvGender);
        tvLocation = (TextView) v.findViewById(R.id.tvLocation);
        tvParticipants = (TextView) v.findViewById(R.id.tvParticipants);
        tvFromDate = (TextView) v.findViewById(R.id.tvFromDate);
        tvToDate = (TextView) v.findViewById(R.id.tvToDate);
        tvFromTime = (TextView) v.findViewById(R.id.tvFromTime);
        tvToTime = (TextView) v.findViewById(R.id.tvToTime);

        //lvDetails = (ListView) v.findViewById(R.id.lvDetails);
        /*List<String> listDetails = new ArrayList<String>();

        listDetails.add("CineStar Westpark");
        listDetails.add("19:30 - 00:30 Uhr");
        listDetails.add("Nur Frauen");
        listDetails.add("2/5 Teilnehmer");
        listDetails.add("Ich hätte lust auf einen gemütlichen Kinoabend blblablalbalkja lajlbökjaöd ladj flajböalj aöjbaldfjsd fjasdöf asdföjasdöfj fjaösdfj aödjsf öajflöadsj fasdjfidsflasdjf löasd fjasödifj siödfljl< h");

        ListAdapter listAdapter = new ArrayAdapter(this.getActivity(), R.layout.custom_listview_layout, listDetails);
*/
        // lvDetails.setAdapter(listAdapter);

        tvActivityType.setText(getArguments().getString("activityName"));
        //tvLocation.setText(getArguments().getString("location"));
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

