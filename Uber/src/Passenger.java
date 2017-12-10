/*	Name: Darian Lio
 * 	Course: CMPE365
 * 	Project: Uber
 * 	Date: November 18, 2017
 * 
 * 	Class: Passenger
 * 	Description: class to store requests of passenger from start position to the final position
 */

public class Passenger {
	
	//Initialize variables
	private int timeStamp, startPos, finalPos;
	
	//Passenger constructor taking the passengers start location and final location
	public Passenger(int timeStamp, int startPos, int finalPos) {
		this.timeStamp = timeStamp;
		this.startPos = startPos;
		this.finalPos = finalPos;
	}
	
	//Getters
	public int getTimeStamp() {
		return timeStamp;
	}
	
	public int getStartPos() {
		return startPos;
	}
	
	public int getFinalPos() {
		return finalPos;
	}
	
	//toString
	public String toString() {
		return getStartPos() + " " + getFinalPos() + " ";
	}
}
