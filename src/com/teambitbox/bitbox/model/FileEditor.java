/*
* FileEditor
* A class to contain all file handling
* 3/2/2014
* Eric Saunders
*/
package com.teambitbox.bitbox.model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.util.Xml;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;


class FileEditor {
  Context mContext;
  ArrayList<Song> mSongList;
  Undo mUndoList;
  
  private static String DEFAULT_DIR = " Music/";
  
  /*
   * CODE SNIPPETS
   * This is for Scanner behavior, scans directory and reads metadata
      MediaMetadataRetriever mmr = new MediaMetadataRetriever();
      mmr.setDataSource(filePath);
      Song tempSong;
      tempSong.setAlbum(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
      ...
      ...
      ...
   *
   * Open file for write
      try {
        FileOutputStream fOut = openFileOutput(fileName, MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        return osw;
      } catch (FileNotFoundException e) {
        // TODO
      }
   *
   * OpenFileOnDevice
    Context fileContext;
    fileContext.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_MUSIC);
   *
    private boolean _renameFile(Song song, String newName)
    {
      File oldFile = new File(song.getLocation(), song.getFileName());
      File newFile = new File(song.getLocation(), newName);
      
      boolean returnVal = oldFile.renameTo(newFile);
      if (returnVal)
      {
        song.setFileName(newName);
      }
      
      return returnVal;
    }
  *
  *
    private boolean _moveFile(Song song, String newLoc)
    {
      File oldFile = new File(song.getLocation(), song.getFileName());
      File newFile = new File(newLoc, song.getFileName());
      
      boolean returnVal = oldFile.renameTo(newFile);
      if (returnVal)
      {
        song.setLocation(newLoc);
      }
      
      return returnVal;
    }
   *
   */
  
