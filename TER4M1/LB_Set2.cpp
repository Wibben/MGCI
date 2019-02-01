/* Problem Set 2
 * Bing Li
 * February 27, 2017
 * TER4M1
*/
#include <iostream>
#include <string>
#include <cstdio>
#include <conio.h>
#include <cstdlib>

using namespace std;

// Outputs a list of numbers and its square
void loopSquare()
{
    // Variable declaration
    int val;

    // Get user input
    cout << "Enter an integer: ";
    cin >> val;

    // Loop through from 1 to val and output squares
    cout << "Number Square" << endl;
    for(int i=1; i<=val; i++) {
        printf(" %-5d %5d\n",i,i*i);
    }
}

// Gets the product of a series of numbers, omitting any negatives and 0
void product()
{
    // Variable declaration
    int nums,val,prod=1;

    // Get user input
    cout << "How many numbers to process? ";
    cin >> nums;

    // Get each num and multiply if valid
    cout << "Enter " << nums << " positive integers\n";
    for(int i=1; i<=nums; i++) {
        cout << "#" << i << ": ";
        cin >> val;
        if(val>0) prod *= val; // Multiply
        else cout << val << " not counted" << endl; // Invalid input
    }

    // Output product
    cout << endl << "The product of the positives is " << prod << "." << endl;
}

// Calculates the factorial of a given number
void factorial()
{
    // Variable declaration
    int fact;
    unsigned long long ans=1;

    // Get user input
    cout << "Enter a non-negative number (0-20): ";
    cin >> fact;
    while(fact<0 || fact>20) { // Keep making user input until number within range
        cout << "Invalid number." << endl << "Enter a non-negative number (0-69): ";
        cin >> fact;
    }

    // Calculate factorial and output
    cout << endl;
    for(int i=fact; i>1; i--) {
        cout << i << " x ";
        ans *= i;
    } cout << "1 = " << ans << endl;
}

// Calculates the financial gains on a monthly basis given monthly deposit and interest rate
void invest()
{
    // Variable declaration
    double deposit,rate,term,balance=0;

    // Get user input
    printf("%-24s: ","Monthly Deposit");
    cin >> deposit;
    printf("%-24s: ","Annual Interest Rate (%)");
    cin >> rate;
    printf("%-24s: ","Term (Months)");
    cin >> term;

    // Display starting balance, interest earned, monthly deposit, and ending balance for each month
    printf("\n%-8s    %8s    %7s    %7s\n","Starting","Interest","Monthly","Ending");
    printf("%-8s    %8s    %7s    %7s\n","Balance","Earned","Deposit","Balance");
    printf("%-8s    %8s    %7s    %7s\n","--------","--------","-------","-------");
    for(int i=0; i<term; i++) {
        printf("%7.2f     %8.2f    %7.2f    %7.2f\n",balance,balance*(rate/1200),deposit,balance+balance*(rate/1200)+deposit);
        balance = balance+balance*(rate/1200)+deposit;
    }
}

// Menu to access programs
int main()
{
	char choice;
	do {
		system("cls"); //clear screen
		cout<<"Problem Set #2\n";
		cout<<"--------------\n";
		cout<<"1. LoopSquare\n";
		cout<<"2. Product\n";
		cout<<"3. Factorial\n";
		cout<<"4. Invest\n";
		cout<<"0. Quit\n";

		cin>> choice;

		system("cls"); //clear screen

		if(choice == '1') loopSquare();
        else if(choice == '2') product();
        else if(choice == '3') factorial();
        else if(choice == '4') invest();

        cout << "\nPress any key to continue...";
        cin.ignore();
        cin.ignore();
	} while (choice != '0'); //exit when 0

	system("cls");
	cout<<"End";

	return 0;
}
