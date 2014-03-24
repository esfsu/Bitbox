package com.teambitbox.bitbox;

import java.io.File;


import android.media.MediaMetadataRetriever;
import android.util.Log;



public class SongStub {
	public MediaMetadataRetriever reader;
	private String songTitle;
	
	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String sT) {
		this.songTitle = sT;
	}

	public SongStub(File path) {
		reader = new MediaMetadataRetriever();
		reader.setDataSource(path.getAbsolutePath());
		
		setSongTitle("Example"); // reader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
	    Log.d("SongStub", getSongTitle());
	}

}
