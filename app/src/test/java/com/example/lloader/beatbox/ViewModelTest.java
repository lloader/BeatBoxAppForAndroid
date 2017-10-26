package com.example.lloader.beatbox;

import org.hamcrest.core.Is;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;

import static org.junit.Assert.*;

/**
 * Created by Alexander Garkavenko
 */
public class ViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private ViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = Mockito.mock(BeatBox.class);
        mSound = new Sound("path");
        mSubject = new ViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        MatcherAssert.assertThat(mSound.getName(), Is.is(mSubject.getTitle()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClick() {
        mSubject.onButtonClicked();
        Mockito.verify(mBeatBox).play(mSound);
    }

}