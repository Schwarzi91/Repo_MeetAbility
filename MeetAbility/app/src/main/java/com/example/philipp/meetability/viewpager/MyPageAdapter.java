package com.example.philipp.meetability.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Philipp on 06.06.15.
 */
public class MyPageAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;

    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {

        return this.fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return this.fragments.size();
    }
}
