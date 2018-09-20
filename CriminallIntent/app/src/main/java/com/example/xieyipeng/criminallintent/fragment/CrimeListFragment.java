package com.example.xieyipeng.criminallintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xieyipeng.criminallintent.CrimePagerActivity;
import com.example.xieyipeng.criminallintent.MainActivity;
import com.example.xieyipeng.criminallintent.R;
import com.example.xieyipeng.criminallintent.tools.Crime;
import com.example.xieyipeng.criminallintent.tools.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xieyipeng on 2018/9/18.
 */

public class CrimeListFragment extends Fragment {

    private static final String SAVE_SUBTITLE_VISIBLE="subtitle";
    private RecyclerView crimeRecycleView;
    private CrimeAdapter adapter;
    private CrimeLab crimeLab;
    private List<Crime> crimes;
    private boolean subtitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        initRecycleView(view);
        if (savedInstanceState!=null){
            subtitle=savedInstanceState.getBoolean(SAVE_SUBTITLE_VISIBLE);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVE_SUBTITLE_VISIBLE,subtitle);
    }

    private void initRecycleView(View view) {
        crimeRecycleView = view.findViewById(R.id.crime_recycle_view);
        crimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        crimeLab = CrimeLab.get(getActivity());
        crimes = crimeLab.getCrimes();
        if (adapter == null) {
            adapter = new CrimeAdapter(crimes);
            crimeRecycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
        MenuItem item=menu.findItem(R.id.show_subtitle);
        if (subtitle){
            item.setTitle("Hide Subtitle");
        }else {
            item.setTitle("Show Subtitle");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).add(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getUuid());
                startActivity(intent);
                return true;

            case R.id.show_subtitle:
                subtitle=!subtitle;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        String sub=crimeCount+" crimes";
        if (!subtitle){
            sub=null;
        }
        appCompatActivity.getSupportActionBar().setSubtitle(sub);
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimeList;

        public CrimeAdapter(List<Crime> crimeList) {
            this.crimeList = crimeList;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            final Crime crime = crimeList.get(position);
            holder.bind(crime);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: MainActivity
//                    Intent intent=MainActivity.newIntent(getActivity(),crime.getUuid());
//                    startActivity(intent);
                    //TODO: CrimePagerActivity
                    Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getUuid());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return crimeList.size();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private Crime crime;
        private TextView title;
        private TextView date;
        private ImageView solved;
        private LinearLayout linearLayout;
        private SimpleDateFormat simpleDateFormat;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.crime_list_item, parent, false));
            title = itemView.findViewById(R.id.crime_list_title);
            date = itemView.findViewById(R.id.crime_list_date);
            solved = itemView.findViewById(R.id.crime_list_solved);
            linearLayout = itemView.findViewById(R.id.crime_list_linearLayout);
        }

        public void bind(Crime crime) {
            this.crime = crime;
            title.setText(this.crime.getTitle());
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date.setText(simpleDateFormat.format(this.crime.getDate()));
            solved.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }
    }
}
