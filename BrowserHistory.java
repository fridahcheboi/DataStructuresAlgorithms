/** 
*
* Browser History class that implements a doubly linked list for keep track the visited webpages
*
* @ cheboi
*/

import java.io.Serializable;
import java.io.*;

class BrowserHistory implements Serializable{
	HistoryNode head = null;
	HistoryNode tail = null;
	int size = 0;
	
	// Constructor not needed
	// public BrowserHistory(){}
	
	boolean isEmpty(){
		return (size==0);
	}
	
	/**
	* Adds a new page to the end of the browser history
	*
	* @param url & timestamp of page to be added
	*/
	public void addPage(String url, String timestamp){
		HistoryNode newNode = new HistoryNode(url, timestamp);
		HistoryNode temp = head;
		// add page at end of history 
		if (isEmpty()){
			head = newNode;
			tail = head;	
		}
		else{
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}
		size++;
	}
	
	/**
	* Remove a page by its timestamp
	*
	* @param timestamp - time when page was visited 
	*/
	public void removePage(String timestamp){
		HistoryNode temp = head;
		int count  = size;
		
		if (isEmpty()){
			System.out.println("No recent history");
			return;
		}
		
		// removes first node
		if (head.getTimestamp().equals(timestamp)){
			System.out.println("Browser history " + head.getUrl() + " removed");
			head = head.next;
			size--;
			return;
		}
		//removes the last node
		else if(tail.getTimestamp().equals(timestamp)){
			System.out.println("Browser history '" + tail.getUrl() + "' removed");
			tail = tail.previous;
			size--;
			return;
		}
		// this removes the middle nodes
		else{
			for (int i=1; i<=size;i++){
				if (temp != null){
					if (temp.getTimestamp().equals(timestamp)){
						temp.previous.next = temp.next;
						temp.next.previous = temp.previous;
						System.out.println("Browser history '" + temp.getUrl() + "' removed");
						size--;
						return;
					}
					temp = temp.next;
				}
			}
				
		}
		
		if (count == size){
			System.out.println("Browsing History for timestamp: "+ timestamp + " not found!");
		}
	}
	
	/**
	* Method to display the browsing history from the oldest to the newest
	*/
	public void displayHistoryForward(){
		HistoryNode temp = head;
		int count = 1;
		if (isEmpty())
			System.out.println("No browsing history");
		else{
			System.out.println("\nYou visited " + size + " pages(forward display): ");
			while (count <= size){
				if (temp != null){
					System.out.println(count  + ". " + temp.getTimestamp() + "    " + temp.getUrl());
					temp = temp.next;
					count++;
				}
			}
		}
	}
	
	
	/**
	* Method to display the browsing history from the newest to the oldest
	*
	*/
	public void displayHistoryBackward(){
		HistoryNode temp = tail;
		int count = 1;
		if (isEmpty())
			System.out.println("No browsing history");
		else{
			System.out.println("\nYou visited " + size + " pages(backward display): ");
			while (count <= size){
				if (temp != null){
					System.out.println(count + ". "+ temp.getTimestamp() + "    " + temp.getUrl());
					temp = temp.previous;
					count++;
				}
			}
		}
	}
	
	/**
	* Main method to:
	* 1. Create BrowserHistory instance
	* 2. Add pages to history, remove pages, and display history
	* 3. Call method to save and load browser history
	*/
	public static void main(String[] args){
		// Instance of BrowserHistory
		BrowserHistory sunday  = new BrowserHistory();
		
		sunday.addPage("www.imdb.com", "9:18 AM");
		sunday.addPage("www.rottentomatoes.com", "10:31 AM");
		sunday.addPage("www.denofgeek.com", "10:41 AM");
		sunday.addPage("www.bikozulu.co.ke", "11:09 AM");
		sunday.addPage("www.kenyans.co.ke", "11:58 AM");
		sunday.addPage("www.nation.africa", "12:02 AM");
		sunday.addPage("www.capitalfm.co.ke", "12:47 PM");
		sunday.addPage("www.nairobiwire.com", "13:14 PM");
		sunday.addPage("www.africasacountry.org", "13:20 PM");
		sunday.addPage("www.undp.org", "13:51 PM");
		sunday.addPage("www.worldstopexports.com", "14:51 PM");
		
		BrowserHistory monday = new BrowserHistory();
		
		// add pages to the history
		monday.addPage("www.youtube.com", "10:00 AM");
		monday.addPage("www.aljazeera.com", "10:05 AM");
		monday.addPage("www.bbc.com", "10:11 AM");
		monday.addPage("www.worldbank.org", "10:19 AM");
		monday.addPage("en.wikipedia.org", "10:29 AM");
		monday.addPage("www.britannica.com", "10:54 AM");
		monday.addPage("www.nytimes.com", "11:12 AM");
		monday.addPage("www.cnn.com", "11:37 AM");
		monday.addPage("www.npr.org", "12:30 PM");
		monday.addPage("www.president.go.ke", "13:00 PM");
		monday.addPage("www.hbo.com", "14:44 PM");
		
		// display the browsing history in forward order
		monday.displayHistoryForward();
		
		// remove the page visited at a certain time
		System.out.println();
		monday.removePage("10:05 AM");
		monday.removePage("14:14 PM"); // edge case
		monday.removePage("10:00 AM");
		monday.removePage("10:54 AM"); 
		
		System.out.println();
		monday.displayHistoryForward();
		
		System.out.println();
		monday.displayHistoryBackward(); // display the browsing history in backward order
		
		
		saveBHistoryFile(sunday, "SundayHistory.ser"); // save the sunday browsing history
		saveBHistoryFile(monday, "MondayHistory.ser");
		
		BrowserHistory yesterday = loadBHistoryFile("SundayHistory.ser");
		yesterday.displayHistoryForward();
		
	}
	
	/**
	* Method to save the browser history into a file 
	*
	* @param object1 - browserHistory instance to be save, filename of file that will be created
	* 
	*/
	
	public static void saveBHistoryFile(BrowserHistory object1, String filename){
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object1);
            System.out.println("Browser History successfully saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	* Method to load the browser history from a file 
	*
	* @param filename of file that will be created
	* @return BrowserHistory instance that will be created from loading a file. 
	*/ 
	public static BrowserHistory loadBHistoryFile(String filename){
		BrowserHistory browserHist = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            browserHist = (BrowserHistory) ois.readObject();
            System.out.println("Successfully loaded the browser history ");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return browserHist;
	}
}