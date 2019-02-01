// The "IntegersInFields" class.
// Bing Li Sept 12, 2014
import java.awt.*;
import hsa.Console;

public class IntegersInFields
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	c.print("12345678901234567890\n");
	c.print("35", 7);
	c.print("999", 5);
	c.print("\n12345678901234567890\n");
	c.print(35, 7);
	c.print(999, 5);
	c.print("\n");
	
	// Place your program here.  'c' is the output console
    } // main method
} // IntegersInFields class
