package com.teambitbox.bitbox.view;

import java.util.ArrayList;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.SongStub;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

// This class contains the ListView object where the songs will be displayed.

public class SongListView extends CustomView{
  
	private ArrayList<SongStub> songList; // song list 
  private ListView songListWidget; // ListView widget that displays the songs.
  private SongListAdapter songListAdapter; // test object for SongListAdapter

	public SongListView(Activity currentActivity, Context currentContext, ArrayList<SongStub> songList){
	  setSongArrayList(songList);
  	setCurrentContext(currentContext);
	  setCurrentActivity(currentActivity);
	  songListWidget = (ListView) currentActivity.findViewById(R.id.listView); // initializes ListView
	  songListAdapter = new SongListAdapter(this); // initialize adapter
	  
	  songListAdapter.setNotifyOnChange(true);
	  songListWidget.setAdapter(songListAdapter); // sets adapter for ListView
    songListWidget.setTextFilterEnabled(true); // used for filtering text in the ListView
   
    songListWidget.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
	    
      // TODO: Selection of List Item Event  
	    	 songListAdapter.setSelectedPosition(position); // sets the position of the object for the last selected object
	    	 Log.d("SongListView", "adapter positon" + songListAdapter.getSelectedPosition()); // this is used for debugging purposes
	    }        
    });
    
  }
	
  // returns the Song ArrayList
	public ArrayList<SongStub> getSongArrayList(){
		return songList; // to be replaced with "return ArrayList<Song>;"
  }

	// sets the Song ArrayList for this object
	private void setSongArrayList(ArrayList<SongStub> sl/*ArrayList<Song> sl*/){
		this.songList = sl; // to be replaced with "this.songList = sl;"
	}
	
	// gets individual Song
	public SongStub getSong(int position){
		return this.songList.get(position); // to be replaced with "this.songList[position];"
	}
	
	// returns the ListView widget 
	public ListView getSongListWidget() {
			return songListWidget;
  }
  
  //	sets the ListView widget for this class
  public void setSongListWidget(ListView songListWidget) {
			this.songListWidget = songListWidget;
  }
	
  public SongListAdapter getSongListAdapter() {
		return songListAdapter;
	}

	public void setAdapterTest(SongListAdapter songListAdapter) {
		this.songListAdapter = songListAdapter;
	}
}
