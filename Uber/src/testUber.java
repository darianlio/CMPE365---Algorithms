	/*	Name: Darian Lio
 * 	Course: CMPE365
 * 	Project: Uber
 * 	Date: November 18, 2017
 * 
 * 	Class: testUber
 * 	Description: test output to check total wait time and runtime
 */

import java.io.*;
import java.util.ArrayList;


public class testUber {
	
	public static void main(String[] args) {
		//Initialize Variables
		ArrayList<Passenger> request = new ArrayList<Passenger>();
		ArrayList<Edge> edge = new ArrayList<Edge>();;
		int currentTime, waitTime;
		currentTime = 0;			//time driver has begun work
		waitTime = 0;

		//Introduction
		System.out.println("========================================================="
						 + "\n=                     Welcome to Uber!                  ="
						 + "\n=========================================================");
		
		//Store information of network in network class
		System.out.println("Generating network..."
						 + "\n---------------------------------------------------------");
		readNetwork("newnetwork.csv", edge);
		
		//Store requests in an array containing the start and end locations
		readRequest("newrequests.csv", request);
		System.out.println("---------------------------------------------------------");
				
		//Initialize network with added edges
		Network nwk = new Network(edge);
		
		//Calculate time it takes for initial request and save initial request to prev
		Passenger prev = request.get(0);
		currentTime += nwk.calculateTime(request.get(0).getStartPos(), request.get(0).getFinalPos());
		
		//Print initial result
		System.out.println("Driver is heading to the initialize location.");
		System.out.println("The driver arrived late to the customer!" 
				 + "\nHeading to the requested location from passenger..."
				 + "\nStart Location: " + (request.get(0).getStartPos()) + "\nEnd Location: " + (request.get(0).getFinalPos()) + "\nLate By: " + Math.abs(request.get(0).getTimeStamp()-currentTime)
				 + "\nDriver has successfully dropped off the passenger."
				 + "\n---------------------------------------------------------");

		//Add to wait time
		waitTime += Math.abs(request.get(0).getTimeStamp() - currentTime);
		
		//Launch Uber
		for (int i = 1; i < request.size(); i++) {
			//Re-initialize network after to test new request
			nwk = new Network(edge);
			System.out.println("Driving to the next destination."
							 + "\nTime Stamp: " + request.get(i).getTimeStamp());
		
			//Calculate time from previous position to this start position
			currentTime += nwk.calculateTime(prev.getFinalPos(), request.get(i).getStartPos());
	
			//if driver is on time
			if ((currentTime <= request.get(i).getTimeStamp())) {
				System.out.println("The driver arrived to the passenger on time!"
								 + "\nHeading to the requested location from passenger..."
								 + "\nStart Location: " + (request.get(i).getStartPos()) + "\nEnd Location: " + (request.get(i).getFinalPos())
								 + "\nDriver has successfully dropped off the passenger."
								 + "\n---------------------------------------------------------");
				currentTime = request.get(i).getTimeStamp();
			} else {
				//Print out shortest path for the one user requested to go to
				System.out.println("The driver arrived late to the customer!" 
								 + "\nHeading to the requested location from passenger..."
								 + "\nStart Location: " + (request.get(i).getStartPos()) + "\nEnd Location: " + (request.get(i).getFinalPos()) + "\nLate By: " + Math.abs(request.get(i).getTimeStamp()-currentTime)
								 + "\nDriver has successfully dropped off the passenger."
								 + "\n---------------------------------------------------------");
				
				//Add to wait time
				waitTime += Math.abs(request.get(i).getTimeStamp() - currentTime);
			}
			//Calculate time it takes from start position to final position for passenger
			currentTime += nwk.calculateTime(request.get(i).getStartPos(), request.get(i).getFinalPos());
			prev = request.get(i);
		}
		
		//Display ending outputs
		System.out.println("Driver has finished work." 
							+ "\nTotal time the passengers spent waiting: " + waitTime);
	}
	
	public static void readNetwork(String fileName, ArrayList<Edge> edge) {
		try {
			//Initialize BufferedReader taking in the file
			BufferedReader networkBR = new BufferedReader(new FileReader(fileName));
			String line = null;
			int row = 0;
			
			//Read each line until null
			while ((line = networkBR.readLine()) != null) {
				//Store each number in an array
				String[] word = line.split(",");
				for (int i = 0; i < word.length; i++) {
					if (word[i].equals("0")) {
						word[i] = "99999";
					}
					//make new edge and store into an ArrayList for each number
					Edge e = new Edge(row, i, Integer.parseInt(word[i]));
					edge.add(e);
				}
				row++;
			}
			networkBR.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Read request file 
	public static void readRequest(String fileName, ArrayList<Passenger> request) {
		System.out.println("Generating requests...");
		try {
		BufferedReader requestBR = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = requestBR.readLine()) != null) {
			//Store each word in time stamp, start loc, and final loc and then store in constructor
			String[] word = line.split(",");
			int timeStamp = Integer.parseInt(word[0]);
			int startLoc = Integer.parseInt(word[1]);
			int finalLoc = Integer.parseInt(word[2]);
			Passenger passenger = new Passenger(timeStamp, startLoc, finalLoc);
			
			//finally, add to array list
			request.add(passenger);
		}
		requestBR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}