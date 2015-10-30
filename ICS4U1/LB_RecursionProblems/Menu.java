/* Bing Li
 * ICS4U1
 * September 28, 2015
 * Recursion Problem Set
 */

import java.util.Scanner;

public class Menu
{
    public static Scanner in;

    public static int recurseGCF(int a, int b)
    {
	// Euclid's algorithm
	if(a==b) return a;
	if(a<b) return recurseGCF(b,a);
	return recurseGCF(b,a-b);
    }

    public static int GCF(int a, int b)
    {
	// Variable declaration
	int temp;
	
	// Make sure a>b
	if(b<a) {
	    temp = a;
	    a = b;
	    b = temp;
	}
    
	// Euclid's Algorithm
	while(b!=0) {
	    temp = b;
	    b = a%b;
	    a = temp;
	}
	
	// Return GCF
	return a;
    }

    public static int recurseFib(int term)
    {
	// If it's the first 2 terms return 1, else it will be the sum of the previous 2 terms
	if(term<=2) return 1;
	return recurseFib(term-1)+recurseFib(term-2);
    }

    public static int Fib(int term)
    {
	// Variable declaration
	int[] fibonacci = new int[term];

	// If it's just the first 2 terms return 1
	if(term <= 2) return 1;
	
	// Above statement needed to avoid out-of-bound in this statement
	// Initialize the first 2 fibonacci numbers
	fibonacci[0] = fibonacci[1] = 1;
	
	// Compute the next fibonacci number with the previous 2 numbers
	for(int i=2; i<term; i++)
	    fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
	
	// Return the last fibonacci term
	return fibonacci[term-1];
    }

    public static void recurseGCFDrive()
    {
	// Variable declaration
	int a,b;
	
	// Get user input
	System.out.printf("Please enter the two numbers you would like to find the GCF of:\n");
	System.out.printf("First Number: ");
	a = in.nextInt();
	in.nextLine();
	System.out.printf("Second Number: ");
	b = in.nextInt();
	in.nextLine();
	
	// Output result
	System.out.printf("The GCF of %d and %d is %d", a, b, recurseGCF(a,b));
    }

    public static void GCFDrive()
    {
	// Variable declaration
	int a,b;
	
	// Get user input
	System.out.printf("Please enter the two numbers you would like to find the GCF of:\n");
	System.out.printf("First Number: ");
	a = in.nextInt();
	in.nextLine();
	System.out.printf("Second Number: ");
	b = in.nextInt();
	in.nextLine();
	
	// Output result
	System.out.printf("The GCF of %d and %d is %d", a, b, GCF(a,b));
    }

    public static void recurseFibDrive()
    {
	// Variable declaration
	int term;
	
	// Get user input
	System.out.printf("Please enter the term of the fibonacci number you want to find: ");
	term = in.nextInt();
	in.nextLine();
	
	// Output result
	System.out.printf("Term #%d of the fibonacci sequence is %d", term, recurseFib(term));
    }

    public static void FibDrive()
    {
	// Variable declaration
	int term;
	
	// Get user input
	System.out.printf("Please enter the term of the fibonacci number you want to find: ");
	term = in.nextInt();
	in.nextLine();
	
	// Output result
	System.out.printf("Term #%d of the fibonacci sequence is %d", term, Fib(term));
    }

    public static int main()
    {
	in = new Scanner(System.in);

	// Variable declaration
	char choice;

	do {
	    // Displaying menu
	    System.out.printf("\u000C");
	    System.out.printf("Please select a Program:\n\n");
	    System.out.printf("1 - Recursive GCF\n");
	    System.out.printf("2 - Loop GCF\n");
	    System.out.printf("3 - Recursive Fib\n");
	    System.out.printf("4 - Loop Fib\n");
	    System.out.printf("0 - Exit\n");

	    // Getting choice
	    choice = in.nextLine().charAt(0);

	    // Running program based on choice
	    System.out.printf("\u000C");
	    if(choice=='1') recurseGCFDrive();
	    else if(choice=='2') GCFDrive();
	    else if(choice=='3') recurseFibDrive();
	    else if(choice=='4') FibDrive();

	    // Wait for user
	    System.out.printf("\nEnter enter to continue...");
	    in.nextLine();
	} while(choice!='0');

	return 0;
    }
}
