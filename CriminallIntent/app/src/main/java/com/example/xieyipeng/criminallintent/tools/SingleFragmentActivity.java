package com.example.xieyipeng.criminallintent.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.xieyipeng.criminallintent.R;

/**
 * Created by xieyipeng on 2018/9/18.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment mainFragment;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initializeFragment();
    }

    private void initializeFragment() {
        fragmentManager = getSupportFragmentManager();
        mainFragment = fragmentManager.findFragmentById(R.id.main_container);
        if (mainFragment == null) {
            mainFragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, mainFragment)
                    .commit();
        }
    }
}
