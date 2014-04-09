/*
 * MyMusicView Class
 *
 * This class is used to initialize all the views for the activity 'MyMusic'
 * 
 * 
 * 26/02/2014
 * Eric Fernandez
 */

package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Scanner;

import android.app.Activity;
import android.content.Context;

// Creates view and sets content view for the MainScreenActivity

public class MyMusicView extends CustomView {
	
	
	private OptionButtonsFactory optionButtonsFactory; // creates buttons to be used in the Main Screen activity
	private SongListView mMainSongListView; // song list displayed in the main screen.
	private Scanner mScanner; // stub used to replace the Scanner object
	
	public MyMusicView(Activity currentActivity, Context currentContext)
	{
		setCurrentActivity(currentActivity);
		setCurrentContext(currentContext);
		currentActivity.setContentView(R.layout.my_music_activity_screen);
		mScanner = new Scanner(getCurrentContext());
		setMainSongListView(new SongListView(getCurrentActivity(), getCurrentContext(),
	        mScanner.scanDevice("")));
		optionButtonsFactory = new OptionButtonsFactory(getCurrentActivity(), getCurrentContext());		
	}
	public SongListView getMainSongListView() {
		return mMainSongListView;
	}
	public void setMainSongListView(SongListView mainSongListView) {
		mMainSongListView = mainSongListView;
	}
  
	
}
