package com.example.xieyipeng.nerdlauncher.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xieyipeng.nerdlauncher.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by xieyipeng on 2018/9/24.
 */

public class NerdLauncherFragment extends Fragment {

    private RecyclerView appRecycleView;

    public static NerdLauncherFragment newInstance() {
        return new NerdLauncherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        appRecycleView = view.findViewById(R.id.app_recycle_view);
        appRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return view;
    }

    //TODO: 查找手机上所有可启动的主activity
    private void setupAdapter() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager manager = getActivity().getPackageManager();
        List<ResolveInfo> activitys = manager.queryIntentActivities(intent, 0);
        Collections.sort(activitys, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo resolveInfo, ResolveInfo t1) {
                PackageManager packageManager = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(resolveInfo.loadLabel(packageManager).toString(), t1.loadLabel(packageManager).toString());
            }
        });
        Log.e(TAG, "setupAdapter: Found " + activitys.size() + " activitys");
        appRecycleView.setAdapter(new ActivityAdapter(activitys));

    }

    private class ActivityHolder extends RecyclerView.ViewHolder {

        private ResolveInfo resolveInfo;
        private TextView nameTextView;

        public ActivityHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView;
        }

        public void bindActivity(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
            PackageManager packageManager = getActivity().getPackageManager();
            String appName = this.resolveInfo.loadLabel(packageManager).toString();
            nameTextView.setText(appName);
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {

        private final List<ResolveInfo> resolveInfoList;

        private ActivityAdapter(List<ResolveInfo> resolveInfoList) {
            this.resolveInfoList = resolveInfoList;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ActivityHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            final ResolveInfo resolveInfo = resolveInfoList.get(position);
            holder.bindActivity(resolveInfo);
            holder.nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    Intent intent = new Intent(Intent.ACTION_MAIN)
                            .setClassName(activityInfo.applicationInfo.packageName, activityInfo.name)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return resolveInfoList.size();
        }
    }
}
