// The "LB_OverHere" class.
import java.awt.*;
import hsa.Console;
import java.security.*;
import java.lang.*;

public class LB_OverHere
{
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
	
	// Declare 2 random number generators
	SecureRandom rand = new SecureRandom();
	
	while(!c.isCharAvail()) { // while user not pressed any keys
	    // Setting a random row and col value
	    int row = rand.nextInt(25)+1;
	    int col = rand.nextInt(72)+1;
	    c.setCursor(row,col); // set cursor at position
	    c.print("OverHere"); // display
	    Thread.sleep(500); // delay 500 ms
	    c.clear(); // clear the screen
	}
	
	// Place your program here.  'c' is the output console
    } // main method
} // LB_OverHere class
