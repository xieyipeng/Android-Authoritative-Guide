package com.example.xieyipeng.boxdrawingview;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xieyipeng.boxdrawingview.fragment.DragAndDrawFragment;
import com.example.xieyipeng.boxdrawingview.tools.SingleFragmentActivity;

public class DragAndDrawActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DragAndDrawFragment.newInstance();
    }
}
