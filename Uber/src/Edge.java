/*	Name: Darian Lio
 * 	Course: CMPE365
 * 	Project: Uber
 * 	Date: November 18, 2017
 * 
 * 	Class: Edge
 * 	Description: class to store edges from a start node, end node, and the given weight
 */

public class Edge {
	
	//Initialize variables
	private int sourceNode;
	private int endNode;
	private int weight;
	
	//Edge constructor containing the start node, end node, and its weight
	public Edge(int sourceNode, int endNode, int weight) {
		this.sourceNode = sourceNode;
		this.endNode = endNode;
		this.weight = weight;
	}
	
	//Getters
	public int getSourceNode() {
		return sourceNode;
	}
	
	public int getEndNode() {
		return endNode;
	}
	
	public int getWeight() {
		return weight;
	}
	
	//Getter to check the node's neighbours based on nodes on this edge
	public int getNeighbour(int node) {
		if (this.sourceNode == node) {
			return this.endNode;
		} else {
			return this.sourceNode;
		}
	}
}
