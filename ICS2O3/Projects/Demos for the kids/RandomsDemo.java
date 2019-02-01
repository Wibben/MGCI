// The "SecureRandomDemo" class.
import java.awt.*;
import hsa.Console;
import java.lang.Math;
import java.util.*;
import java.security.*;

// There are random number generators in java.lang.Math, java.util.* and java.security.*
// I will explain the difference later


public class RandomsDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	// Random number generators are pretty much just syntax
	
	// RNG in the math library;
	c.print("Math Library:\n");
	for(int i=0; i<50; i++) {
	    double random = Math.random();
	    c.print(random+", ");
	} c.print("\n\n");
	
	
	// RNG in the utility library
	c.print("Utility library:\n");
	for(int i=0; i<50; i++) {
	    Random rand = new Random();
	    int random = rand.nextInt(100);
	    c.print(random+", ");
	} c.print("\n\n");
	
	// RNG in the security library
	c.print("Security library:\n");
	for(int i=0; i<50; i++) {
	    SecureRandom rand = new SecureRandom();
	    int random = rand.nextInt(100);
	    c.print(random+", ");
	}
	
	/*
	
	To declare a random number generator, you must use
	    if using java.lang.Math you do not need to declare a random number generator
	    Random RNG = new Random(); if using java.util
	    SecureRandom RNG = new SecureRandom(); if using java.security
	    
	To generate a random number with the generator, use
	    int var = Math.random(); in java.lang.Math;
	    int var = RNG.nextInt(MAX-MIN)+MIN; in java.util and java.security
	    double var = RNG.nextDouble(MAX-MIN)+MIN; if generating a double
	    int var = RNG.nextInt();
	
	RNG:
	variable name of the random number generator
	
	var:
	variable name of the generated random number
	
	MAX:
	the highest number you want to be generated
	
	MIN:
	the lowest number you want to be generated
	
	Why use RNG.nextInt(MAX-MIN)+MIN;?
	the random number generator generates a random number from 0 up to and EXCLUDING the value you put in the brackets
	so if you want to generate a series of numbers not starting from 0, you must generate a number to add onto the minimum value
	
	Also, if you wish to generate a number between 0 and x, both inclusive, use 
	    RNG.nextInt(x+1); 
	
	Differences:
	    the math library generates a double between 0.0 and 1.0, this is somewhat reliable to be random
	    
	    the utility library generates a number between 0(inclusive) and MAX(exclusive), if used once it is
	    effective enough to serve the purpose, but once called upon a multiple number of times a pattern emerges
	
	    the security library is used for cryptographic purposes, it does the same thing as the utility library,
	    but the numbers generated are "more" random and can be continually relied on if generating a large number
	    of random numbers
	
	*/
	
	// Place your program here.  'c' is the output console
    } // main method
} // SecureRandomDemo class
