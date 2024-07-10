/**
* Fully functional  playlist class implemented using circularly linked list to store song objects
* 
* @ cheboi
*/

import java.util.*;
import java.io.Serializable;

class FullyFunctionalPlaylist implements Serializable{
	private int size = 0;
	private DoubleNode head = null; 
	private DoubleNode tail = null;
	private DoubleNode current = null;
	
	// check if playlist is empty
	public boolean isEmpty(){
			return (size == 0);
	}
	
	/** 
	* Method to add song at specific position
	*
	* @param element - new song object to be added
	* @param position - where new element is to be added
	*/
	public void insert(Song element, int position){
		DoubleNode newNode = new DoubleNode(element);
		if (position < 1 || position > size+1)
			System.out.println("Please provide a valid position.");	
		
		// insert at beginning
		else if (position == 1){
			if (isEmpty()){
				head = newNode;
				head.next = head.previous = head;
				current = newNode;
				
			}
			else{
				tail = head.previous;
				newNode.next = head;
				head.previous = newNode;
				newNode.previous = tail;
				tail.next = newNode;
				head = newNode;
			}
			size++;
		}
		
		// add song at end of playlist 
		else if (position == size+1){
			tail = head.previous;
			tail.next = newNode;
			newNode.previous = tail;
			newNode.next = head;
			head.previous = newNode;
			tail = newNode;
			size++;
		}
		// insert at middle somewhere -- this part is similar to that of the double linked list
		else{
			DoubleNode temp = head;
			for (int i=1; i<position;i++){
				temp = temp.next;				
			}
			newNode.next = temp.next;
			newNode.previous = temp;
			temp.next.previous = newNode;
			temp.next = newNode;
			size++;
		}
	}
	
	/** 
	* Method to remove song by position
	*
	* @param position - where element is to be removed
	*/
	public void delete(int position){
		DoubleNode n = null; // this variable holds the node to be deleted. 
		if (position < 1 || position > size+1){
			System.out.println("Please provide a valid position.");	
			return;
		}
		if (isEmpty()){
			System.out.println("The playlist is empty");
			return;
		}
		// delete song at the beginning
		else if (position == 1){
			n = head;
			// this is when there is only one node in the linked list
			if (head == tail){
				head = null;
				tail = null;
				current = null;
			}
			// this is when there is more than one node in the list
			else{
				head = head.next;
				head.previous = tail;
				tail.next = head;
				if (current == n){
					current = head; // if the first song was playing and is deleted, then the current song plaing will be updated to the next song. 
				}			
			}
		}
		
		// delete song at the end 
		else if (position == size){
			n = tail;
			tail = tail.previous;
			tail.next = head;
			head.previous = tail;
			if (current == n){
				current = tail; // if current song was playing and is deleted, then the previous song will be updated to current
			}
		}
		
		//delete song somewhere in the middle -- this part remains similar to that of a double linked list. 
		else {
			DoubleNode temp = head;
			for (int i=1; i<position;i++){
				temp = temp.next;
			}
			temp.previous.next = temp.next;
            if (temp.next != null) {
                temp.next.previous = temp.previous;
            }
            if (current == temp) {
                current = temp.next;
            }		
		}
		size--; 
	}
		
	/** 
	* Method to remove song by title
	*
	* @param title - of element to be removed
	*/
	public void remove(String title){
		DoubleNode temp = head;
		int count = size;
		if (isEmpty()){
			System.out.println("The playlist is empty");
			return;
		}
		for (int i=1; i<=size;i++){
			if (temp.song.getTitle().equals(title)){
				delete(i);
				return;
			}
			temp = temp.next;
		}
		if (count == size)
			System.out.println("The song given is not in the playlist");
	}
	
	
	// THIS NAVIGATION IS FOR CONTINUOUS PLAY: REPEAT PLAYLIST.
	/** 
	* Method that plays the next song 
	* this is a getter method that will retrieve the next song
	*
	* @return Song object of the next song to play
	*/
	public Song getContinuousNextSong(){
		if (current == null){
			System.out.println("The playlist is empty");
			return null;
		}
		else{
			current = current.next;
			return current.song;
		}
	}
	
	/** 
	* Method that plays the previous song 
	*
	* @return Song object of the previous song that will play 
	*/
	public Song getContinuousPreviousSong(){
		if (current == null){
			System.out.println("The playlist is empty. No more songs to play");
			return null;
		}
		else{
			current = current.previous;
			return current.song;
		}
	}
	// THIS NAVIGATION IS FOR NORMAL PLAY MODE: similar to those found in the enhanced playlist
	// method that plays the next song 
	// this is a getter method that will retrieve the next song
	public Song getNextSong(){
		if (current == null){
			System.out.println("The playlist is empty");
			return null;
		}
		// if current song is last in play list
		if (current == tail){
			System.out.println("You have reached the end of the playlist. No more songs to play");
			return current.song;
		}
		else{
			current = current.next;
			return current.song;
		}
	}
	public Song getCurrentSong(){
		if (isEmpty()){
			System.out.println("The playlist is empty");
			return null;
		}
		else 
			return current.song;
	}
	public Song getUserRequest(int position){
		DoubleNode temp = head;
		if (position < 1 || position > size+1){
			System.out.println("Please provide a valid position.");
			return null;
		}
		if (position == 1){
			current = head;
			return current.song;
		}
		for (int i=1; i<position;i++){
			temp = temp.next;
		}
		current = temp;
		return current.song;
	} 
	
	// Search method to find songs by title  
	public Song getSongbyTitle(String title){
		DoubleNode temp = head;
		for (int i=1;i<=size;i++){
			if (temp.song.getTitle().equals(title)){
				current = temp;
				return current.song;
			}
			temp = temp.next;
		}
		System.out.println("The song given is not in the playlist");
		return null; 
	}
	
