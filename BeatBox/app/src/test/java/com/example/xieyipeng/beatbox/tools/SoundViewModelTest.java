package com.example.xieyipeng.beatbox.tools;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by xieyipeng on 2018/9/24.
 */
public class SoundViewModelTest {
    private BeatBox beatBox;
    private Sound sound;
    private SoundViewModel soundViewModel;
    @Before
    public void setUp() throws Exception {
        beatBox=mock(BeatBox.class);
        sound=new Sound("assetPath");
        soundViewModel=new SoundViewModel(beatBox);
        soundViewModel.setSound(sound);
    }

    @Test
    public void exposeSoundNameAsTitle(){
//        assertThat(soundViewModel.getTitle(),is(sound.getName()));
        soundViewModel.onButtonClicked();
        verify(beatBox).play(sound);
    }

}