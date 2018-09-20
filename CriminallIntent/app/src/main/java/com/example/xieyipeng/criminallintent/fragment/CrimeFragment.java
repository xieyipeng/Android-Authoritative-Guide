package com.example.xieyipeng.criminallintent.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.xieyipeng.criminallintent.R;
import com.example.xieyipeng.criminallintent.tools.Crime;
import com.example.xieyipeng.criminallintent.tools.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xieyipeng on 2018/9/18.
 */

public class CrimeFragment extends Fragment {

    public static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "dialogDate";
    private static final int REQUEST_DATE = 0;

    private Crime crime;
    private EditText titleField;
    private Button dataButton;
    private CheckBox solvedCheckBox;
    private SimpleDateFormat simpleDateFormat;

    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            updateDate();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 直接Extra
//        UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(MainActivity.EXTRA_CRIME_ID);
//        crime = CrimeLab.get(getActivity()).getCrime(crimeID);
        //TODO: fragment argument
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        crime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        initViews(view);
        initClicks();
        return view;
    }

    private void initClicks() {
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
//                DatePickerFragment dialog=new DatePickerFragment();
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crime.setSolved(b);
            }
        });
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initViews(View view) {
        titleField = view.findViewById(R.id.crime_title);
        titleField.setText(crime.getTitle());

        dataButton = view.findViewById(R.id.crime_date);
        updateDate();

        solvedCheckBox = view.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(crime.isSolved());
    }

    private void updateDate() {
        dataButton.setText(simpleDateFormat.format(crime.getDate()));
    }
}
