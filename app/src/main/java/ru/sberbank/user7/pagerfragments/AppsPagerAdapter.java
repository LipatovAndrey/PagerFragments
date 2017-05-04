package ru.sberbank.user7.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by user7 on 04.05.2017.
 */

public class AppsPagerAdapter extends FragmentStatePagerAdapter {

    private List<ApplicationInfo> infoList;

    public AppsPagerAdapter(FragmentManager fm, List<ApplicationInfo> infoList) {
        super(fm);
        this.infoList = infoList;
    }

    @Override
    public Fragment getItem(int position) {
        return AppInfoFragment.newInstance(infoList.get(position));
    }

    @Override
    public int getCount() {
        return infoList.size();
    }
}
