/*
 * Scanner
 * A class to scan for music files on the mobile device
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.id3.*;

public class Scanner {
  
  // Member variables
  ArrayList<Song> mSongList = new ArrayList<Song>();
  private FileEditor mEditor;
  private int mTotalFiles = 0;
  private int mNumFilesScanned = 0;
  private int mNumMusicFiles = 0;
  private boolean mIsScanComplete = false;
  
  
  // Constructor
  public Scanner(Context context)
  {
    mEditor = new FileEditor(context);
  }
  
  // Scans the device for media files, gets data, calls FileEditor to 
  // create persistent file.
  public ArrayList<Song> scanDevice(String location) {
    try {
      
      // reset our scanning members 
      mTotalFiles = 0;
      mNumFilesScanned = 0;
      mNumMusicFiles = 0;
      mIsScanComplete = false;
      
      // default directory if none is provided
      File songDirectory = new File("/storage/sdcard0/" + Environment.DIRECTORY_MUSIC);
      if (!(location == null) && !(location == ""))
      {
        songDirectory = new File(location);
      }
      Log.d("Scan Location", songDirectory.getAbsolutePath());
      
      // get inital file path
      File[] files = songDirectory.listFiles();
      _getTotalFiles(files);
      Log.d("Total Files", String.valueOf(mTotalFiles));
      
      // reset the file list after the count traversal
      files = songDirectory.listFiles();
      _recursiveScan(files);
      
      Log.d("Tag read complete", "");
      // after scanning, create the persistent copy of the data (file)
      mEditor.createMyMusicFile(mSongList);
      Log.d("mEditor done", "");
      
      mIsScanComplete = true;
      
      return mSongList;
      
    } catch (Exception e) {
      // if any error occurs
      Log.e("File open error", "Location doesn't exist.");
      e.printStackTrace();
      
      mTotalFiles = mNumFilesScanned = 1;
      mNumMusicFiles = 0;
      mIsScanComplete = true;
      
      return mSongList;
    }
  }
  
  private void _getTotalFiles(File[] files)
  {
    for (File file : files)
    {
      ++mTotalFiles;
      //Log.d("Checking", file.getAbsolutePath());
      if (file.isDirectory() && file.canRead())
      {
        _getTotalFiles(file.listFiles());
      }
    }
  }
  
  // Recursive scan method for all subdirectories
  private void _recursiveScan(File[] files)
  {
    try {
      // for each file in our directory
      for (File file : files)
      {
        ++mNumFilesScanned;
        Log.d("Checking", file.getAbsolutePath());
        if (file.isDirectory() && file.canRead())
        {
          _recursiveScan(file.listFiles());
        }
        else
        { 
          // filter out non-MP3 files
          if (file.getName().contains(".mp3"))
          {
            ++mNumMusicFiles;
            
            MP3File f = (MP3File)AudioFileIO.read(file);
            //AbstractID3v2Tag v2tag  = f.getID3v2Tag();
            ID3v24Tag        v24tag = /*(AbstractID3v2Tag)*/f.getID3v2TagAsv24();
            ID3v1Tag  v1tag  = f.getID3v1Tag();
            
            // temporary object to store songs
            Song tempSong = new Song();
            // temporary object to store id3 information
            String id3Data = "";
            // collector for id3 data that isn't validated
            String missingData = "";
            
            // MP3 Library was not built in a way that would allow tags to be passed into
            // function as generics, so using this clunky duplication of code instead :-(
            if (f.hasID3v2Tag())
            {
              Log.d("V2 Tag", "");
              
              Log.d("File", file.getName());
              tempSong.setFileName(file.getName());
              tempSong.setLocation(file.getParent());
    
              id3Data = (v24tag.hasField(FieldKey.TITLE)) ? v24tag.getFirst(FieldKey.TITLE) : null;
              if (_validateId3(id3Data))
                tempSong.setSongName(id3Data);
              else
                missingData += Id3.SONG_NAME.toString() + ",";
              
              id3Data = (v24tag.hasField(FieldKey.ARTIST)) ? v24tag.getFirst(FieldKey.ARTIST) : null;
              if (_validateId3(id3Data))
                tempSong.setArtist(id3Data);
              else
                missingData += Id3.ARTIST.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.ALBUM_ARTIST)) ? v24tag.getFirst(FieldKey.ALBUM_ARTIST) : null;
              if (_validateId3(id3Data))
                tempSong.setAlbumArtist(id3Data);
              else
                missingData += Id3.ALBUM_ARTIST.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.ALBUM)) ? v24tag.getFirst(FieldKey.ALBUM) : null;
              if (_validateId3(id3Data))
                tempSong.setAlbum(id3Data);
              else
                missingData += Id3.ALBUM.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.COMPOSER)) ? v24tag.getFirst(FieldKey.COMPOSER) : null;
              if (_validateId3(id3Data))
                tempSong.setComposer(id3Data);
              else
                missingData += Id3.COMPOSER.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.GENRE)) ? v24tag.getFirst(FieldKey.GENRE) : null;
              if (_validateId3(id3Data)) {
                tempSong.setGenre(id3Data);
              } else
                missingData += Id3.GENRE.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.TRACK)) ? v24tag.getFirst(FieldKey.TRACK) : null;
              if (_validateId3(id3Data))
                tempSong.setTrackNum(id3Data);
              else
                missingData += Id3.TRACK_NUM.toString() + ",";
              
              id3Data = (v24tag.hasField(FieldKey.TRACK_TOTAL)) ? v24tag.getFirst(FieldKey.TRACK_TOTAL) : null;
              if (_validateId3(id3Data))
                tempSong.setTrackTotal(id3Data);
              else
                missingData += Id3.TOTAL_TRACKS.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.DISC_NO)) ? v24tag.getFirst(FieldKey.DISC_NO) : null;
              if (_validateId3(id3Data))
                tempSong.setDiscNum(id3Data);
              else
                missingData += Id3.DISC_NUM.toString() + ",";
    
              id3Data = (v24tag.hasField(FieldKey.YEAR)) ? v24tag.getFirst(FieldKey.YEAR) : null;
              if (_validateId3(id3Data))
                tempSong.setYear(id3Data);
              else
                missingData += Id3.YEAR.toString() + ",";
            }
            else
            {
              // id3V1 has many fewer tags than id3V2, so we set those as missing by default
              Log.d("V1 Tag", "");
              
              Log.d("File", file.getName());
              tempSong.setFileName(file.getName());
              tempSong.setLocation(file.getParent());
    
              id3Data = (v1tag.hasField(FieldKey.TITLE)) ? v1tag.getFirstTitle() : null;
              if (_validateId3(id3Data))
                tempSong.setSongName(id3Data);
              else
                missingData += Id3.SONG_NAME.toString() + ",";
              
              id3Data = (v1tag.hasField(FieldKey.ARTIST)) ? v1tag.getFirstArtist() : null;
              if (_validateId3(id3Data))
                tempSong.setArtist(id3Data);
              else
                missingData += Id3.ARTIST.toString() + ",";
      
              id3Data = (v1tag.hasField(FieldKey.ALBUM)) ? v1tag.getFirstAlbum() : null;
              if (_validateId3(id3Data))
                tempSong.setAlbum(id3Data);
              else
                missingData += Id3.ALBUM.toString() + ",";

              id3Data = (v1tag.hasField(FieldKey.TRACK)) ? v1tag.getFirstTrack() : null;
              if (_validateId3(id3Data))
                tempSong.setTrackNum(id3Data);
              else
                missingData += Id3.TRACK_NUM.toString() + ",";
    
              id3Data = (v1tag.hasField(FieldKey.YEAR)) ? v1tag.getFirstYear() : null;
              id3Data = v1tag.getFirstYear();
              if (_validateId3(id3Data))
                tempSong.setYear(id3Data);
              else
                missingData += Id3.YEAR.toString() + ",";

              id3Data = (v1tag.hasField(FieldKey.GENRE)) ? v1tag.getFirstGenre() : null;
              id3Data = v1tag.getFirstYear();
              if (_validateId3(id3Data))
                tempSong.setYear(id3Data);
              else
                missingData += Id3.GENRE.toString() + ",";
              
              // v1 can't handle this
              missingData += Id3.ALBUM_ARTIST.toString() + ",";
              missingData += Id3.DISC_NUM.toString() + ",";
              missingData += Id3.COMPOSER.toString() + ",";
              
              missingData += Id3.TOTAL_TRACKS.toString() + ",";
            } // end tag versioning
                        
            // turn our missing data String list into an Array
            tempSong.setMissingData(missingData.split(","));
            
            Log.d("Missing", missingData);
            // turn our missing data String list into an Array
            tempSong.setMissingData(missingData.split(","));
            
            // add all of the data to the list
            mSongList.add(tempSong);
            
          } // end file filter
        } // end file lis
      } // end subdirectory check;
     
     } catch (FileNotFoundException e) {
       Log.e("FileNotFoundException", "Couldn't find file.");
       e.printStackTrace();     } catch (Exception e) {       // if any error occurs       Log.e("Tag read error", "Data likely doesn't exist or is parsed incorrectly.");       e.printStackTrace();     }
  }
  
  // make sure we're not getting empty data. If it is, it goes to missingData.
  private boolean _validateId3(String id3) {
    if (id3 == null) Log.d("id3", "null");
    if (id3 != null) Log.d("id3", id3);

    return !(id3 == null || id3 == "null" || id3 == "" || id3 == " ");
  }

  public int getTotalFiles() {return mTotalFiles;}
  public int getTotalMusicFiles() {return mNumMusicFiles;}
  public int getFileProgress() {return mNumFilesScanned;}
  public boolean getIsScanComplete() {return mIsScanComplete;}
}