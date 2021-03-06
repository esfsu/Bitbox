/*
 * FileEditor
 * A class to contain all file handling
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Environment;
import android.provider.DocumentsContract.Document;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.util.Xml;

import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

public class FileEditor {
  Context mContext;
  ArrayList<Song> mSongList = new ArrayList<Song>();
  Undo mUndoList = new Undo();

  // private static String DEFAULT_DIR = Environment.DIRECTORY_MUSIC;
  
  public FileEditor(Context context) {
    mContext = context;
  }

  // Called after Scanner
  public void createMyMusicFile(ArrayList<Song> songList) {
    try {
      Log.d("Writing File", "Writing File");
      FileOutputStream fOut = mContext.openFileOutput("MyMusic.xml", Context.MODE_PRIVATE);
      OutputStreamWriter osw = new OutputStreamWriter(fOut);

      String fileContent = "";
      fileContent += "<MyMusic>\n";
      osw.write(fileContent);
      osw.flush();

      for (Song item : songList) {
        fileContent = "";
        fileContent += "  <" + Id3.SONG_FIRST.toString() + ">\n";
        fileContent += "    <" + Id3.FILE_NAME.toString() + " value=\""
            + item.getFileName() + "\" />\n";
        fileContent += "    <" + Id3.FILE_LOC.toString() + " value=\""
            + item.getLocation() + "\" />\n";
        fileContent += "    <" + Id3.SONG_NAME.toString() + " value=\""
            + item.getSongName() + "\" />\n";
        fileContent += "    <" + Id3.ARTIST.toString() + " value=\""
            + item.getArtist() + "\" />\n";
        fileContent += "    <" + Id3.ALBUM.toString() + " value=\""
            + item.getAlbum() + "\" />\n";
        fileContent += "    <" + Id3.ALBUM_ARTIST.toString() + " value=\""
            + item.getAlbumArtist() + "\" />\n";
        fileContent += "    <" + Id3.COMPOSER.toString() + " value=\""
            + item.getComposer() + "\" />\n";
        fileContent += "    <" + Id3.GENRE.toString() + " value=\""
            + item.getGenre() + "\" />\n";
        fileContent += "    <" + Id3.YEAR.toString() + " value=\""
            + item.getYear() + "\" />\n";
        fileContent += "    <" + Id3.TRACK_NUM.toString() + " value=\""
            + item.getTrackNum() + "\" />\n";
        fileContent += "    <" + Id3.TOTAL_TRACKS.toString() + " value=\""
            + item.getTrackTotal() + "\" />\n";
        fileContent += "    <" + Id3.DISC_NUM.toString() + " value=\""
            + item.getDiscNum() + "\" />\n";
        fileContent += "    <" + Id3.MISSING.toString() + " value=\""
            + item.getMissingData() + "\" />\n";
        fileContent += "  <" + Id3.SONG_LAST.toString() + ">\n";

        osw.write(fileContent);
        osw.flush();
      }
      fileContent += "</MyMusic>\n\n";
      osw.write(fileContent);
      osw.flush();
      osw.close();
      fOut.close();
      Log.d("Write Complete", "File writing complete");

      /*
       * Debug only InputStream fIn = mContext.openFileInput("MyMusic.xml");
       * BufferedReader input = new BufferedReader(new InputStreamReader(fIn));
       * Log.d("xml", input.readLine()); input.close(); fIn.close();
       * 
       * _parseMyMusicFile();
       * 
       * Log.d("SongList", String.valueOf(mSongList.size())); for (Song song :
       * mSongList) { Log.d("SongFile", song.getFileName()); }
       */
    } catch (java.io.IOException e) {
      // TODO
      // do something if an IOException occurs.
      Log.e("File Write Error", "File write could not complete");
    }
  }

  // Internally setup the songList
  private void _parseMyMusicFile() {
    Log.d("Parsing File", "parse");
    XmlPullParser parser = Xml.newPullParser();
    try {
      InputStream fIn = mContext.openFileInput("MyMusic.xml");
      InputStreamReader isr = new InputStreamReader(fIn);

      // auto-detect the encoding from the stream
      parser.setInput(isr);
      int eventType = parser.getEventType();
      Song currentSong = null;
      boolean done = false;

      while (eventType != XmlPullParser.END_DOCUMENT && done != true) {
        String name = null;

        switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          Log.d("Start", "START_DOC");
          // move on to the next tag
          break;

        case XmlPullParser.START_TAG:
          name = parser.getName();
          Log.d("Name", name);

          if (name.equalsIgnoreCase("MyMusic")) {
            // create a new list container at the root
            mSongList = new ArrayList<Song>();
            Log.d("Parsing", "New Song List");
          } else if (mSongList != null) {
            // getting attributes
            if (name.equalsIgnoreCase(Id3.SONG_FIRST.toString())) {
              currentSong = new Song();
            } else if (name.equalsIgnoreCase(Id3.FILE_NAME.toString())) {
              currentSong.setFileName(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.FILE_LOC.toString())) {
              currentSong.setLocation(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.SONG_NAME.toString())) {
              currentSong.setSongName(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.ARTIST.toString())) {
              currentSong.setArtist(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.ALBUM_ARTIST.toString())) {
              currentSong.setAlbumArtist(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.ALBUM.toString())) {
              currentSong.setAlbum(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.COMPOSER.toString())) {
              currentSong.setComposer(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.GENRE.toString())) {
              currentSong.setGenre(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.YEAR.toString())) {
              currentSong.setYear(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.TRACK_NUM.toString())) {
              currentSong.setTrackNum(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.TOTAL_TRACKS.toString())) {
              currentSong.setTrackTotal(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.DISC_NUM.toString())) {
              currentSong.setDiscNum(parser.getAttributeValue(0));
            } else if (name.equalsIgnoreCase(Id3.MISSING.toString())) {
              currentSong.setMissingData(parser.getAttributeValue(0));
            }
          } // end getting attributes
          break;

        case XmlPullParser.END_TAG:
          name = parser.getName();

          if (name.equalsIgnoreCase(Id3.SONG_FIRST.toString()) && currentSong != null) {
            Log.d("Adding to List", currentSong.getFileName());
            // add to list at closing song tag
            mSongList.add(currentSong);
          } else if (name.equalsIgnoreCase("MyMusic")) {
            // quit if done
            done = true;
          }
          break;

        default:
          // move on to the next tag
          break;
        }
        eventType = parser.next();
      }
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
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
  public void /* boolean */ editId3Data(ArrayList<Song> songList, ArrayList<Id3Data> dataList) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, dataList, FileOp.EDITID3);

    Log.d("NumSongs", String.valueOf(songList.size()));
    
    try {
      for (Song song : songList) {
        Log.d("Song", song.getFileName()); // debug purposes
        File songFile = new File(song.getLocation() + song.getFileName());
        songFile.setWritable(true);

        MP3File f = (MP3File) AudioFileIO.read(songFile);
        ID3v24Tag v24tag = f.getID3v2TagAsv24();
        
        for (Id3Data data : dataList) {
          Log.d(data.mId3.toString(), data.mValue);

          switch (data.mId3) {
            case SONG_NAME:
              v24tag.setField(FieldKey.TITLE, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case ARTIST:
              v24tag.setField(FieldKey.ARTIST, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case ALBUM_ARTIST:
              v24tag.setField(FieldKey.ALBUM_ARTIST, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case ALBUM:
              v24tag.setField(FieldKey.ALBUM, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case COMPOSER:
              v24tag.setField(FieldKey.COMPOSER, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case GENRE:
              v24tag.setField(FieldKey.GENRE, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case TRACK_NUM:
              v24tag.setField(FieldKey.TRACK, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case TOTAL_TRACKS:
              v24tag.setField(FieldKey.TRACK_TOTAL, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case DISC_NUM:
              v24tag.setField(FieldKey.DISC_NO, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            case YEAR:
              v24tag.setField(FieldKey.YEAR, data.mValue);
              _updateMyMusicFileTag(song.getFileName(), data.mId3, data.mValue);
              break;
  
            default:
              Log.e("Invalid Tag", "An invalid ID3 tag " + data.mId3
                  + " was found during tag update.");
              break;
          }

          f.commit();
        }
        songFile.setWritable(false);        
      }

      //*
      // Debug only
      InputStream fIn = mContext.openFileInput("MyMusic.xml");
      BufferedReader input = new BufferedReader(new InputStreamReader(fIn));
      while (Log.d("xml", input.readLine()) != 0);
      input.close();
      fIn.close();
      //*/

    } catch (NumberFormatException e) {
    } catch (Exception e) {
      // if any other error occurs
      e.printStackTrace();
    }

    // return true;
  }

  // Update attributes in the MyMusic file
  private boolean _updateMyMusicFileTag(String fileName, Id3 id3, String value) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setValidating(false);
      DocumentBuilder db = dbf.newDocumentBuilder();
      org.w3c.dom.Document doc = db.parse(mContext.openFileInput("MyMusic.xml"));

      NodeList keyList = doc.getElementsByTagName(Id3.FILE_NAME.toString());
      int count = 0;
      for (int x = 0; x < keyList.getLength(); x++)
      {
        Node keynode = keyList.item(x);
        Element elm = (Element) keynode; 
        Log.d("elm att", "*"+elm.getAttribute("value")+"*");
        Log.d("file", "*"+fileName+"*");
        if (elm.getAttribute("value").equalsIgnoreCase(fileName))
        {
          count = x;
          Node tag = doc.getElementsByTagName(id3.toString()).item(x);
          tag.setNodeValue(value);
          break;
        }
      }
      Log.d("count", String.valueOf(count));
      
    } catch (Exception e) {
      e.printStackTrace();
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
      Log.d("Rename New", value + song.getFileName());
      Log.d("Rename Old", song.getLocation() + song.getFileName());
      // destination file
      File newFile = new File(value + song.getFileName());
      // File (or directory) to be moved
      File file = new File(song.getLocation() + song.getFileName());
      // Destination directory
      file.renameTo(newFile);

      _updateMyMusicFileTag(song.getFileName(), Id3.FILE_NAME, value);
    }
  }

  // Edit real file and MyMusic file location (same as rename)
  public void moveFile(ArrayList<Song> songList, String value) {
    // make sure we can reverse it
    mUndoList.setUndoList(songList, value, FileOp.RENAME);

    if (value == null || value == "") {
      // set default dir
      value = "/storage/sdcard0/" + Environment.DIRECTORY_MUSIC;
    }

    for (Song song : songList) {
      Log.d("Rename New", value + song.getFileName());
      Log.d("Rename Old", song.getLocation() + song.getFileName());
      // destination file
      File newFile = new File(value + song.getFileName());
      // File (or directory) to be moved
      File file = new File(song.getLocation() + song.getFileName());
      // Destination directory
      file.renameTo(newFile);

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
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings",
        Context.MODE_PRIVATE);

    // set all settings at once
    Editor prefsEditor = prefsFile.edit();
    prefsEditor.putString(Settings.SORT_TYPE.toString(),
        (String) values.get(Settings.SORT_TYPE.toString()));
    prefsEditor.putString(Settings.SORT_ORDER.toString(),
        (String) values.get(Settings.SORT_ORDER.toString()));
    prefsEditor.putBoolean(Settings.MULTI_SELECT.toString(),
        (Boolean) values.get(Settings.MULTI_SELECT.toString()));
    prefsEditor.putBoolean(Settings.SHOW_DETAILS.toString(),
        (Boolean) values.get(Settings.SHOW_DETAILS.toString()));
    prefsEditor.putBoolean(Settings.IS_SCANNED.toString(),
        (Boolean) values.get(Settings.IS_SCANNED.toString()));

    // apply edits to the file
    prefsEditor.commit();
  }

  // Get settings from the Android provided storage area
  public Map<String, ?> getSettings() {
    SharedPreferences prefsFile = mContext.getSharedPreferences("Settings",
        Context.MODE_PRIVATE);
    return prefsFile.getAll();
  }
}