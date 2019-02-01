/* Problem Set 4
 * Bing Li
 * March 14, 2017
 * TER4M1
*/
#include <iostream>
#include <cstdio>
#include <conio.h>
#include <cstdlib>
#include <climits>
#include <cmath>

using namespace std;

// Makes user input a value that is within a range
int getInt(int low, int high=INT_MAX)
{
	// Variable declaration
	int val;

	// Get user input
	cout << "Enter a number between " << low << " and " << high << " (inclusive): " << endl;
	cin >> val;

	// Make sure input within range
	while (val<low || val>high) {
		cout << "Error: value out of range." << endl;
		cout << "Enter a number between " << low << " and " << high << " (inclusive): " << endl;
		cin >> val;
	}

	return val;
}

// Returns the sum of all integers from 1 up to val
int sumLimit(int val)
{
	return val*(val+1)/2; // Sum from 1 to n is n*(n+1)/2
}

// Checks if val is a prime
bool prime(int val)
{
	// Loop from 3 to val/2, increasing by 2s
	// if a number is divisible, not a prime
	// Check divisibility by 2 first, also 1 is not prime
	if(val%2==0 || val==1) return false;
	for(int i=3; i<=val/2; i+=2) {
		if(val%i==0) return false;
	}

	// If it passed the loop it will be prime
	return true;
}

// Checks whether a quadratic equation in the form of
// ax^2 + bx + c = 0 has a solution and solves it if there is
bool quadratic(double &x1, double &x2, double a, double b, double c)
{
	// Check discriminant to see if there is a solution
	if(b*b-4*a*c < 0) return false;

	// Use quadratic formula to assign values and return true
	x1 = (-b+sqrt(b*b-4*a*c))/(2*a);
	x2 = (-b-sqrt(b*b-4*a*c))/(2*a);
	return true;
}

// Testing the sumLimit function
void sumTest()
{
	// Variable declaration
	int maxi;

	// Get user input, make sure positive
	maxi=getInt(1);

	// Output the results of sumLimit
	cout << "The sum of integers from 1 to " << maxi << " is " << sumLimit(maxi) << "." << endl;
}

// Testing the prime function
void primeTest()
{
	// Variable declaration
	int val;

	// Get user input
	val = getInt(1,10000);

	// Output whether the number is a prime
	cout << val << (prime(val) ? " is ":" is not ") << " a prime number." << endl;
}

// Testing the quadratic function
void quadTest()
{
	// Variable declaration
	double x1,x2,a,b,c;

	// Get user input
	cout << "This program solves a quadratic in the form of Ax^2+Bx+C=0" << endl;
	cout << "Please enter the value for A: ";
	cin >> a;
	cout << "Please enter the value for B: ";
	cin >> b;
	cout << "Please enter the value for C: ";
	cin >> c;

	// Checks if there is a solution and outputs accordingly
	cout << endl;
	if(quadratic(x1,x2,a,b,c)) {
		if(x1==x2) cout << "There is one solution.\nX = " << x1 << endl; // Double roots
		else cout << "There are 2 solutions.\nX1 = " << x1 << endl << "X2 = " << x2 << endl;
	} else cout << "There are no solutions." << endl;
}

// Menu to access programs
int main()
{
	char choice;
	do {
		system("cls"); //clear screen
		cout<<"Problem Set #4\n";
		cout<<"--------------\n";
		cout<<"1. sumLimit\n";
		cout<<"2. prime\n";
		cout<<"3. quadratic\n";
		cout<<"0. Quit\n";

		cin>> choice;

		system("cls"); //clear screen

		if(choice == '1') sumTest();
        else if(choice == '2') primeTest();
        else if(choice == '3') quadTest();

        cout << "\nPress any key to continue...";
        cin.ignore();
        cin.ignore();
	} while (choice != '0'); //exit when 0

	system("cls");
	cout<<"End";

	return 0;
}
