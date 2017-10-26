package com.example.lloader.beatbox;

/**
 * Created by Alexander Garkavenko
 */

public class Sound {
    private String mName;
    private String mAssetPath;
    private Integer mSoundId;

    public Sound(String assetPath) {
        final String[] components = assetPath.split("/");
        mName = components[components.length - 1];
        mAssetPath = assetPath;
        mName.replace(".wav", "");
        mSoundId = null;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
