/*
Bing Li
ICS2O3
Nov 27, 2014
*/

/* For this in-class assignment, you are to complete the program by writing these methods so that
   the program can produce the output shown.

   I would suggest that you write near-empty methods with return statements first to get the
   program running, then work on the problems themselves.
     
   1. numCaps accepts a string, then returns the number of characters that are capital letters.

   2. capitalize accepts a string of words, then returns the string with the first letter in each 
      word capitalized.  A word starts after a space (or spaces).
      HINT: toUpperCase will only change a string, so use a string of length 1 at start of words

   3. reverse accepts a string, then returns the same string in reverse order
      HINT: build the new string like in alphabetize (or yesterday's noVowels)
   
   YOU ARE NOT ALLOWED TO CHANGE THE MAIN PROGRAM (except for commenting out code).
*/
import java.awt.*;
import hsa.Console;

public class LB_Inclass2b
{
    static Console c;
    
    public static int numCaps (String message)
    {
	int caps = 0;
	
	for(int i=0; i<message.length(); i++)
	    if(message.charAt(i)>64 && message.charAt(i)<91) caps++; // If it's in caps add to counter
	
	return caps;
    }
    
    public static String capitalize (String message)
    {
	String ret = "";
    
	for(int i=0; i<message.length(); i++) {
	    if(message.charAt(i)>96 && message.charAt(i)<123) ret += (char)(message.charAt(i)-32); // Change to caps if it's lower case
	    else ret += message.charAt(i);
	}
	
	return ret;
    }

    public static String reverse (String message)
    {
	String ret = "";
    
	for(int i=message.length()-1; i>=0; i--) // Start from the end of message and work backwards to assign in reverse
	    ret += message.charAt(i);
	
	return ret;
    }
    
    public static void main (String [] args)
    {
	c = new Console ();

	c.print ("\n\nEnter a multi-word message: ");
	String message = c.readLine ();

	c.println ("\nThe message has " + numCaps (message) + " capital letter(s).");

	c.println ("\nThe capitalized version is " + capitalize (message));

	c.println ("\nThe reversed version is " + reverse (message));
    } // main method
} // Inclass2 class

/* Sample run

   Enter a multi-word message: This is a test

   The message has 1 capital letter(s).
   
   The capitalized version is  This Is A Test
   
   The reversed version is tset a si sihT
*/
