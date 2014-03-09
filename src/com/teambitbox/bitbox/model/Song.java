import java.lang.String;
import Enum;

public class Song {
  private String fileName;
  private String songName;
  private String location;
  private String artist;
  private String albumArtist;
  private String album;
  private int duration;
  private String composer;
  private Enum genre;
  private int year;
  private int bitrate;
  private int track;
  private int trackTotal;
  private int disc;
  private int discTotal;

  public Song() {

  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String n) {
    name = n;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String n) {
    name = n;
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

  public void setAblum(String al) {
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

  public int getTrack() {
    return track;
  }

  public void setTrack(int t) {
    track = t;
  }

  public int getTrackTotal() {
    return trackTotal;
  }

  public void setTrackTotal(int tt) {
    trackTotal = tt;
  }

  public int getDisc() {
    return disc;
  }

  public void setDisc(int d) {
    disc = d;
  }

  public int getDiscTotal() {
    return discTotal;
  }

  public void setDiscTotal(in dt) {
    discTotal = dt;
  }

  public Enum getId3Data() {
    return Id3Option;
  }

  public void setMissingData() {
  }

  public void setExplicit(String e) {
    // TODO Auto-generated method stub

  }

  public void setMissingData(String[] missingData) {
    // TODO Auto-generated method stub

  }
}
