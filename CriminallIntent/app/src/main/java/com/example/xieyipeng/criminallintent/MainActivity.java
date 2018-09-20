package com.example.xieyipeng.criminallintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.xieyipeng.criminallintent.fragment.CrimeFragment;
import com.example.xieyipeng.criminallintent.tools.SingleFragmentActivity;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.xieyipeng.android.CriminalIntent.crime_id";

    //TODO: 直接Extra
    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //TODO: fragment argument
        UUID crimeID=(UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }

}
