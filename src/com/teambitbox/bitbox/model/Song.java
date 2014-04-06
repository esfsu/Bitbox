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
  private String mComposer = "";
  private String mGenre = Genre.GENRE_INVALID.toString();
  private String mYear = "";
  private String mTrackNum = "";
  private String mTrackTotal = "";
  private String mDiscNum = "";
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

  public String getComposer() {
    return mComposer;
  }

  public void setComposer(String composer) {
    mComposer = composer;
  }

  public String getGenre() {
    return mGenre;
  }
  
  public String getGenreString() {
    return mGenre.toString();
  }

  public void setGenre(String genre) {
    mGenre = genre;
  }

  public String getYear() {
    return mYear;
  }

  public void setYear(String year) {
    mYear = year;
  }

  public String getTrackNum() {
    return mTrackNum;
  }

  public void setTrackNum(String trackNum) {
    mTrackNum = trackNum;
  }

  public String getTrackTotal() {
    return mTrackTotal;
  }

  public void setTrackTotal(String trackTotal) {
    mTrackTotal = trackTotal;
  }

  public String getDiscNum() {
    return mDiscNum;
  }

  public void setDiscNum(String discNum) {
    mDiscNum = discNum;
  }
 
  public void setMissingData(String[] missingData) {
    mMissingData = missingData;
  }
  
  public String[] getMissingData()
  {
    return mMissingData;
  }
}
