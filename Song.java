import java.io.Serializable;
// Song class with attributes like title, artist and duration
public class Song implements Serializable{
	// instance variables of the song class
	private String title;
	private String artist;
	private double duration; // time in seconds
	
	// constructor 
	public Song(String title, String artist, double duration){
		this.title = title;
		this.artist = artist;
		this.duration = duration;		
	}
	public Song(){
		this.title = null;
		this.artist = null;
		this.duration = 0;
	}
	// getter methods to retrieve the title, artist and durations of the song 
	public String getTitle(){
		return title;
	}
	public String getArtist(){
		return artist;
	}
	public double getDuration(){
		return duration;
	}
	// toString that creates a string for displaying title, duration and artist name. 
	@Override
	public String toString(){
		return artist + ": " + title + " - " + duration + " seconds."; 
	}
	// tester main method to test the song class. 
	public static void main(String args[]){
		Song peddi = new Song("Peddi", "Sauti Sol", 1000);
		System.out.println(peddi);
	}
}