/* Name: Darian Lio
 * Course: CMPE 365
 * Lab 3
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Lab3_CMPE365 {
	
	//calculate the hamming distance of 2 strings in the list
	public static int getHD(List<String> img1, List<String> img2) {
		
		//initialize the distance
		int distance = 0;
		
		//calculate the total distance for each string in the array
		for(int i = 0; i < 10; i++) {
			distance = distance + (Integer.parseInt((String) img1.toArray()[i]) - Integer.parseInt((String)img2.toArray()[i]));
		}
		return distance;
	}
	
	//calculate the average hamming distance for the whole list
	public static double avg(HashMap<Integer, List<String>> img) {
		
		//initialize average
		double avg = 0;
		int counter = 0;
		
		//calculate the average of the total hamming distance
		for (int i = 0; i < img.size(); i++) {
			for (int j = 0; j < img.size(); j++) {
				avg = avg + getHD(img.get(i), img.get(j));
				counter++;
			}
		}
		
		//return the average
		avg = avg/counter;
		return avg;			
	}

	public static void main(String []args) throws IOException {
		
		//Initialize HashMap to get the list of images
		HashMap<Integer, List<String>> images = new HashMap<Integer, List<String>>();
		
		//Initialize variables
		double avgHD = 0;	
		
		int k = 20;
		
		try {
			//Read the file
			BufferedReader br = new BufferedReader(new FileReader("images.csv"));
			String line = "";
			
			//read file and store in images
			for(int i = 0; i < k; i++) {
				line = br.readLine();
				images.put(i, Arrays.asList(line.split(",")));
			}
			
			//determine the average from the initial values with the limited space of k
			avgHD = avg(images);
			
			//Read the file
			while ((line = br.readLine()) != null) {
				for (int i = 0; i < k; i++) {	
					List<String> values = images.get(i);
					//if the average of the images is greater then the initial calculated average, let this be the new average
					if(avg(images) > avgHD) {
						avgHD = avg(images);
						images.put(i, Arrays.asList(line.split(",")));
					}
					//else it should keep the initial value
					else {
						images.put(i, values);
					}
				}
			}
			
			//Close the br
			br.close();
			
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		
		//Print out the images
		System.out.println("The images of k = " + k + " is displayed below:");
		
		for(int i = 0; i < k; i++) {
			System.out.println(images.get(i));
		}
	}
}
