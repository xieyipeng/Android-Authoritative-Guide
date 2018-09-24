package com.example.xieyipeng.beatbox.tools;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by xieyipeng on 2018/9/24.
 */

public class SoundViewModel extends BaseObservable{
    private Sound sound;
    private BeatBox beatBox;

    public SoundViewModel(BeatBox beatBox) {
        this.beatBox = beatBox;
    }

    @Bindable
    public String getTitle(){
        return sound.getName();
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
        notifyChange();
    }

    public void onButtonClicked() {
        beatBox.play(sound);
    }
}
