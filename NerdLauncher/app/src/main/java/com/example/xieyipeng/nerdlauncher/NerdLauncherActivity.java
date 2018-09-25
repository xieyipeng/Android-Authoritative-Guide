package com.example.xieyipeng.nerdlauncher;

import android.support.v4.app.Fragment;

import com.example.xieyipeng.nerdlauncher.fragment.NerdLauncherFragment;
import com.example.xieyipeng.nerdlauncher.tools.SingleFragmentActivity;

public class NerdLauncherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NerdLauncherFragment.newInstance();
    }


}
