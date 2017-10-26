package com.example.lloader.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Garkavenko
 */

public class BeatBox {
    public static final String TAG = "BeatBox";
    public static final String SOUNDS_FOLDER = "sample_sounds";
    public static final int MAX_SOUNDS = 5;

    private List<Sound> mSounds;
    private final AssetManager mAssetManager;
    private SoundPool mSoundPool;

    public BeatBox(final Context context) {
        mAssetManager = context.getAssets();
        mSounds = new ArrayList<>();
        if(Build.VERSION.SDK_INT < 21) {
            mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        } else {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_SOUNDS)
                    .build();
        }
        loadSounds();
    }

    public AssetManager getAssetManager() {
        return mAssetManager;
    }

    public void loadSounds() {
        try {
            final String[] sounds = mAssetManager.list(SOUNDS_FOLDER);
            for(final String string : sounds) {
                final String assetPath = SOUNDS_FOLDER + "/" + string;
                final Sound sound = new Sound(assetPath);
                Log.d(TAG, sound.getName());
                load(sound);
                mSounds.add(sound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(final Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = getAssetManager().openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor, 1);
        sound.setSoundId(soundId);
    }

    public void play(final Sound sound) {
        Integer soundId = sound.getSoundId();
        if(soundId != null) {
            mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
