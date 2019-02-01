/*
Bing Li
TEJ3M1
Feb 3, 2015
Set 1
*/

#include <iostream>
#include <windows.h>
#include <conio.h>

using namespace std;

//----------------------------------While----------------------------------

void While()
{
	int x = 1;
	
	// Display x as long as x < 10
	while(x < 10)
		cout << x++ << endl; // increment x after displaying
}

//----------------------------------Variables----------------------------------

void Variables()
{
	int num;
	
	// Prompt for number
	cout << "Please enter a number: ";
	cin >> num;

	// Display number
	cout << "You entered: " << num << endl;
}

//----------------------------------Strings----------------------------------

void Strings()
{
	string first,last;
	
	// Prompt for first and last name
	cout << "Please enter your first name: ";
	getline(cin,first);
	cout << "Please enter your last name: ";
	getline(cin,last);
	
	// Display names with end message
	cout << "Hi " << first << " " << last << ".\n";
	cout << "Welcome to one of my many programs.\n\nSadly, this program has terminated.\nGoodbye!\n";
}

//----------------------------------gotoxy----------------------------------

void gotoxy(int x, int y)
{
	COORD coord;
	coord.X = x;
	coord.Y = y;
	
	// Set cursor position
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),coord);
}

//----------------------------------GotoxyProgram----------------------------------

void GotoxyProgram()
{
	gotoxy(15,1); // Call gotoxy function
	cout << "I am over here!!!!\n"; // Display message
}

//----------------------------------For----------------------------------

void For()
{
	// Display i as long as i < 10, double i after display
	for(int i=1; i<10; i*=2)
		cout << i << endl;
}

//----------------------------------DoWhile----------------------------------

void DoWhile()
{
	int x = 4;
	
	// Decrement x and display, if x > 2 repeat
	do {
		x--;
		cout << x << endl;
	} while(x>2);
}

//----------------------------------Decision----------------------------------

void Decision()
{
	int mark,outof;
	float percent;
	
	// Prompt for test results
	cout << "What mark did you get? ";
	cin >> mark;
	cout << "What was it out of? ";
	cin >> outof;

	percent = mark*100.0/outof;
	
	// Display percentage and pass/fail message
	cout << "\nYout mark is " << percent << "% " << (percent>=50 ? "(Pass)":"(Fail)") << endl;
}

//----------------------------------Control----------------------------------

void Control()
{
	int mark,outof;
	float percent;
	
	// Set all number output to 1 decimal
	cout.setf(ios::fixed);
	cout.precision(1);
	
	// Prompt for test results
	cout << "What mark did you get? ";
	cin >> mark;
	cout.width(23); cout << "What was it out of? ";
	cin >> outof;

	percent = mark*100.0/outof;
	
	// Display percentage and goodbye message
	cout << "\nYout mark is " << percent << "%";
	cout.width(30); cout << "Goodbye!\n\n";
}

//----------------------------------Main----------------------------------

int main()
{
	char choice;

	// Regular menu stuff
    do {
		cout << "Please choose from the following programs: \n";
		cout << "1 - While\n";
		cout << "2 - Variables\n";
		cout << "3 - Strings\n";
		cout << "4 - Gotoxy\n";
		cout << "5 - For\n";
		cout << "6 - Do while\n";
		cout << "7 - Decision\n";
		cout << "8 - Control\n";
		cout << "x - Exit\n\n";
		cout << "Please press the corresponding key.";

		choice = getch();

		system("cls");

        if(choice=='1') While();
        else if(choice=='2') Variables();
        else if(choice=='3') Strings();
		else if(choice=='4') GotoxyProgram();
		else if(choice=='5') For();
		else if(choice=='6') DoWhile();
		else if(choice=='7') Decision();
		else if(choice=='8') Control();

		cout << "Press enter to continue...";
		getch();
		system("cls");
	} while(choice!='x');

    cout << "Program Terminated\n";

	return 0;
}
