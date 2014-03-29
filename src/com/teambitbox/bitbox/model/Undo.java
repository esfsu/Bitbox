package com.teambitbox.bitbox.model;

/*
* Undo
* Class for holding undo button data
* 3/2/2014
* Eric Saunders
*/

import java.util.ArrayList;


public class Undo
{
  private ArrayList<Song> mSongList;
  private ArrayList<Id3Data> mDataList;
  private String mDataValue;
  private FileOp mOperation;
  
  // Set undo list (for Id3 tags)
  public void setUndoList(ArrayList<Song> songs, ArrayList<Id3Data> data, FileOp op)
  {
    mSongList  = songs;
    mDataList  = data;
    mOperation = op;
    mDataValue = "";
  }

  // Set undo list (for rename and move, since there is only 1 data point)
  public void setUndoList(ArrayList<Song> songs, String data, FileOp op)
  {
    mSongList  = songs;
    mDataValue = data;
    mOperation = op;
    mDataList.clear();
  }
    
  public ArrayList<Song> getUndoSongList()
  {
    return mSongList;
  }
  
  public ArrayList<Id3Data> getUndoDataList()
  {
    return mDataList;
  }
  
  public String getUndoDataValue()
  {
    return mDataValue;
  }
  
  public FileOp getOperation()
  {
    return mOperation;
  }
}