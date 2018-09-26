package com.example.xieyipeng.sunset;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xieyipeng.sunset.fragment.SunsetFragment;
import com.example.xieyipeng.sunset.tools.SingleFragmentActivity;

public class SunsetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SunsetFragment.newInstance();
    }

}