	//method that plays the pervious song
	// this is another getter method that will retrieve the previous song. 
	public Song getPreviousSong(){
		if (current == null){
			System.out.println("The playlist is empty. There are no songs to play");
			return null;
		}
		// if current song is first in play list
		if (current == head){
			System.out.print("This is the first song in playlist. There is no previous song");
			return current.song;
		}
		else{
			current = current.previous;
			return current.song;
		}
	}
	
	// method that shuffles the playlist
	public void shufflePlaylist(){
		if (isEmpty()){
			System.out.println("The playlist is empty");
			return;
		}
		DoubleNode temp = head;
		ArrayList<Song> playlist = new ArrayList<Song>();
		
		// convert double linked list to array for easy handling
		int count = 1;
		while (count<=size){
			playlist.add(temp.song);
			temp = temp.next;
			count++;
		}
		
		// new shuffled array
		Collections.shuffle(playlist);
		temp = head;
		// return shuffled array into a doubly linked list
		for (int i=0;i<size;i++){
			temp.song = playlist.get(i);
			temp = temp.next;
		}	
	}
	// display playlist in order
	public void display(){
		DoubleNode temp = head;
		int count = 1;
		if (isEmpty())
			System.out.println("The playlist is empty");
		else
			while (count <= size){
				System.out.println(count + ". " + temp.song);
				temp = temp.next;
				count++;
			}
	}
	
	// calculate total duration of playlist
	public double totalDuration(){
		DoubleNode temp = head;
		double sum = 0;
		int count = 1;
		while (count <= size){
			sum = sum + temp.song.getDuration();
			temp = temp.next;
			count++;
		}
		return sum;
	}
	
	// tester main method to demostrate method operations
	public static void main(String[] args){
		System.out.println("\nWelcome to the Continuous Play Mode !!!\nThis is a fully functional playlist implemented using a circularly linked list");
		FullyFunctionalPlaylist playlist3 = new FullyFunctionalPlaylist();
				
		Song[] songs = {
			new Song("Wadibusa", "Uncle Waffles ft. Ohp Sage", 309),
			new Song("Sofa Silahlane", "Wanitwa Mos, Master KG & Lowsheen", 258),
			new Song("Isgubhu", "Sam Deep, Njelic, Aymos", 240.6),
			new Song("Nkao Tempela","Ch'cco, Mellow & Sleazy", 309),
			new Song("Mafikizolo", "Ngeke Balunge", 268.8),
			new Song("You're The One", "Elaine",201.6),
			new Song("Fatela", "Aymos & Ami Faku", 259.8),
			new Song("Umqombothi", "Yvonne Chaka Chaka", 330.6),
			new Song("Love Is Wicked", "Brick & Lace", 203.4),
			new Song("Miss Independent", "Ne-Yo", 268.2),
			new Song("Give It Up To Me", "Sean Paul", 262.2),
			new Song("Melanin","Sauti Sol ft Patoranking", 261),	
			new Song("Particula", "Major Lazer & DJ Maphorisa", 199.2),
			new Song("Dilemma", "Nelly", 264),
			new Song("No Scrubs", "TLC", 245.4),
			new Song("If it's Lovin' That You Want","Rihanna", 201.6)
		};
		int count = 1;
		for (Song song: songs){
			playlist3.insert(song, count);
			count ++;
		}
		
		System.out.println("\nThis playlist now has 16 songs: ");
		playlist3.display();
		
		while (true){
			System.out.println("\n\nChoose an operation to perform: ");
			System.out.println("0. Exit application \n1. Play playlist \n2. Choose song to play by position \n3. Choose song to play by title \n4. Play next song \n5. Play previous song \n6. Shuffle play list \n7. Get total duration of playlist \n8. Delete a song from playlist by position \n9. Delete song from playlist by title");
			Scanner keyboard = new Scanner(System.in);
				
			int input = keyboard.nextInt();
			if (input == 0)
				System.exit(0);
			else if(input == 1){
				System.out.print("Now Playing: " + playlist3.getCurrentSong());							
			}
			else if(input == 2){
				System.out.print("Choose the song to be played by position: ");
				int pos = keyboard.nextInt();
				System.out.print("Now Playing: " + playlist3.getUserRequest(pos));
			}
			else if(input == 3){
				keyboard.nextLine();
				System.out.print("Choose the song to be played by title: ");
				String title = keyboard.nextLine();
				System.out.print("Now Playing: " + playlist3.getSongbyTitle(title));				
			}
			else if(input == 4){
				System.out.print("Now Playing: " + playlist3.getContinuousNextSong());	
			}
			else if(input == 5){
				System.out.print("Now Playing: " + playlist3.getContinuousPreviousSong());	
			}
			else if(input == 6){
				System.out.println("Shuffled Playlist: ");
				playlist3.shufflePlaylist();
				System.out.println();
				playlist3.display();
			}
			else if(input == 7){
				System.out.println("Total Duration of playlist is: " + playlist3.totalDuration() + " seconds.");
			}
			else if(input == 8){
				System.out.print("Choose the song to be deleted: ");
				int pos = keyboard.nextInt();
				playlist3.delete(pos);
				System.out.println();
				playlist3.display();
			}
			else if(input == 9){
				keyboard.nextLine();
				System.out.print("Choose the song to be removed: ");
				String title = keyboard.nextLine();
				playlist3.remove(title);
				System.out.println();
				playlist3.display();
			}
			else
				System.out.println("Invalid command. Please enter a valid command.");	
		}
		
	}
}