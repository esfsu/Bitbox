/*
 * FileEditor
 * A class to contain all file handling
 * 3/2/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaMetadataEditor;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.util.Xml;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

class FileEditor {
  Context mContext;
  ArrayList<Song> mSongList;
  Undo mUndoList;

  //private static String DEFAULT_DIR = "Music/";

  /*
   * Open file for write try { FileOutputStream fOut = openFileOutput(fileName,
   * MODE_PRIVATE); OutputStreamWriter osw = new OutputStreamWriter(fOut);
   * return osw; } catch (FileNotFoundException e) { // TODO }
   * 
   * OpenFileOnDevice Context fileContext;
   * fileContext.getExternalStoragePublicDirectory
   * (android.os.Environment.DIRECTORY_MUSIC);
   * 
   * private boolean _renameFile(Song song, String newName) { File oldFile = new
   * File(song.getLocation(), song.getFileName()); File newFile = new
   * File(song.getLocation(), newName);
   * 
   * boolean returnVal = oldFile.renameTo(newFile); if (returnVal) {
   * song.setFileName(newName); }
   * 
   * return returnVal; }
   * 
   * 
   * private boolean _moveFile(Song song, String newLoc) { File oldFile = new
   * File(song.getLocation(), song.getFileName()); File newFile = new
   * File(newLoc, song.getFileName());
   * 
   * boolean returnVal = oldFile.renameTo(newFile); if (returnVal) {
   * song.setLocation(newLoc); }
   * 
   * return returnVal; }
   */

  // Called after Scanner, and for any edits, write the entire file
  public void createMyMusicFile(ArrayList<Song> songList) {
    try {
      // TODO OPEN FILE
      FileOutputStream fOut = mContext.openFileOutput("MyMusic.xml", Context.MODE_PRIVATE);
      OutputStreamWriter osw = new OutputStreamWriter(fOut);

      String fileContent = "";
      for (Song item : songList) {
        fileContent += "  " + Id3.SONG_FIRST.toString() + "\n";
        fileContent += "    <" + Id3.FILE_NAME.toString() + " value=" + item.getFileName()
            + " />\n";
        fileContent += "    <" + Id3.FILE_LOC.toString() + " value=" + item.getLocation() + " />\n";
        fileContent += "    <" + Id3.SONG_NAME.toString() + " value=" + item.getSongName()
            + " />\n";
        fileContent += "    <" + Id3.ARTIST.toString() + " value=" + item.getArtist() + " />\n";
        fileContent += "    <" + Id3.ALBUM.toString() + " value=" + item.getAlbum() + " />\n";
        fileContent += "    <" + Id3.ALBUM_ARTIST.toString() + " value=" + item.getAlbumArtist()
            + " />\n";
        fileContent += "    <" + Id3.COMPOSER.toString() + " value=" + item.getComposer() + " />\n";
        fileContent += "    <" + Id3.GENRE.toString() + " value=" + item.getGenre().toString()
            + " />\n";
        fileContent += "    <" + Id3.YEAR.toString() + " value=" + item.getYear() + " />\n";
        fileContent += "    <" + Id3.TRACK_NUM.toString() + " value=" + item.getTrackNum()
            + " />\n";
        fileContent += "    <" + Id3.TOTAL_TRACKS.toString() + " value=" + item.getTrackTotal()
            + " />\n";
        fileContent += "    <" + Id3.DISC_NUM.toString() + " value=" + item.getDiscNum() + " />\n";
        fileContent += "    <" + Id3.TOTAL_DISCS.toString() + " value=" + item.getDiscTotal()
            + " />\n";
        fileContent += "    <" + Id3.EXPLICIT.toString() + " value=" + item.getExplicit() + " />\n";
        fileContent += "    <" + Id3.BITRATE.toString() + " value=" + item.getBitRate() + " />\n";

        fileContent += "    <" + Id3.MISSING.toString() + " value=";
        for (String missingData : item.getMissingData()) {
          fileContent += missingData + ",";
        }
        fileContent += " />\n";

        fileContent += "  " + Id3.SONG_LAST.toString() + "\n\n";
      }

      osw.write(fileContent);

      osw.close();
      fOut.close();
    } catch (java.io.IOException e) {
      // TODO
      // do something if an IOException occurs.
    }
  }

  // Internally setup the songList
  private void _parseMyMusicFile() {
    XmlPullParser parser = Xml.newPullParser();
    try {
      FileInputStream fIn = mContext.openFileInput("MyMusic.xml");
      InputStreamReader isr = new InputStreamReader(fIn);

      // auto-detect the encoding from the stream
      parser.setInput(isr);
      int eventType = parser.getEventType();
      Song currentSong = null;
      boolean done = false;

      while (eventType != XmlPullParser.END_DOCUMENT && !done) {
        String name = null;
        
        switch (eventType) {
          case XmlPullParser.START_DOCUMENT:
            // move on to the next tag
            break;

          case XmlPullParser.START_TAG:
            name = parser.getName();
  
            if (name.equalsIgnoreCase("MyMusic")) {
              // create a new list container at the root
              mSongList = new ArrayList<Song>();
            } else if (mSongList != null) {
              // getting attributes
              String currentAttribute = "";
              currentSong = new Song();
              if (name.equalsIgnoreCase(Id3.SONG_FIRST.toString())) {
                currentSong = new Song();
              } else if (name.equalsIgnoreCase(Id3.FILE_NAME.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setFileName(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.FILE_LOC.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setLocation(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.SONG_NAME.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setSongName(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.ARTIST.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setArtist(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.ALBUM_ARTIST.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setAlbumArtist(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.ALBUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setAlbum(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.COMPOSER.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setComposer(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.GENRE.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                // currentSong.setGenre(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.YEAR.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setYear(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.TRACK_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackNum(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.TOTAL_TRACKS.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackTotal(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.DISC_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setDiscNum(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.TOTAL_DISCS.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setDiscTotal(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.EXPLICIT.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setExplicit(Boolean.parseBoolean(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.BITRATE.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setBitrate(Integer.parseInt(currentAttribute));
              } else if (name.equalsIgnoreCase(Id3.MISSING.toString())) {
                // TODO PARSE further?
                String[] missingData = currentAttribute.split(",");
                currentSong.setMissingData(missingData);
              }
            }
            break;

          case XmlPullParser.END_TAG:
            name = parser.getName();
  
            if (name.equalsIgnoreCase(Id3.SONG_FIRST.toString()) && currentSong != null) {
              // add to list at closing song tag
              mSongList.add(currentSong);
            } else if (name.equalsIgnoreCase("MyMusicList")) {
              // quit if done
              done = true;
            }
            break;
          }
        eventType = parser.next();
      }
    } catch (FileNotFoundException e) {
      // TODO
    } catch (IOException e) {
      // TODO
    } catch (Exception e) {
      // TODO
    }
  }

  // For MyMusic screen to get latest data
  public void getUpdatedSongList() {
    _parseMyMusicFile();
  }

  // Edit real file and MyMusic Id3 data
  public void /*boolean*/ editId3Data(ArrayList<Song> songList, ArrayList<Id3Data> dataList) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, dataList, FileOp.EDITID3);
    
    // TODO
    // What doesn't work:
    // MediaMetadataEditor, have yet to find an Android SDK supported way to edit tags
    //  Will probably need an external library
    // MediaMetadataEditor enums are false
    // What does work:
    // Basic structure for updating files based on data passed in
    
    /*
    try {      
      for (Song song : songList) {
        MediaMetadataEditor mediaEditor = new MediaMetadataEditor();
        mediaEditor.setDataSource(song.getFileName());
        Log.d("ScannerStub", song.getFileName()); // debug purposes
        
        for (Id3Data data : dataList) {
          
          switch(data.id3)
          {
            case SONG_NAME:
              mediaEditor.putString(MetadataEditor.METADATA_KEY_TITLE, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case ARTIST:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_ARTIST, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case ALBUM_ARTIST:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_ALBUMARTIST, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case ALBUM:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_ALBUM, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case COMPOSER:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_COMPOSER, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case GENRE:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_GENRE, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case TRACK_NUM:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_NUM_TRACKS, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
            break;
    
            case TOTAL_TRACKS:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_CD_TRACK_NUMBER, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
            break;
    
            case DISC_NUM:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_DISC_NUMBER, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
    
            case YEAR:
              mediaEditor.putString(MediaMetadataEditor.METADATA_KEY_YEAR, data.value);
              _updateMyMusicFileTag(song.GetFileName(), data.id3, data.value);
              break;
          }
  
          mediaEditor.apply();
          mediaEditor.clear();
        }
      }
    } catch (Exception e) {
      // if any error occurs
      e.printStackTrace();
      //return false;
    }
    
    //return true;
    */
  }
  
  // Update attributes in the MyMusic file
  private boolean _updateMyMusicFileTag(String fileName, Id3 id3, String value)
  {
    XmlPullParser parser = Xml.newPullParser();
    try {
      FileInputStream fIn = mContext.openFileInput("MyMusic.xml");
      InputStreamReader isr = new InputStreamReader(fIn);

      // auto-detect the encoding from the stream
      parser.setInput(isr);
      int eventType = parser.getEventType();
      boolean done = false;

      while (eventType != XmlPullParser.END_DOCUMENT && !done) {
        String name = null;
        
        switch (eventType) {
          case XmlPullParser.START_TAG:
            name = parser.getName();
            
            // look for file name
            if (name.equalsIgnoreCase(fileName)) {
              // look for tag
              if (name.equalsIgnoreCase(id3.toString())) {
                parser.setProperty(id3.toString(), value);
                done = true;
              }
            }
            break;
            
          case XmlPullParser.END_TAG:
            done = true;
            break;
         }
       eventType = parser.next();
     }
   } catch (FileNotFoundException e) {
     // TODO
     return false;
   } catch (IOException e) {
     // TODO
     return false;
   } catch (Exception e) {
     // TODO
     return false;
   }
   return true;
  }

  // Edit real file and MyMusic file name
  public void renameFile(ArrayList<Song> songList, String value) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, value, FileOp.RENAME);

     for (Song song : songList) {
       //open realFile;
       //realFileName = value;
       _updateMyMusicFileTag(song.getFileName(), Id3.FILE_NAME, value);
     }
  }

  // Edit real file and MyMusic file location (same as rename)
  public void moveFile(ArrayList<Song> songList, String value) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, value, FileOp.RENAME);

     for (Song song : songList) {
       //open realFile;
       //realFileName = value;
       _updateMyMusicFileTag(song.getFileName(), Id3.FILE_NAME, value);
     }
  }

  // Restore real file and MyMusic from Undo list
  public boolean restoreFromUndoList() {
    ArrayList<Song> newSongList = mUndoList.getUndoSongList();
    ArrayList<Id3Data> newDataList = mUndoList.getUndoDataList();
    String newDataValue = mUndoList.getUndoDataValue();

     switch (mUndoList.getOperation()) {
       case RENAME:
         renameFile(newSongList, newDataValue);
         return true;
       
       case EDITID3:
         editId3Data(newSongList, newDataList);
         return true;
       
       case MOVE:
         moveFile(newSongList, newDataValue);
         return true;
       
       default: // TODO Throw error
         return false;
     }
  }

  // Update settings in an Android provided storage area
  // Takes a Map of String keys, and values of either String OR Bool
  // Can make all values String if this doesn't initially work
  public void updateSettings(Map<String, ?> values) {
    // Open Android provided context Settings location
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings", Context.MODE_PRIVATE);
   
    // set all settings at once
    Editor prefsEditor = prefsFile.edit();
    prefsEditor.putString(Settings.SORT_TYPE.toString(), (String) values.get(Settings.SORT_TYPE.toString() ) );
    prefsEditor.putString(Settings.SORT_ORDER.toString(), (String) values.get(Settings.SORT_ORDER.toString() ) );
    prefsEditor.putBoolean(Settings.MULTI_SELECT.toString(), (Boolean) values.get(Settings.MULTI_SELECT.toString() ) );
    prefsEditor.putBoolean(Settings.SHOW_DETAILS.toString(), (Boolean) values.get(Settings.SHOW_DETAILS.toString() ) );
    prefsEditor.putBoolean(Settings.IS_SCANNED.toString(), (Boolean) values.get(Settings.IS_SCANNED.toString() ) );
  
    // apply edits to the file
    prefsEditor.commit();
 }

  // Get settings from the Android provided storage area
  public Map<String, ?> getSettings() {
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    return prefsFile.getAll();
  }
}