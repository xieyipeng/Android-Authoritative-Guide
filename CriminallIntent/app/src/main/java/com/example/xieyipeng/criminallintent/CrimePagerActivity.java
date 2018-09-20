package com.example.xieyipeng.criminallintent;

import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xieyipeng.criminallintent.fragment.CrimeFragment;
import com.example.xieyipeng.criminallintent.tools.Crime;
import com.example.xieyipeng.criminallintent.tools.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    public static final String EXTRA_CRIME_ID="com.xieyipeng.android.CriminalIntent.crime_id";

    private ViewPager viewPager;
    private List<Crime> crimeList;
    private FragmentManager fragmentManager;
    private UUID crimeID;

    public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent intent=new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        initViews();
        initViewPager();
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime=crimeList.get(position);
                return CrimeFragment.newInstance(crime.getUuid());
            }

            @Override
            public int getCount() {
                return crimeList.size();
            }
        });
        for (int i = 0; i < crimeList.size(); i++) {
            if (crimeList.get(i).getUuid().equals(crimeID)){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    private void initViews() {
        viewPager=findViewById(R.id.crime_view_pager);
        crimeList= CrimeLab.get(this).getCrimes();
        fragmentManager=getSupportFragmentManager();
        crimeID=(UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
    }
}
