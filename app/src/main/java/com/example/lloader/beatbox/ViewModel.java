package com.example.lloader.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Alexander Garkavenko
 */

public class ViewModel extends BaseObservable {
    private Sound mSound;
    private final BeatBox mBeatBox;


    public ViewModel(final BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }
}
