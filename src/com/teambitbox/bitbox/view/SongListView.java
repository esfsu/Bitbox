/* 
 * mSongListView Class
 * 
 * This class contains the ListView widget where the songs will be displayed in the MyMusic View. It also contains an 
 * ArrayList of the Songs in the default audio directory of the device. Songs selected from the list will be stored 
 * in the selectedSong ArrayList in order to be used by other objects.
 * 
 * 03/03/2014
 * Eric Fernandez
 */

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

public class SongListView extends CustomView{
  
	private ArrayList<Song> mSongList = new ArrayList<Song>(); // song list to be displayed
  private ListView mSongListWidget; // ListView widget that displays the songs.
  private BitboxApp mSelectedSongsSingletonInitializer; // used to initialize singletons
  private SongListAdapter mSongListAdapter; // test object for mSongListAdapter

	// Constructor
	public SongListView(Activity currentActivity, Context currentContext, final ArrayList<Song> mSongList){
		mSongListWidget = (ListView) currentActivity.findViewById(R.id.listView); // initializes ListView
		 
		// sets all the attributes of this object
		setSongArrayList(mSongList);
		setCurrentContext(currentContext);
	  setCurrentActivity(currentActivity);
	  mSongListAdapter = new SongListAdapter(this); // initialize adapter
	  mSongListWidget.setAdapter(mSongListAdapter); // sets adapter for ListView
	  mSongListAdapter.setNotifyOnChange(true); // reflect changes made to the list of songs set to true  
    mSongListWidget.setTextFilterEnabled(true); // used for filtering text in the ListView for the search feature
    mSelectedSongsSingletonInitializer = (BitboxApp)getCurrentActivity().getApplication();
    
    /* setOnItemClick Listener for the ListView widget. When the user clicks a song in the list, That song is added 
     * or removed from the mSelectedSongs ArrayList depending on its current state.*/  
    mSongListWidget.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	    @Override
	    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
	    	mSongListAdapter.setSelectedPositions(position); // sets the position of the object for the last selected object
	    	
	    	/* If list is empty, add song to mSelectedSongs else, if the song selected is on the list and it was selected 
	    	 * again, remove it from the selectedSong ArrayList. If the song the array contains elements and the 
	    	 * selected was not selected before, add it to the selected song ArrayList */
	    	if (SelectedSongsSingleton.getInstance().getSelectedSongs().isEmpty()){
	    	  // used for debugging purposes in the logcat window; the ArrayList should be empty when no songs have been selected. 
	    		Log.d("mSongListView", "First Case: empty array: " + SelectedSongsSingleton.getInstance().getSelectedSongs().isEmpty()); 
	    	  
	    		// mSelectedSongs.add(mSongList.get(position)); // add song to the mSelectedSongs ArrayList
	    		SelectedSongsSingleton.getInstance().getSelectedSongs().add(mSongList.get(position)); // add song to the mSelectedSongs ArrayList
	    	  
	    	  // this is used for debugging purposes in the Logcat window. Now the size should show one
		    	Log.d("mSongListView", "First Case: Array contains one Song: " + SelectedSongsSingleton.getInstance().getSelectedSongs().size() ); 
	    	}
	
	    	else if (SelectedSongsSingleton.getInstance().getSelectedSongs().contains(mSongList.get(position))){
	    		Log.d("mSongListView", "Second Case: Song is in Array: " + 
	    				SelectedSongsSingleton.getInstance().getSelectedSongs().contains(mSongList.get(position)) ); // Used for debugging purposes in the logcat window. It should show true
	    				
	    		// Used for debugging purposes in the logcat window. It shows the array size before a song has been deselected
	    		Log.d("mSongListView", "Second Case: Size of Array before " 
	    		+ "Song has been deselected: " + SelectedSongsSingleton.getInstance().getSelectedSongs().size());
	    		
	    		SelectedSongsSingleton.getInstance().getSelectedSongs().remove(mSongList.get(position)); // remove song from mSelectedSongs ArrayList
	    	  /* This is used for debugging purposes in the logcat window. This should no longer show true 
	    	   * since the song is no longer in the array
	    	   */
	    		Log.d("mSongListView", "Second Case: Song is in Array: " + SelectedSongsSingleton.getInstance().getSelectedSongs().contains(mSongList.get(position)));
	    		
	    	  // This is used for debugging purposes in the logcat window. The size shown should be one less than the previous size shown.
	    		Log.d("mSongListView", "Second Case: Size of Array Song after Song has been deselected: " + SelectedSongsSingleton.getInstance().getSelectedSongs().size() ); 
	    	}
	    	else{
	    	  // Used for debugging purposes in the logcat window. The size should be greater or equal to one.
	    		Log.d("mSongListView", "Third Case: Array with size : " + SelectedSongsSingleton.getInstance().getSelectedSongs().size()); 
	    		SelectedSongsSingleton.getInstance().getSelectedSongs().add(mSongList.get(position)); // add song to the mSelectedSongs ArrayList
	    	  
	    		// Used for debugging purposes in the logcat window. After adding a song to the selectedSong 
	    		Log.d("mSongListView", "Third Case: Array with size: " + SelectedSongsSingleton.getInstance().getSelectedSongs().size()); 
	    		Log.d("SelectedSongsSingleton", "Array second song is: " + SelectedSongsSingleton.getInstance().getSelectedSongs().get(1).getSongName() ); 
	    	}
	       // Used for debugging purposes in the logcat window. It should show the selected songs' positions 
	    	 Log.d("mSongListView", "Adapter positions" + mSongListAdapter.getSelectedPositions()); 
	    }        
    }); // end setOnItemClickListener 
  } // end Constructor
	
	// Accessor and Mutators
	
  // returns the Song ArrayList
	public ArrayList<Song> getSongArrayList(){
		return mSongList; 
  } // end getSongArrayList

	// sets the Song ArrayList for this object
	private void setSongArrayList(ArrayList<Song> sl){
		mSongList = sl; 
	} // end getSongArrayList
	
	// gets individual Song
	public Song getSong(int position){
		return mSongList.get(position); // to be replaced with "this.mSongList[position];"
	} // ends getSong
	
	// returns the ListView widget 
	public ListView getSongListWidget() {
			return mSongListWidget;
  } // ends getmSongListWidget
  
  //	sets the ListView widget
  private void setSongListWidget(ListView songListWidget) {
		mSongListWidget = songListWidget;
  } // ends setmSongListWidget
	
  // returns adapter for this ListView
  public SongListAdapter getSongListAdapter() {
		return mSongListAdapter;
	} // ends getmSongListAdapter

  // sets the adapter for this ListView
	public void setSongListAdapter(SongListAdapter songListAdapter) {
		mSongListAdapter = songListAdapter;
	} // ends setmSongListAdapter
	
}// ends mSongListView class
