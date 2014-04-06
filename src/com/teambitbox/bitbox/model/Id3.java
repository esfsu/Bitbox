/*
 * Id3
 * An enum class to provided consistent references to Id3 tags,
 * as well as used for creating the MyMusic.xml file tags.
 * 3/30/2014
 * Eric Saunders
 */

package com.teambitbox.bitbox.model;

public enum Id3 {
  SONG_FIRST("song"),
  FILE_NAME("fileName"),
  FILE_LOC("fileLocation"),
  SONG_NAME("songName"),
  ARTIST("artist"),
  ALBUM_ARTIST("albumArtist"),
  ALBUM("album"),
  COMPOSER("composer"),
  GENRE("genre"),
  YEAR("year"),
  TRACK_NUM("trackNum"),
  TOTAL_TRACKS("totalTracks"),
  DISC_NUM("discNum"),
  TOTAL_DISCS("totalDiscs"),
  MISSING("missingData"),
  SONG_LAST("/song");
  
  private Id3(final String name) {
      mName = name;
  }

  private final String mName;

  @Override
  public String toString() {
      return mName;
  }
}
