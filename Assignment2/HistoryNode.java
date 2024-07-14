/** 
*
* HistoryNode class that will be used as a blueprint to create 
* instances of the webpages visited. 
* @ cheboi
*/
import java.io.Serializable; 
// the interface above will allow us to serialize an object into a byte stream for saving purposes

class HistoryNode implements Serializable{
	String url; // URL of the visited page
	String timestamp; // time when the page was visited
	HistoryNode previous;
	HistoryNode next;
	
	HistoryNode(String url, String timestamp){
		this.url = url;
		this.timestamp = timestamp;
	}
	
	/**
	* Return url of the page visited at a certain instance 
	*
	* @return String value of url. 
	*/
	public String getUrl(){
		return url;
	}
	
	/**
	* Return timestamp of the page visited at a certain instance 
	*
	* @return String value of timestamp. 
	*/
	public String getTimestamp(){
		return timestamp;
	}
}
		