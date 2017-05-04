package ru.sberbank.user7.pagerfragments;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import static ru.sberbank.user7.pagerfragments.R.id.tv;

/**
 * Created by user7 on 04.05.2017.
 */

public class AppTitleSubFragment extends Fragment {

    private TextView packageViewTextView;
    private ImageView imageView;

    private  static final String ARG_APPLICATION = "application";

    private ApplicationInfo applicationInfo;
    public static AppTitleSubFragment newInstance(ApplicationInfo app){
        AppTitleSubFragment fragment = new AppTitleSubFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_APPLICATION, app);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.appitemsubfragment, container, false);

        packageViewTextView = (TextView) root.findViewById(R.id.appTitle);
        imageView = (ImageView) root.findViewById(R.id.appIcon);
        AppInfoTask appInfoTask = new AppInfoTask(applicationInfo, packageViewTextView, imageView);
        appInfoTask.execute();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private static class AppInfoTask extends AsyncTask<Void, Void, AppInfoTask.ProcessInfo>{
        PackageManager packageManager;
        WeakReference<ImageView> img;
        WeakReference<TextView> text;
        ApplicationInfo applicationInfo;

        public AppInfoTask(ApplicationInfo applicationInfo, TextView tv, ImageView imageView){
            this.applicationInfo = applicationInfo;
             img = new WeakReference<ImageView>(imageView);
            text = new WeakReference<TextView>(tv);
            packageManager = imageView.getContext().getPackageManager();
        }

        @Override
        protected ProcessInfo doInBackground(Void... params) {
            ProcessInfo pi = new ProcessInfo();
            pi.icon = packageManager.getApplicationIcon(applicationInfo);
            pi.title = packageManager.getApplicationLabel(applicationInfo);
            return pi;
        }

        @Override
        protected void onPostExecute(ProcessInfo processInfo) {
            ImageView imageView = img.get();
            TextView textView = text.get();
            if (processInfo!=null&&imageView!=null&&textView!=null){
                imageView.setImageDrawable(processInfo.icon);
                textView.setText(processInfo.title);
            }

        }


        static class ProcessInfo{
            Drawable icon;
            CharSequence title;


        }
    }
}
