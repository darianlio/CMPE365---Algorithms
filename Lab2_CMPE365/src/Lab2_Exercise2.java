
/* Name: Darian Lio
 * Course: CMPE 365
 * Lab 2 : Activity 2 - Compare runtime
 * Collatz Program : for any initial value, the loop terminates;
 * 
 */

public class Lab2_Exercise2 {
	
	public static void main(String []args) {

		//start run time test
		long startTime, endTime, totalTime;
		startTime = System.currentTimeMillis();
		
		//Declare initial integer value
		int num;
		
		//Loop starting at the initial integer and decreases
		for(int i = 100; i >= 1; i--) {
			num = i;									//set num to initial integer
			
			System.out.print(num + ": ");
			
			while(num != 1) {
				System.out.print(num + " ");
				
				if(num%2==0) {							//if the number is even
					num = num/2;						//divide it by 2
				} else {								//if the number is odd
					num = 3*num + 1;					//multiply it by 3 and add 1
				}
			}
			System.out.println("");						//reiterate as num decreases from initial in loop
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("The total runtime for the straightforward implementation is : " + totalTime + " msec");
	}
}
