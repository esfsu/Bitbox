package com.teambitbox.bitbox.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.teambitbox.bitbox.R;

//extends ArrayAdapter Class; this adapter is used to create the Text Views for each list item
public class SongListAdapter extends ArrayAdapter<String> {

  private SongListView currentSongList;

  // Adapter Constructor
  public SongListAdapter(SongListView songList) {
    super(songList.getActivity(), R.layout.row_layout, songList.getFilenames());
    this.setCurrentSongList(songList);
  }
	    
  @Override
  public View getView(int position, View convertView,ViewGroup parent){
  
  	View row = convertView; // view to be returned
  
    if (row == null) {                          
      LayoutInflater inflater = (LayoutInflater) getCurrentSongList().getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	    row = inflater.inflate(R.layout.row_layout, parent, false);
    }
	      
    // initializes textView
    TextView label = (TextView)row.findViewById(R.id.fileNameLabel);
  
    // uses filenames array to set the text for each list item depending on position
    label.setText(getCurrentSongList().getElement(position));

    return(row);
  } 
 
  private void setCurrentSongList(SongListView sl){
		currentSongList = sl;	
	}

	private SongListView getCurrentSongList(){
		return currentSongList;
	}
}

