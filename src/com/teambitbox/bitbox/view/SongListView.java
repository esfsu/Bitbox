package com.teambitbox.bitbox.view;

import java.util.ArrayList;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Song;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

// This class contains the ListView object where the songs will be displayed.

public class SongListView extends CustomView {

	// song list
	private ArrayList<Song> mSongList;
	// ListView widget that displays the songs.
	private ListView mSongListWidget;
	// test object for SongListAdapter
	private SongListAdapter mSongListAdapter;

	public SongListView(Activity currentActivity, Context currentContext,
			ArrayList<Song> songList) {
		setSongArrayList(songList);
		setCurrentContext(currentContext);
		setCurrentActivity(currentActivity);
		// initializes ListView
		mSongListWidget = (ListView) currentActivity.findViewById(R.id.listView);
		// initialize adapter
		mSongListAdapter = new SongListAdapter(this);

		mSongListAdapter.setNotifyOnChange(true);
		// sets adapter for ListView
		mSongListWidget.setAdapter(mSongListAdapter);
		// used for filtering text in the ListView
		mSongListWidget.setTextFilterEnabled(true);

		mSongListWidget.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long arg3) {

						// TODO: Selection of List Item Event
						// sets the position of the object for the last selected object
					  mSongListAdapter.setSelectedPosition(position);
						// this is used for debugging purposes
						Log.d("SongListView", "adapter positon"
								+ mSongListAdapter.getSelectedPosition());
					}
				});

	}

	// returns the Song ArrayList
	public ArrayList<Song> getSongArrayList() {
		// to be replaced with "return ArrayList<Song>;"
		return mSongList;
	}

	// sets the Song ArrayList for this object
	private void setSongArrayList(ArrayList<Song> sl) {
		mSongList = sl;
	}

	// gets individual Song
	public Song getSong(int position) {
		return mSongList.get(position);
	}

	// returns the ListView widget
	public ListView getSongListWidget() {
		return mSongListWidget;
	}

	// sets the ListView widget for this class
	public void setSongListWidget(ListView songListWidget) {
		mSongListWidget = songListWidget;
	}

	public SongListAdapter getSongListAdapter() {
		return mSongListAdapter;
	}

	public void setAdapterTest(SongListAdapter songListAdapter) {
		mSongListAdapter = songListAdapter;
	}
}
