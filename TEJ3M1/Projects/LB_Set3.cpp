#include <iostream>
#include <time.h>
#include <windows.h>
#include <conio.h>

using namespace std;

void multiply()
{
	int multiplier;

	// Prompt for multiplier
	cout << "Multiplier: ";
	cin >> multiplier;

	// Display multiplication table for multiplier from 1 to 12
	for(int i=1; i<=12; i++) cout << multiplier << " x " << i << " = " << multiplier*i << endl;
}

void factorial()
{
    long long int fact,ans = 1;

	// Prompt for integer
	cout << "Enter a non-negative integer between 1 and 20: ";
	cin >> fact;

	// Make sure fact is in range [0,20]
	while(fact>20 || fact<0) {
		cout << (fact>20 ? "Number too large, please try again: ":"Please enter a NON-NEGATIVE number: ");
		cin >> fact;
	}

	// 0 is a special case
	if(fact==0) {
		cout << 1;
		return;
	}

	// Display calculation and results
	for(int i=fact; i>1; i--) {
		cout << i << " x ";
		ans*=i;
	}
	cout << "1 = " << ans;
}

void banking()
{
	// Set to output only 2 decimal places
	cout.setf(ios::fixed);
	cout.precision(2);

	float investment,annualInterest,term;
	float balance = 0;

	// Prompt for user info
	cout << "Monthly Investment\t:  ";
	cin >> investment;
	cout << "Annual Interest Rate (%)"; cout << ":  ";
	cin >> annualInterest; annualInterest/=1200;
	cout << "Term (Months)\t\t"; cout << ":  ";
	cin >> term;

	// Set up columns
	cout << "\nStarting\tInterest\tMonthly\t\tEnding\n";
	cout << "Balance\t\t  Earned\tDeposit\t\tBalance\n";
	cout << "--------\t--------\t-------\t\t-------\n";

	// Display monthly information
	for(int i=0; i<term; i++) {
		cout.width(8); cout << balance << "\t";
		cout.width(8); cout << balance*(annualInterest) << "\t";
		cout.width(7); cout << investment << "\t\t";
		cout.width(7); cout << investment + balance*(1+annualInterest) << endl;
		balance = investment + balance*(1+annualInterest);
	}
}

void hiLo()
{
	int generated,guess,cnt=0;

	// Generate random number from 1 to 100
	srand(time(NULL));
	generated = rand()%100+1;

	// Prompt for user guess
	cout << "I am thinking of a number between 1 and 100" << endl;

	// Keep guessing until user guesses right
	do {
		cin >> guess; cnt++;
		if(guess==generated) cout << "Correct! It took you " << cnt << " guesses.\n";
		else cout << (guess<generated ? "Higher":"Lower") << endl;
	} while(guess!=generated);
}

int main()
{
	char choice;

	// Regular menu stuff
    do {
		cout << "Please choose from the following programs: \n";
		cout << "1 - Multiplication Table\n";
		cout << "2 - Factorial\n";
		cout << "3 - Banking\n";
		cout << "4 - Hi-Lo\n";
		cout << "x - Exit\n\n";
		cout << "Please press the corresponding key.";

		choice = getch();

		system("cls");

        if(choice=='1') multiply();
        else if(choice=='2') factorial();
        else if(choice=='3') banking();
		else if(choice=='4') hiLo();
		cout << "Press enter to continue...";
		getch();
		system("cls");
	} while(choice!='x');

    cout << "Program Terminated\n";

	return 0;
}
