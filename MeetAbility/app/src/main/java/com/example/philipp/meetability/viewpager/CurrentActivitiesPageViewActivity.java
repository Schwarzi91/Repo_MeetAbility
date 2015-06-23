package com.example.philipp.meetability.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.philipp.meetability.Aktivitys.ResultActivity;
import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 06.06.15.
 */
public class CurrentActivitiesPageViewActivity extends android.support.v4.app.FragmentActivity
{
    MyPageAdapter pageAdapter;
    private List<Aktivity> activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);


        activityList = new ArrayList<>();

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setAdapter(pageAdapter);


    }

    public List<Fragment> getFragments()
    {
        List<Fragment> fragmentList = new ArrayList<>();

        for(int i = 0; i < activityList.size(); i++)
        {
            fragmentList.add(ResultActivity.newInstance(activityList.get(i).getAktivityName(), activityList.get(i).getSex(),
                    activityList.get(i).getMaxParticipants(), activityList.get(i).getStartDate(), activityList.get(i).getEndDate(),
                    activityList.get(i).getDescription()));
        }

        return fragmentList;
    }
}