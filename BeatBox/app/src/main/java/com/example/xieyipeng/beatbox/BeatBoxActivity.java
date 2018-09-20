package com.example.xieyipeng.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xieyipeng.beatbox.fragment.BeatBoxFragment;
import com.example.xieyipeng.beatbox.tools.SingleFragmentActivity;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