  // TODO DOES NOT BELONG IN THIS CLASS!
  private void _scanDevice()
  {
    MediaMetadataRetriever mediaRetriever = new MediaMetadataRetriever();
    mContext.getFilesDir(Environment.DIRECTORY_MUSIC);
    //while (songs in list)
    {
      File[] mainPath = new File().listRoots();
      for (File tempFile : mainPath)
      {
        FilenameFilter tempFilter;
        File[] = tempFile.listFiles(tempFilter);
      }
      
      mediaRetriever.setDataSource(filePath);
      Song tempSong = new Song();
      String id3Data;
      String missingData;

      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
      if (_validateId3(id3Data))
        tempSong.setSongName(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
      if (_validateId3(id3Data))
        tempSong.setArtist(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);      
      if (_validateId3(id3Data))
        tempSong.setAlbumArtist(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
      if (_validateId3(id3Data))
        tempSong.setAlbum(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER);
      if (_validateId3(id3Data))
        tempSong.setComposer(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
      if (_validateId3(id3Data))
        tempSong.setGenre(id3Data);
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS);
      if (_validateId3(id3Data))
        tempSong.setTrackTotal(Integer.parseInt(id3Data));
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
      if (_validateId3(id3Data))
        tempSong.setTrackNum(Integer.parseInt(id3Data));
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER);
      if (_validateId3(id3Data))
        tempSong.setDiscNum(Integer.parseInt(id3Data));
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
      if (_validateId3(id3Data))
        tempSong.setYear(Integer.parseInt(id3Data));
      else
        missingData += id3Data;
      
      id3Data = mediaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
      if (_validateId3(id3Data))
        tempSong.setBitRate(Integer.parseInt(id3Data));
      else
        missingData += id3Data;
      
      mSongList.add(tempSong);
    }
    
    mediaRetriever.release();
    
    // after scanning, update the 
    _createMyMusicFile(mSongList);
  }
  
  // Called after Scanner, and for any edits, write the entire file
  private void _createMyMusicFile(ArrayList<Song> songList)
  {
    try {
      // TODO OPEN FILE
      FileOutputStream fOut = mContext.openFileOutput("MyMusic.xml", Context.MODE_PRIVATE);
      OutputStreamWriter osw = new OutputStreamWriter(fOut);
  
      String fileContent = "";
      for (Song item : songList)
      {
        fileContent += "  " + Id3.SONG_FIRST.toString() + "\n";
        fileContent += "    <" + Id3.FILE_NAME.toString() + " value=" + item.getFileName() + " />\n";
        fileContent += "    <" + Id3.FILE_LOC.toString() + " value=" + item.getLocation() + " />\n";
        fileContent += "    <" + Id3.SONG_NAME.toString() + " value=" + item.getSongName() + " />\n";
        fileContent += "    <" + Id3.ARTIST.toString() + " value=" + item.getArtist() + " />\n";
        fileContent += "    <" + Id3.ALBUM.toString() + " value=" + item.getAlbum() + " />\n";
        fileContent += "    <" + Id3.ALBUM_ARTIST.toString() + " value=" + item.getAlbumArtist() + " />\n";
        fileContent += "    <" + Id3.COMPOSER.toString() + " value=" + item.getComposer() + " />\n";
        fileContent += "    <" + Id3.GENRE.toString() + " value=" + item.getGenre().toString() + " />\n";
        fileContent += "    <" + Id3.YEAR.toString() + " value=" + item.getYear() + " />\n";
        fileContent += "    <" + Id3.TRACK_NUM.toString() + " value=" + item.getTrackNum() + " />\n";
        fileContent += "    <" + Id3.TOTAL_TRACKS.toString() + " value=" + item.getTrackTotal() + " />\n";
        fileContent += "    <" + Id3.DISC_NUM.toString() + " value=" + item.getDiscNum() + " />\n";
        fileContent += "    <" + Id3.TOTAL_DISCS.toString() + " value=" + item.getDiscTotal() + " />\n";
        fileContent += "    <" + Id3.EXPLICIT.toString() + " value=" + item.getExplicit() + " />\n";
        fileContent += "    <" + Id3.BITRATE.toString() + " value=" + item.getBitRate() + " />\n";
        
        fileContent += "    <" + Id3.MISSING.toString() + " value=";
        for (String missingData : item.getMissingData())
        {
          fileContent += missingData + ",";
        }
        fileContent += " />\n";
        
        fileContent += "  " + Id3.SONG_LAST.toString() + "\n";
      }
  
      osw.write(fileContent);
      
      osw.close();
      fOut.close();
    } catch (java.io.IOException e) {
      //do something if an IOException occurs.
    }
  }
    
  // Internally setup the songList
  private void _parseMyMusicFile()
  {
    XmlPullParser parser = Xml.newPullParser();
    try
    {
      FileInputStream fIn = mContext.openFileInput("MyMusic.xml");
      InputStreamReader isr = new InputStreamReader(fIn);

      // auto-detect the encoding from the stream
      parser.setInput(isr);
      int eventType = parser.getEventType();
      Song currentSong = null;
      boolean done = false;

      while (eventType != XmlPullParser.END_DOCUMENT && !done)
      {
        String name = null;
        switch (eventType)
        {
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
              }
              else if (name.equalsIgnoreCase(Id3.FILE_NAME.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setFileName(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.FILE_LOC.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setLocation(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.SONG_NAME.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setSongName(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.ARTIST.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setArtist(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.ALBUM_ARTIST.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setAlbumArtist(currentAttribute);
              }
  
              else if (name.equalsIgnoreCase(Id3.ALBUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setAlbum(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.COMPOSER.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setComposer(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.GENRE.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setGenre(currentAttribute);
              }
              else if (name.equalsIgnoreCase(Id3.YEAR.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setYear(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.TRACK_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackNum(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.TOTAL_TRACKS.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setTrackTotal(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.DISC_NUM.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setDiscNum(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.TOTAL_DISCS.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setDiscTotal(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.EXPLICIT.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setExplicit(Boolean.parseBoolean(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.BITRATE.toString())) {
                currentAttribute = parser.getAttributeValue(0);
                currentSong.setBitrate(Integer.parseInt(currentAttribute));
              }
              else if (name.equalsIgnoreCase(Id3.MISSING.toString())) {
                // TODO PARSE further?
                String[] missingData = currentAttribute.split(",");
                currentSong.setMissingData(missingData);
              }
            }
            break;

          case XmlPullParser.END_TAG:
            name = parser.getName();
  
            if (name.equalsIgnoreCase(Id3.SONG_FIRST.toString()) && currentSong != null)
            {
              // add to list at closing song tag
              mSongList.add(currentSong);
            }
            else if (name.equalsIgnoreCase("MyMusicList"))
            {
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
  public void getUpdatedSongList()
  {
    _parseMyMusicFile();
  }
    
  // Edit real file and MyMusic Id3 data
  public void editId3Data(ArrayList<Song> songList, ArrayList<Song> dataList)
  {
    mUndoList.setUndoList(songList, FileOp.EDITID3);
    /*
    // TODO open MyMusicXml
    
    for (int x = 0; x < songList.size(); ++x)
    {
      String oldName = songList.get(x).getFileName();
      String newName = dataList.get(x);
      if (File.fileName != newName && newName != "")
      {
        realFileName.data = newName;
        
        MyMusicXML.updateEntry;
      }
    }
    */
  }
  
  // Edit real file and MyMusic file name
  public void renameFile(ArrayList<Song> songList, ArrayList<String> dataList)
  {
    mUndoList.setUndoList(songList, FileOp.RENAME);
    /* TODO open MyMusicXml
    
    for (int x = 0; x < songList.size(); ++x)
    {
      open realFile
      realFileName = name
      MyMusicXML.updateFileName;
    }
    */
  }
  
  // Edit real file and MyMusic file location
  public void moveFile(ArrayList<Song> songList, ArrayList<String> dataList)
  {
    mUndoList.setUndoList(songList, FileOp.MOVE);
    /*
    // TODO open MyMusicXml
    
    for (int x = 0; x < songList.size(); ++x)
    {
      open File
        realFileName = name
        MyMusicXML.updateFileName;
    }
    */
  }
  
  // Restore real file and MyMusic from Undo list
  public void restoreFromUndoList()
  {
    //mUndoList.getUndoList();
    
    /*
    switch (mUndoList.getFileOp())
    {
      case RENAME:
        for (int x = 0; x < songList.size(); ++x)
          _renameFile(songList.get(x), dataList.get(x));
        break;
        
      case EDITID3:
        for (int x = 0; x < songList.size(); ++x)
          _editId3Data(songList.get(x), dataList.get(x));
        break;
        
      case MOVE:
        for (int x = 0; x < songList.size(); ++x)
          _moveFile(songList.get(x), dataList.get(x));
        break;
        
      default:
        // TODO Throw error
        break;
    }
    */
  }
/*
  public void updateSettings(Map<String, ?> values)
  {
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    
    Editor prefsEditor = prefsFile.edit();
    prefsEditor.putString(Settings.SORT_TYPE.toString(),     values.get(Settings.SORT_TYPE.toString() ) );
    prefsEditor.putString(Settings.SORT_ORDER.toString(),    values.get(Settings.SORT_ORDER.toString() ) );
    prefsEditor.putBoolean(Settings.MULTI_SELECT.toString(), values.get(Settings.MULTI_SELECT.toString() ) );
    prefsEditor.putBoolean(Settings.SHOW_DETAILS.toString(), values.get(Settings.SHOW_DETAILS.toString() ) );
    prefsEditor.putBoolean(Settings.IS_SCANNED.toString(),   values.get(Settings.IS_SCANNED.toString() ) );
    
    prefsEditor.commit();
    // TODO should this be closed????
  }*/
  
  public Map<String, ?> getSettings()
  {
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    return prefsFile.getAll();
  }
  
  private boolean _validateId3(String id3)
  {
    return !(id3 == null || id3 == "" || id3 == " ");
  }
}