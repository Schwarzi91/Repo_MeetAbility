package com.example.philipp.meetability.Aktivitys;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
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
import android.widget.Toast;

import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends Fragment implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private ListView lvDetails;
    private RatingBar ratingBar;
    private int intRatingValue;
    private Button btnSubmit;
    private View view;

    public static final HistoryActivity newInstance(String message)
    {
        HistoryActivity f = new HistoryActivity();
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

        addListenerOnRatingBar();

        //lvDetails = (ListView) v.findViewById(R.id.lvDetails);
        List<String> listDetails = new ArrayList<String>();

        listDetails.add("CineStar Westpark");
        listDetails.add("19:30 - 00:30 Uhr");
        listDetails.add("Nur Frauen");
        listDetails.add("2/5 Teilnehmer");
        listDetails.add("Ich hätte lust auf einen gemütlichen Kinoabend blblablalbalkja lajlbökjaöd ladj flajböalj aöjbaldfjsd fjasdöf asdföjasdöfj fjaösdfj aödjsf öajflöadsj fasdjfidsflasdjf löasd fjasödifj siödfljl< h");

        ListAdapter listAdapter = new ArrayAdapter(this.getActivity(), R.layout.custom_listview_layout, listDetails);



        // lvDetails.setAdapter(listAdapter);
         btnSubmit = (Button) view.findViewById(R.id.btCreate);
        btnSubmit.setOnClickListener(this);
        return v;
    }

    public void addListenerOnRatingBar(){

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                intRatingValue = ratingBar.getNumStars();

            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}

