package com.teambitbox.bitbox.model;

import java.io.File;
import java.util.ArrayList;

import android.media.MediaMetadataRetriever;
import android.util.Log;

public class Scanner {
  
  // Constants
  private static String DEFAULT_DIR = "Music/";
  
  // Member variables
  ArrayList<Song> mSongList;
  private FileEditor mEditor = new FileEditor();
  
  public ArrayList<Song> scanDevice() {
    try {
      File songDirectoryFiles = null;
      File[] paths;

      // create new file
      songDirectoryFiles = new File(DEFAULT_DIR);
      // returns pathnames for files and directory
      paths = songDirectoryFiles.listFiles();

      for (File path : paths) {
        MediaMetadataRetriever mediaRetriever = new MediaMetadataRetriever();
        mediaRetriever.setDataSource(path.getAbsolutePath());
        Log.d("ScannerStub", path.getAbsolutePath()); // debug purposes

        // temporary object to store songs
        Song tempSong = new Song();
        // temporary object to store id3 information
        String id3Data = "";
        // collector for id3 data that isn't validated
        String missingData = "";

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (_validateId3(id3Data))
          tempSong.setSongName(id3Data);
        else
          missingData += Id3.SONG_NAME.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (_validateId3(id3Data))
          tempSong.setArtist(id3Data);
        else
          missingData += Id3.ARTIST.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
        if (_validateId3(id3Data))
          tempSong.setAlbumArtist(id3Data);
        else
          missingData += Id3.ALBUM_ARTIST.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        if (_validateId3(id3Data))
          tempSong.setAlbum(id3Data);
        else
          missingData += Id3.ALBUM.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER);
        if (_validateId3(id3Data))
          tempSong.setComposer(id3Data);
        else
          missingData += Id3.COMPOSER.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
        if (_validateId3(id3Data)) {
          // tempSong.setGenre(id3Data);
        } else
          missingData += Id3.GENRE.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS);
        if (_validateId3(id3Data))
          tempSong.setTrackTotal(Integer.parseInt(id3Data));
        else
          missingData += Id3.TOTAL_TRACKS.toString();

        id3Data = mediaRetriever
            .extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
        if (_validateId3(id3Data))
          tempSong.setTrackNum(Integer.parseInt(id3Data));
        else
          missingData += Id3.TRACK_NUM.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER);
        if (_validateId3(id3Data))
          tempSong.setDiscNum(Integer.parseInt(id3Data));
        else
          missingData += Id3.DISC_NUM.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
        if (_validateId3(id3Data))
          tempSong.setYear(Integer.parseInt(id3Data));
        else
          missingData += Id3.YEAR.toString();

        id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
        if (_validateId3(id3Data))
          tempSong.setBitRate(Integer.parseInt(id3Data));
        else
          missingData += Id3.BITRATE.toString();

        // turn our missing data String list into an Array
        tempSong.setMissingData(missingData.split(","));

        // add all of the data to the list
        mSongList.add(tempSong);

        mediaRetriever.release();
      }
    } catch (Exception e) {
      // if any error occurs
      e.printStackTrace();
    }

    // after scanning, create the persistent copy of the data (file)
    mEditor.createMyMusicFile(mSongList);
    
    return mSongList;
  }
  
  // make sure we're not getting empty data. If it is, it goes to missing data
  private boolean _validateId3(String id3) {
    return !(id3 == null || id3 == "" || id3 == " ");
  }
}