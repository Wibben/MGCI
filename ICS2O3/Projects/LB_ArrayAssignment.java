/* 
The "LB_ArrayAssignment" class.
Bing Li
ICS2O3
Arrays practice
*/
import java.awt.*;
import hsa.Console;

public class LB_ArrayAssignment
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	int size,sum,prod;
	char choice;
	
	do{
	    sum = 0; prod = 1; // Initializing
	    
	    c.print("Please enter the size of the array: "); // Prompt for size
	    size = c.readInt();
	    int array[] = new int[size]; // Creating array
	    
	    for(int i=0; i<size; i++) { // Getting each element of the array
		c.print("Please enter an integer for element " + (i+1) + ": ");
		array[i] = c.readInt();
		sum+=array[i]; // Updating sum
		prod*=array[i]; // Updating product
	    }
	    
	    for(int i=0; i<size; i++) // Display array 
		c.println("Element " + (i+1) + " = " + array[i]);
	    
	    // Display sum and product
	    c.println("\nThe sum of the numebrs is " + sum);
	    c.println("The prodict of the numebrs is " + prod);
	    // Ending stuff
	    c.println("\nYou picked a fine size user\nWith just 1 for loop we filled the array efficiently\nThank you for loop");
	    
	    // Seeing if user wants to continue
	    c.print("\nPress <q> to continue or any other key to start over");
	    choice = c.getChar();
	    c.clear();
	} while(choice!='q');
	
	c.print("Thank you for using this program."); // Termination message
	
	// Place your program here.  'c' is the output console
    } // main method
} // LB_ArrayAssignment class
