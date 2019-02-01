// The "ArrayInClass" class.
import java.awt.*;
import hsa.Console;

public class LB_ArrayInClass
{
    static Console c;

    // print will accept an array of integers and display them separated by commas and
    // spaces as shown in the sample run.  If the list is empty, a message should be
    // displayed.   [5 marks]

    // neworder will accept an array of integers and return another array with the order changed 
    // as follows: 0 1 2 3 4 5 6 7 8 will become 0 5 1 6 2 7 3 8 4  (odd number of values)
    // 0 1 2 3 4 5 6 7 will become 0 4 1 5 2 6 3 7  (even number of values)     [8 marks]

    // twodigits will accept an array of integers and return another array that contains
    // only the 2-digit numbers.    [8 marks]

    public static void print(int[] list)
    {
	if(list.length==0) c.print("The list is empty."); // Error message
	else
	    for(int i=0; i<list.length; i++) // Loop through each element and displays it
		c.print(list[i] + " ");
    }
    
    public static int[] neworder(int[] list)
    {
	int processing[] = new int[100];
	int cnt = 0;
	
	// Putting the first half in the even numbered elements
	for(int i=0; i<=(list.length-1)/2; i++) {
	    processing[cnt] = list[i];
	    cnt+=2;
	}
	
	// Putting the second half in the odd numbered elements
	cnt = 1;        
	for(int i=(list.length-1)/2+1; i<list.length; i++) {
	    processing[cnt] = list[i];
	    cnt+=2;
	}
	
	// Transferring processing to a new array to remove trailing zeros
	int array[] = new int[list.length];
	for(int i=0; i<list.length; i++)
	    array[i] = processing[i];
	    
	return array;
    }
    
    public static int[] twodigits(int[] list)
    {
	int cnt = 0;
	int processing[] = new int[100];
	
	for(int i=0; i<list.length; i++) // If list[i] is between 10 and 100, put it into the new array
	    if(list[i]<100 && list[i]>9) processing[cnt++] = list[i];
	
	// Transferring processing to a new array to remove trailing zeros
	int array[] = new int[cnt];
	for(int i=0; i<cnt; i++)
	    array[i] = processing[i];
	    
	return array;
    }
    
    public static void main (String [] args)
    {
	c = new Console ();

	int list [] = {3, 23, 234, 173, 245, 34, 45}; // change this list for testing purposes

	c.print ("Here is the original list: ");
	print (list);

	c.print ("\nThe re-ordered list: ");
	int changed [] = neworder (list);
	print (changed);

	c.print ("\nThe 2-digit numbers: ");
	int some [] = twodigits (list);
	print (some);
    } // main method
} // ArrayInClass class

// Here is the original list: 3, 23, 234, 173, 245, 34, 45

// The re-ordered list: 3, 245, 23, 34, 234, 45, 173

// The 2-digit numbers: 23, 34, 45
