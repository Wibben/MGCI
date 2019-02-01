/* Problem Set 5
 * Bing Li
 * March 14, 2017
 * TER4M1
*/
#include <iostream>
#include <conio.h>
#include <cstdlib>
#include <time.h>

using namespace std;

// Fills an array of desired size with random numbers from 1 to 10
int * fillArr(int *arr, int s)
{
	// Variable declaration
	int* temp = new int[s];

	// Fill up the array with random integers in range [1,10]
	for(int i=0; i<s; i++)
	{
		temp[i] = rand()%10 + 1;
	}

	// Dispose of old array
	delete [] arr;

	// Return new array
	return temp;
}

// Allows the addition of an element to the array at the desired location
int * addItem(int *arr, int &s, int val, int pos)
{
	// Variable declaration
	int* temp = new int[s+1];

	// Copy old array to new array, skipping the desire location
	for(int i=0; i<s; i++) {
		temp[(i<pos? i:i+1)] = arr[i];
	}
	temp[pos] = val; // Insert new value
	s++; // Update size;

	// Dispose of old array
	delete [] arr;

	// Return new array
	return temp;
}

// Allows the deletion of an element from the array at the desired location
int * deleteItem(int *arr, int &s, int pos)
{
	// Variable declaration
	int* temp = new int[s-1];

	// Copy old array to new array, skipping the desire location
	for(int i=0; i<s-1; i++) {
		temp[i] = arr[(i<pos? i:i+1)];
	}
	s--; // Update size;

	// Dispose of old array
	delete [] arr;

	// Return new array
	return temp;
}

// Allows user to input values into an array
int * input(int *arr, int &s)
{
	// Variable declaration
	int val;

	// Make arr an empty array
	int * temp = new int[0];
	delete [] arr;
	arr = temp;
	s = 0;

	// Get user input until user enters -1
	cout << "Please input values into the array separated by spaces, enter -1 to stop." << endl;
	cin >> val;
	while(val!=-1) {
		// Update array
		arr = addItem(arr,s,val,s);
		cin >> val;
	}

	// Return new array
	return arr;
}

// Find the first occurance of a value in an array, -1 if not found
int search(int *arr, int s, int val)
{
	// Loop through the array and see if value is an element
	for(int i=0; i<s; i++) {
		if(arr[i]==val) return i; // If found, return position
	}

	// Nothing found
	return -1;
}

// Returns the average given an array of integers and its size
double average(int *arr, int s)
{
	// Variable declaration
	double sum=0;

	// Loop through the array and get the sum of all elements
	for(int i=0; i<s; i++) {
		sum += arr[i];
	}

	// Return the average (sum/size)
	return sum/s;
}

// Returns the maximum value given an array of integers and its size
int maximum(int *arr, int s)
{
	// Variable declaration
	int maxi=arr[0];

	// Loop through the array and update the maximum each time
	for(int i=1; i<s; i++) {
		maxi = max(maxi,arr[i]);
	}

	// Return the maximum
	return maxi;
}

// Returns the minimum value given an array of integers and its size
int minimum(int *arr, int s)
{
	// Variable declaration
	int mini=arr[0];

	// Loop through the array and update the maximum each time
	for(int i=1; i<s; i++) {
		mini = min(mini,arr[i]);
	}

	// Return the maximum
	return mini;
}

// Test the fillArr function by having the user enter a size
int * fillTest(int *arr, int &s)
{
	// Get user input
	cout << "What is the desired size of this new array? ";
	cin >> s;

    // Make sure users can't try to make negative sized arrays
    while(s<=0) {
        cout << "ERROR: Must enter a positive integer for size..." << endl;
        cin >> s;
    }

	// Run fill(int*,int)
	return fillArr(arr,s);
}

// Tests the search function
void searchTest(int *arr, int s)
{
	// Variable declaration
	int val;

	// Get user input
	cout << "Please enter the value that you want to search for: ";
	cin >> val;

	// Run search(int*,int,int) and display results
	int pos = search(arr,s,val);
	if(pos==-1) cout << "Value does not exist in array." << endl;
	else cout << "The first occurance is at position: " << pos << endl;
}

// Test the addItem function
int * addTest(int *arr, int &s)
{
	// Variable declaration
	int pos,val;

	// Get user input
	cout << "Value: ";
	cin >> val;
	cout << "Position to add (0 to " << s << ", everything else will be pushed back): ";
	cin >> pos;
	while (pos<0 || pos>s) { // Make sure the users input a valid position
		cout << "ERROR: Invalid position..." << endl;
		cout << "POsition to add (0 to " << s << ", everything else will be pushed back): ";
		cin >> pos;
	}

	// Run addItem(int*,int,int,int)
	return addItem(arr,s,val,pos);
}

// Test the deleteItem function
int * deleteTest(int *arr, int &s)
{
	// Variable declaration
	int pos;

	// Get user input
	cout << "Position to delete (0 to " << s-1 << ", everything else will be pushed forwards): ";
	cin >> pos;
	while (pos<0 || pos>s-1) { // Make sure the users input a valid position
		cout << "ERROR: Invalid position..." << endl;
		cout << "Position to delete (0 to " << s-1 << ", everything else will be pushed forwards): ";
		cin >> pos;
	}

	// Run addItem(int*,int,int)
	return deleteItem(arr,s,pos);
}

// Displays the current array along with statistics
void display(int *arr, int s)
{
	// Border framing
	cout << "------------------------------" << endl;
	cout << "Current Array: " << endl;
	for(int i=0; i<s; i++) { // Loop through to display each element
		cout << arr[i] << " ";
	} cout << endl << "------------------------------" << endl;

	// Displays statistics
	cout << "Size   : " << s << endl;
	if (s==0) {
		cout << "No additional statistics available" << endl;
	} else {
		cout << "Average: " << average(arr,s) << endl;
		cout << "Maximum: " << maximum(arr,s) << endl;
		cout << "Minimum: " << minimum(arr,s) << endl;
	}
}

// Menu to access programs
int main()
{
	int* arr = new int[0]; // Initial array
	int s = 0; // s of the array

	// Initialize random seed
	srand(time(NULL));

	char choice;
	do {
		system("cls"); //clear screen
		cout << "Problem Set #5\n";
		cout << "--------------\n";
		cout << "1. Fill\n";
		cout << "2. Input\n";
		cout << "3. Search\n";
		cout << "4. Add Item\n";
		cout << "5. Remove Item\n";
		cout << "0. Quit\n";

		display(arr,s); // Display the current array

		cout << endl << "Please enter your choice: ";
		cin >> choice;

		system("cls"); //clear screen

		if(choice == '1') arr = fillTest(arr,s);
		else if(choice == '2') arr = input(arr,s);
		else if(choice == '3') searchTest(arr,s);
		else if(choice == '4') arr = addTest(arr,s);
		else if(choice == '5') arr = deleteTest(arr,s);

        cout << "\nPress any key to continue...";
        cin.ignore();
        cin.ignore();
	} while (choice != '0'); //exit when 0

	system("cls");
	cout<<"End";

	return 0;
}
