/**
* Basic playlist class implemented using singly linked list to store song objects
*
* @ cheboi
*/
class BasicPlaylist{
	private int size = 0;
	private BasicNode head = null; 
	
	// check if playlist is empty
	public boolean isEmpty(){
		return (size==0);
	}
	
	/** 
	* Method to add song at end of playlist 
	*
	* @param element - new song object to be added 
	*/
	public void insertAtEnd(Song element){ 
		BasicNode newBasicNode = new BasicNode(element);
		BasicNode temp = head;
		if (isEmpty()){
			head = newBasicNode;
			size++;
		}
		else{
			while (temp.next != null){
				temp = temp.next;
			}
			temp.next = newBasicNode;
			size++;
		}
	}
	
	/** 
	* Method to add song at specific position
	*
	* @param element - new song object to be added
	* @param position - where new element is to be added
	*/
	public void insert(Song element, int position){
		BasicNode newBasicNode = new BasicNode(element);
		if (position < 1 || position > size+1)
			System.out.println("Please provide a valid position.");	
		else if (position == 1){
			newBasicNode.next = head;
			head = newBasicNode;
			size++;
		}
		else{
			BasicNode temp = head;
			for (int i=1; i<position-1;i++){
				temp = temp.next;				
			}
			newBasicNode.next = temp.next;
			temp.next = newBasicNode;
			size++;
		}
	}
	
	
	/** 
	* Method to remove song by position
	*
	* @param position - where element is to be removed
	*/
	public void delete(int position){
		if (position < 1 || position > size+1)
			System.out.println("Please provide a valid position.");	
		else if (isEmpty())
			System.out.println("The playlist is empty");
		else if (position == 1){
			head = head.next;
			size--;
		}
		else if (position == size){
			BasicNode temp = head;
			for (int i=1; i<position;i++){
				temp = temp.next;
			}
			temp.next = null;
			size--;
		}
		else {
			BasicNode temp = head;
			for (int i=1; i<position;i++){
				temp = temp.next;
			}
			temp.next = temp.next.next;
			size--;
		}
	}
	
	/** 
	* Method to remove song by title
	*
	* @param title - of element is to be removed
	*/
	public void remove(String title){
		BasicNode temp = head;
		int count = size;
		if (isEmpty())
			System.out.println("The playlist is empty");
		else{
			for (int i=1; i<=size;i++){
				if (temp.song.getTitle().equals(title))
					delete(i);
				temp = temp.next;
			}
		}
		if (count == size){
			System.out.println("The song given is not in the playlist");
		}
	}
	
	/** 
	* Method to display the songs of a playlist sequentially
	*
	*/
	public void display(){
		BasicNode temp = head;
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
		BasicNode temp = head;
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
	1. Adding song to end of plylist
	2. Adding song at specific position
	3. Remove song by title or position
	4. Display playlist by order
	5. Calculate total duration. 	
	*/
	public static void main(String[] args){
		
		System.out.println("This is a basic playlist implemented using a singly linked list");
		BasicPlaylist playlist1 = new BasicPlaylist();
		System.out.println("The playlist is created");
		playlist1.display();
		
		Song song1 = new Song("Rhumba Japani", "Sauti Sol", 210.6);
		Song song2 = new Song("The Danko!", "Samthing Soweto", 320);
		Song song3 = new Song("Sondela", "Tresor ft. Msaki", 261);
		Song song4 = new Song("Hamba Wena Hamba","Gwijo", 366.7);
		
		System.out.println("\nAdding 3 new songs each at End of the empty playlist");
		playlist1.insertAtEnd(song1);
		playlist1.insertAtEnd(song2);
		playlist1.insertAtEnd(song3);
		playlist1.display();
		
		System.out.println("\nAdding a song to specific position 2 of playlist");
		playlist1.insert(song4, 2);
		playlist1.display();
		
		System.out.println("\nTotal duration of the current playlist is: " + playlist1.totalDuration());
		
		System.out.println("\nRemove song Sauti sol by position");
		playlist1.delete(1);
		playlist1.display();
		System.out.println("\nRemove 'The Danko!' song by title");
		playlist1.remove("The Danko!");
		playlist1.display();
		
		System.out.println("\nTotal duration of the current playlist is: " + playlist1.totalDuration());
	}
}
