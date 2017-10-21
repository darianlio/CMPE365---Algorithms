/*Name: Darian Lio
 *Course: CMPE365
 *Lab 4
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Lab4_CMPE365 {

	public static BoundingBox fSR = null;
	
	public static void main(String[] args) {
		
		File file = new File("points.csv");
		double[] xCoord = new double[300];
		double[] yCoord = new double[300];
		double coord1 = 0;
		double coord2 = 0;
		int counter = 0;
		
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNext()) {
				String data = scan.next();
                String[] nums = data.split(",");
                coord1 = Double.parseDouble(nums[0]);	//number in first col
                coord2 = Double.parseDouble(nums[1]);	//number in second col
                xCoord[counter] = coord1;				//first number as x coord
				yCoord[counter] = coord2;				//second number as y coord
				counter++;
			}
			
			scan.close();
			
			//Sort arrays
			
			Arrays.sort(xCoord);
			Arrays.sort(yCoord);
			
			System.out.println("Sorted coordinates in increasing order:");
			
			for(int i = 0; i < xCoord.length; i++) {
				System.out.println("x = " + xCoord[i] + " y = " + yCoord[i]);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Get pair of all four corners of rectangle of all the points
		Pair bl = new Pair(xCoord[0], yCoord[0]);
		Pair br = new Pair(xCoord[299], yCoord[0]);
		Pair tl = new Pair(xCoord[0], yCoord[299]);
		Pair tr = new Pair(xCoord[299], yCoord[299]);
		
		//Generate the bounding box
		BoundingBox bb = new BoundingBox(bl, br, tl, tr);
		
		System.out.println("The points per area of the bounding box with all the points is: \n" + bb.generateRatio(xCoord, yCoord));
		
		//Calculate the full subrectangle
		fullSR(xCoord, yCoord, bb, true);
		
		//Print out subrectangle coordinates
		System.out.println("The fullest subrectangles coordinates are:");
		System.out.println("Bottom Left: (" + fSR.getBL().getX() + ", " + fSR.getBL().getY() + ")");
		System.out.println("Bottom Right: (" + fSR.getBR().getX() + ", " + fSR.getBR().getY() + ")");
		System.out.println("Top Left: (" + fSR.getTL().getX() + ", " + fSR.getTL().getY() + ")");
		System.out.println("Top Right: (" + fSR.getTR().getX() + ", " + fSR.getTR().getY() + ")");
	}
	
	public static void fullSR(double[] x, double[] y, BoundingBox biggestBB, boolean test) {
		
		if (test == true) {
			//Split BoundingBox to two
			BoundingBox left, right;
					
			//Vertically
			//Pairs for the left side (split the x coordinate in half)
			Pair bll = biggestBB.getBL();
			Pair brl = new Pair(biggestBB.getBR().getX()/2, biggestBB.getBR().getY());
			Pair tll = biggestBB.getTL();
			Pair trl = new Pair(biggestBB.getTR().getX()/2, biggestBB.getTR().getY());
			//Pairs for the right side
			Pair blr = new Pair(biggestBB.getBL().getX()/2, biggestBB.getBR().getY());
			Pair brr = biggestBB.getBR();
			Pair tlr = new Pair(biggestBB.getTL().getX()/2, biggestBB.getTR().getY());
			Pair trr = biggestBB.getTR();
				
			//Put the pairs into their designated box
			left = new BoundingBox(bll,brl,tll,trl);
			right = new BoundingBox(blr,brr,tlr,trr);
					
			BoundingBox SR = null;
					
			if (left.generateRatio(x, y) > right.generateRatio(x, y)) {
				SR = left;
			} else {
				SR = right;
			}
			
			//If the ratio of points per unit in box is greater than the biggest bounding box ratio put it as the fullest subrectangle
			if (SR.generateRatio(x, y) > biggestBB.generateRatio(x, y)) {
				fSR = SR;
			} else { //else return
				return;			
			}
			
			fullSR(x, y, SR, true);
		
		} else {
			//Split BoundingBox to two
			BoundingBox top, bottom;
			
			//Horizontally
			//Pairs for the top side (split the y coordinate in half)
			Pair bll = new Pair(biggestBB.getBL().getX(), biggestBB.getBL().getY()/2);
			Pair brl = new Pair(biggestBB.getBR().getX(), biggestBB.getBR().getY()/2);
			Pair tll = biggestBB.getTL();
			Pair trl = biggestBB.getTR();
			//Pairs for the bottom side (split the y coordinate in half)
			Pair blr = biggestBB.getBR();
			Pair brr = biggestBB.getBL();
			Pair tlr = new Pair(biggestBB.getTR().getX(), biggestBB.getTR().getY()/2);
			Pair trr = new Pair(biggestBB.getTL().getX(), biggestBB.getTL().getY()/2);
	
			top = new BoundingBox(bll, brl, tll, trl);
			bottom = new BoundingBox(blr, brr, tlr, trr);
					
			BoundingBox SR = null;
			
			if (top.generateRatio(x, y) > bottom.generateRatio(x, y)) {
				SR = top;
			} else {
				SR = bottom;
			}
					
			if (SR.generateRatio(x, y) > biggestBB.generateRatio(x, y)) {
				fSR = SR;
			} else {
				return;
			}
			
			fullSR(x, y, SR, false);
		}
	}

}
