/* 
 * SongListAdapter Class
 * 
 * Extends ArrayAdapter<SongStub> Class. This adapter is used to inflate the rows of the ListView
 * and make visual changes to the selected songs in the list.
 * 
 * Eric Fernandez
 * 27/02/2014
 */

package com.teambitbox.bitbox.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Song;

public class SongListDefaultAdapter extends ArrayAdapter<Song> {

  private SongListView mCurrentSongListWidget; // song widget that will be used for
  private ArrayList <Integer> mSelectedSongsPositions = new ArrayList<Integer>(); // all the positions of the songs selected.
  private SharedPreferences mSharedPrefs; // used to check settings
  private int lastSelectedSongPosition = -1; // initial value for no item selected.
  
  // Adapter Constructor
  public SongListDefaultAdapter(SongListView songListView) {
    super(songListView.getCurrentActivity(), R.layout.row_layout, songListView.getSongArrayList()); // uses parent Constructor
    setCurrentSongList(songListView); // sets the current Song list to be used in this adapter
  }
	    
  @Override
  public View getView(int position, View convertView,ViewGroup parent){
  	View row = convertView; // view to be returned
    Song song = getItem(position); // gets the Song object from ArrayList in the SongClass depending on its position
   
    // inflates the view for the row in the ListView
    if (row == null) {                          
      LayoutInflater inflater = (LayoutInflater) getCurrentSongList().getCurrentContext().getSystemService( 
      	Context.LAYOUT_INFLATER_SERVICE);
	    row = inflater.inflate(R.layout.row_layout, null);
    }
    LinearLayout rowLayout = (LinearLayout)row.findViewById(R.id.rowLinearLayout); // initialized entire row layout
    	    
    TextView songTitleView = (TextView)row.findViewById(R.id.songNameLabel); // initializes TextView for the song name
    TextView artistNameView = (TextView)row.findViewById(R.id.artistLabel); // initializes TextView for the song name 
    	   
    // uses Song from the ArrayList in the SongListView class to set the name and artist of each song of the ListView
    songTitleView.setText(song.getSongName());
    artistNameView.setText(song.getArtist());
    if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == false) {
      if (getSelectedPositions().contains(position)) {
 	    rowLayout.setBackgroundColor(Color.DKGRAY);
 	    songTitleView.setTextColor(Color.WHITE);
 	    artistNameView.setTextColor(Color.WHITE);
 	  }
 	  else {
 	    rowLayout.setBackgroundColor(Color.WHITE);
 	    songTitleView.setTextColor(Color.DKGRAY);
 	    artistNameView.setTextColor(Color.DKGRAY);
 	  }
	}
	else if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == true) {
	  if (getLastSelectedSongPosition() == position){
	    rowLayout.setBackgroundColor(Color.DKGRAY);
	    songTitleView.setTextColor(Color.WHITE);
 	    artistNameView.setTextColor(Color.WHITE);
	  }
	  else {
	    rowLayout.setBackgroundColor(Color.WHITE);
	    songTitleView.setTextColor(Color.DKGRAY);
 	    artistNameView.setTextColor(Color.DKGRAY);
	  }
	}
    
    notifyDataSetChanged();
    return(row);
  } // end getView
 
  // sets the SongListView widget to be used for this adapter
  private void setCurrentSongList(SongListView sl){
    mCurrentSongListWidget = sl;	
  }

  // returns the SongListView widget used in this adapter
  private SongListView getCurrentSongList(){
	return mCurrentSongListWidget;
  }
	
	// sets the selected position number to the number of the item selected in the list
	public void setSelectedPositions(Integer pos){
		if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == false) {
		  if (!getSelectedPositions().contains(pos)){
		    mSelectedSongsPositions.add(pos);
		  }
		  else{
			mSelectedSongsPositions.remove(pos);
		  }
		}
		else if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == true) {
		  if (pos != getLastSelectedSongPosition()){
			setLastSelectedSongPosition(pos);
		  }
		  else {
		    setLastSelectedSongPosition(-1);
		  }
		}
		// inform the view of this change
		notifyDataSetChanged();
	}

	// returns the selected item position
	public ArrayList<Integer> getSelectedPositions(){
		return mSelectedSongsPositions;
	}

	private int getLastSelectedSongPosition() {
		return lastSelectedSongPosition;
	}

	private void setLastSelectedSongPosition(int selectedPosition) {
		lastSelectedSongPosition = selectedPosition;
	}

	
}
