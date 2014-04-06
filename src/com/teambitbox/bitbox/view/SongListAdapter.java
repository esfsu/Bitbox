package com.teambitbox.bitbox.view;

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

// Extends ArrayAdapter Class. This adapter is used to initialize the rows of the ListView 
public class SongListAdapter extends ArrayAdapter<Song> {

  // song widget that will be used for initial value for no item selected.
  private SongListView currentSongListWidget;
  private int selectedPosition = -1;

  // Adapter Constructor
  public SongListAdapter(SongListView songListView) {
    // uses parent Constructor
    super(songListView.getCurrentActivity(), R.layout.row_layout, songListView.getSongArrayList());
    // sets the current Song list to be used in this adapter
    this.setCurrentSongList(songListView);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    // view to be returned
    View row = convertView;
    // gets the Song object from ArrayList in the SongClass depending on its position
    Song song = getItem(position);

    // inflates the view for the row in the ListView
    if (row == null) {
      LayoutInflater inflater = (LayoutInflater) getCurrentSongList().getCurrentContext()
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      row = inflater.inflate(R.layout.row_layout, null);
    }

    // initialized entire row layout
    LinearLayout rowLayout = (LinearLayout) row.findViewById(R.id.rowLinearLayout);

    // initializes TextView for the song name
    TextView songNameView = (TextView) row.findViewById(R.id.songNameLabel);

    // uses Song from the ArrayList in the SongListView class to set the
    // text for each song of the ListView
    
    // TODO: if (sortingByArtist) song.getArtist() + "-" + song.getSongName()  (Default)
    // TODO: if (sortingBySongName) song.getSongName() + "-" + song.getArtist()
    songNameView.setText(song.getArtist() + "-" + song.getSongName());

    // change the row color based on selected state
    if (getSelectedPosition() == position) {
      rowLayout.setBackgroundColor(Color.CYAN);
    } else {
      rowLayout.setBackgroundColor(Color.WHITE);
    }

    return (row);
  }

  // sets the SongListView widget to be used for this adapter
  private void setCurrentSongList(SongListView sl) {
    this.currentSongListWidget = sl;
  }

  // returns the SongListView widget used in this adapter
  private SongListView getCurrentSongList() {
    return this.currentSongListWidget;
  }

  // sets the selected position number to the number of the item selected in
  // the list
  public void setSelectedPosition(int pos) {
    if (pos != getSelectedPosition()) {
      this.selectedPosition = pos;
    } else {
      this.selectedPosition = -1;
    }
    // inform the view of this change
    notifyDataSetChanged();
  }

  // returns the selected item position
  public int getSelectedPosition() {
    return this.selectedPosition;
  }
}
