// The "RandomStars" class.
import java.awt.*;
import hsa.Console;
import java.security.*;
// DON'T FORGET TO IMPORT!

public class RandomStarsDemo
{

    // this is OUTSIDE and BEFORE your main moon function
    public static void Stars(int x, int y, int star)
    {
	if(star==1) {
	    c.setColor(Color.yellow);
	    c.fillOval(x,y,5,5);
	    c.setColor(Color.blue);
	    c.fillOval(x+2,y+2,1,1);
	} else if(star==2) {
	    c.setColor(Color.green);
	    c.fillOval(x,y,4,4);
	    c.setColor(Color.red);
	    c.fillOval(x+1,y+1,2,2);
	} else if(star==3) {
	    c.setColor(Color.blue);
	    c.fillOval(x,y,3,3);
	    c.setColor(Color.pink);
	    c.fillOval(x+1,y+1,1,1);
	} else if(star==4) {
	    c.setColor(Color.pink);
	    c.fillOval(x,y,5,5);
	    c.setColor(Color.white);
	    c.fillOval(x+2,y+2,1,1);
	}
    } // just some random stars, feel free to sub in your own
    //NOTE: THIS IS A SEPARATE FUNCTION

    // this is your main moon function
    public static void moon()
    {
	//Other moon shit here
	
	c.setColor(Color.black);
	c.fillRect(0,0,640,500);
	for(int i=0; i<1000/*number of stars*/; i++) {
	    SecureRandom rand = new SecureRandom(); // declares a random number generator
	    int x = rand.nextInt(640); // random x value from 0-639 (inclusive)
	    int y = rand.nextInt(500); // random y value from 0-499 (inclusive)
	    int star = rand.nextInt(4)+1; // random value from 0-3 (inclusive) plus 1, so 1-4 (inclusive)
	    Stars(x,y,star); // calles the function
	}
	// For information on randoms, see RandomsDemo.java
	
	//Other moon shit here
    }
    
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	// main function here
	moon();
	
    } // main method
} // RandomStars class
