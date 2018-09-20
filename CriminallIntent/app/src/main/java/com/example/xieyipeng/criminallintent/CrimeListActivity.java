package com.example.xieyipeng.criminallintent;

import android.support.v4.app.Fragment;

import com.example.xieyipeng.criminallintent.fragment.CrimeListFragment;
import com.example.xieyipeng.criminallintent.tools.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
