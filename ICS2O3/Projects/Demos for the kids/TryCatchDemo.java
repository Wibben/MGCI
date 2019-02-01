// The "TryCatchDemo" class.
import java.awt.*;
import hsa.Console;

public class TryCatchDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	String str;
	int x;
	// You know, the regular stuff
	c.print("Please enter a string: ");
	str = c.readLine();
	c.print("Please enter a number to indicate which letter of the string you want to see: ");
	x = c.readInt();
	
	// Ooo what is this?
	try 
	{
	    c.print("The letter at position " + x + " is \"" + str.substring(x-1,x) + "\""); // Display character at position x
	} 
	catch(StringIndexOutOfBoundsException ex) 
	{
	    c.print("ERROR: Nothing exists position " + x + " of your string"); // Output if user's input is longer than string's length
	}
	
	/* 
	
	What was this about?
	
	If we try to  get the value of an element outside of the String's length,
	it would usually output a runtime error and end the program there and then.
	But once we have exceptions in place, they offer a backup action for the 
	program when it would otherwise just crash.  
	
	What is a try-catch?
	A try-catch is the way to give your program a backup action in case a specific 
	runtime error occurrs. Although there are too many different run time errors, 
	try-catches are a way to help deal with what you believe would be the most common errors.
	
	How do I use a try-catch?
	to use a try-catch, the syntax is simple:
	
	try
	{
	    //Protected code
	}
	catch(ExceptionName ex)
	{
	    //Catch block
	}
	
	ExceptionName:
	Name of the exception you're expecting, more on this is at the bottom of the demo
	
	ex:
	Variable name of your exception, just include it to make the code compile
	
	NOTE:
	- Catch blocks cannot exist without a try block
	- There can be multiple catch blocks for one try block 
	
	*/
	
	c.print("\n\nProgram Terminated");
	
	// Place your program here.  'c' is the output console
    } // main method
} // TryCatchDemo class

/*
List of built in exceptions and what they mean, some exceptions may require you to import some stuff

ExceptionName                           Use when...

ArithmeticException                     Arithmetic error, such as divide-by-zero.
ArrayIndexOutOfBoundsException          Array index is out-of-bounds.
ArrayStoreException                     Assignment to an array element of an incompatible type.
ClassCastException                      Invalid cast.
IllegalArgumentException                Illegal argument used to invoke a method.
IllegalMonitorStateException            Illegal monitor operation, such as waiting on an unlocked thread.
IllegalStateException                   Environment or application is in incorrect state.
IllegalThreadStateException             Requested operation not compatible with current thread state.
IndexOutOfBoundsException               Some type of index is out-of-bounds.
NegativeArraySizeException              Array created with a negative size.
NullPointerException                    Invalid use of a null reference.
NumberFormatException                   Invalid conversion of a string to a numeric format.
SecurityException                       Attempt to violate security.
StringIndexOutOfBoundsException         Attempt to index outside the bounds of a string.
UnsupportedOperationException           An unsupported operation was encountered.

Here are some more exceptions that can be used when you import java.lang.Exception

ClassNotFoundException                  Class not found.
CloneNotSupportedException              Attempt to clone an object that does not implement the Cloneable interface.
IllegalAccessException                  Access to a class is denied.
InstantiationException                  Attempt to create an object of an abstract class or interface.
InterruptedException                    One thread has been interrupted by another thread.
NoSuchFieldException                    A requested field does not exist.
NoSuchMethodException                   A requested method does not exist.

Don't worry if you do not understand what each and every one of these exceptions mean, 
you will gain knowledge through experience
*/
