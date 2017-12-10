/*	Name: Darian Lio
 * 	Course: CMPE365
 * 	Project: Uber
 * 	Date: November 18, 2017
 * 
 * 	Class: Network
 * 	Description: Generate network from file and calculate shortest path using Dijkstra's Algorithm
 */

import java.util.ArrayList;

public class Network {
	
	//Initialize Variables
	int amountOfNodes;
	private ArrayList<Node> node = new ArrayList<Node>();
	
	//Network Constructor taking the network from CSV
	public Network(ArrayList<Edge> edge) {
		
		amountOfNodes = getAmountOfNodes(edge);
	    for (int n = 0; n < amountOfNodes; n++) {
	        node.add(new Node());
	    }
	    
	    
		for(int i = 0; i < edge.size(); i++) {
			node.get(edge.get(i).getSourceNode()).getEdge().add(edge.get(i));
			node.get(edge.get(i).getEndNode()).getEdge().add(edge.get(i));
		}
	}
	
	//Calculate shortest path using Dijkstra's Algorithm depending on
	public void calculateShortestPath(int startLoc) {
		node.get(startLoc).setDistance(0);			//Set the distance for the first node to be 0
		int next = startLoc;						//Initialize next node as first node
		int neighbourIndex = 0;
		int temp = 0;
		//For each node
		for(int i = 0; i < node.size(); i++) {
		
			ArrayList<Edge> currentEdge = node.get(next).getEdge();							//Get edge of node
			for(int j = 0; j < currentEdge.size(); j++) {
				neighbourIndex = currentEdge.get(j).getNeighbour(next);		//Get index of neighbour node
				if(!node.get(neighbourIndex).isVisited()) {					//If neighbour node is not visited
					temp = node.get(next).getDistance() + currentEdge.get(j).getWeight();
					if(temp < node.get(neighbourIndex).getDistance()) {
						node.get(neighbourIndex).setDistance(temp);
					}
				}
			}
			node.get(next).setVisited(true);	//Node visited set to true after checking all neighbours
			next = getShortestDistance();		//Get shortest distance of the next node
		}
	}
	
	//Calculate the time it takes to get from start to end loc
	public int calculateTime(int startLoc, int endLoc) {
		int time = 0;
		calculateShortestPath(startLoc-1);
		time = node.get(endLoc-1).getDistance();
		return time;
	}
	
	//Getters	
	public ArrayList<Node> getNode(){
		return node;
	}
	
	//Calculate the amount of nodes in network
	public int getAmountOfNodes(ArrayList<Edge> edge) {
		int amountOfNodes = 0;
		for(Edge e: edge) {
		    if (e.getSourceNode() > amountOfNodes) {
		    	amountOfNodes = e.getSourceNode();
		    } 
		    if (e.getEndNode() > amountOfNodes) {
				amountOfNodes = e.getEndNode();
			}
		}
		amountOfNodes++;
		return amountOfNodes;
	}
	
	//Calculate shortest distance of node
	public int getShortestDistance() {
		int index = 0;
		int dist = Integer.MAX_VALUE;
		for(int i = 0; i < node.size(); i++) {
			int currentDist = node.get(i).getDistance();
			if(!node.get(i).isVisited() && currentDist < dist) {
				dist = currentDist;
				index = i;
			}
		}
		return index;
	}
}
