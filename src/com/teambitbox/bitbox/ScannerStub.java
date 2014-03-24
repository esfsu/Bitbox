package com.teambitbox.bitbox;

import java.io.File;
import java.util.ArrayList;

import android.util.Log;

public class ScannerStub {
  //SDCard Path
  final String MEDIA_PATH = "/sdcard/Music/";
  private ArrayList<SongStub> songsList = new ArrayList<SongStub>();

  // Constructor
  public ScannerStub(){

  }

  /**
   * Function to read all mp3 files from sdcard/Music directory
   * and store the details in ArrayList
   * */
  public ArrayList<SongStub> getMyMusicList(){
  	File songDirectoryFiles = null;
    File[] paths;
    SongStub song = null;
    
    try{      
      // create new file
      songDirectoryFiles = new File(MEDIA_PATH);
       
       // returns pathnames for files and directory
       paths = songDirectoryFiles.listFiles();
       
       int i = 0; // variable used for example song names
       
       // for each pathname in pathname array
       for(File path:paths){
      	  Log.d("ScannerStub", path.getAbsolutePath()); // debug purposes
          song = new SongStub(path);
          song.setSongTitle("Example " + i);
          this.songsList.add(song);
          ++i;
       }
    }catch(Exception e){
       // if any error occurs
       e.printStackTrace();
    }
    
    // return songs list array
    return this.songsList;
 }   
  	
}
