package com.teambitbox.bitbox;

import java.io.File;
import java.util.ArrayList;

import com.teambitbox.bitbox.model.Song;


import android.media.MediaMetadataRetriever;
import android.util.Log;

public class ScannerStub {
	// SDCard Path
	final String MEDIA_PATH = "Music/";
	private ArrayList<Song> songList = new ArrayList<Song>();

	// Constructor
	public ScannerStub() {

	}

	/**
	 * Function to read all mp3 files from Music directory and store the details
	 * in ArrayList
	 * */
	public ArrayList<Song> getMyMusicList() {
    File songDirectoryFiles = null;
    File[] paths;
    Song song = null;

    try {
      // create new file
      songDirectoryFiles = new File(MEDIA_PATH);

      // returns pathnames for files and directory
      paths = songDirectoryFiles.listFiles();

      int i = 0; // variable used for example song names

      // for each pathname in pathname array
      for (File path : paths) {
        Log.d("ScannerStub", path.getAbsolutePath()); // debug purposes

        MediaMetadataRetriever reader = new MediaMetadataRetriever();
        reader.setDataSource(path.getAbsolutePath());

				song.setSongName("Example" + i); // reader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
				Log.d("Song", song.getSongName());
				songList.add(song);
				++i;
			}
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}

		// return songs list array
		return songList;
	}

}
