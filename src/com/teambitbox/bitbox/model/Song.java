package com.teambitbox.bitbox.model;

import java.lang.String;

public class Song {
  private String fileName;
  private String songName;
  private String location;
  private String artist;
  private String albumArtist;
  private String album;
  private int duration;
  private String composer;
  private Genre genre;
  private int year;
  private int bitrate;
  private int track;
  private int trackTotal;
  private int disc;
  private int discTotal;
  private int mBitrate;
  private boolean isExplicit;
  private String[] missingData;

  public Song() {

  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String n) {
    songName = n;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String n) {
    songName = n;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String l) {
    location = l;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String a) {
    artist = a;
  }

  public String getAlbumArtist() {
    return albumArtist;
  }

  public void setAlbumArtist(String aa) {
    albumArtist = aa;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String al) {
    album = al;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int d) {
    duration = d;
  }

  public String getComposer() {
    return composer;
  }

  public void setComposer(String c) {
    composer = c;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre g) {
    genre = g;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int y) {
    year = y;
  }

  public int getBitRate() {
    return bitrate;
  }

  public void setBitRate(int b) {
    bitrate = b;
  }

  public int getTrackNum() {
    return track;
  }

  public void setTrackNum(int t) {
    track = t;
  }

  public int getTrackTotal() {
    return trackTotal;
  }

  public void setTrackTotal(int tt) {
    trackTotal = tt;
  }

  public int getDiscNum() {
    return disc;
  }

  public void setDiscNum(int d) {
    disc = d;
  }

  public int getDiscTotal() {
    return discTotal;
  }

  public void setDiscTotal(int dt) {
    discTotal = dt;
  }

  public void setMissingData(String[] md) {
    missingData = md;
  }
  
  public String[] getMissingData()
  {
    return missingData;
  }

  public void setExplicit(boolean e) {
    isExplicit = e;
  }
  
  public boolean getExplicit()
  {
    return isExplicit;
  }
  
  public void setBitrate(int bitrate)
  {
    mBitrate = bitrate;
  }
}
