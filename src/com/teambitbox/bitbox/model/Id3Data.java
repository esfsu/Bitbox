/*
 * Id3Data
 * A simple class to populate within an ArrayList for editing Id3Data
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

public class Id3Data {
  public Id3 mId3 = Id3.SONG_FIRST;
  public String mValue = "";
  
  public Id3Data()
  {}
  
  public Id3Data(Id3 id3, String value)
  {
    mId3 = id3;
    mValue = value;
  }
  
  public void setId3(Id3 id3) {mId3 = id3;}
  public void setValue(String value) {mValue = value;}  
  public Id3 getId3() {return mId3;}
  public String getValue() {return mValue;}
}
