import java.util.HashSet;
import java.util.Set;

/* Name: Darian Lio
 * Course: CMPE 365
 * Lab 2 : Activity
 * Collatz Program : for any initial value, the loop terminates;
 */

public class Lab2_Exercise1 {
	
	public static void main(String []args) {
		
		//Test runtime
		long startTime, endTime, totalTime;
		startTime = System.currentTimeMillis();
		
		//Declare a set of integers to be stored
		Set<Integer> set = new HashSet<>();
		
		//Declare initial integer value
		int num, temp;
		
		//Loop starting at the initial integer and decreases
		for(int i = 100; i >= 1; i--) {
			num = i;									//set num to initial integer
			temp = num;									//store the initial integer in a temp
			
			System.out.print(num + ": ");
			
			while(num != 1) {
				
				if(!set.contains(temp)) {				//check if set has the initial integer
					set.add(temp);						//if not, add it into the set
					System.out.print(temp + " ");
				}
				
				if(num%2==0) {							//if the number is even
					num = num/2;						//divide it by 2
				} else {								//if the number is odd
					num = 3*num + 1;					//multiply it by 3 and add 1
				}
				
				if(set.contains(num)) {					//if the set already contains the calculated value
					break;								//terminate as we know it will reach 1
				}
				
				set.add(num);							//if not in set, add the num to set
	
				System.out.print(num + " ");
			}
			System.out.println("");						//reiterate as num decreases from initial in loop
		}
		
		//Output runtime
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("The total runtime for the improved program is : " + totalTime + " msec");
	}
}
