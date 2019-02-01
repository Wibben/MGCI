// The "AnimationDemo" class.
import java.awt.*;
import hsa.Console;

public class BasicAnimationDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException // <-- VERY IMPORTANT TO INCLUDE "throws InterruptedException"
    {
	c = new Console ();
	
	c.setColor(Color.blue);
	for(int i=0; i<640; i+=5) {
	    c.clear();
	    c.fillOval(i,100,100,100);
	    Thread.sleep(40);
	}
	
	/*
	
	This is merely a basic animation demo, there are definitely better
	ways to animate objects out there, bit this is the one easiest to implement for now
	
	Structure:
	for(int i=start; i<end; i+=inc) {
	    update
	    c.method(x+a*i,y+b*i,the-rest);
	    update
	    Thread.sleep(milli);
	}
	
	start:
	the value at which you want to start generating an expression of the location of your object
	
	end:
	the value at which you want to finish generating an expression of the location of your object
	
	inc:
	the value at which you want to increment for the location-generating expressions
	
	update:
	you need to cover up the previous shape you made, so you need to implement an update to revert the
	background to its original state to cover up the shape you made in the last "frame"
	
	method:
	the method for the object you want to create (may need more than one method if creating complicated object)
	
	x:
	initial x-value of the object
	
	a:
	the increment at which you wish to move the object left/right
	
	y:
	initial y-value of the object
	
	b:
	the increment at which you wish to move the object up/down
	
	the-rest:
	literally the rest of the code, you can use an increment to change the height, width, angle, etc of the object 
	
	update;
	sometimes you need to cover up some of the generated shape, so you do a update after the generation
	
	milli:
	the number of milliseconds you want to pause each frame before going onto the next
	
	
	Note:
	to use Thread.sleep(), you must add to the end of your main function and any function using Thread.sleep()
	the following:
	    throws InterruptedException
	this is telling the system to be prepared to pause for just a bit
	
	If you want it to move in a slope, use a nested for loop
	
	
	WARNING:
	if you have a lot of updates or your pause time is very short, it will cause flickers to appear
	so if you know anyone who has photo-sensitive epilepsy use this wisely
	
	
	CREATIVITY and LOGIC are key to the success of your animation
	there are too many methods and variations for me to cover
	so come up with the exact details in the c.method()s yourself
	
	*/
	
	// Place your program here.  'c' is the output console
    } // main method
} // AnimationDemo class
