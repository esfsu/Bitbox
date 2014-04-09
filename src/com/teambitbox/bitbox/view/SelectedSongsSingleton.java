/*
 * SelectedSongsSingleton Class
 * 
 * Singleton class that holds the current selected songs
 * 
 * 09/04/2014
 * Eric Fernandez
 */


package com.teambitbox.bitbox.view;

import java.util.ArrayList;
import com.teambitbox.bitbox.model.Song;

public class SelectedSongsSingleton {
  
	private static SelectedSongsSingleton instance;
  
  private ArrayList<Song> mSelectedSongs = new ArrayList<Song>();
   
  public ArrayList<Song> getSelectedSongs() {
		return mSelectedSongs;
	}

	public void setSelectedSongs(ArrayList<Song> selectedSongs) {
		mSelectedSongs = selectedSongs;
	}

	public static void initInstance(){
    if (instance == null){
      // Create the instance
      instance = new SelectedSongsSingleton();
    }
  }
 
  public static SelectedSongsSingleton getInstance(){
    // Return the instance
    return instance;
  }
   
  private SelectedSongsSingleton(){
    // Constructor hidden because this is a singleton
  }
   
  public void customSingletonMethod(){
    // Custom method
  }
}
