import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;

class FileEditor {
  ArrayList<Song> songList;

  public void parseMyMusicFile() {
    XmlPullParser parser = Xml.newPullParser();
    try {
      FileInputStream fIn = openFileInput("MyMusic.xml");
      InputStreamReader isr = new InputStreamReader(fIn);

      // auto-detect the encoding from the stream
      parser.setInput(isr);
      int eventType = parser.getEventType();
      Song currentSong = null;
      boolean done = false;

      while (eventType != XmlPullParser.END_DOCUMENT && !done) {
        String name = null;
        switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          // move on to the next tag
          break;

        case XmlPullParser.START_TAG:
          name = parser.getName();
          if (name.equalsIgnoreCase("MyMusic")) {
            // create a new list container at the root
            songList = new ArrayList<Song>();
          } else if (songList != null) {
            // getting attributes
            String currentAttribute = "";
            currentSong = new Song();
            if (name.equalsIgnoreCase("song")) {
              currentSong = new Song();
            }

            else if (name.equalsIgnoreCase("filename")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setFilename(currentAttribute);
            }

            else if (name.equalsIgnoreCase("location")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setLocation(currentAttribute);
            }

            else if (name.equalsIgnoreCase("songName")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setSongName(currentAttribute);
            }

            else if (name.equalsIgnoreCase("artist")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setArtist(currentAttribute);
            }

            else if (name.equalsIgnoreCase("albumArtist")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setAlbumArtist(currentAttribute);
            }

            else if (name.equalsIgnoreCase("album")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setAlbum(currentAttribute);
            }

            else if (name.equalsIgnoreCase("composer")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setComposer(currentAttribute);
            }

            else if (name.equalsIgnoreCase("genre")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setGenre(currentAttribute);
            }

            else if (name.equalsIgnoreCase("year")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setYear(currentAttribute);
            }

            else if (name.equalsIgnoreCase("trackNum")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setTrackNum(currentAttribute);
            }

            else if (name.equalsIgnoreCase("totalTracks")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setTotalTracks(currentAttribute);
            }

            else if (name.equalsIgnoreCase("discNum")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setDiscNum(currentAttribute);
            }

            else if (name.equalsIgnoreCase("totalDiscs")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setTotalDiscs(currentAttribute);
            }

            else if (name.equalsIgnoreCase("explicit")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setExplicit(currentAttribute);
            }

            else if (name.equalsIgnoreCase("bitrate")) {
              currentAttribute = parser.getAttributeValue(0);
              currentSong.setBitrate(currentAttribute);
            }

            else if (name.equalsIgnoreCase("missingddata")) {
              // PARSE further
              String[] missingData = currentAttribute.split(",");
              currentSong.setMissingData(missingData);
            }
          }
          break;

        case XmlPullParser.END_TAG:
          name = parser.getName();

          if (name.equalsIgnoreCase("song") && currentSong != null) {
            // add to list at closing song tag
            songList.add(currentSong);
          } else if (name.equalsIgnoreCase("MyMusicList")) {
            // quit if done
            done = true;
          }
          break;
        }
        eventType = parser.next();
      }
    } catch (FileNotFoundException e) {
      // TODO
    } catch (IOException e) {
      // TODO
    } catch (Exception e) {
      // TODO
    }
  }

  // write a line of XML to an open file
  private void _writeXmlToFile(Id3 tag, String value, OutputStreamWriter osw) {
    String fileContent = "";
    switch (tag) {
    case SONG_FIRST:
      fileContent = "  <song>\n";
      break;

    case SONG_LAST:
      fileContent = "  </song>\n";
      break;

    default:
      fileContent = "    <" + tag.toString() + " value=" + value + " />\n";
      break;
    }

    osw.write(fileContent);
  }

  private OutputStreamWriter _openFileForWrite(String fileName) {
    try {
      FileOutputStream fOut = openFileOutput(fileName, MODE_WORLD_READABLE);
      OutputStreamWriter osw = new OutputStreamWriter(fOut);
      
      return osw;
    } catch (FileNotFoundException e) {
      // TODO
    }

    return 0;
  }

  private void _closeFileForWrite(OutputStreamWriter osw) {
    osw.flush();
    osw.close();
  }

  public void editFile(Song song, FileOp op, String data) {
    // TODO
  }

  private boolean _renameFile(Song song, String newName) {
    File oldFile = new File(song.getLocation(), song.getFileName());
    File newFile = new File(song.getLocation(), newName);
    
    boolean returnVal = oldFile.renameTo(newFile);
    if (returnVal)
    {
      song.setFileName(newName);
    }
    
    return returnVal;
  }

  private boolean _moveFile(Song song, String newLoc) {
    File oldFile = new File(song.getLocation(), song.getFileName());
    File newFile = new File(newLoc, song.getFileName());
    
    boolean returnVal = oldFile.renameTo(newFile);
    if (returnVal)
    {
      song.setLocation(newLoc);
    }
    
    return returnVal;
  }

  private void _setUndoList(ArrayList<Song> undoList) {
    // must have access to existing persistent Undo item.
    // access object, set it.

  }

  private void _setSongName(Song song, String value) {
    song.setSongName(value);
  }

  private void _setArtist(Song song, String value) {
    song.setArtist(value);
  }

  private void _setAlbum(Song song, String value) {
    song.setAlbum(value);
  }

  private void _setAlbumArtist(Song song, String value) {
    song.setAlbumArtist(value);
  }

  private void _setGenre(Song song, Genre value) {
    song.setGenre(value);
  }

  private void _setYear(Song song, String value) {
    song.setYear(value);
  }

  private void _setTrack(Song song, String value) {
    song.setTrack(value);
  }

  private void _setTrackTotal(Song song, String value) {
    song.setTrackTotal(value);
  }

  private void _setExplicit(Song song, String value) {
    song.setExplicit(value);
  }

  private void _setDisc(Song song, String value) {
    song.setDisc(value);
  }

  private void _setDiscTotal(Song song, String value) {
    song.setDiscTotal(value);
  }
}
