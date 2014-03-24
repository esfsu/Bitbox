package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.ScannerStub;

import android.app.Activity;
import android.content.Context;

// Creates view and sets content view for the MainScreenActivity

public class MyMusicView extends CustomView {
	
	
	private OptionButtonsFactory optionButtonsFactory; // creates buttons to be used in the Main Screen activity
	private SongListView mainSongListView; // song list displayed in the main screen.
	private ScannerStub scanner; // stub used to replace the Scanner object
	
	public MyMusicView(Activity currentActivity, Context currentContext)
	{
		setCurrentActivity(currentActivity);
		setCurrentContext(currentContext);
		currentActivity.setContentView(R.layout.activity_main_screen);
		scanner = new ScannerStub();

		optionButtonsFactory = new OptionButtonsFactory(getCurrentActivity(), getCurrentContext());
		setMainSongListView(new SongListView(getCurrentActivity(), getCurrentContext(), scanner.getMyMusicList()));
	}
	public SongListView getMainSongListView() {
		return mainSongListView;
	}
	public void setMainSongListView(SongListView mainSongListView) {
		this.mainSongListView = mainSongListView;
	}
  
	
}
