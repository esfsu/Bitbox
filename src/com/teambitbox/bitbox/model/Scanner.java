/*
 * Scanner
 * A class to scan for music files on the mobile device
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.media.MediaMetadataRetriever;
import android.util.Log;

public class Scanner {
  
  // Member variables
  ArrayList<Song> mSongList = new ArrayList<Song>();
  private FileEditor mEditor;
  private MediaMetadataRetriever mMediaReader;
  
  
  // Constructor
  public Scanner(Context context)
  {
    mEditor = new FileEditor(context);
  }
  
  // Scans the device for media files, gets data, calls FileEditor to 
  // create persistent file.
  public ArrayList<Song> scanDevice() {
    try {
      File songDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
      File[] files;

      // returns pathnames for files and directory
      files = songDirectory.listFiles();

      for (File file : files) {
        mMediaReader = new MediaMetadataRetriever();
        mMediaReader.setDataSource(file.getAbsolutePath());

        // temporary object to store songs
        Song tempSong = new Song();
        // temporary object to store id3 information
        String id3Data = "";
        // collector for id3 data that isn't validated
        String missingData = "";
        
        Log.d("File", file.getName());
        tempSong.setFileName(file.getName());
        tempSong.setLocation(file.getParent());

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (_validateId3(id3Data))
          tempSong.setSongName(id3Data);
        else
          missingData += Id3.SONG_NAME.toString() + ",";
        
        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (_validateId3(id3Data))
          tempSong.setArtist(id3Data);
        else
          missingData += Id3.ARTIST.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
        if (_validateId3(id3Data))
          tempSong.setAlbumArtist(id3Data);
        else
          missingData += Id3.ALBUM_ARTIST.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        if (_validateId3(id3Data))
          tempSong.setAlbum(id3Data);
        else
          missingData += Id3.ALBUM.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER);
        if (_validateId3(id3Data))
          tempSong.setComposer(id3Data);
        else
          missingData += Id3.COMPOSER.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
        if (_validateId3(id3Data)) {
          // TODO set Genre properly
          //tempSong.setGenre(id3Data);
          tempSong.setGenre(Genre.GENRE_INVALID);
        } else
          missingData += Id3.GENRE.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS);
        if (_validateId3(id3Data))
          tempSong.setTrackTotal(Integer.parseInt(id3Data));
        else
          missingData += Id3.TOTAL_TRACKS.toString() + ",";

        id3Data = mMediaReader
            .extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
        if (_validateId3(id3Data))
          tempSong.setTrackNum(Integer.parseInt(id3Data));
        else
          missingData += Id3.TRACK_NUM.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER);
        if (_validateId3(id3Data))
          tempSong.setDiscNum(Integer.parseInt(id3Data));
        else
          missingData += Id3.DISC_NUM.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
        if (_validateId3(id3Data))
          tempSong.setYear(Integer.parseInt(id3Data));
        else
          missingData += Id3.YEAR.toString() + ",";

        id3Data = mMediaReader.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
        if (_validateId3(id3Data))
          tempSong.setBitRate(Integer.parseInt(id3Data));
        else
          missingData += Id3.BITRATE.toString() + ",";

        // turn our missing data String list into an Array
        tempSong.setMissingData(missingData.split(","));
        
        Log.d("Missing", missingData);

        // add all of the data to the list
        mSongList.add(tempSong);

        mMediaReader.release();
      }
      
      // after scanning, create the persistent copy of the data (file)
      mEditor.createMyMusicFile(mSongList);
      
    } catch (Exception e) {
      // if any error occurs
      Log.e("FileError", "Files not found");
      e.printStackTrace();
    }
    
    return mSongList;
  }
  
  // make sure we're not getting empty data. If it is, it goes to missingData.
  private boolean _validateId3(String id3) {
    if (id3 == null) Log.d("id3", "null");
    if (id3 != null) Log.d("id3", id3);

    return !(id3 == null || id3 == "" || id3 == " ");
  }
}