/* Name: Darian Lio
 * Course: CMPE 365
 * Lab 1 : Activity
 */

import java.io.File;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab1_Activity {
	
	public static void main(String []args) {
		//Variable initialization
		double best1 = 0;
		double bestposn1 = 0;
		int size = 1000;
		double e = Math.E;
		Double[] scoreArray1 = new Double[size]; 
		String[] fileArray = {"es1.csv", "es2.csv", "es3.csv", "us1.csv", "us2.csv", "us3.csv", "us4.csv"};
		
		
		//Scan file
		for (int k = 0; k < fileArray.length; k++){
		
		try {
			//Create a scanner to scan through the file

			File file1 = new File(fileArray[k]);
			
			Scanner in1 = new Scanner(file1);
			
			//Initiate scanner
			while (in1.hasNext()) {
				
				//Read the data and put it into the string array scores
				String data = in1.next();
				String[] scores = data.split(",");
				
				//Convert string into a double
				for (int i = 0; i < scores.length; i++) {
					scoreArray1[i] = Double.parseDouble(scores[i]);
				}
			}
			
			//Close scanners
			in1.close();
		
		} 

		//Catch errors
		catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		//Determine the best score in the file
		best1 = scoreArray1[1];
		for (int i = 0; i < scoreArray1.length; i++) {
			if (scoreArray1[i] > best1) {
				best1 = scoreArray1[i];
			}
		}	
		
		//Print answer
		System.out.println("");
		System.out.println("The best score is : " + best1);	
	
		
		//Determine the best score in the group
		best1 = 0;
		for (int i = 0; i < (int)scoreArray1.length/e; i++) {
			if (scoreArray1[i] > best1) {
				best1 = scoreArray1[i];
				bestposn1 = i;
			}
		}
		
		System.out.println("");
		System.out.println("Best employee in the group in " + fileArray[k] + " is Employee " + bestposn1 + " with the score of " + best1);
		System.out.println("");
		
		
		//Loop to find best employee
		
		for (int i = (int) (scoreArray1.length/e + 1); i < scoreArray1.length ; i++) {
			if (scoreArray1[i] > best1) {
				best1 = scoreArray1[i];
				bestposn1 = i;
				break;
			}
		}
		
		System.out.println("Best employee after group in " + fileArray[k] + " is Employee " + bestposn1 + " with the score of " + best1);
	
	}
	}
}
