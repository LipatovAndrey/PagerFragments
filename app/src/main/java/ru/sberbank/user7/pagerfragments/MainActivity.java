package ru.sberbank.user7.pagerfragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.mainPger);
        PackageManager pm = getPackageManager();
        AppsPagerAdapter adapter = new AppsPagerAdapter(getSupportFragmentManager(), pm.getInstalledApplications(0));
        viewPager.setAdapter(adapter);
    }
}
