/* For this in-class assignment, you are to complete the program by writing the methods getint,
   getDigit and noVowels so that the program can produce the output shown.

   I would suggest that you write near-empty methods with return statements first to get the
   program running, then work on the problems themselves.

   YOU ARE NOT ALLOWED TO CHANGE THE MAIN PROGRAM.
*/
import java.awt.*;
import hsa.Console;

public class LB_Inclass2
{
    static Console c;

    /* getInt is an overloaded method that uses a loop to do a range check and displays an error
       message as shown below if the user enters a value outside the range.  Since you did this
       as an assignment and have access to your files, this should be automatic marks.

       getDigit will return the specified digit from the given number.  If the digit doesn't exist
       (ex. 3rd digit of 57) or a negative value is entered, a -1 should be returned.

       noVowels will return a version of the accepted string that doesn't have any vowels
       (caps or small AEIOU)
       
       BONUS METHOD
       excluded will accept an additional string that contains the characters to be excluded
       from the returned version
    */
    
    public static int getInt(int upper)
    {
	int in = c.readInt(); // Read in an integer
	while(in<0 || in>upper) { // If there is a problem
	    c.print("Not in range - 0 to" + upper + ": ");
	    in = c.readInt();
	}
	
	return in; // Return proper number
    }
    
    public static int getInt(int lower, int upper)
    {
	int in = c.readInt(); // Read in an integer
	while(in<lower || in>upper) { // If there is a problem
	    c.print("Not in range - " + lower + " to " + upper + ": ");
	    in = c.readInt();
	}
	
	return in; // Return proper number
    }
    
    public static int getDigit(int num, int digit)
    {
	int digits[] = {-1,-1,-1}; //  Pre-define as all -1
	int ret[] = {-1,-1,-1}; //  Pre-define as all -1
	int i = 0;
	
	while(num>0) { //  Get the digits into the array
	    digits[i++] = num%10;
	    num /= 10;
	}
	
	for(int j=i-1,cnt=0; j>=0; j--,cnt++) // Putting digits into new array but arranged better 
	    ret[cnt] = digits[j]; // ret[0] = digit1, ret[1] = digit2, ret[2] = digit3
	
	return ret[digit-1]; // Return desired digit
    }
    
    public static String noVowels(String message) 
    {
	String ret = "";
	char vowels[] = {'a','e','i','o','u','A','E','I','O','U'}; // Pre-defined things to exclude
	
	for(int i=0; i<message.length(); i++) {
	    boolean vowel = false; // Assume character is not vowel
	    for(int j=0; j<10; j++)
		if(message.charAt(i)==vowels[j]) vowel = true; // If it is a vowel mark true
	    if(!vowel) ret += message.charAt(i); // If vowel false insert into returning string
	}
	
	return ret; // Return modified string
    }
    
    public static String exclude(String message, String ex) 
    {
	String ret = "";
	
	for(int i=0; i<message.length(); i++) {
	    boolean keep = true; // Assuming keeping character
	    for(int j=0; j<ex.length(); j++)
		if(message.charAt(i)==ex.charAt(j))keep = false; // If it is part of the ex string mark false
	    if(keep) ret += message.charAt(i); // If keep is true insert into returning string
	}
	
	return ret; // Return modified string
    }

    public static void main (String [] args)
    {
	c = new Console ();

	c.print ("Enter an integer from 0 to 999: ");
	int number = getInt (999); // forces user to enter value from 0 to 999

	c.print ("\nWhich digit do you want?(1-3): ");
	int digit = getInt (1, 3); // forces user to enter value from 1 to 3

	c.println ("\nThe digit you requested is " + getDigit (number, digit));

	c.print ("\n\nEnter a secret message: ");
	String message = c.readLine ();
	c.print ("\nEnter a string of the letters to be excluded: ");
	String excluded = c.readLine();
	
	
	c.println ("\nThe no vowel version is  " + noVowels (message));
	
	c.println( "\nThe excluded letters version is " + exclude(message,excluded));
    } // main method
} // Inclass2 class

/* Sample run

   Enter an integer from 0 to 999: 9876
   Not in range - 0 to 999: 987

   Which digit do you want?(1-3): 1

   The digit you requested is 9


   Enter a secret message: This is a test

   The no vowel version is  Ths s  tst
*/
