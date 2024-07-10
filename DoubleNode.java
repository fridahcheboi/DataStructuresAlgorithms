/**
* DoubleNode class is a node for implementating a doubly linked list
*
* @ cheboi
*/
import java.io.Serializable;

class DoubleNode implements Serializable{
	DoubleNode previous;
	DoubleNode next;
	Song song;
	
	// constructor
	public DoubleNode(Song song){
		this.song = song;
	}
}