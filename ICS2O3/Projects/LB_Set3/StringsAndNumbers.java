// The "StringsAndNumbers" class.
import java.awt.*;
import hsa.Console;

public class StringsAndNumbers
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	c.print("An example of an integer is "+ 4);
	c.print("\nAn example of a real number is " + 7.25);
	c.print("\nAdding an integer to a real number, such as 4+7.25 gives a real result: " + (4+7.25));
	c.print("\nThe result is ");
	c.println(765.5678,0,2);
	
	// Place your program here.  'c' is the output console
    } // main method
} // StringsAndNumbers class
