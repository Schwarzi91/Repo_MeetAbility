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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private ListView lvDetails;

    public static final ResultActivity newInstance(String message)
    {
        ResultActivity f = new ResultActivity();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.activity_history, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.tvActivityType);
        messageTextView.setText(message);

        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#ffd700"), PorterDuff.Mode.SRC_ATOP);

        //lvDetails = (ListView) v.findViewById(R.id.lvDetails);
        List<String> listDetails = new ArrayList<String>();

        listDetails.add("CineStar Westpark");
        listDetails.add("19:30 - 00:30 Uhr");
        listDetails.add("Nur Frauen");
        listDetails.add("2/5 Teilnehmer");
        listDetails.add("Ich hätte lust auf einen gemütlichen Kinoabend blblablalbalkja lajlbökjaöd ladj flajböalj aöjbaldfjsd fjasdöf asdföjasdöfj fjaösdfj aödjsf öajflöadsj fasdjfidsflasdjf löasd fjasödifj siödfljl< h");

        ListAdapter listAdapter = new ArrayAdapter(this.getActivity(), R.layout.custom_listview_layout, listDetails);

        // lvDetails.setAdapter(listAdapter);

        return v;
    }
}

