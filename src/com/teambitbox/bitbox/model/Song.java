
import java.lang.String; 
import Enum;

public class Song
{
    private String name;
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
	
	public Song () {
		
	}
	
	public String GetName() {
		return name;
	}
	
	public void SetName(String n) {
		name = n; 
	}
	
	public String GetLocation() {
		return location;
	}
	
	public void SetLocation(String l) {
		location = l;
	}
	
	public String GetArtist() {
		return artist;
	}
	
	public void SetArtist(String a) {
		artist = a; 
	}
	
	public String GetAlbumArtist() {
		return albumArtist;
	}
	
	public void SetAlbumArtist(String aa) {
		albumArtist = aa;
	}
	
	public String GetAlbum() {
		return album;
	}
	
	public void SetAblum(String al) {
		album = al;
	}
	
	public int GetDuration() {
		return duration;
	}
	
	public void SetDuration(int d) {
		duration = d;
	}
	
	public String GetComposer() {
		return composer;
	}
	
	public void SetComposer(String c) {
		composer = c;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public void SetGenre(Genre g) {
		genre = g; 
	}
	
	public int GetYear() {
		return year;
	}
	
	public void SetYear(int y) {
		year = y; 
	}
	
	public int GetBitRate() {
		return bitrate; 
	}
	
	public void SetBitRate(int b) {
		bitrate = b; 
	}
	
	public int GetTrack() {
		return track;
	}
	
	public void SetTrack(int t) {
		track = t; 
	}
	
	public int GetTrackTotal() {
		return trackTotal;
	}
	
	public void SetTrackTotal(int tt) {
		trackTotal = tt; 
	}
	
	public int GetDisc() {
		return disc; 
	}
	
	public void SetDisc(int d) {
		disc = d;
	}
	
	public int GetDiscTotal() {
		return discTotal;
	}
	
	public void SetDiscTotal(in dt) {
		discTotal = dt; 
	}
	
	public Enum getId3Data() {
		return Id3Option; 
	}
	
	public void SetDataFromFile() {
	}
}




	
