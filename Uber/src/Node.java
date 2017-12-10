/*	Name: Darian Lio
 * 	Course: CMPE365
 * 	Project: Uber
 * 	Date: November 18, 2017
 * 
 * 	Class: Node
 * 	Description: Nodes in network getting the distance from source, to check if it was visited, and to get the edge of the current node
 */

import java.util.ArrayList;

public class Node {
	
	//Initialize variables
	private int distance = Integer.MAX_VALUE;				//Assume distance can go to infinity from source
	private boolean isVisited;								//Check if node was visited in network
	private ArrayList<Edge> edge = new ArrayList<Edge>();	//Create edge according to nodes
	
	//Getters
	public int getDistance() {
		return distance;
	}
	
	public boolean isVisited() {
		return isVisited;
	}
	
	public ArrayList<Edge> getEdge(){
		return edge;
	}
	
	//Setters
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
}
