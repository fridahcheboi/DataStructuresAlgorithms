/**
* Enhanced playlist class implemented using doubly linked list to store song objects
*
* @ cheboi
*/

import java.util.*; // linkedlist and Math.random() imported


class EnhancedPlaylist{
	private int size = 0;
	private DoubleNode head = null; 
	private DoubleNode tail = null;
	private DoubleNode current = null;
	
	// check if playlist is empty
	public boolean isEmpty(){
		return (size==0);
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
				tail = newNode;
				current = newNode;
			}
			else{
				newNode.next = head;
				head.previous = newNode;
				head = newNode;
			}
			size++;
		}
		
		// add song at end of playlist 
		else if (position == size+1){
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
			size++;
		}
		// insert at middle somewhere
		else{
			DoubleNode temp = head;
			for (int i=1; i<position-1;i++){
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
			if (head == tail){
				head = null;
				tail = null;
				current = null;
			}
			else{
				head = head.next;
				head.previous = null;
				if (current == head.previous){
					current = head;
				}			
			}
		}
		
		// delete song at the end
		else if (position == size){
			tail = tail.previous;
			tail.next = null;
			if (current == tail.next){
				current = tail;
			}
		}
		
		//delete song somewhere in the middle
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
	 
	// THIS NAVIGATION IS FOR NORMAL PLAY MODE
	/** 
	* Method that plays the next song 
	* this is a getter method that will retrieve the next song
	*
	* @return Song object of the next song to play
	*/
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
	
	/** 
	* Method that plays the current song 
	* this is a getter method that will retrieve the current song
	*
	* @return Song object of the current song that's playing 
	*/
	public Song getCurrentSong(){
		if (isEmpty()){
			System.out.println("The playlist is empty");
			return null;
		}
		else 
			return current.song;
	}
	
	/** 
	* Method that plays a song that user wants
	* 
	* @param position of song the user wants
	* @return Song object of the current song that's playing 
	*/
	public Song getUserRequest(int position){
		DoubleNode temp = head;
		if (position < 1 || position > size+1){
			System.out.println("Please provide a valid position.");
			return null;
		}
		for (int i=1; i<position;i++){
			temp = temp.next;
		}
		current = temp;
		return current.song;
	}
	
	/** 
	* Method that plays the previous song 
	*
	* @return Song object of the previous song that will play 
	*/
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
	
	/** 
	* Method shuffles the playlist
	*/
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
	
	/** 
	* Method to calculate total duration of playlist
	*
	* @return sum - double value of totalDuration
	*/
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
	
	/** 
	* Tester main method to demostrate operations: 
	1. Play next song
	2. Play previous song
	3. Shuffling the playlist
	*/
	public static void main(String[] args){
		
		System.out.println("\nWelcome to the Normal Play Mode !!!\nThis is an enhanced playlist implemented using a double linked list");
		EnhancedPlaylist playlist2 = new EnhancedPlaylist();
		System.out.println("The playlist is created");
		playlist2.display();
		
		Song song1 = new Song("Love Is Wicked", "Brick & Lace", 203.4);
		Song song2 = new Song("Miss Independent", "Ne-Yo", 268.2);
		Song song3 = new Song("Give It Up To Me", "Sean Paul", 262.2);
		Song song4 = new Song("Melanin","Sauti Sol ft Patoranking", 261);
		Song song5 = new Song("Particula", "Major Lazer & DJ Maphorisa", 199.2);
		Song song6 = new Song("Dilemma", "Nelly", 264);
		Song song7 = new Song("No Scrubs", "TLC", 245.4);
		Song song8 = new Song("If it's Lovin' That You Want","Rihanna", 201.6);
		
		
		
		playlist2.insert(song1,1);
		playlist2.insert(song2,2);
		playlist2.insert(song3,3);
		playlist2.insert(song4,4);
		playlist2.insert(song5,5);
		playlist2.insert(song6,6);
		playlist2.insert(song7,7);
		playlist2.insert(song8,8);
		System.out.println("\nThis playlist now has 8 songs: ");
		playlist2.display();
		
		
		while (true){
			System.out.println("\n\nChoose an operation: ");
			System.out.println("0. Exit application \n1. Play playlist \n2. Choose song to play by position \n3. Play next song \n4. Play previous song \n5. Shuffle play list \n6. Get total duration of playlist \n7. Delete a song from playlist by position \n8. Delete song from playlist by title");
			Scanner keyboard = new Scanner(System.in);
			
			int input = keyboard.nextInt();
			if (input == 0)
				System.exit(0);
			else if(input == 1){
				System.out.print("Now Playing: " + playlist2.getCurrentSong());							
			}
			else if(input == 2){
				System.out.print("Choose the song to be played: ");
				int pos = keyboard.nextInt();
				System.out.print("Now Playing: " + playlist2.getUserRequest(pos));
			}
			else if(input == 3){
				System.out.print("Now Playing: " + playlist2.getNextSong());	
			}
			else if(input == 4){
				System.out.print("Now Playing: " + playlist2.getPreviousSong());	
			}
			else if(input == 5){
				System.out.println("Shuffled Playlist: ");
				playlist2.shufflePlaylist();
				System.out.println();
				playlist2.display();
			}
			else if(input == 6){
				System.out.println("Total Duration of playlist is: " + playlist2.totalDuration());
			}
			else if(input == 7){
				System.out.print("Choose the song to be deleted: ");
				int pos = keyboard.nextInt();
				playlist2.delete(pos);
				System.out.println();
				playlist2.display();
			}
			else if(input == 8){
				keyboard.nextLine();
				System.out.print("Choose the song to be removed: ");
				String title = keyboard.nextLine();
				playlist2.remove(title);
				System.out.println();
				playlist2.display();
			}
			else
				System.out.println("Invalid command. Please enter a valid command.");
		}	
	}
}