package com.example.philipp.meetability.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.philipp.meetability.Aktivitys.HistoryActivity;
import com.example.philipp.meetability.Database.Aktivity;
import com.example.philipp.meetability.Database.Storage;
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
        List<Aktivity> listAktivity = Storage.getStorageInstance().listAktivitiesForHistorysById();

        for (int x = 0; x < listAktivity.size(); x++)
        {
            fragmentList.add(HistoryActivity.newInstance(listAktivity.get(x).getAktivityName(), listAktivity.get(x).getSex(), listAktivity.get(x).getMaxParticipants(),
                    listAktivity.get(x).getStartDate(), listAktivity.get(x).getEndDate()));
        }

        return fragmentList;
    }
}
