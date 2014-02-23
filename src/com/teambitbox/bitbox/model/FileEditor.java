
import 

class FileEditor
{
  // **TODO** This is the incorrect variable type
  // ideally we need a list of songs paired with the type of edit
  private String[] songList;
  private Genre genre;
  
  public boolean editFile(FileOperation op, String data) {
    return true; // success
  }

  private boolean renameFile(Song song, String name) {
    return true; // success
  }
  
  private boolean moveFile(Song song, String location) {
    return true; // success
  }

  private boolean addToUndoList(FileOperation op, String value) {
    return true; // success
  }

  private boolean setId3Name(Song song, String value) {
    return true; // success
  }

  private boolean setId3Artist(Song song, String value) {
    return true; // success
  }

  private boolean setId3Year(Song song, String value) {
    return true; // success
  }

  private boolean setId3Album(Song song, String value) {
    return true; // success
  }

  private boolean setId3AlbumArtist(Song song, String value) {
    return true; // success
  }

  private boolean setId3Genre(Song song, Genre value) {
    return true; // success
  }

  private boolean setId3Track(Song song, String value) {
    return true; // success
  }

  private boolean setId3TrackTotal(Song song, String value) {
    return true; // success
  }

  private boolean setId3Explicit(Song song, String value) {
    return true; // success
  }

  private boolean setId3Disc(Song song, String value) {
    return true; // success
  }

  private boolean setId3DiscTotal(Song song, String value) {
    return true; // success
  }

}
