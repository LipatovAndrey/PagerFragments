package ru.sberbank.user7.pagerfragments;


import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user7 on 04.05.2017.
 */

public class AppInfoFragment extends Fragment {
    private TextView packageViewTextView;
    private  static final String ARG_APPLICATION = "application";
    private ApplicationInfo applicationInfo;
    public static AppInfoFragment newInstance(ApplicationInfo app){
        AppInfoFragment fragment = new AppInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APPLICATION, app);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);
        AppTitleSubFragment fragment = AppTitleSubFragment.newInstance(applicationInfo);
        getChildFragmentManager().beginTransaction().add(R.id.appinfolayout, fragment).commit();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.appinfofragment, container, false);
        packageViewTextView = (TextView) root.findViewById(R.id.tv);
        packageViewTextView.setText(applicationInfo.packageName);
        return root;
    }
}
