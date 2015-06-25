package com.example.philipp.meetability.Aktivitys;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.media.tv.TvContract;
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

import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.Database.History;
import com.example.philipp.meetability.Database.Participant;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import org.xml.sax.helpers.LocatorImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends Fragment implements View.OnClickListener{
    private ListView lvDetails;
    private RatingBar ratingBar;
    private int userId;
    private Button btRate;
    private View view;
    private List<Participant> participantList;
    private List<Aktivity> aktivityList;
    private SimpleDateFormat format;
    private static Date date;
    private static Date atmDate;
    private TextView tvActivityType;
    private TextView tvGender;
    private TextView tvParticipants;
    private TextView tvFromDate;
    private TextView tvToDate;
    private TextView tvLocation;
    private TextView tvFromTime;
    private TextView tvToTime;
    private TextView tvDescription;
    private int intRatingValue;

    public static final HistoryActivity newInstance(String activityName, int gender, String location,
                                                    int participants, String startDate, String endDate,
                                                    String description, int intGetRating, int position)
    {
        HistoryActivity f = new HistoryActivity();
        Bundle bdl = new Bundle(1);
        bdl.putString("activityName", activityName);
        bdl.putInt("gender", gender);
        bdl.putInt("participants", participants);
        bdl.putString("startDate", startDate);
        bdl.putString("endDate", endDate);
        bdl.putString("description", description);
        bdl.putString("location", location);
        bdl.putInt("rating", intGetRating);
        bdl.putInt("position", position);
        f.setArguments(bdl);
        return f;
    }

    public static void ActivityToHistory ()
    {
        //Hier werden die "vergangenen" Aktivities zu Historys gemacht!
        List<Aktivity> listAktivities = Storage.getStorageInstance().getAktivityList();
        List<History> listHistory = Storage.getStorageInstance().getHistoryList();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = sdf.format(c.getTime());
        atmDate = Storage.getStorageInstance().dateFormatter(formattedDate);

        for (int x = 0; x < listAktivities.size(); x++)
        {
            date = Storage.getStorageInstance().dateFormatter(listAktivities.get(x).getEndDate());
            if (listAktivities.get(x).getChangeToHistory()==false)
            {
                if (date.getTime() <= atmDate.getTime()) {
                    Storage.getStorageInstance().saveHistory(new History(listAktivities.get(x), 0, listAktivities.get(x).getDescription()));

                    listAktivities.get(x).setChangeToHistory(true);
                    Storage.getStorageInstance().saveActivity(listAktivities.get(x));
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_history, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.tvActivityType);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#ffd700"), PorterDuff.Mode.SRC_ATOP);

        tvActivityType = (TextView) v.findViewById(R.id.tvActivityType);
        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        tvGender = (TextView) v.findViewById(R.id.tvGender);
        tvFromDate = (TextView) v.findViewById(R.id.tvFromDate);
        tvToDate = (TextView) v.findViewById(R.id.tvToDate);
        tvParticipants = (TextView) v.findViewById(R.id.tvParticipants);
        tvLocation = (TextView) v.findViewById(R.id.tvLocation);

        addListenerOnRatingBar();


        List<Aktivity> listAktivity = Storage.getStorageInstance().listAktivitiesForHistorysById();



        tvActivityType.setText(getArguments().getString("activityName"));
        if(getArguments().getInt("gender") == 0)
            tvGender.setText("egal");
        else if(getArguments().getInt("gender") == 1)
            tvGender.setText("männlich");
        else if(getArguments().getInt("gender") == 2)
            tvGender.setText("weiblich");
        tvParticipants.setText(getArguments().getInt("participants")+"/"+"10");
        tvFromDate.setText(getArguments().getString("startDate"));
        tvToDate.setText(getArguments().getString("endDate"));
        tvDescription.setText(getArguments().getString("description"));
        tvLocation.setText(getArguments().getString("location"));



        ListAdapter listAdapter = new ArrayAdapter(this.getActivity(), R.layout.custom_listview_layout);

        ratingBar.setRating(getArguments().getInt("rating"));


        // lvDetails.setAdapter(listAdapter);
       // btRate = (Button) v.findViewById(R.id.btRate);
        //btRate.setOnClickListener(this);
        return v;
        //userId = LoginActivity.usercheckItem.getUser_id();
    }

    //for (x=0; x < userID; i++){




    public void addListenerOnRatingBar(){


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                //intRatingValue = ratingBar.getNumStars();
                Participant partiRating = Storage.getStorageInstance().getParticipantByLoggedUser().get(getArguments().getInt("position"));
                partiRating.setRating((int) ratingBar.getRating());
                Storage.getStorageInstance().saveParticipant(partiRating);
                }
        });

    }


    @Override
    public void onClick(View v) {

        History historyCheckItem = Storage.getStorageInstance().getHistoryList().get(0);
        historyCheckItem.setRating(intRatingValue);
        Storage.getStorageInstance().saveHistory(historyCheckItem);
    }
}

