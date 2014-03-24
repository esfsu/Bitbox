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
  private ArrayList<Song> songEditList;
  private FileOp mOperation;
  
  // TODO THIS IS NOT OPTIMIZED
  // Currently sets EVERYTHING, not only selected operations
  public void setUndoList(ArrayList<Song> dataList, FileOp op)
  {
    songEditList = dataList;
    setOperation(op);
  }
    
  public ArrayList<Song> getUndoList(ArrayList<Song> songList)
  {
    return songEditList;
  }
  
  public void setOperation(FileOp op)
  {
    mOperation = op;
  }
  
  public FileOp getOperation()
  {
    return mOperation;
  }
}