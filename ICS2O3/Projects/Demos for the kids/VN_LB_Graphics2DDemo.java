/* Nicholas Vadivelu and Bingran Li
ICS 203-02
November 20, 2014
Graphics 2D Demo*/

import java.awt.*;
import hsa.Console;
import java.awt.image.BufferedImage; //need to animate image

public class VN_LB_Graphics2DDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
	
	c.setColor(Color.red); //this is how you normally set the console colour to red
	c.fillRect(50, 50, 100, 100); //this is how you normally draw a rectangle
	//this way works just fine, but when you try to animate the shapes/ images they become laggy and stuttery.  
	//The solution to this is using graphics2D, as shown below
	
	BufferedImage image = new BufferedImage(c.getWidth(),c.getHeight(),BufferedImage.TYPE_INT_ARGB); //just copy this code exactly, it basically creates the buffered image with the appropriate size and colour type
	Graphics2D g2d = image.createGraphics(); //this is similar to declaring the console, basically g2d is where you draw all your images, shapes, etc.
	
	for (int i = 1; i <= 500; i++) //just a normal for loop
	{
	    g2d.setColor(Color.white); //sets the colour of graphics to white.
	    g2d.fillRect(0, 0, 640, 500); //this creates a rectangle of size 640x500.  You need to do this so there is a background.
	    g2d.setColor(Color.red); //sets the graphics colour to red
	    g2d.fillRect(i, 50, 50, 50); //creates a small red rectangle.
	    //the x co-ordinate is i because every time this loop runs, the i value increases by one. Each time this loop runs the rectangle will be drawn in a slightly different position.  
	    //Using loops and variables for coordinates is basically how to do all kinds of animation
	    c.drawImage(image, 1, 1, null); //all this time you drew on to the g2d, not this draws the g2d onto the console for the user to see
	    Thread.sleep(10); //this makes the loop wait for a little while before running again, increasing/ decreasing this number will slow down/ speed up the animation respectively
	}
	c.println("Easy as that!!");
	
	
    } // main method
} // VN_LB_Graphics2DDemo class
