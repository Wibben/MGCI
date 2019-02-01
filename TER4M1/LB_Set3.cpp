/* Problem Set 3
 * Bing Li
 * March 1, 2017
 * TER4M1
*/
#include <iostream>
#include <string>
#include <cstdio>
#include <conio.h>
#include <cstdlib>

using namespace std;

// Gets the sum of a sequence of integers
void intAdd()
{
	// Variable declaration
	int val,sum=0;
	
	// Get user input
	cout << "Enter first integer (enter 0 to quit): ";
	cin >> val;
	if(val==0) {
		cout << "No integers were entered." << endl << "bye" << endl;
	} else {
		// Keep getting values
		while(val!=0) {
			sum+=val; // Add to sum
			cout << "Enter an integer (enter 0 to quit): ";
			cin >> val;
		}
	
		// Display results
		cout << "Sum of the integers: " << sum << endl << "bye" << endl;
	}
}

// Calculates fuel efficiency given odometer readings and fuel used
void milesPG()
{
	// Variable declaration
	int initial,fin,gallon;
	
	// Get user input
	cout << "Miles Per Gallon Program" << endl << "Initial miles" << endl;
	cin >> initial;
	while(initial>=0) {
		cout << "Final miles:" << endl;
		cin >> fin;
		cout << "Gallons" << endl;
		cin >> gallon;
		
		// Display result
		printf("Miles per Gallon: %.1f\n",1.0*(fin-initial)/gallon);
		
		// Start again
		cout << endl << "Initial miles:" << endl;
		cin >> initial;
	}
	
	// Exit statement
	cout << "bye" << endl;
}

// Calculates two sums: sum of values both inside and outside of a given range
void inrange()
{
	// Variable declaration
	int mini,maxi,val,inside=0,outside=0;
	
	// Get user input
	cout << "In-range Adder" << endl << "Low end of range:" << endl;
	cin >> mini;
	cout << "High end of range:" << endl;
	cin >> maxi;
	
	// Get data
	do {
		cout << "Enter data:" << endl;
		cin >> val;
		if(val>=mini && val<=maxi) inside+=val;
		else outside+=val;
	} while(val!=0);
	
	// Output sums
	cout << "Sum of in range values: " << inside << endl;
	cout << "Sum of out of range values: " << outside << endl;
}

// Calculates shipping costs given weight
void shipping()
{
	// Variable declaration
	int weight;
	
	// Get user input
	cout << "Weight of Order:" << endl;
	cin >> weight;
	while(weight>0) {
		// Output cost and get next input
		printf("Shipping cost: $%.2f\n\nWeight of Order\n",3+max(weight-10,0)*0.25);
		cin >> weight;
	}
	
	// Exit statement
	cout << endl << "bye" << endl;
}

// Outputs a hailstone sequence given an initial value
void hailstone()
{
	// Variable declaration
	int num,maxi=0,cnt=0;
	
	// Get user input
	cout << "Initial Value: ";
	cin >> num;
	cout << endl << num << endl;
	
	// Loop until number is 0
	// If num is even, num = num/2
	// If num is odd, num = 3*num+1
	do {
		// Calculations and display number
		if(num%2==0) num/=2;
		else num = num*3+1;
		
		cout << num << endl;
		
		maxi = max(maxi,num); // Record maximum
		cnt++; // Increment sequence length
	} while(num>1);
	
	// Output length and max
	cout << endl << "Length of Sequence: " << cnt << endl;
	cout << "Highest Number: " << maxi << endl;
}

// Menu to access programs
int main()
{
	char choice;
	do {
		system("cls"); //clear screen
		cout<<"Problem Set #3\n";
		cout<<"--------------\n";
		cout<<"1. Adding Integers\n";
		cout<<"2. Miles per Gallon\n";
		cout<<"3. In-Range Adder\n";
		cout<<"4. Shipping Cost Calculator\n";
		cout<<"5. Hailstone Numbers\n";
		cout<<"0. Quit\n";

		cin>> choice;

		system("cls"); //clear screen

		if(choice == '1') intAdd();
        else if(choice == '2') milesPG();
        else if(choice == '3') inrange();
        else if(choice == '4') shipping();
		else if(choice == '5') hailstone();

        cout << "\nPress any key to continue...";
        cin.ignore();
        cin.ignore();
	} while (choice != '0'); //exit when 0

	system("cls");
	cout<<"End";

	return 0;
}
