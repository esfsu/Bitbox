package com.teambitbox.bitbox.view;

import com.teambitbox.bitbox.R;

import android.app.Activity;
import android.content.Context;

public class MainScreenView {
	
	private Context mainContext;
	private Activity mainActivity;
	private OptionButtonsFactory optionButtonsFactory;
	private SongListView songList;
	
	public MainScreenView(Activity currentActivity, Context currentContext)
	{
		setActivity(currentActivity);
		setContext(currentContext);
		currentActivity.setContentView(R.layout.activity_main_screen);
		optionButtonsFactory = new OptionButtonsFactory(getActivity(), getContext());
		songList = new SongListView(getActivity(), getContext());
	}
  
	private void setContext(Context c) {
		mainContext = c;	
	}

	private Context getContext() {
		return mainContext;
	}
	
	private void setActivity(Activity a) {
		mainActivity = a;	
	}

	private Activity getActivity() {
		return mainActivity;
	}
}
