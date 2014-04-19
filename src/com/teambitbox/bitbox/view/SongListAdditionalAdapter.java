package com.teambitbox.bitbox.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Song;

public class SongListAdditionalAdapter extends ArrayAdapter<Song> {
  private SongListView mCurrentSongListWidget; // song widget that will be used for
  private ArrayList <Integer> mSelectedSongsPositions = new ArrayList<Integer>(); // all the positions of the songs selected.
  private int lastSelectedSongPosition = -1; // initial value for no item selected.
	
	// Adapter Constructor
	public SongListAdditionalAdapter(SongListView songListView) {
	  super(songListView.getCurrentActivity(), R.layout.additional_info_layout, songListView.getSongArrayList()); // uses parent Constructor
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
		    row = inflater.inflate(R.layout.additional_info_layout, null);
	    }
	    LinearLayout rowLayout = (LinearLayout)row.findViewById(R.id.additionalRowLinearLayout); // initialized entire row layout
	    	
	        TextView additionalSongTitleView = (TextView)row.findViewById(R.id.additionalSongNameLabel); // initializes TextView for the song name
	        TextView artistNameView = (TextView)row.findViewById(R.id.additionalArtistLabel); // initializes TextView for the song name 
	        TextView albumNameView = (TextView)row.findViewById(R.id.additionalAlbumLabel);   
	        // uses Song from the ArrayList in the SongListView class to set the name and artist of each song of the ListView
	       
	        additionalSongTitleView.setText(song.getSongName());
	        artistNameView.setText(song.getArtist());
	        albumNameView.setText(song.getAlbum());
	    
	        if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == false) {
	            if (getSelectedPositions().contains(position)) {
	       	    rowLayout.setBackgroundColor(Color.CYAN);
	       	  }
	       	  else {
	       	    rowLayout.setBackgroundColor(Color.WHITE);
	       	  }
	      	}
	      	else if (getCurrentSongList().getSharedPreferences().getBoolean("prefSingleSelect", false) == true) {
	      	  if (getLastSelectedSongPosition() == position){
	      	    rowLayout.setBackgroundColor(Color.CYAN);
	      	  }
	      	  else {
	      	    rowLayout.setBackgroundColor(Color.WHITE);
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
