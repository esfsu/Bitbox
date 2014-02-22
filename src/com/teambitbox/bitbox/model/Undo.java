/*
 * Class for handling all undo activity
 */

public class Undo
{
  // **TODO** This is the incorrect variable type
  // ideally we need a list of songs paired with the type of edit
  private String[] songEditList;
  
  boolean public setUndoList(String[] songList) {
    return 1; //success
  }
  
  boolean public getUndoList(String[] songList) {
    return 1; //success
  }
}