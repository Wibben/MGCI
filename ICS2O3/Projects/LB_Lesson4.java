/*
Bing Li
Sept 27, 2014
ICS2O3
Lesson 4 programs in a menu
*/
import java.awt.*;
import hsa.Console;

public class LB_Lesson4
{
    public static int MAX = 2147483647;
    
    //*******************************sumOdd*********************************
  
    public static int sumOdd(int num) {
	int sum = 0; // Initialize to 0
	
	for(int i=1; i<=num; i+=2) // Loops all odd numbers from 1 to num, and adds them to sum
	    sum+=i;
	
	return sum;
    }
    
    //*******************************factorialRecursion*********************************
  
    public static double factorialRecursion(int value) { // Simple recursion
	 if(value<=1) return 1;
	 else return value*factorialRecursion(value-1);
	 /*
	 Recursion:
	 
	 The theory of recursion works with the fact that there is a base case which returns
	 a specified value if met, and if the base case is not met, the function calls upon 
	 itself in such a way that it works its way all the way to the base case, which sends
	 return values all the back to the first instance the program was called.
	 */
    }
    
    //*******************************factorialFor*********************************
  
    public static double factorialFor(int value) { // Simple recursion
	double prod = 1;
	    
	for(int i=1; i<=value; i++) // Loop from 1 to value and multiply by prod
	    prod*=i;
	    
	return prod; 
    }
	
    //*******************************prime*********************************
  
    public static boolean prime(int test) { 
	if(test==1) return false; // 1 is special
	
	for(int i=2; i<=test/2; i++) { // Loops from 2 to half of test
	    if(test%i==0) return false; // If test not prime then false and end function
	}
	
	return true;
    }
    
    //*******************************gcfEuclid*********************************
  
    public static int gcfEuclid(int a, int b) { 
	while(b!=0) { // Euclid's algorithm for GCF, google it
	    int temp = b;
	    b = a%b;
	    a = temp;
	}
	
	return a;
    }
    
    //*******************************gcfFor*********************************
  
    public static int gcfFor(int a, int b) { 
	int gcf = 1;
	
	for(int i=2; i<=Math.min(a,b); i++) // Loop 2 to smallest of a and b
	    if(a%i==0 && b%i==0) gcf = i; // If i is divisible by both a and b update gcf
	
	return gcf;
    }
    
    //*******************************getInt*********************************
  
    public static int getInt(int min, int max) { 
	int value;
	
	c.print("Please enter a number between " + min + " and " + max+ ": "); // Primary input
	value = c.readInt();
	
	while(value<min || value>max) { // Error message and re-input
	    c.print("\nERROR: Number out of range.\nPlease enter a number between " + min + " and " + max+ ": ");
	    value = c.readInt();
	} while(value<min || value>max);
	
	return value;
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
    
	int choice;
	
	// Usual menu stuff
	do {
	    c.print("Please choose from the following menu\n\n");
	    c.print("1 - sumOdd\n");
	    c.print("2 - factorial (Using recursion)\n");
	    c.print("3 - factorial (Using for loops)\n");
	    c.print("4 - prime\n");
	    c.print("5 - gcf (Using Euclid's algorithm)\n");
	    c.print("6 - gcf (Using for loops)\n");
	    c.print("\nEnter 0 to exit\n\n");
    
	    choice = getInt(0,6);
    
	    c.clear();
	    if(choice==1) {
		int num = getInt(0,5001);
		c.print("The sum of all odd integers from 1 to " + num + " is " + sumOdd(num));
	    } else if(choice==2) {
		int num = getInt(0,69); // Calculator's max is 69! so I chose this
		c.print(num + "! = ");
		c.print(factorialRecursion(num),0,0);
	    } else if(choice==3) {
		int num = getInt(0,69); // Calculator's max is 69! so I chose this
		c.print(num + "! = ");
		c.print(factorialFor(num),0,0);
	    } else if(choice==4) {
		int num = getInt(0,MAX);
		c.print(num + " is " + (prime(num) ? "prime":"not prime"));
		/*
		The above code works in a way like this:
		
		    c.print(boolean_condition ? true_output:false_output);
		
		The "?" symbol serves as a signal to the program to test the condition that comes right before it
		and performs the specified actions right after it
		
		The ":" symbol serves as a divider between the if-true and if-false instructions
		*/
	    } else if(choice==5) {
		// Get the 2 values for the GCF function
		int a = getInt(0,MAX);
		int b = getInt(0,MAX);
		c.print("The greatest common factor of " + a + " and " + b + " is " + gcfEuclid(Math.max(a,b),Math.min(a,b))); // Euclid's algorithm needs the bigget value first to function properly
	    } else if(choice==6) {
		// Get the 2 values for the GCF function
		int a = getInt(0,MAX);
		int b = getInt(0,MAX);
		c.print("The greatest common factor of " + a + " and " + b + " is " + gcfFor(a,b));
	    }
	
	    c.print("\nPress Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n"); // Termination message
	
    } // main method
} // LB_Set6 class
