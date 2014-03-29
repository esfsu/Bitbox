package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Scanner;

import android.app.Activity;
import android.content.Context;

// Creates view and sets content view for the MainScreenActivity

public class MyMusicView extends CustomView {

  // creates buttons to be used in the Main Screen activity
  private OptionButtonsFactory mOptionButtonsFactory;
  // song list displayed in the main screen.
  private SongListView mMainSongListView;
  private Scanner mScanner;

  public MyMusicView(Activity currentActivity, Context currentContext) {
    setCurrentActivity(currentActivity);
    setCurrentContext(currentContext);
    currentActivity.setContentView(R.layout.activity_main_screen);
    mScanner = new Scanner();

    mOptionButtonsFactory = new OptionButtonsFactory(getCurrentActivity(), getCurrentContext());
    setMainSongListView(new SongListView(getCurrentActivity(), getCurrentContext(),
        mScanner.scanDevice()));
  }

  public SongListView getMainSongListView() {
    return mMainSongListView;
  }

  public void setMainSongListView(SongListView mainSongListView) {
    mMainSongListView = mainSongListView;
  }

}
