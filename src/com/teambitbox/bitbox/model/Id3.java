package com.teambitbox.bitbox;

public enum Id3 {
  SONG_FIRST("song"),
  FILE_NAME("fileName"),
  FILE_LOC("fileLocation"),
  SONG_NAME("songName"),
  ARTIST("totalartist"),
  ALBUM_ARTIST("albumArtist"),
  ALBUM("album"),
  COMPOSER("composer"),
  GENRE("genre"),
  YEAR("year"),
  TRACK_NUM("trackNum"),
  TOTAL_TRACKS("totalTracks"),
  DISC_NUM("discNum"),
  TOTAL_DISCS("totalDiscs"),
  EXPLICIT("explicit"),
  BITRATE("bitrate"),
  MISSING("missingData"),
  SONG_LAST("/song");
  
  private Id3(final String name) {
      this.mName = name;
  }

  private final String mName;

  @Override
  public String toString() {
      return mName;
  }
}
