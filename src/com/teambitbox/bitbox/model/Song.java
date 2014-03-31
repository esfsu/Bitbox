/*
 * Song
 * A class to store music file data
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

import java.lang.String;

public class Song {
  
  // Member variables
  private String mFileName = "";
  private String mSongName = "";
  private String mLocation = "";
  private String mArtist = "";
  private String mAlbumArtist = "";
  private String mAlbum = "";
  private int mDuration = 0;
  private String mComposer = "";
  private Genre mGenre = Genre.GENRE_INVALID;
  private int mYear = 0;
  private int mTrackNum = 0;
  private int mTrackTotal = 0;
  private int mDiscNum = 0;
  private int mDiscTotal = 0;
  private int mBitrate = 0;
  private boolean mIsExplicit = false;
  private String[] mMissingData;


  public String getFileName() {
    return mFileName;
  }

  public void setFileName(String name) {
    mSongName = name;
  }

  public String getSongName() {
    return mSongName;
  }

  public void setSongName(String name) {
    mSongName = name;
  }

  public String getLocation() {
    return mLocation;
  }

  public void setLocation(String location) {
    mLocation = location;
  }

  public String getArtist() {
    return mArtist;
  }

  public void setArtist(String artist) {
    mArtist = artist;
  }

  public String getAlbumArtist() {
    return mAlbumArtist;
  }

  public void setAlbumArtist(String albumArtist) {
    mAlbumArtist = albumArtist;
  }

  public String getAlbum() {
    return mAlbum;
  }

  public void setAlbum(String album) {
    mAlbum = album;
  }

  public int getDuration() {
    return mDuration;
  }

  public void setDuration(int duration) {
    mDuration = duration;
  }

  public String getComposer() {
    return mComposer;
  }

  public void setComposer(String composer) {
    mComposer = composer;
  }

  public Genre getGenre() {
    return mGenre;
  }
  
  public String getGenreString() {
    return mGenre.toString();
  }

  public void setGenre(Genre genre) {
    mGenre = genre;
  }

  public int getYear() {
    return mYear;
  }

  public void setYear(int year) {
    mYear = year;
  }

  public int getBitRate() {
    return mBitrate;
  }

  public void setBitRate(int bitrate) {
    mBitrate = bitrate;
  }

  public int getTrackNum() {
    return mTrackNum;
  }

  public void setTrackNum(int trackNum) {
    mTrackNum = trackNum;
  }

  public int getTrackTotal() {
    return mTrackTotal;
  }

  public void setTrackTotal(int trackTotal) {
    mTrackTotal = trackTotal;
  }

  public int getDiscNum() {
    return mDiscNum;
  }

  public void setDiscNum(int discNum) {
    mDiscNum = discNum;
  }

  public int getDiscTotal() {
    return mDiscTotal;
  }

  public void setDiscTotal(int discTotal) {
    mDiscTotal = discTotal;
  }

  public void setMissingData(String[] missingData) {
    mMissingData = missingData;
  }
  
  public String[] getMissingData()
  {
    return mMissingData;
  }

  public void setExplicit(boolean explicit) {
    mIsExplicit = explicit;
  }
  
  public boolean getExplicit()
  {
    return mIsExplicit;
  }
}
