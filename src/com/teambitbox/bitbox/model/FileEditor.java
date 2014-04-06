/*
 * FileEditor
 * A class to contain all file handling
 * 3/30/2014
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
import android.os.Environment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.util.Xml;

import java.util.ArrayList;
import java.util.Map;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.xmlpull.v1.XmlPullParser;

class FileEditor {
  Context mContext;
  ArrayList<Song> mSongList = new ArrayList<Song>();
  Undo mUndoList = new Undo();
  
  public FileEditor(Context context)
  {
    mContext = context;
  }

  //private static String DEFAULT_DIR = Environment.DIRECTORY_MUSIC;

  /* 
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

  // Called after Scanner
  public void createMyMusicFile(ArrayList<Song> songList) {
    try {
      Log.d("Writing File", "");
      File myMusic = new File("MyMusic.xml");
      if (!myMusic.exists())
      {
        myMusic.createNewFile();
      }
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
        fileContent += "    <" + Id3.GENRE.toString() + " value=" + item.getGenreString()
            + " />\n";
        fileContent += "    <" + Id3.YEAR.toString() + " value=" + item.getYear() + " />\n";
        fileContent += "    <" + Id3.TRACK_NUM.toString() + " value=" + item.getTrackNum()
            + " />\n";
        fileContent += "    <" + Id3.TOTAL_TRACKS.toString() + " value=" + item.getTrackTotal()
            + " />\n";
        fileContent += "    <" + Id3.DISC_NUM.toString() + " value=" + item.getDiscNum() + " />\n";
        fileContent += "    <" + Id3.MISSING.toString() + " value=";
        for (String missingData : item.getMissingData()) {
          fileContent += missingData + ",";
        }
        fileContent += " />\n";

        fileContent += "  " + Id3.SONG_LAST.toString() + "\n\n";
      }

      osw.write(fileContent);
      osw.flush();
      osw.close();
      fOut.close();
      Log.d("Write Complete", "File writing complete");
    } catch (java.io.IOException e) {
      // TODO
      // do something if an IOException occurs.
      Log.e("File Write Error", "File write could not complete");
    }
  }

  // Internally setup the songList
  private void _parseMyMusicFile() {
    Log.d("Parsing File", "");
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
                currentSong.setYear(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.TRACK_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackNum(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.TOTAL_TRACKS.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackTotal(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.DISC_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setDiscNum(currentAttribute);
              } else if (name.equalsIgnoreCase(Id3.MISSING.toString())) {
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
  public ArrayList<Song> getUpdatedSongList() {
    _parseMyMusicFile();
    return mSongList;
  }

  // Edit real file and MyMusic Id3 data
  public void /*boolean*/ editId3Data(ArrayList<Song> songList, ArrayList<Id3Data> dataList) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, dataList, FileOp.EDITID3);
        
    try {      
      for (Song song : songList) {
        Log.d("Song", song.getFileName()); // debug purposes
        File songFile = new File(song.getFileName());
        
        MP3File f = (MP3File)AudioFileIO.read(songFile);
        ID3v24Tag v24tag = f.getID3v2TagAsv24();
                
        for (Id3Data data : dataList) {
          
          switch(data.id3)
          {
            case SONG_NAME:
              v24tag.setField(FieldKey.TITLE, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case ARTIST:
              v24tag.setField(FieldKey.ARTIST, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case ALBUM_ARTIST:
              v24tag.setField(FieldKey.ALBUM_ARTIST, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case ALBUM:
              v24tag.setField(FieldKey.ALBUM, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case COMPOSER:
              v24tag.setField(FieldKey.COMPOSER, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case GENRE:
              v24tag.setField(FieldKey.GENRE, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case TRACK_NUM:
              v24tag.setField(FieldKey.TRACK, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
            break;
    
            case TOTAL_TRACKS:
              v24tag.setField(FieldKey.TRACK_TOTAL, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
            break;
    
            case DISC_NUM:
              v24tag.setField(FieldKey.DISC_NO, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
    
            case YEAR:
              v24tag.setField(FieldKey.YEAR, data.value);
              _updateMyMusicFileTag(song.getFileName(), data.id3, data.value);
              break;
              
            default:
              Log.e("Invalid Tag", "An invalid ID3 tag " + data.id3 + " was found during tag update.");
              break;
          }
  
          f.commit();
        }
      }
    } catch (Exception e) {
      // if any error occurs
      e.printStackTrace();
      //return false;
    }
    
    //return true;
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

  // TODO THIS OPERATION IS CUT!!!
  // Edit real file and MyMusic file name
  public void renameFile(ArrayList<Song> songList, String value) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, value, FileOp.RENAME);

     for (Song song : songList) {
       File sd = Environment.getExternalStorageDirectory();
      // File (or directory) to be moved
      String sourcePath = song.getLocation();
      File file = new File(sd, sourcePath);
      // Destination directory
      file.renameTo(new File(sd, value));
    
       //realFileName = value;
       _updateMyMusicFileTag(song.getFileName(), Id3.FILE_NAME, value);
     }
  }

  // Edit real file and MyMusic file location (same as rename)
  public void moveFile(ArrayList<Song> songList, String value) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, value, FileOp.RENAME);

     for (Song song : songList) {
       File sd = Environment.getExternalStorageDirectory();
      // File (or directory) to be moved
      String sourcePath = song.getLocation();
      File file = new File(sd, sourcePath);
      // Destination directory
      file.renameTo(new File(sd, value));
      
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