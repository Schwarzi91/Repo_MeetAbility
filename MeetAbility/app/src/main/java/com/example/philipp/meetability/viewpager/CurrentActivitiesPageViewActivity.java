package com.example.philipp.meetability.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.philipp.meetability.Aktivitys.CurrentActivity;
import com.example.philipp.meetability.Aktivitys.LoginActivity;
import com.example.philipp.meetability.Aktivitys.ResultActivity;
import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.Database.Storage;
import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 06.06.15.
 */
public class CurrentActivitiesPageViewActivity extends android.support.v4.app.FragmentActivity
{
    MyPageAdapter pageAdapter;
    private List<Aktivity> currentActivityList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);


        currentActivityList = new ArrayList<>();

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setAdapter(pageAdapter);


    }

    public List<Fragment> getFragments()
    {
        List<Fragment> fragmentList = new ArrayList<>();
        currentActivityList = Storage.getStorageInstance().getAktivitiesbyUserId(LoginActivity.usercheckItem.getUser_id());

        for(int i = 0; i < currentActivityList.size(); i++)
        {
            fragmentList.add(CurrentActivity.newInstance(currentActivityList.get(i).getAktivityName(), currentActivityList.get(i).getSex(),
                    currentActivityList.get(i).getLocation(), currentActivityList.get(i).getMaxParticipants(),
                    currentActivityList.get(i).getStartDate(), currentActivityList.get(i).getEndDate(), currentActivityList.get(i).getDescription(), i));
        }
        return fragmentList;
    }
}