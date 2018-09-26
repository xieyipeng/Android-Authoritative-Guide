package com.example.xieyipeng.sunset.fragment;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.example.xieyipeng.sunset.R;

/**
 * Created by xieyipeng on 2018/9/25.
 */

public class SunsetFragment extends Fragment {

    private View sceneView;
    private View sunView;
    private View skyView;

    private int blueSkyColor;
    private int sunsetSkyColor;
    private int nightSkyColor;


    public static SunsetFragment newInstance(){
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sunset,container,false);

        sceneView=view;
        sunView=view.findViewById(R.id.sun);
        skyView=view.findViewById(R.id.sky);

        Resources resources=getResources();
        blueSkyColor=resources.getColor(R.color.blue_sky);
        sunsetSkyColor=resources.getColor(R.color.sunset_sky);
        nightSkyColor=resources.getColor(R.color.night_sky);



        sceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        return view;
    }

    private void startAnimation(){
        float sunYStart=sunView.getTop();
        float sunYEnd=skyView.getHeight();
        ObjectAnimator heightAnimator= ObjectAnimator.ofFloat(sunView,"y",sunYStart,sunYEnd).setDuration(3200);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator sunSkyAnimator=ObjectAnimator.ofInt(skyView,"backgroundColor",blueSkyColor,sunsetSkyColor)
                .setDuration(3000);

        sunSkyAnimator.setEvaluator(new ArgbEvaluator());

        ObjectAnimator nightSkyAnimator=ObjectAnimator.ofInt(skyView,"backgroundColor",sunsetSkyColor,nightSkyColor)
                .setDuration(1500);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animationSet=new AnimatorSet();
        animationSet.play(heightAnimator)
                .with(sunSkyAnimator)
                .before(nightSkyAnimator);
        animationSet.start();

    }

}
