// The "PolygonDemo" class.
import java.awt.*;
import hsa.Console;

public class PolygonDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	Color tri = new Color(100,200,150);
	c.setColor(tri);
	
	int[] x = {10,110,60}; //array containing all the x-values 
	int[] y = {10,10,110}; //array containing all the y-values
	//Note: the x and y values coorespond just in case you didn't know...
	c.fillPolygon(x,y,3); 
	/*fillPolygon works as such
	
	c.fillPolygon(array containing x values, array containing y-values, number of (x,y) values)
	
	*/
	// Place your program here.  'c' is the output console
    } // main method
} // PolygonDemo class
