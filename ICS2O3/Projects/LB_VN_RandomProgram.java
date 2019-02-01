/* Nicholas Vadivelu & Bingran Li
October 16th, 2014
ICS 203-02
Random Program in class contest
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;

public class LB_VN_RandomProgram
{
    static Console c;           // The output console
    public static void Prog1 () throws InterruptedException
    {
	int random, limit;
	c.println ("What do you want the limit to be: ");
	limit = c.readInt ();
	limit++;

	while (!c.isCharAvail ())
	{
	    random = (int) (Math.random () * limit);
	    c.print (random + " ");
	    Thread.sleep (50);
	}
    }


    public static void Prog2 () throws InterruptedException
    {
	int random, limit;
	c.println ("What do you want the limit to be: ");
	limit = c.readInt ();

	while (!c.isCharAvail ())
	{
	    random = (int) (Math.random () * limit) + 1;
	    c.print (random + " ");
	    Thread.sleep (50);
	}
    }


    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
	int choice;

	do
	{
	    c.print ("\nPlease choose from the following menu\n\n");
	    c.print ("Enter a number from 1 to 2:\n");
	    c.print ("1 - 0-Limit\n");
	    c.print ("2 - 1-Limit\n");
	    c.print ("\nEnter 0 to exit\n");

	    choice = c.readInt ();

	    c.clear ();
	    if (choice == 1)
		Prog1 ();
	    else if (choice == 2)
		Prog2 ();

	    c.print ("Press Enter to continue");
	    c.getChar ();
	    c.clear ();
	}
	while (choice != 0);

	c.print ("Program terminated\n");


	// Place your program here.  'c' is the output console
    } // main method
} // RandomProgram class
