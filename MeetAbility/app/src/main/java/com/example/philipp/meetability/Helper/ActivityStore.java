package com.example.philipp.meetability.Helper;

import android.app.ListActivity;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.philipp.meetability.Database.Aktivity;

import java.util.List;

/**
 * Created by Philipp on 22.06.15.
 */
public class ActivityStore //speichert die gefundenen Activities zwischen
{
    private static List<Aktivity> listActivity;

    public ActivityStore(List<Aktivity> listActivity)
    {
        this.listActivity = listActivity;
    }

    public void setListActivity(List<Aktivity> listActivity) {
        this.listActivity = listActivity;
    }

    public static List<Aktivity> getListActivity() {
        return listActivity;
    }
}
