package com.example.xieyipeng.criminallintent.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.xieyipeng.criminallintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by xieyipeng on 2018/9/20.
 */

public class DatePickerFragment extends AppCompatDialogFragment {

    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.xieyipeng.android.CriminalIntent.date";
    private DatePicker datePicker;
    private Date date;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        datePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
        datePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Date of crime")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        Date date=new GregorianCalendar(year,month,day).getTime();
                        sendResoult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }

    private void sendResoult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
