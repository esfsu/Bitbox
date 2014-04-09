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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teambitbox.bitbox.R;
import com.teambitbox.bitbox.model.Song;

public class SongListAdapter extends ArrayAdapter<Song> {

  private SongListView currentSongListWidget; // song widget that will be used for
  private ArrayList <Integer> selectedSongsPositions = new ArrayList<Integer>(); // all the positions of the songs selected.
  
  // Adapter Constructor
  public SongListAdapter(SongListView songListView) {
    super(songListView.getCurrentActivity(), R.layout.row_layout, songListView.getSongArrayList()); // uses parent Constructor
    this.setCurrentSongList(songListView); // sets the current Song list to be used in this adapter
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
    
    // TODO: if (sortingByArtist) song.getArtist() + "-" + song.getSongName()  (Default)
    // TODO: if (sortingBySongName) song.getSongName() + "-" + song.getArtist()
    
    // change the row color based on selected state
    if (getSelectedPositions().contains(position)){
    	rowLayout.setBackgroundColor(Color.CYAN);
    }
    else{
    	rowLayout.setBackgroundColor(Color.WHITE);
    }
    
    return(row);
  } // end getView
 
  // sets the SongListView widget to be used for this adapter
  private void setCurrentSongList(SongListView sl){
		this.currentSongListWidget = sl;	
	}

  // returns the SongListView widget used in this adapter
	private SongListView getCurrentSongList(){
		return this.currentSongListWidget;
	}
	
	// sets the selected position number to the number of the item selected in the list
	public void setSelectedPositions(Integer pos){
		if (!getSelectedPositions().contains(pos)){
		  this.selectedSongsPositions.add(pos);
		}
		else{
			selectedSongsPositions.remove(pos);
		}
		// inform the view of this change
		notifyDataSetChanged();
	}

	// returns the selected item position
	public ArrayList<Integer> getSelectedPositions(){
		return this.selectedSongsPositions;
	}
}
