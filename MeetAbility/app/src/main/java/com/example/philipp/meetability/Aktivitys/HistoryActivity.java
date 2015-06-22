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
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private ListView lvDetails;
    private RatingBar ratingBar;
    private int intRatingValue;
    private int userId;
    private Button btRate;
    private View view;
    private List<Participant> participantList;
    private List<Aktivity> aktivityList;
    private SimpleDateFormat format;
    private Date date;
    private Date atmDate;

    public static final HistoryActivity newInstance(String message)
    {
        HistoryActivity f = new HistoryActivity();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    public void ActivityToHistory ()
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

            if (date.getTime() <= atmDate.getTime()) {
                Storage.getStorageInstance().saveHistory(new History(listAktivities.get(x), 0, listAktivities.get(x).getDescription()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityToHistory();

        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.activity_history, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.tvActivityType);
        messageTextView.setText(message);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
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

        ratingBar.setRating(Storage.getStorageInstance().getHistoryList().get(0).getRating());


        participantList = Storage.getStorageInstance().getParticipantList();
        aktivityList = Storage.getStorageInstance().getAktivityByUserId(LoginActivity.usercheckItem.getUser_id());



        // lvDetails.setAdapter(listAdapter);
        btRate = (Button) v.findViewById(R.id.btRate);
        btRate.setOnClickListener(this);
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
                intRatingValue = ratingBar.getNumStars();


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

