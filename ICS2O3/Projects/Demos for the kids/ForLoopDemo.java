// The "ForLoopDemo" class.
import java.awt.*;
import hsa.Console;

public class ForLoopDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	c.setColor(Color.magenta);
	
	for(int i=0; i<11; i++) {
	    c.fillRect(0+30*i,0,25,25);
	}
	/*
	Structure
	for(var = a; var op b; var inc) {
	    conditions;
	}
	
	var:
	variable used to execute the loop
	can be any number-storing data type
	you can declare it outside the loop
	    int i;
	    for(i...)
	or inside the loop
	    for(int i...)
	When declared inside the loop the system destroys the variable after execution, freeing up more memory
	
	a:
	initial value
	can be an integer or a real number (depending on data type of var)
	can include calculations
	
	b:
	limit
	same properties as a
	
	op:
	boolean operator
	can be from the following - >, <, >=, <=
	if the expression (a op b) is true, it will keep looping
	else it will stop the loop
	
	inc:
	increment
	can be from the following - ++, --, +=, -=, *=, /=, and perhaps more
	++ is +1 and -- is -1
	+=, -=, *=, and /= are followed by a number c (int or real, depending on data type of var)
	and they mean var+c, var-c, var*c, and var/c respectively
	
	conditions:
	code to be performed during each loop cycle
	
	NOTES:
	Be careful to make it so a for loop can eventually reach its limit
	or else it will become an infinite loop and your program may crash
	
	*/
	
	// Place your program here.  'c' is the output console
    } // main method
} // ForLoopDemo class
