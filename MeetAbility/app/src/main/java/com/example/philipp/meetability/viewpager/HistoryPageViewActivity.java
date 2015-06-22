package com.example.philipp.meetability.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.philipp.meetability.Aktivitys.HistoryActivity;
import com.example.philipp.meetability.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 06.06.15.
 */
public class HistoryPageViewActivity extends android.support.v4.app.FragmentActivity
{
    MyPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setAdapter(pageAdapter);
    }

    public List<Fragment> getFragments()
    {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(HistoryActivity.newInstance("activityName", 2, 10, "20-06-2015 14:44", "20-06-2015 14:44")); //String activityName, int gender/*, String location*/, int participants, String startDate, String endDate)
        
        return fragmentList;
    }
}
